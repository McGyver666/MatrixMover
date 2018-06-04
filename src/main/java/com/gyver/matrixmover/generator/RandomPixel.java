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

/**
 * The Class Metaballs. Calculates a classic metaballs effect.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 * 
 */
public class RandomPixel extends Generator {
    
    private int minThres = 0;
    private int ticksToRefresh = 1;
    private int colorNoise = 0;

    /**
     * Instantiates a new metaballs.
     *
     * @param md the MatrixData of the Matrix
     */
    public RandomPixel(MatrixData md) {
        super(GeneratorName.RANDOM_PIXEL, md);
        
    }
    
    @Override
    public void init(){
        
    }
    
    @Override
    public void update() {
        for(int i = 0; i < this.internalBuffer.length; i++) {
            if (Math.random() < (1.0f / getTicksToRefresh())) {
                int r = ((int) (Math.random() * 256) % (256-getMinThres()))+getMinThres();
                int topBound = Math.min(r + getColorNoise(), 255);
                int bottomBound = Math.max(r - getColorNoise(), 0);
                int g = (int) (Math.random() * (topBound - bottomBound)) + bottomBound;
                int b = (int) (Math.random() * (topBound - bottomBound)) + bottomBound;

                this.internalBuffer[i] = (r << 16) | (g << 8) | b;
            }
        }
    }
    
    /**
     * Gets the parameter of the generator as String
     * 
     * @return the parameter as String
     */
    @Override
    public String parameterToString(){
        String ret = "minThres="+getMinThres()+"\n";
        return ret;
    }
    
    
    @Override
    public void configureFromString(String configuration) {
        String[] config = configuration.split(";");
        for(String conf : config) {
            String par = conf.split("=")[0];
            String var = conf.split("=")[1];
            switch (par) {
                case "minThres":
                    setMinThres((int) Integer.valueOf(var));
                    break;
                default: 
                    throw new UnsupportedOperationException("Unknown Parameter for Generator "+this.getName()+": '"+conf+"'.");
            }
            
        }
    }

    /**
     * @return the minThres
     */
    public int getMinThres() {
        return minThres;
    }

    /**
     * @param minThres the minThres to set
     */
    public void setMinThres(int minThres) {
        if (minThres < 0 || minThres > 255) 
            throw new IllegalArgumentException("MinThres out of bound 0..255: "+minThres);
        this.minThres = minThres;
    }

    /**
     * @return the ticksToRefresh
     */
    public int getTicksToRefresh() {
        return ticksToRefresh;
    }

    /**
     * @param ticksToRefresh the ticksToRefresh to set
     */
    public void setTicksToRefresh(int ticksToRefresh) {
        if (ticksToRefresh < 1) 
            throw new IllegalArgumentException("TicksToRefresh smaller 1: "+minThres);
        this.ticksToRefresh = ticksToRefresh;
    }

    /**
     * @return the colorNoise
     */
    public int getColorNoise() {
        return colorNoise;
    }

    /**
     * @param colorNoise the colorNoise to set
     */
    public void setColorNoise(int colorNoise) {
        if (colorNoise < 0 || colorNoise > 255) 
            throw new IllegalArgumentException("ColorNoise out of bound 0..255: "+colorNoise);
        this.colorNoise = colorNoise;
    }
}