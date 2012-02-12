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
import com.gyver.matrixmover.generator.enums.TextwriterScrollMode;
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
    
    /** The Constant CHANGE_SCROLLING_DIRECTION_TIMEOUT. */
    private static final int CHANGE_SCROLLING_DIRECTION_TIMEOUT = 12;
    /** The Constant SCROLL_AMMOUNT. */
    private static final int SCROLL_AMMOUNT = 1;
    /** The log. */
    private static final Logger LOG = Logger.getLogger(Textwriter.class.getName());
    /** The color. */
    private Color color;
    /** The xofs. */
    private int xofs;
    private boolean scrollRight = true;
    /** The wait. */
    private int wait;
    /** The text buffer. */
    private int[] textBuffer;
    /** The tmp. */
    private int[] tmp;
    /** The text. */
    private String text;
    
    private Font font;
    private int textWidth;
    private int textHeidth;
    private int yOffset;
    private int speed;
    private boolean antialiasing;
    private TextwriterScrollMode mode;

    /**
     * Instantiates a new textwriter.
     *
     * @param md the MatrixData of this
     */
    public Textwriter(MatrixData md) {
        super(GeneratorName.TEXTWRITER, md);
        color = new Color(255, 255, 255);
        yOffset = 0;
        speed = 120;
        antialiasing = true;
        mode = TextwriterScrollMode.LEFT_TO_RIGHT;
        tmp = new int[internalBuffer.length];
            
        try {
            font = new Font("Dialog", 1, 9);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Failed to load font", e);
        }
        
        createTextImage(INITIAL_TEXT);
    }

    @Override
    public void update() {

        if (wait > 0) {
            wait--;
        } else {
            if (textWidth < getInternalBufferXSize()) {
//no need to scroll
                xofs = (getInternalBufferXSize() - textWidth) / 2;
                wait = 99999;
            } else {
//todo, if image < buffer
                if (scrollRight) {
                    xofs += SCROLL_AMMOUNT;
                    if (xofs > textWidth - internalBufferWidth) {
                        scrollRight = false;
                        xofs = textWidth - internalBufferWidth;
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
            if (textWidth < getInternalBufferXSize()) {
//text image smaller than internal buffer
                srcOfs = 0;
                dstOfs = xofs;
//we need to clear the buffer first!
                Arrays.fill(tmp, 0);

                for (int y = 0; y < textHeidth; y++) {
                    System.arraycopy(textBuffer, srcOfs, tmp, dstOfs, textWidth);
                    dstOfs += internalBufferWidth;
                    srcOfs += textWidth;
                }
            } else {
                for (int y = 0; y < textHeidth; y++) {
                    System.arraycopy(textBuffer, srcOfs, tmp, dstOfs, internalBufferWidth);
                    dstOfs += internalBufferWidth;
                    srcOfs += textWidth;
                }
            }

            this.internalBuffer = tmp;
        } catch (Exception e) {
//if the image is resized, this could lead to an arrayoutofboundexception!
        }

    }

    @Override
    public void init() {
        tmp = new int[internalBuffer.length];
        createTextImage(this.text);
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
        init();
    }

    /**
     * @return the yOffset
     */
    public int getyOffset() {
        return yOffset*(-1);
    }

    /**
     * @param yOffset the yOffset to set
     */
    public void setyOffset(int yOffset) {
        this.yOffset = yOffset*(-1);
        init();
    }
    
    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the mode
     */
    public TextwriterScrollMode getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(TextwriterScrollMode mode) {
        this.mode = mode;
        init();
    }

    /**
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * @param font the font to set
     */
    public void setFont(Font font) {
        this.font = font;
        init();
    }

    /**
     * @return the antialiasing
     */
    public boolean isAntialiasing() {
        return antialiasing;
    }

    /**
     * @param antialiasing the antialiasing to set
     */
    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
        init();
    }
    
    /**
     * create image.
     *
     * @param text the text
     */
    private void createTextImage(String text) {
        this.text = text;

        Graphics2D g2 = (new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)).createGraphics();
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout layout = new TextLayout(text, getFont(), frc);
        Rectangle2D rect = layout.getBounds();

        textHeidth = (int) (0.5f + rect.getHeight());
        textWidth = (int) (0.5f + rect.getWidth());
        
        System.out.println(textHeidth +" - "+ internalBufferHeight);

        BufferedImage img = new BufferedImage(textWidth, textHeidth, BufferedImage.TYPE_INT_RGB);
        g2 = img.createGraphics();

        g2.setColor(color);
        g2.setFont(getFont());
        g2.setClip(0, 0, textWidth, textHeidth);
        
        if(this.isAntialiasing()) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        g2.drawString(text, 0, textHeidth);
        DataBufferInt dbi = (DataBufferInt) img.getRaster().getDataBuffer();
        textBuffer = dbi.getData();
        g2.dispose();

        wait = 0;
        xofs = 0;
        scrollRight = false;
    }
}