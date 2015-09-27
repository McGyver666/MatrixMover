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
import java.awt.Color;
import java.util.LinkedList;

public class BoxCutOut extends Effect {

    private int xBegin;
    private int xEnd;
    private int yBegin;
    private int yEnd;
    private Color fillColor;
    private int[] internalBuffer;
    
    /**
     * Instantiates a new box cut out.
     *
     * @param controller the controller
     */
    public BoxCutOut(MatrixData md) {
        super(EffectName.BOXCUTOUT, md);
        
        xBegin = 0;
        xEnd = md.getWidth();
        yBegin = 0;
        yEnd = md.getHeight();
        fillColor = Color.BLACK;
        this.internalBuffer = new int[md.getHeight() * md.getWidth()];

    }

    @Override
    public int[] getBuffer(int[] buffer) {
        internalBuffer = buffer;
        for (int x = 0; x < internalBufferWidth; x++) {
            for (int y = 0; y < internalBufferHeight; y++) {
                if (x < getxBegin() || x >= getxEnd() || y < getyBegin() || y >= getyEnd()) {
                    this.internalBuffer[y * internalBufferWidth + x] = fillColor.getRGB();
                }
            }
        }
        return internalBuffer;
        
    }

    /**
     * @return the xBegin
     */
    public int getxBegin() {
        return xBegin;
    }

    /**
     * @param xBegin the xBegin to set
     */
    public void setxBegin(int xBegin) {
        this.xBegin = xBegin;
    }

    /**
     * @return the xEnd
     */
    public int getxEnd() {
        return xEnd;
    }

    /**
     * @param xEnd the xEnd to set
     */
    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    /**
     * @return the yBegin
     */
    public int getyBegin() {
        return yBegin;
    }

    /**
     * @param yBegin the yBegin to set
     */
    public void setyBegin(int yBegin) {
        this.yBegin = yBegin;
    }

    /**
     * @return the yEnd
     */
    public int getyEnd() {
        return yEnd;
    }

    /**
     * @param yEnd the yEnd to set
     */
    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    /**
     * Gets the parameter of the generator as String
     * 
     * @return the parameter as String
     */
    @Override
    public String parameterToString(){
        String ret = "xBegin="+xBegin+"\n";
        ret += "xEnd="+xEnd+"\n";
        ret += "yBegin="+yBegin+"\n";
        ret += "yEnd="+yEnd+"\n";
        return ret;
    }
    
    
    @Override
    public void configureFromString(String configuration) {
        String[] config = configuration.split(";");
        for(String conf : config) {
            String par = conf.split("=")[0];
            String var = conf.split("=")[1];
                        
            switch (par) {
                case "xBegin":
                    setxBegin(Integer.valueOf(var));
                    break;
                case "xEnd":
                    setxEnd(Integer.valueOf(var));
                    break;
                case "yBegin":
                    setyBegin(Integer.valueOf(var));
                    break;
                case "yEnd":
                    setyEnd(Integer.valueOf(var));
                    break;
                default: 
                    throw new UnsupportedOperationException("Unknown Parameter for Generator "+this.getName()+": '"+conf+"'.");
            }
        }
    }
    
}