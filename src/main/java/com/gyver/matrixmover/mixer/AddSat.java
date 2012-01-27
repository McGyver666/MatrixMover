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
package com.gyver.matrixmover.mixer;

/**
 * The Class AddSat.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class AddSat extends Mixer {

    /**
     * Instantiates a new adds the sat.
     *
     * @param controller the controller
     */
    public AddSat() {
        super(MixerName.ADDSAT);
    }

    @Override
    public int[] getBuffer(int[] src1, int[] src2) {
        int[] dst = new int[src1.length];
        short r, g, b;
        int col_s, col_d;

        for (int i = 0; i < src1.length; i++) {
            col_s = src1[i];
            r = (short) ((col_s >> 16) & 255);
            g = (short) ((col_s >> 8) & 255);
            b = (short) (col_s & 255);
            col_d = src2[i];
            r += (short) ((col_d >> 16) & 255);
            g += (short) ((col_d >> 8) & 255);
            b += (short) (col_d & 255);

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            dst[i] = (int) (r << 16) | (g << 8) | b;
        }

        return dst;
    }
}