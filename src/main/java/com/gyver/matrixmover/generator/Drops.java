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
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Gyver
 */
public class Drops extends ObjectsContainingGenerator {
    
    private MatrixData md = null;
    private DropDirection dropDirection = null;
    
    private int dropsPerScreen = 2;
    private int lengthDrops = 5;
    private double updatesToNextDrop = 0;
    private double updatesToNextShift = 0;
    private double dropUpdatesDone = 0;
    private double shiftUpdatesDone = 0;
    private int nextColor = 0;
    private int bpm = 120;

    

    
    public enum DropDirection {
        LEFT_TO_RIGHT(0),
        RIGHT_TO_LEFT(1),
        TOP_TO_BOTTOM(2),
        BOTTOM_TO_TOP(3);
        
        private int mode;

        private DropDirection(int mode) {
            this.mode = mode;
        }

        public int getMode() {
            return mode;
        }

        public static DropDirection getDropDirection(int nr) {
            for (DropDirection s : DropDirection.values()) {
                if (s.getMode() == nr) {
                    return s;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            switch (this) {
                case LEFT_TO_RIGHT:
                    return "Left to Right";
                case RIGHT_TO_LEFT:
                    return "Right to Left";
                case TOP_TO_BOTTOM:
                    return "Top to Bottom";
                case BOTTOM_TO_TOP:
                    return "Bottom to Top";
                default:
                    super.toString();
            }
            // if it has no string, return the enum-string
            return super.toString();
        }
    }
    
    public Drops(MatrixData md){
        super(GeneratorName.DROPS, md, null);
        
        this.md = md;
        dropDirection = DropDirection.TOP_TO_BOTTOM;
        
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
    
    private void leftToRight() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void rightToLeft() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void topToBottom() {
        
        while(dropUpdatesDone >= updatesToNextDrop){
            dropUpdatesDone = dropUpdatesDone - updatesToNextDrop;
            //paint a new drop
            int position = (int) Math.floor(Math.random()*md.getWidth());
            nextColor = ((nextColor+1) % colorMap.size());
            internalBuffer[position] = this.getColor(nextColor);
        }
        while(shiftUpdatesDone >= updatesToNextShift){
            shiftUpdatesDone = shiftUpdatesDone - updatesToNextShift;
            //shift the buffer down, keep first line
            for(int i = md.getHeight()-1; i >= 1; i--){
                for(int n = 0; n < md.getWidth(); n++){
                    internalBuffer[i*md.getWidth()+n] = internalBuffer[(i-1)*md.getWidth()+n];
                }
            }
            //then dimm fist line
            for(int n = 0; n < md.getWidth(); n++){
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
            int position = (int) Math.floor(Math.random()*md.getWidth());
            nextColor = ((nextColor+1) % colorMap.size());
            internalBuffer[internalBuffer.length - md.getWidth() + position] = this.getColor(nextColor);
        }
        while(shiftUpdatesDone >= updatesToNextShift){
            shiftUpdatesDone = shiftUpdatesDone - updatesToNextShift;
            //shift the buffer up, keep first line
            for(int i = 0; i < md.getHeight()-1; i++){
                for(int n = 0; n < md.getWidth(); n++){
                    internalBuffer[i*md.getWidth()+n] = internalBuffer[(i+1)*md.getWidth()+n];
                }
            }
            //then dimm fist line
            for(int n = 0; n < md.getWidth(); n++){
                int col = internalBuffer[internalBuffer.length - md.getWidth() + n];
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
                
                internalBuffer[internalBuffer.length - md.getWidth() + n] = (int) ((red << 16) | (green << 8) | blue);
                            
            }
        }
    }
    
    private void calculateUpdateRates() {
        int fps = Controller.getControllerInstance().getFps();
        updatesToNextDrop = ((fps / (bpm / 60F)) / (float) dropsPerScreen);
        switch (dropDirection) {
            case LEFT_TO_RIGHT:
            case RIGHT_TO_LEFT:
                updatesToNextShift = (fps / (float)md.getWidth()) / (bpm / 60F);
                break;
            case TOP_TO_BOTTOM:
            case BOTTOM_TO_TOP:
                updatesToNextShift = (fps / (float)md.getHeight()) / (bpm / 60F);
                break;
        }
        
    }
    
    public void setBpm(int bpm){
        this.bpm = bpm;
        calculateUpdateRates();
    }
    
    public int getBpm(){
        return bpm;
    }
    
    public void setDrosPerScreen(int nrDrops){
        this.dropsPerScreen = nrDrops;
        calculateUpdateRates();
    }
    
    public int getDropsPerScreen(){
        return dropsPerScreen;
    }
    
    public void setDropLength(int lengthDrops){
        this.lengthDrops = lengthDrops;
    }
    
    public int getDropLength(){
        return lengthDrops;
    }
    
    public DropDirection getMode(){
        return dropDirection;
    }
    
    public void setMode(DropDirection dir){
        this.dropDirection = dir;
    }
    
}
