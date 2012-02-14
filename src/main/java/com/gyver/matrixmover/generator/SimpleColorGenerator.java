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

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import java.awt.Color;
import java.util.Arrays;

/**
 * This Generator simply generates a one colored image.
 * 
 * @author Gyver
 */
public class SimpleColorGenerator extends Generator {

    private Color color = null;
    private float[] decayedSpectrum = null;

    /**
     * Instantiates a new null generator.
     *
     * @param matrix the MatrixData of the matrix.
     */
    public SimpleColorGenerator(MatrixData matrix) {
        super(GeneratorName.SIMPLE_COLOR_GENERATOR, matrix);
        color = new Color(0);
    }

    @Override
    public void init() {
        // nothing to do here.
    }

    @Override
    public void update() {

        int bands = 32;

        float[] spectrum = Controller.getControllerInstance().getSpectrum(bands);

        if (decayedSpectrum == null) {
            decayedSpectrum = new float[bands];
        }

        for (int i = 0; i < bands; i++) {
            spectrum[i] = spectrum[i] * ((i/3)+1) / 2.5F - 1;
            if (spectrum[i] >= decayedSpectrum[i]) {
                decayedSpectrum[i] = spectrum[i];
            } else {
                decayedSpectrum[i] -= 2F;
                if (spectrum[i] >= decayedSpectrum[i]) {
                    decayedSpectrum[i] = spectrum[i];
                }
            }
        }

        for (int i = 0; i < this.internalBufferWidth; i++) {
            for (int j = 0; j < this.internalBufferHeight; j++) {
                if (decayedSpectrum[i] > j) {
                    internalBuffer[i + (j * internalBufferWidth)] = 0xFFFFFF;
                } else {
                    internalBuffer[i + (j * internalBufferWidth)] = 0x000000;
                }
            }
        }


//        Arrays.fill(this.internalBuffer, color.getRGB());
    }

    /**
     * Sets the color
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the color
     * @return
     */
    public Color getColor() {
        return this.color;
    }
}
