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
package com.gyver.matrixmover.core.timer;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.gui.Frame;
import java.util.TimerTask;

/**
 * The ExecutionTimerTask is called for every frame and hits the controller
 * to calculate the output.
 * 
 * @author Gyver
 */
public class FadeTimerTask extends TimerTask {
    
    private Controller controller = null;
    private int[] fadeSteps = null;
    private int currentStep = 0;
    
    public FadeTimerTask(Controller controller, int[] fadeSteps){
        this.controller = controller;
        this.fadeSteps = fadeSteps;
    }

    @Override
    public void run() {
        controller.setCrossfaderValue(fadeSteps[currentStep]);
        Frame.getFrameInstance().getMasterPanel().getSFadePosition().setValue(fadeSteps[currentStep]);
        currentStep++;
        if(currentStep >= fadeSteps.length) {
            controller.setIsFading(false);
            this.cancel();
        }
    }
    
}
