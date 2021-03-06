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
import java.util.List;

/**
 * The class ColorFade. Calculates a simple color fader. The
 * whole matrix has one color. Colors change dependent on the 
 * color map.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author McGyver
 */
public class ColorFade extends ColorMapAwareGenerator {

    private int speed;
    private float frameCount;
    private int fade;
    private double forward;

    /**
     * Instantiates a new colorscroll
     *
     * @param matrix The MatrixData of the matrix.
     * @param colorMap The colorMap for the effect.
     */
    public ColorFade(MatrixData matrix, List<Color> colorMap) {
        super(GeneratorName.COLOR_FADE, matrix, colorMap);

        this.fade = Controller.getControllerInstance().getFps();
        
        speed = 30;
        calcForward();
    }

    @Override
    public void update() {
        Arrays.fill(this.internalBuffer, getColor());
        frameCount = (float) (frameCount + forward);
    }

    @Override
    public void init() {
        calcForward();
    }
    
    /**
     * @return the colorFadeTime
     */
    public int getSpeed() {
        return speed;
    }
    
    @Override
    public void setColorMap(List<Color> colorMap) {
        super.setColorMap(colorMap);
        setSpeed(this.speed);
    }

    /**
     * Sets the speed of this (in beats per minute)
     * @param bpm the speed in bpm
     */
    public void setSpeed(int bpm) {
        this.speed = bpm;
        calcForward();
    }
    
    private void calcForward(){
        // this is ne number of pixels to move the pattern per frame
        // to get the pattern moving across the whole scren in 1 beat
        // we have to to some calculations (speed is given in bpm)
        
        int fps = Controller.getControllerInstance().getFps();
        forward = (speed / 60F);
        forward = forward * (fade / (float) fps);
    }

    private int getColor() {
        int colornumber = (int) (Math.round(Math.floor(frameCount / fade)));
        colornumber = colornumber % colorMap.size();
        int nextcolornumber = (colornumber + 1) % colorMap.size();
        float ratio = (float) ((frameCount % fade) / (float) fade);
        return super.getColor(colornumber, nextcolornumber, ratio);
    }
    
    /**
     * Gets the parameter of the generator as String
     * 
     * @return the parameter as String
     */
    @Override
    public String parameterToString(){
        String ret = "speed="+speed+"\n";
        ret += super.colorMapToString(colorMap);
        return ret;
    }
    
    
    @Override
    public void configureFromString(String configuration) {
        String[] config = configuration.split(";");
        for(String conf : config) {
            String par = conf.split("=")[0];
            String var = conf.split("=")[1];
            
            if(par.startsWith("color")) {
                continue;
            }
            
            switch (par) {
                case "speed":
                    setSpeed(Integer.valueOf(var));
                    break;
                default: 
                    throw new UnsupportedOperationException("Unknown Parameter for Generator "+this.getName()+": '"+conf+"'.");
            }
        }
        super.setColorFromConfigurationString(configuration);
    }
    
}
