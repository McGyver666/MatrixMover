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
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

/**
 * The class ColorFase
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
     * @param controller the controller
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

    /**
     * Gets the color.
     *
     * @param val the val
     * @return the color
     */
    private int getColor() {
        int colornumber = (int) (Math.round(Math.floor(frameCount / fade)));
        colornumber = colornumber % colorMap.size();
        int nextcolornumber = (colornumber + 1) % colorMap.size();
        float ratio = (float) ((frameCount % fade) / (float) fade);
        return super.getColor(colornumber, nextcolornumber, ratio);
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
     * @param colorFadeTime the colorFadeTime to set
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
}
