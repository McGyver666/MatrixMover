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
package com.gyver.matrixmover.fader;

/**
 * The CrossFader.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class CrossFader extends Fader {

    /**
     * Instantiates a new crossfader.
     */
    public CrossFader() {
        super(FaderName.CROSSFADE);
    }

    @Override
    public int[] fade(int[] left, int[] right, int position) {
        
        short leftBlue, leftGreen, leftRed, rightBlue, rightGreen, rightRed;
        
        int[] mix = new int[left.length];
        
        float ratio = (position / 1000f);
        
        int col;
        
        for(int i = 0; i < left.length; i++){
            col = left[i];
            leftRed = (short) ((col >> 16) & 255);
            leftGreen = (short) ((col >> 8) & 255);
            leftBlue = (short) (col & 255);

            col = right[i];
            rightRed = (short) ((col >> 16) & 255);
            rightGreen = (short) ((col >> 8) & 255);
            rightBlue = (short) (col & 255);    
            
            
            
            rightRed = (short) (Math.round((leftRed - rightRed) * ratio));
            rightGreen = (short) (Math.round((leftGreen - rightGreen) * ratio));
            rightBlue = (short) (Math.round((leftBlue - rightBlue) * ratio));
            
            rightRed = (short) (leftRed - rightRed);
            rightGreen = (short) (leftGreen - rightGreen);
            rightBlue = (short) (leftBlue - rightBlue);
            
                
//            rightRed = (short) (leftRed - Math.round((leftRed - rightRed) * ratio));
//            rightGreen = (short) (leftGreen - Math.round((leftGreen - rightGreen) * ratio));
//            rightBlue = (short) (leftBlue - Math.round((leftBlue - rightBlue) * ratio));
                
//            short r = leftRed - (int) Math.round((leftRed - rightRed) * ratio);
//            short g = leftGreen - (int) Math.round((leftGreen - rightGreen) * ratio);
//            short b = leftBlue - (int) Math.round((leftBlue - rightBlue) * ratio);

            mix[i] = (rightRed << 16) | (rightGreen << 8) | rightBlue;
        }
        return mix;
    }
}