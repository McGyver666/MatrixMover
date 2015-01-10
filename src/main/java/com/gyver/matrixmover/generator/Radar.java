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
import com.gyver.matrixmover.generator.enums.ScrollMode;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The Class Radar
 *
 * @author Gyver
 */
public class Radar extends ColorMapAwareGenerator {

    /** The fade. */
//    private int fade;
    /** The frame count. */
//    private float frameCount;
    /** The internal buffer x size2. */
    private float middleX;
    /** The internal buffer y size2. */
    private float middleY;
    /** The speed given in bpm */
    private int speed;
    private float curve;
    
    private float forward = 0;

    /**
     * Instantiates a new colorscroll.
     *
     * @param matrix The MatrixData of the matrix
     * @param colorMap the color list
     */
    public Radar(MatrixData matrix, List<Color> colorMap) {
        super(GeneratorName.RADAR, matrix, colorMap);

        speed = 60;
        curve = 0;

        middleX = internalBufferWidth / 2F - 0.5F;
        middleY = internalBufferHeight / 2F - 0.5F;
    }

    @Override
    public void update() {

        // rotory factor: 1 turn per beat
        // 1 turn = 2pi
        int fps = Controller.getControllerInstance().getFps();
        forward = forward + (speed / 60F / (float)fps * 2F * (float)Math.PI);

        if (forward > Math.PI * 2) {
            forward = (float) (forward - Math.PI * 2);
        }

        for (int y = 0; y < internalBufferHeight; y++) {
            for (int x = 0; x < internalBufferWidth; x++) {
                float r = calcR(x - middleX, y - middleY);
                float phi = (float) Math.atan2(y - middleY, x - middleX);
                phi = phi + forward + (r * curve);
                while (phi > Math.PI * 2) {
                    phi = (float) (phi - Math.PI * 2);
                }
                while (phi < 0) {
                    phi = (float) (phi + Math.PI * 2);
                }
                
                // reverse tail with:
                //phi = (float) (2* Math.PI) - phi;
                
                // build ramp with:
                phi = (phi - (float) Math.PI) * 2;
                if (phi < 0) {
                    phi = phi * -1;
                }
                
                int col = (int) (phi / Math.PI * 128F);
                internalBuffer[y * internalBufferWidth + x] = (col << 16) | (col << 8) | col;
            }
        }

    }

    @Override
    public void init() {
        // do nothing
    }

    private float calcR(float x, float y) {
        return (float) Math.sqrt((x * x) + (y * y));
    }
    
     /**
     * Gets the parameter of the generator as String
     * 
     * @return the parameter as String
     */
    @Override
    public String parameterToString(){
        String ret = "speed="+getSpeed()+"\n";
        ret += "middleX="+getMiddleX()+"\n";
        ret += "middleY="+getMiddleX()+"\n";
        ret += "curve="+getCurve()+"\n";
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
                    setSpeed((int) Integer.valueOf(var));
                    break;
                case "middleX":
                    setMiddleX(Integer.parseInt(var));
                    break;
                case "middleY":
                    setMiddleY(Integer.parseInt(var));
                    break;
                case "curve":
                    setCurve(Integer.parseInt(var));
                    break;
                default: 
                    throw new UnsupportedOperationException("Unknown Parameter for Generator "+this.getName()+": '"+conf+"'.");
            }
        }
        super.setColorFromConfigurationString(configuration);
    }

    /**
     * @return the curve
     */
    public int getCurve() {
        return (int) curve * 100;
    }

    /**
     * @param curve the curve to set
     */
    public void setCurve(int curve) {
        this.curve = curve / 100F;
    }

    /**
     * @return the middleX
     */
    public int getMiddleX() {
        return (int) (middleX * 100 / internalBufferWidth);
    }

    /**
     * @param middleX the middleX to set
     */
    public void setMiddleX(int middleX) {
        this.middleX = internalBufferWidth * middleX / 100;
    }

    /**
     * @return the middleY
     */
    public int getMiddleY() {
        return (int) (middleY * 100 / internalBufferHeight);
    }

    /**
     * @param middleY the middleY to set
     */
    public void setMiddleY(int middleY) {
        this.middleY = internalBufferHeight * middleY / 100;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}