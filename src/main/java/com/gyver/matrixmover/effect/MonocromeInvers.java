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
package com.gyver.matrixmover.effect;

import com.gyver.matrixmover.core.MatrixData;

/**
 * The Class Inverter.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class MonocromeInvers extends Effect {

    /**
     * Instantiates a new inverter.
     */
    public MonocromeInvers(MatrixData md) {
        super(EffectName.MONOCROME_INVERS, md);
    }

    @Override
    public int[] getBuffer(int[] buffer) {
        int[] ret = new int[buffer.length];

        short cr, cg, cb, mono;
        int col;

        for (int i = 0; i < buffer.length; i++) {
            col = buffer[i];
            cr = (short) (255 - ((col >> 16) & 255));
            cg = (short) (255 - ((col >> 8) & 255));
            cb = (short) (255 - (col & 255));

            mono = (short) (255 - Math.round((0.2125 * cr) + (0.7154 * cg) + (0.0721 * cb)));
            
            ret[i] = (mono << 16) | (mono << 8) | mono;
        }
        return ret;
    }
}
