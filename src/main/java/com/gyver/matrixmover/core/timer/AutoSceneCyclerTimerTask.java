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
import com.gyver.matrixmover.gui.AutoSceneCycler;
import com.gyver.matrixmover.gui.Frame;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ExecutionTimerTask is called for every frame and hits the controller
 * to calculate the output.
 * 
 * @author Gyver
 */
public class AutoSceneCyclerTimerTask extends TimerTask {

    private Controller controller = null;
    private int[] leftSceneList = {1, 2, 3, 4};
    private int[] rightSceneList = {1, 2, 3, 4};
    private int currentLeft = 0;
    private int currentRight = 0;
    private boolean left = true;

    public AutoSceneCyclerTimerTask(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        int currentPosition = Frame.getFrameInstance().getMasterPanel().getSFadePosition().getValue();
        if (currentPosition < 500) {
            left = true;
        } else {
            left = false;
        }
        
        if (left) {
            try {
                Controller.getControllerInstance().autoFade(Integer.parseInt(Frame.getFrameInstance().getMasterPanel().getTfFadeTime().getText()));
            } catch (NumberFormatException nfe) {
                this.cancel();
                Frame.getFrameInstance().showWarning("Fadetime has to be an integer number.");
                AutoSceneCycler.getInstance().setTextStopped();
                return;
            }
            left = false;

            //wait for fade, then change left current scene!
            try {
                Thread.sleep(Integer.parseInt(Frame.getFrameInstance().getMasterPanel().getTfFadeTime().getText()) + 100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSceneCyclerTimerTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            currentLeft++;
            if (currentLeft >= leftSceneList.length) {
                currentLeft = 0;
            }
            controller.sceneSelected(leftSceneList[currentLeft], Controller.BOTTOM_SIDE);

        } else {
            try {
                Controller.getControllerInstance().autoFade(Integer.parseInt(Frame.getFrameInstance().getMasterPanel().getTfFadeTime().getText()));
            } catch (NumberFormatException nfe) {
                this.cancel();
                Frame.getFrameInstance().showWarning("Fadetime has to be an integer number.");
                AutoSceneCycler.getInstance().setTextStopped();
                return;
            }
            left = true;

            //wait for fade, then change right current scene!
            try {
                Thread.sleep(Integer.parseInt(Frame.getFrameInstance().getMasterPanel().getTfFadeTime().getText()) + 100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSceneCyclerTimerTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            currentRight++;
            if (currentRight >= rightSceneList.length) {
                currentRight = 0;
            }
            controller.sceneSelected(rightSceneList[currentRight], Controller.TOP_SIDE);
        }

    }

    public void setLeftSceneListFromString(String leftList) throws NumberFormatException {
        leftList = leftList.replaceAll(" ", "");
        String[] rawArray = leftList.split(",");
        leftSceneList = new int[rawArray.length];
        for (int i = 0; i < leftSceneList.length; i++) {
            int number = Integer.parseInt(rawArray[i]);
            if (number < 1 || number > 48){
                throw new NumberFormatException();
            }
            leftSceneList[i] = number;
        }
    }

    public void setRightSceneListFromString(String rightList) throws NumberFormatException {
        rightList = rightList.replaceAll(" ", "");
        String[] rawArray = rightList.split(",");
        rightSceneList = new int[rawArray.length];
        for (int i = 0; i < rightSceneList.length; i++) {
            int number = Integer.parseInt(rawArray[i]);
            if (number < 1 || number > 48){
                throw new NumberFormatException();
            }
            rightSceneList[i] = number;
        }
    }
}
