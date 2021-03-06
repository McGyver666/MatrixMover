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
 * The Class Mix.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class Mix extends Mixer {

    /**
     * Instantiates a new mix.
     *
     * @param controller the controller
     */
    public Mix() {
        super(MixerName.MIX);
    }

    @Override
    public int[] getBuffer(int[] src1, int[] src2) {
        short redSource, greenSource, blueSource;
        short redDest, greenDest, blueDest;
        int col;

        int[] dst = new int[src1.length];

        for (int i = 0; i < src1.length; i++) {
            col = src1[i];
            redSource = (short) ((col >> 16) & 255);
            greenSource = (short) ((col >> 8) & 255);
            blueSource = (short) (col & 255);

            col = src2[i];
            redDest = (short) ((col >> 16) & 255);
            greenDest = (short) ((col >> 8) & 255);
            blueDest = (short) (col & 255);

            redDest = (short) ((redDest + redSource) / 2);
            greenDest = (short) ((greenDest + greenSource) / 2);
            blueDest = (short) ((blueDest + blueSource) / 2);

            dst[i] = (int) ((redDest << 16) | (greenDest << 8) | blueDest);
        }

        return dst;
    }
}