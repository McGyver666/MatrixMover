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
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The Class Fire. Computes a fire of flame like effect. 
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class Fire extends ColorMapAwareGenerator {

    /* paletter */
    /** The colors. */
    private int[] colors;
    /** The r. */
    private Random r;

    /* fire buffer, contains 0..255 */
    /** The buffer. */
    private int[] buffer;
    private int[] thisFirePicture;
    private int[] nextFirePicture;
    private float step = 0;
    private float speed = 0;
    private int decay = 0;
    private int intensity = 0;

    /**
     * Instantiates a new fire.
     *
     * @param md The MatrixData of the matrix.
     */
    public Fire(MatrixData md) {
        super(GeneratorName.FIRE, md, null);

        intensity = 6;
        decay = 2;
        speed = 0.2F;

        //Setup palette
        colors = new int[256];
        buffer = new int[internalBufferWidth * (internalBufferHeight + 2)];
        thisFirePicture = new int[internalBufferHeight * internalBufferWidth];
        nextFirePicture = new int[internalBufferHeight * internalBufferWidth];
        

        CopyOnWriteArrayList<Color> localColorMap = new CopyOnWriteArrayList<Color>();

        localColorMap.add(new Color(0, 0, 0));
        localColorMap.add(new Color(0, 0, 64));
        localColorMap.add(new Color(255, 0, 0));
        localColorMap.add(new Color(255, 255, 0));
        localColorMap.add(new Color(255, 255, 64));
        localColorMap.add(new Color(255, 255, 128));
        localColorMap.add(new Color(255, 255, 192));
        localColorMap.add(new Color(255, 255, 224));
        localColorMap.add(new Color(255, 255, 255));

        super.setColorMap(localColorMap);

        updateColorArray();

        r = new Random();
    }

    @Override
    public void update() {
        step += speed;
        if (step >= 1) {
            thisFirePicture = nextFirePicture;
            nextFirePicture = calcNewImage();
            step -= 1;
        }

        combineBuffers(thisFirePicture, nextFirePicture, step);
    }
    
    @Override
    public void init() {
        buffer = new int[internalBufferWidth * (internalBufferHeight + 2)];
        thisFirePicture = new int[internalBufferHeight * internalBufferWidth];
        nextFirePicture = new int[internalBufferHeight * internalBufferWidth];
        
    }

    /**
     * Update the color map
     *
     * @param colorMap
     */
    @Override
    public void setColorMap(List<Color> colorMap) {
        super.setColorMap(colorMap);
        updateColorArray();
    }

    /**
     * @return the decay
     */
    public int getDecay() {
        return decay;
    }

    /**
     * @param decay the decay to set
     */
    public void setDecay(int decay) {
        this.decay = decay;
    }

    /**
     * @return the intensity
     */
    public int getIntensity() {
        return intensity;
    }

    /**
     * @param intensity the intensity to set
     */
    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    private int[] calcNewImage() {
        int j = internalBufferWidth * (internalBufferHeight);
        int[] tmpBuffer = new int[j];

        int random;
        for (int i = 0; i < internalBufferWidth; i++) {
            random = r.nextInt(16);
            /* the lower the value, the intense the fire, compensate a lower value with a higher decay value*/
            if (random > 16 - getIntensity()) {
                /*maximum heat*/
                this.buffer[j + i] = 255;
            } else {
                this.buffer[j + i] = 0;
            }
        }

        /* move fire upwards, start at bottom*/
        int temp;
        for (int index = 0; index < internalBufferHeight; index++) {
            for (int i = 0; i < internalBufferWidth; i++) {
                if (i == 0) {
                    /* at the left border*/
                    temp = buffer[j];
                    temp += buffer[j + 1];
                    temp += buffer[j - internalBufferWidth];
                    temp /= 3;
                } else if (i == internalBufferWidth - 1) {
                    /* at the right border*/
                    temp = buffer[j + i];
                    temp += buffer[j - internalBufferWidth + i];
                    temp += buffer[j + i - 1];
                    temp /= 3;
                } else {
                    temp = buffer[j + i];
                    temp += buffer[j + i + 1];
                    temp += buffer[j + i - 1];
                    temp += buffer[j - internalBufferWidth + i];
                    temp >>= 2;
                }

                /* decay */
                temp -= getDecay();
                if (temp < 0) {
                    temp = 0;
                }
                this.buffer[j - internalBufferWidth + i] = temp;
                tmpBuffer[j - internalBufferWidth + i] = colors[temp];
            }
            j -= internalBufferWidth;
        }

        return tmpBuffer;
    }

    private void combineBuffers(int[] thisFirePicture, int[] nextFirePicture, float step) {
        short r1, g1, b1, r2, g2, b2;
        int col_s, col_d;
        for (int i = 0; i < Math.max(internalBuffer.length, Math.max(thisFirePicture.length, nextFirePicture.length)); i++) {
            col_s = thisFirePicture[i];
            r1 = (short) ((col_s >> 16) & 255);
            g1 = (short) ((col_s >> 8) & 255);
            b1 = (short) (col_s & 255);
            col_d = nextFirePicture[i];
            r2 = (short) ((col_d >> 16) & 255);
            g2 = (short) ((col_d >> 8) & 255);
            b2 = (short) (col_d & 255);

            r1 = (short) (r1 - (short) Math.round((r1 - r2) * step));
            g1 = (short) (g1 - (short) Math.round((g1 - g2) * step));
            b1 = (short) (b1 - (short) Math.round((b1 - b2) * step));

            internalBuffer[i] = (int) (r1 << 16) | (g1 << 8) | b1;
        }
    }
    
    private void updateColorArray() {
        float index = 0;
        float indicesPerColorChange = 256 / (float) (colorMap.size() - 1);

        int thisColor = 0;
        int nextColor = 1;

        for (int i = 0; i < colors.length; i++) {
            colors[i] = super.getColor(thisColor, nextColor, index / indicesPerColorChange);
            index++;
            if (index > indicesPerColorChange) {
                index = index - indicesPerColorChange;
                thisColor++;
                nextColor++;
            }
        }
    }
}
