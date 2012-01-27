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
import java.util.Random;

/**
 * The Class Fire.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class Fire extends Generator {

    /* paletter */
    /** The colors. */
    private int[] colors;
    /** The r. */
    private Random r;

    /* fire buffer, contains 0..255 */
    /** The buffer. */
    private int[] buffer;
    
    private MatrixData md = null;

    /**
     * Instantiates a new fire.
     *
     * @param controller the controller
     */
    public Fire(MatrixData md) {
        super(GeneratorName.FIRE, md);
        this.md = md;

        //Setup palette
        colors = new int[256];
        buffer = new int[md.getWidth() * (md.getHeight()+2)];
        for (int i = 0; i < 32; ++i) {
            /* black to blue, 32 values*/
            colors[i] = getColor(0, 0, i << 1);

            /* blue to red, 32 values*/
            colors[i + 32] = getColor(i << 3, 0, 64 - (i << 1));

            /*red to yellow, 32 values*/
            colors[i + 64] = getColor(255, i << 3, 0);

            /* yellow to white, 162 */
            colors[i + 96] = getColor(255, 255, i << 2);
            colors[i + 128] = getColor(255, 255, 64 + (i << 2));
            colors[i + 160] = getColor(255, 255, 128 + (i << 2));
            colors[i + 192] = getColor(255, 255, 192 + i);
            colors[i + 224] = getColor(255, 255, 224 + i);
        }

        r = new Random();
    }

    /**
     * Gets the color.
     *
     * @param r the r
     * @param g the g
     * @param b the b
     * @return the color
     */
    private int getColor(int r, int g, int b) {
        return (r << 16) | (g << 8) | (b);
    }

    @Override
    public void update() {
        int j = md.getWidth() * (md.getHeight()+1);

        int random;
        for (int i = 0; i < md.getWidth(); i++) {
            random = r.nextInt(16);
            /* the lower the value, the intense the fire, compensate a lower value with a higher decay value*/
            if (random > 10) {
                /*maximum heat*/
                this.buffer[j + i] = 255;
            } else {
                this.buffer[j + i] = 0;
            }
        }

        /* move fire upwards, start at bottom*/
        int temp;
        for (int index = 0; index < md.getHeight()+1; index++) {
            for (int i = 0; i < md.getWidth(); i++) {
                if (i == 0) {
                    /* at the left border*/
                    temp = buffer[j];
                    temp += buffer[j + 1];
                    temp += buffer[j - md.getWidth()];
                    temp /= 3;
                } else if (i == md.getWidth() - 1) {
                    /* at the right border*/
                    temp = buffer[j + i];
                    temp += buffer[j - md.getWidth() + i];
                    temp += buffer[j + i - 1];
                    temp /= 3;
                } else {
                    temp = buffer[j + i];
                    temp += buffer[j + i + 1];
                    temp += buffer[j + i - 1];
                    temp += buffer[j - md.getWidth() + i];
                    temp >>= 2;
                }
                if (temp > 1) {
                    /* decay */
                    temp--;
                }
                this.buffer[j - md.getWidth() + i] = temp;
                int position = j + i - (2*md.getWidth());
                if(position >= 0){
                    this.internalBuffer[position] = colors[temp];
                }
            }
            j -= md.getWidth();
        }

    }
}
