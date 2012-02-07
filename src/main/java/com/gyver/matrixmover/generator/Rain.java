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
import com.gyver.matrixmover.generator.enums.RainDirection;

/**
 * Generator generating rain drop kind of a effect. Drops are 
 * traveling over the screen in an direkction.
 * 
 * @author Gyver
 */
public class Rain extends ObjectsContainingGenerator {
    
    private RainDirection dropDirection = null;
    private int dropsPerScreen = 2;
    private int lengthDrops = 5;
    private double updatesToNextDrop = 0;
    private double updatesToNextShift = 0;
    private double dropUpdatesDone = 0;
    private double shiftUpdatesDone = 0;
    private int nextColor = 0;
    private int bpm = 120;

    /**
     * Instantiates a new rain generator.
     *
     * @param md the MatrixData of the matrix
     */
    public Rain(MatrixData md){
        super(GeneratorName.RAIN, md, null);
        
        dropDirection = RainDirection.TOP_TO_BOTTOM;
        
        calculateUpdateRates();
    }

    @Override
    public void update() {
        dropUpdatesDone++;
        shiftUpdatesDone++;
        
        
        switch (dropDirection) {
            case LEFT_TO_RIGHT:
                leftToRight();
                break;
            case RIGHT_TO_LEFT:
                rightToLeft();
                break;
            case TOP_TO_BOTTOM:
                topToBottom();
                break;
            case BOTTOM_TO_TOP:
                bottomToTop();
                break;
        }
        
    }
    
    @Override
    public void init() {
        calculateUpdateRates();
    }
    
    /**
     * Sets the speed as bpm
     * @param bpm the speed
     */
    public void setBpm(int bpm){
        this.bpm = bpm;
        calculateUpdateRates();
    }
    
    /**
     * Returns the speet as bpm
     * @return the speed
     */
    public int getBpm(){
        return bpm;
    }
    
    /**
     * Returns the number of drops per screen
     * @param nrDrops the number of drops
     */
    public void setDrosPerScreen(int nrDrops){
        this.dropsPerScreen = nrDrops;
        calculateUpdateRates();
    }
    
    /**
     * Gets the number of drops per screen
     * @return the number of drops
     */
    public int getDropsPerScreen(){
        return dropsPerScreen;
    }
    
    /**
     * Sets the drops tail length
     * @param lengthDrops the length
     */
    public void setRainLength(int lengthDrops){
        this.lengthDrops = lengthDrops;
    }
    
    /**
     * Returns the drops tail length
     * @return the length
     */
    public int getRainLength(){
        return lengthDrops;
    }
    
    /**
     * Returns the RainDirection
     * @return the RainDirection 
     */
    public RainDirection getMode(){
        return dropDirection;
    }
    
    /**
     * Sets the RainDirection mode
     * @param dir the RainDirection
     */
    public void setMode(RainDirection dir){
        this.dropDirection = dir;
    }

    private void leftToRight() {
        while(dropUpdatesDone >= updatesToNextDrop){
            dropUpdatesDone = dropUpdatesDone - updatesToNextDrop;
            //paint a new drop
            int position = (int) Math.floor(Math.random()*internalBufferHeight);
            nextColor = ((nextColor+1) % colorMap.size());
            internalBuffer[position * internalBufferWidth] = this.getColor(nextColor);
        }
        while(shiftUpdatesDone >= updatesToNextShift){
            shiftUpdatesDone = shiftUpdatesDone - updatesToNextShift;
            //shift the buffer down, keep first line
            for(int i = internalBufferWidth-1; i >= 1; i--){
                for(int n = 0; n < internalBufferHeight; n++){
                    internalBuffer[n*internalBufferWidth+i] = internalBuffer[n*internalBufferWidth+i-1];
                }
            }
            //then dimm fist line
            for(int n = 0; n < internalBufferHeight; n++){
                int col = internalBuffer[n * internalBufferWidth];
                if(col == 0){
                    continue;
                }
                short red = (short) ((col >> 16) & 255);
                short green = (short) ((col >> 8) & 255);
                short blue = (short) (col & 255);
                
                red = (short) (red - (256 / lengthDrops));
                if(red < 0){
                    red = 0;
                }
                green = (short) (green - (256 / lengthDrops));
                if(green < 0){
                    green = 0;
                }
                blue = (short) (blue - (256 / lengthDrops));
                if(blue < 0){
                    blue = 0;
                }
                
                internalBuffer[n * internalBufferWidth] = (int) ((red << 16) | (green << 8) | blue);
                            
            }
        }
    }
    
    private void rightToLeft() {
        while(dropUpdatesDone >= updatesToNextDrop){
            dropUpdatesDone = dropUpdatesDone - updatesToNextDrop;
            //paint a new drop
            int position = (int) Math.floor(Math.random()*internalBufferHeight);
            nextColor = ((nextColor+1) % colorMap.size());
            internalBuffer[((position+1) * internalBufferWidth)-1] = this.getColor(nextColor);
        }
        while(shiftUpdatesDone >= updatesToNextShift){
            shiftUpdatesDone = shiftUpdatesDone - updatesToNextShift;
             
            for(int i = 1; i < internalBufferWidth; i++){
                for(int n = 0; n < internalBufferHeight; n++){
                    
                    internalBuffer[(n*internalBufferWidth)+i-1] = internalBuffer[(n*internalBufferWidth)+i];
                }
            }
            //then dimm fist line
            for(int n = 0; n < internalBufferHeight; n++){
                int col = internalBuffer[((n+1) * internalBufferWidth)-1];
                if(col == 0){
                    continue;
                }
                short red = (short) ((col >> 16) & 255);
                short green = (short) ((col >> 8) & 255);
                short blue = (short) (col & 255);
                
                red = (short) (red - (256 / lengthDrops));
                if(red < 0){
                    red = 0;
                }
                green = (short) (green - (256 / lengthDrops));
                if(green < 0){
                    green = 0;
                }
                blue = (short) (blue - (256 / lengthDrops));
                if(blue < 0){
                    blue = 0;
                }
                
                internalBuffer[((n+1) * internalBufferWidth)-1] = (int) ((red << 16) | (green << 8) | blue);
                            
            }
        }
    }

    private void topToBottom() {
        
        while(dropUpdatesDone >= updatesToNextDrop){
            dropUpdatesDone = dropUpdatesDone - updatesToNextDrop;
            //paint a new drop
            int position = (int) Math.floor(Math.random()*internalBufferWidth);
            nextColor = ((nextColor+1) % colorMap.size());
            internalBuffer[position] = this.getColor(nextColor);
        }
        while(shiftUpdatesDone >= updatesToNextShift){
            shiftUpdatesDone = shiftUpdatesDone - updatesToNextShift;
            //shift the buffer down, keep first line
            for(int i = internalBufferHeight-1; i >= 1; i--){
                for(int n = 0; n < internalBufferWidth; n++){
                    internalBuffer[i*internalBufferWidth+n] = internalBuffer[(i-1)*internalBufferWidth+n];
                }
            }
            //then dimm fist line
            for(int n = 0; n < internalBufferWidth; n++){
                int col = internalBuffer[n];
                if(col == 0){
                    continue;
                }
                short red = (short) ((col >> 16) & 255);
                short green = (short) ((col >> 8) & 255);
                short blue = (short) (col & 255);
                
                red = (short) (red - (256 / lengthDrops));
                if(red < 0){
                    red = 0;
                }
                green = (short) (green - (256 / lengthDrops));
                if(green < 0){
                    green = 0;
                }
                blue = (short) (blue - (256 / lengthDrops));
                if(blue < 0){
                    blue = 0;
                }
                
                internalBuffer[n] = (int) ((red << 16) | (green << 8) | blue);
                            
            }
        }
    }

    private void bottomToTop() {
        while(dropUpdatesDone >= updatesToNextDrop){
            dropUpdatesDone = dropUpdatesDone - updatesToNextDrop;
            //paint a new drop
            int position = (int) Math.floor(Math.random()*internalBufferWidth);
            nextColor = ((nextColor+1) % colorMap.size());
            internalBuffer[internalBuffer.length - internalBufferWidth + position] = this.getColor(nextColor);
        }
        while(shiftUpdatesDone >= updatesToNextShift){
            shiftUpdatesDone = shiftUpdatesDone - updatesToNextShift;
            //shift the buffer up, keep first line
            for(int i = 0; i < internalBufferHeight-1; i++){
                for(int n = 0; n < internalBufferWidth; n++){
                    internalBuffer[i*internalBufferWidth+n] = internalBuffer[(i+1)*internalBufferWidth+n];
                }
            }
            //then dimm fist line
            for(int n = 0; n < internalBufferWidth; n++){
                int col = internalBuffer[internalBuffer.length - internalBufferWidth + n];
                if(col == 0){
                    continue;
                }
                short red = (short) ((col >> 16) & 255);
                short green = (short) ((col >> 8) & 255);
                short blue = (short) (col & 255);
                
                red = (short) (red - (256 / lengthDrops));
                if(red < 0){
                    red = 0;
                }
                green = (short) (green - (256 / lengthDrops));
                if(green < 0){
                    green = 0;
                }
                blue = (short) (blue - (256 / lengthDrops));
                if(blue < 0){
                    blue = 0;
                }
                
                internalBuffer[internalBuffer.length - internalBufferWidth + n] = (int) ((red << 16) | (green << 8) | blue);
                            
            }
        }
    }
    
    private void calculateUpdateRates() {
        int fps = Controller.getControllerInstance().getFps();
        updatesToNextDrop = ((fps / (bpm / 60F)) / (float) dropsPerScreen);
        switch (dropDirection) {
            case LEFT_TO_RIGHT:
            case RIGHT_TO_LEFT:
                updatesToNextShift = (fps / (float)internalBufferWidth) / (bpm / 60F);
                break;
            case TOP_TO_BOTTOM:
            case BOTTOM_TO_TOP:
                updatesToNextShift = (fps / (float)internalBufferHeight) / (bpm / 60F);
                break;
        }
        
    }
}
