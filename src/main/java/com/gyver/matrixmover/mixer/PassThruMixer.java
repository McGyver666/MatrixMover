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
package com.gyver.matrixmover.mixer;

/**
 * The Class PassThruMixer.
 * 
 * @author Gyver
 */
public class PassThruMixer extends Mixer {

    /**
     * Instantiates a new pass thru mixer.
     *
     * @param controller the controller
     */
    public PassThruMixer() {
        super(MixerName.PASSTHRU);
    }

    @Override
    public int[] getBuffer(int[] src1, int[] src2) {
        return src1;
    }
}