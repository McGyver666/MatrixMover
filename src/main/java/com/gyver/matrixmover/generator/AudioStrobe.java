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
import com.gyver.matrixmover.generator.enums.GeneratorName;
import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Gyver
 */
public class AudioStrobe extends Generator {
    
    private final float CLIPPING_MULT = 10F;
    private final float CLIPPING_SUB = 9F;
    private final int RUNNING_MEAN_LENGTH = 125;

    private int bandToUse = 0;
    private float[] decayedSpectrum = null;
    private Color color = null;
    private LinkedList<Double> runningMean = null;

    /**
     * Instantiates a new null generator.
     *
     * @param matrix the MatrixData of the matrix.
     */
    public AudioStrobe(MatrixData matrix) {
        super(GeneratorName.AUDIO_STROBE, matrix);
        
        runningMean = new LinkedList<Double>();
        color = Color.WHITE;
        bandToUse = 1;
    }

    @Override
    public void update() {

        float[] spectrum = Controller.getControllerInstance().getSpectrum(10);
        if (spectrum == null) {
            return;
        }

        addToStack(spectrum[bandToUse]);
        
        double mult = getValBasedOnMeanMinMax();
        
        short r = (short) (color.getRed() * mult);
        short g = (short) (color.getGreen() * mult);
        short b = (short) (color.getBlue() * mult);
            
        Arrays.fill(internalBuffer, (int) ((r << 16) | (g << 8) | b));
    }

    @Override
    public void init() {
        // do nothing
    }

    private void addToStack(double val) {
        runningMean.add(val);
        if (runningMean.size() > RUNNING_MEAN_LENGTH) {
            runningMean.remove();
        }
    }

    private double getValBasedOnMeanMinMax() {
        if (runningMean.size() == 0) {
            return 0;
        }
        double min = 1;
        double max = 0;
        
        for (double elem : runningMean) {
            if(elem < 0){
                elem = 0;
            }
            if(elem < min){
                min = elem;
            }
            if(elem > max){
                max = (elem * 0.5) + 0.5;
            }
        }
        
        //get the newest and scale it between min and max.
        double ret = runningMean.getLast() - min;
        ret = ret / (max - min);
        ret = ret * CLIPPING_MULT - (ret * CLIPPING_SUB);
        
        //clip ret to [0,1]
        if(ret < 0){
            ret = 0;
        }
        if(ret > 1){
            ret = 1;
        }
        
        return ret;
    }

    public Color getColor() {
        return color;
    }
    
    public void setColor(Color col){
        color = col;
    }

    public int getBand() {
        return bandToUse;
    }
    
    public void setBand(int band){
        this.bandToUse = band;
    }
    
    /**
     * Gets the parameter of the generator as String
     * 
     * @return the parameter as String
     */
    @Override
    public String parameterToString(){
        String ret = "band="+bandToUse+"\n";
        ret += "color="+color.getRGB()+"\n";
        return ret;
    }
    
    @Override
    public void configureFromString(String configuration) {
        String[] config = configuration.split(";");
        for(String conf : config) {
            String par = conf.split("=")[0];
            String var = conf.split("=")[1];
            switch (par) {
                case "band":
                    setBand(Integer.valueOf(var));
                    break;
                case "color":
                    setColor(new Color(Integer.valueOf(var)));
                    break;
                default: 
                    throw new UnsupportedOperationException("Unknown Parameter for Generator "+this.getName()+": '"+conf+"'.");
            }
        }
    }
}
