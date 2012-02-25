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
package com.gyver.matrixmover.generator;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.generator.enums.AnalyserDirection;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Gyver
 */
public class Analyser extends ColorMapAwareGenerator {
    
    private AnalyserDirection analyserDirection = null;
    private float[] decayedSpectrum = null;
    private float gain = 1F;
    
    /**
     * Instantiates a new null generator.
     *
     * @param matrix the MatrixData of the matrix.
     */
    public Analyser(MatrixData matrix) {
        super(GeneratorName.ANALYSER, matrix, null);
        
        // set default color
        CopyOnWriteArrayList<Color> defaultColorMap = new CopyOnWriteArrayList<Color>();
        defaultColorMap.add(new Color(0, 0, 255));
        defaultColorMap.add(new Color(255, 0, 0));
        
        this.setColorMap(defaultColorMap);
        
        
        analyserDirection = AnalyserDirection.LEFT_TO_RIGHT;
        
    }
    
    @Override
    public void update() {
        
        

        switch (getAnalyserDirection()) {
            case LEFT_TO_RIGHT:
                leftToRight();
                break;
            case RIGHT_TO_LEFT:
                rightToLeft();
                break;
            case TOP_TO_BOTTOM:
                topDown();
                break;
            case BOTTOM_TO_TOP:
                bottomUp();
                break;
        }
        
    }

    @Override
    public void init() {
        // do nothing
    }
    
    private void leftToRight(){
        int bands = internalBufferHeight;

        float[] spectrum = Controller.getControllerInstance().getSpectrum(bands);
        if(spectrum == null){
            return;
        }

        if (decayedSpectrum == null || decayedSpectrum.length != bands) {
            decayedSpectrum = new float[bands];
        }

        for (int i = 0; i < bands; i++) {
            spectrum[i] *= internalBufferWidth * gain;
            if (spectrum[i] >= decayedSpectrum[i]) {
               decayedSpectrum[i] = spectrum[i];
            } else {
                decayedSpectrum[i] -= 0.5F;
                if (spectrum[i] >= decayedSpectrum[i]) {
                    decayedSpectrum[i] = spectrum[i];
                }
            }
        }
        
        for (int i = 0; i < this.internalBufferWidth; i++) {
            for (int j = 0; j < this.internalBufferHeight; j++) {
                
                if (decayedSpectrum[j] > i) {
                    
                    float rawColor = i / (float) Math.floor(decayedSpectrum[j]) * (colorMap.size()-1);
                    
                    int thisColor = (int) Math.floor(rawColor);
                    int nextColor = (thisColor+1) % colorMap.size();
                    float ratio = rawColor - (float) thisColor;
                    
                    internalBuffer[i + (j * internalBufferWidth)] = getColor(thisColor, nextColor, ratio);
                } else {
                    internalBuffer[i + (j * internalBufferWidth)] = 0x000000;
                }
            }
        }
    }
    
    private void rightToLeft(){
        int bands = internalBufferHeight;

        float[] spectrum = Controller.getControllerInstance().getSpectrum(bands);
        if(spectrum == null){
            return;
        }

        if (decayedSpectrum == null || decayedSpectrum.length != bands) {
            decayedSpectrum = new float[bands];
        }

        for (int i = 0; i < bands; i++) {
            spectrum[i] *= internalBufferWidth * gain;
            if (spectrum[i] >= decayedSpectrum[i]) {
               decayedSpectrum[i] = spectrum[i];
            } else {
                decayedSpectrum[i] -= 0.5F;
                if (spectrum[i] >= decayedSpectrum[i]) {
                    decayedSpectrum[i] = spectrum[i];
                }
            }
        }
        
        for (int i = 0; i < this.internalBufferWidth; i++) {
            for (int j = 0; j < this.internalBufferHeight; j++) {
                
                if (decayedSpectrum[j] > i) {
                    
                    float rawColor = i / (float) Math.floor(decayedSpectrum[j]) * (colorMap.size()-1);
                    
                    int thisColor = (int) Math.floor(rawColor);
                    int nextColor = (thisColor+1) % colorMap.size();
                    float ratio = rawColor - (float) thisColor;
                    
                    internalBuffer[(internalBufferWidth - 1 - i) + (j * internalBufferWidth)] = getColor(thisColor, nextColor, ratio);
                } else {
                    internalBuffer[(internalBufferWidth - 1 - i) + (j * internalBufferWidth)] = 0x000000;
                }
            }
        }
    }
    
    private void topDown(){
        int bands = internalBufferWidth;

        float[] spectrum = Controller.getControllerInstance().getSpectrum(bands);
        if(spectrum == null){
            return;
        }

        if (decayedSpectrum == null || decayedSpectrum.length != bands) {
            decayedSpectrum = new float[bands];
        }

        for (int i = 0; i < bands; i++) {
            spectrum[i] *= internalBufferHeight * gain;
            if (spectrum[i] >= decayedSpectrum[i]) {
               decayedSpectrum[i] = spectrum[i];
            } else {
                decayedSpectrum[i] -= 0.5F;
                if (spectrum[i] >= decayedSpectrum[i]) {
                    decayedSpectrum[i] = spectrum[i];
                }
            }
        }
        
        for (int i = 0; i < this.internalBufferWidth; i++) {
            for (int j = 0; j < this.internalBufferHeight; j++) {
                
                if (decayedSpectrum[i] > j) {
                    
                    float rawColor = j / (float) Math.floor(decayedSpectrum[i]) * (colorMap.size()-1);
                    
                    int thisColor = (int) Math.floor(rawColor);
                    int nextColor = (thisColor+1) % colorMap.size();
                    float ratio = rawColor - (float) thisColor;
                    
                    internalBuffer[i + (j * internalBufferWidth)] = getColor(thisColor, nextColor, ratio);
                } else {
                    internalBuffer[i + (j * internalBufferWidth)] = 0x000000;
                }
            }
        }
    }
    
    private void bottomUp(){
        int bands = internalBufferWidth;

        float[] spectrum = Controller.getControllerInstance().getSpectrum(bands);
        if(spectrum == null){
            return;
        }

        if (decayedSpectrum == null || decayedSpectrum.length != bands) {
            decayedSpectrum = new float[bands];
        }

        for (int i = 0; i < bands; i++) {
            spectrum[i] *= internalBufferHeight * gain;
            if (spectrum[i] >= decayedSpectrum[i]) {
               decayedSpectrum[i] = spectrum[i];
            } else {
                decayedSpectrum[i] -= 0.5F;
                if (spectrum[i] >= decayedSpectrum[i]) {
                    decayedSpectrum[i] = spectrum[i];
                }
            }
        }
        
        for (int i = 0; i < this.internalBufferWidth; i++) {
            for (int j = 0; j < this.internalBufferHeight; j++) {
                
                if (decayedSpectrum[i] > j) {
                    
                    float rawColor = j / (float) Math.floor(decayedSpectrum[i]) * (colorMap.size()-1);
                    
                    int thisColor = (int) Math.floor(rawColor);
                    int nextColor = (thisColor+1) % colorMap.size();
                    float ratio = rawColor - (float) thisColor;
                    
                    internalBuffer[i + ((internalBufferHeight - 1 - j) * internalBufferWidth)] = getColor(thisColor, nextColor, ratio);
                } else {
                    internalBuffer[i + ((internalBufferHeight - 1 - j) * internalBufferWidth)] = 0x000000;
                }
            }
        }
    }

    /**
     * @return the gain
     */
    public int getGain() {
        return (int) (gain * 100F);
    }

    /**
     * @param gain the gain to set
     */
    public void setGain(int gain) {
        this.gain = (gain / 100F);
    }

    /**
     * @return the analyserDirection
     */
    public AnalyserDirection getAnalyserDirection() {
        return analyserDirection;
    }

    /**
     * @param analyserDirection the analyserDirection to set
     */
    public void setAnalyserDirection(AnalyserDirection analyserDirection) {
        this.analyserDirection = analyserDirection;
    }
    
}
