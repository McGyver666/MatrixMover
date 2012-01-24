/*
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
package com.gyver.matrixmover.gui;

import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.properties.PropertiesHelper;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

/**
 *
 * @author jonas
 */
public class LedScreen extends javax.swing.JPanel {

    private PropertiesHelper ph = null;
    private MatrixData md = null;
    private int PixelWidth = 0;
    private int PixelHeigth = 0;
    private int PixelSpace = 0;
    private int[] PixelImage = null;
    private double PixelRatio = 0;
    private int OffsetToCenterHorrizontal = 0;
    private int OffsetToCenterVertical = 0;

    public LedScreen() {
        this.md = new MatrixData(8, 8);
        PixelHeigth = 10;
        PixelWidth = 10;
        PixelSpace = 1;
        PixelRatio = PixelWidth / PixelHeigth;
        this.setBackground(new Color(51,51,51));
    }

    public void init(PropertiesHelper ph, MatrixData md) {
        this.ph = ph;
        this.md = md;
        PixelHeigth = ph.getLedScreenPixelHeigth();
        PixelWidth = ph.getLedScreenPixelWidth();
        PixelSpace = ph.getLedScreenPixelSpace();
        PixelRatio = PixelWidth / PixelHeigth;
        recomputePixelSize();
        this.repaint();
    }

    public void setPixelImage(int[] image) {
        this.PixelImage = image;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);

        Graphics2D ledGraphics = (Graphics2D) grphcs;

        // if not right ammount of Pixels: fill with black
        if (PixelImage == null) {
            PixelImage = new int[md.getWidth() * md.getHeight()];
            //get a dark gray color is empty
            Arrays.fill(PixelImage, (75 << 16) | (75 << 8) | 75);
        }

        for (int x = 0; x < md.getWidth(); x++) {
            int px_start = x * (PixelWidth + PixelSpace) + PixelSpace;

            for (int y = 0; y < md.getHeight(); y++) {
                ledGraphics.setColor(new Color(PixelImage[(y * md.getWidth() + x)]));

                int py_start = y * (PixelHeigth + PixelSpace) + PixelSpace;

                ledGraphics.fillRect(OffsetToCenterHorrizontal + px_start, OffsetToCenterVertical + py_start, PixelWidth, PixelHeigth);
            }
        }

    }

    public void recomputePixelSize() {

        int newMaxPixelSizeX = (int) Math.round(Math.floor((this.getSize().width - (md.getWidth() * PixelSpace)) / md.getWidth()));
        int newMaxPixelSizeY = (int) Math.round(Math.floor((this.getSize().height - (md.getHeight() * PixelSpace)) / md.getHeight()));
        if (PixelRatio > (newMaxPixelSizeY / newMaxPixelSizeX)) {
            PixelHeigth = newMaxPixelSizeY;
            PixelWidth = (int) Math.round(newMaxPixelSizeY * PixelRatio);
        } else {
            PixelWidth = newMaxPixelSizeX;
            PixelHeigth = (int) Math.round(newMaxPixelSizeX / PixelRatio);
        }
        OffsetToCenterHorrizontal = (int) Math.round(Math.floor(this.getSize().width - (md.getWidth() * (PixelWidth + PixelSpace))) / 2);
        OffsetToCenterVertical = (int) Math.round(Math.floor(this.getSize().height - (md.getHeight() * (PixelHeigth + PixelSpace))) / 2);
    }
}