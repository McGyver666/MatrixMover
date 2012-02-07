/*
 * Copyright (C) 2011 Michael Vogt <michu@neophob.com>
 * Copyright (C) 2012 Gyver
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gyver.matrixmover.generator;

import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 * The Class Textwriter.
 * 
 * This class needs some work!
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class Textwriter extends Generator {

    private static final String INITIAL_TEXT = "MatrixMover";
    
    /** The Constant TEXT_BUFFER_X_SIZE. */
    private static final int TEXT_BUFFER_X_SIZE = 128;
    /** The Constant CHANGE_SCROLLING_DIRECTION_TIMEOUT. */
    private static final int CHANGE_SCROLLING_DIRECTION_TIMEOUT = 12;
    /** The Constant SCROLL_AMMOUNT. */
    private static final int SCROLL_AMMOUNT = 4;
    /** The log. */
    private static final Logger LOG = Logger.getLogger(Textwriter.class.getName());
    /** The ypos. */
    private int xpos, ypos;
    /** The font. */
    private Font font;
    /** The color. */
    private Color color;
    /** The xofs. */
    private int xofs;
    /** The max x pos. */
    private int maxXPos;
    /** The scroll right. */
    private boolean scrollRight = true;
    /** The wait. */
    private int wait;
    /** The text buffer. */
    private int[] textBuffer;
    /** The tmp. */
    private int[] tmp;
    /** The text. */
    private String text;

    /**
     * Instantiates a new textwriter.
     *
     * @param md the MatrixData of this
     */
    public Textwriter(MatrixData md) {
        super(GeneratorName.TEXTWRITER, md);
        color = new Color(255, 255, 255);
        xpos = 0;
        ypos = md.getHeight();
        try {
            tmp = new int[internalBuffer.length];
            font = new Font("Dialog", 1, 12);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Failed to load font", e);
        }
        
        createTextImage(INITIAL_TEXT);
    }

    /**
     * create image.
     *
     * @param text the text
     */
    public void createTextImage(String text) {
        //only load if needed
        if (StringUtils.equals(text, this.text)) {
            return;
        }

        this.text = text;

        BufferedImage img = new BufferedImage(TEXT_BUFFER_X_SIZE, internalBufferHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = img.createGraphics();
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout layout = new TextLayout(text, font, frc);
        Rectangle2D rect = layout.getBounds();

        int h = (int) (0.5f + rect.getHeight());
        maxXPos = (int) (0.5f + rect.getWidth()) + 5;
        ypos = internalBufferHeight - (internalBufferHeight - h) / 2;

        img = new BufferedImage(maxXPos, internalBufferHeight, BufferedImage.TYPE_INT_RGB);
        g2 = img.createGraphics();

        g2.setColor(color);
        g2.setFont(font);
        g2.setClip(0, 0, maxXPos, internalBufferHeight);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawString(text, xpos, ypos);
        DataBufferInt dbi = (DataBufferInt) img.getRaster().getDataBuffer();
        textBuffer = dbi.getData();
        g2.dispose();

        wait = 0;
        xofs = 0;
        scrollRight = false;
    }


    @Override
    public void update() {

        if (wait > 0) {
            wait--;
        } else {
            if (maxXPos < getInternalBufferXSize()) {
//no need to scroll
                xofs = (getInternalBufferXSize() - maxXPos) / 2;
                wait = 99999;
            } else {
//todo, if image < buffer
                if (scrollRight) {
                    xofs += SCROLL_AMMOUNT;
                    if (xofs > maxXPos - internalBufferWidth) {
                        scrollRight = false;
                        xofs = maxXPos - internalBufferWidth;
                        wait = CHANGE_SCROLLING_DIRECTION_TIMEOUT;
                    }
                } else {
                    xofs -= SCROLL_AMMOUNT;
                    if (xofs < 1) {
                        scrollRight = true;
                        xofs = 0;
                        wait = CHANGE_SCROLLING_DIRECTION_TIMEOUT;
                    }
                }
            }
        }

        int srcOfs = xofs;
        int dstOfs = 0;

        try {
            if (maxXPos < getInternalBufferXSize()) {
//text image smaller than internal buffer
                srcOfs = 0;
                dstOfs = xofs;
//we need to clear the buffer first!
                Arrays.fill(tmp, 0);

                for (int y = 0; y < internalBufferHeight; y++) {
                    System.arraycopy(textBuffer, srcOfs, tmp, dstOfs, maxXPos);
                    dstOfs += internalBufferWidth;
                    srcOfs += maxXPos;
                }
            } else {
                for (int y = 0; y < internalBufferHeight; y++) {
                    System.arraycopy(textBuffer, srcOfs, tmp, dstOfs, internalBufferWidth);
                    dstOfs += internalBufferWidth;
                    srcOfs += maxXPos;
                }
            }

            this.internalBuffer = tmp;
        } catch (Exception e) {
//if the image is resized, this could lead to an arrayoutofboundexception!
        }

    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    @Override
    public void init() {
        // nothing to do here!
    }
}