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

package com.gyver.matrixmover.effect;

import com.gyver.matrixmover.core.MatrixData;

public class Emboss extends Effect {

    /** The emboss kernel. */
    private static float[] embossKernel = new float[]{
        -2, -2, 0,
        -2, 6, 0,
        0, 0, 0
    };
    /** The boxoffset. */
    private int[] boxoffset;
    /** The buffer size. */
    private int bufferSize;

    /**
     * Instantiates a new emboss.
     *
     * @param controller the controller
     */
    public Emboss(MatrixData md) {
        super(EffectName.EMBOSS, md);

        bufferSize = internalBufferWidth * internalBufferHeight;
        boxoffset = new int[]{
            bufferSize - internalBufferWidth - 1, bufferSize - internalBufferWidth, bufferSize - internalBufferWidth + 1,
            bufferSize - 1, 0, 1,
            internalBufferWidth - 1, internalBufferWidth, internalBufferWidth + 1
        };

    }

    @Override
    public int[] getBuffer(int[] buffer) {
        int ret[] = new int[buffer.length];
        float f;
        int val, valr, valg, valb;
        int index = 0;

        for (int y = 0; y < internalBufferHeight; y++) {
            for (int x = 0; x < internalBufferWidth; x++) {
                valr = 128;
                valg = 128;
                valb = 128;

                for (int ofsn = 0; ofsn < 9; ofsn++) {
                    f = embossKernel[ofsn];
                    val = buffer[(index + boxoffset[ofsn]) % (bufferSize)];
                    valr += (int) (f * ((val >> 16) & 255));
                    valg += (int) (f * ((val >> 8) & 255));
                    valb += (int) (f * ((val) & 255));
                }

                if (valr > 255) {
                    valr = 255;
                }
                if (valg > 255) {
                    valg = 255;
                }
                if (valb > 255) {
                    valb = 255;
                }

                if (valr < 0) {
                    valr = 0;
                }
                if (valg < 0) {
                    valg = 0;
                }
                if (valb < 0) {
                    valb = 0;
                }

                ret[index] = (int) (valr << 16) | (valg << 8) | valb;
                index++;
            }

        }
        return ret;
    }
}