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
import com.gyver.matrixmover.core.SceneReader;
import com.gyver.matrixmover.core.VisualSetup;
import com.gyver.matrixmover.gui.AutoSceneCycler;
import com.gyver.matrixmover.gui.Frame;
import java.io.File;
import java.io.FilenameFilter;
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
    private File[] sceneFiles = null;
    private int sceneFileListIndex = 0;
    int currentPosition = 0;
    private boolean left = true;
    private boolean firstRun = true;
    private final int STAND_ALONE_FADE_TIME = 2000;
    public AutoSceneCyclerTimerTask(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        
        if (!Controller.getControllerInstance().isPlayer()) {
            currentPosition = Frame.getFrameInstance().getMasterPanel().getSFadePosition().getValue();
            if (currentPosition < 500) {
                left = true;
            } else {
                left = false;
            }
        }
        
        // if we run the first time: load the first scene before fading into the target visual setup!
        if (firstRun) {
            if (!left) {
                VisualSetup vstmp = SceneReader.loadVisualSetup(sceneFiles[sceneFileListIndex], Controller.getControllerInstance().getMatrixData());
                controller.setVisualSetup(vstmp, Controller.LEFT_SIDE);
            } else { 
                VisualSetup vstmp = SceneReader.loadVisualSetup(sceneFiles[sceneFileListIndex], Controller.getControllerInstance().getMatrixData());
                controller.setVisualSetup(vstmp, Controller.RIGHT_SIDE);
            }
        }
            


        if (left) {
            if (!Controller.getControllerInstance().isPlayer()) {
                try {
                    Controller.getControllerInstance().autoFade(Integer.parseInt(Frame.getFrameInstance().getMasterPanel().getTfFadeTime().getText()), 0);
                } catch (NumberFormatException nfe) {
                    this.cancel();
                    Frame.getFrameInstance().showWarning("Fadetime has to be an integer number.");
                    AutoSceneCycler.getInstance().setTextStopped();
                    return;
                }
            } else {
                Controller.getControllerInstance().autoFade(STAND_ALONE_FADE_TIME, 0);
            }
            left = false;

            //wait for fade, then change left current scene!
            try {
                if (!Controller.getControllerInstance().isPlayer()) {
                    Thread.sleep(Integer.parseInt(Frame.getFrameInstance().getMasterPanel().getTfFadeTime().getText()) + 100);
                } else {
                    Thread.sleep(STAND_ALONE_FADE_TIME + 100);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSceneCyclerTimerTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            sceneFileListIndex++;
            if (sceneFileListIndex >= sceneFiles.length) {
                sceneFileListIndex = 0;
            }
            VisualSetup vs = SceneReader.loadVisualSetup(sceneFiles[sceneFileListIndex], Controller.getControllerInstance().getMatrixData());
            controller.setVisualSetup(vs, Controller.LEFT_SIDE);

        } else {
            if (!Controller.getControllerInstance().isPlayer()) {
                try {
                    Controller.getControllerInstance().autoFade(Integer.parseInt(Frame.getFrameInstance().getMasterPanel().getTfFadeTime().getText()), 1000);
                } catch (NumberFormatException nfe) {
                    this.cancel();
                    Frame.getFrameInstance().showWarning("Fadetime has to be an integer number.");
                    AutoSceneCycler.getInstance().setTextStopped();
                    return;
                } 
            } else {
                Controller.getControllerInstance().autoFade(STAND_ALONE_FADE_TIME, 1000);
            }
            left = true;

            //wait for fade, then change right current scene!
            try {
                if (!Controller.getControllerInstance().isPlayer()) {
                    Thread.sleep(Integer.parseInt(Frame.getFrameInstance().getMasterPanel().getTfFadeTime().getText()) + 100);
                } else {
                    Thread.sleep(STAND_ALONE_FADE_TIME + 100);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(AutoSceneCyclerTimerTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            sceneFileListIndex++;
            if (sceneFileListIndex >= sceneFiles.length) {
                sceneFileListIndex = 0;
            }
            VisualSetup vs = SceneReader.loadVisualSetup(sceneFiles[sceneFileListIndex], Controller.getControllerInstance().getMatrixData());
            controller.setVisualSetup(vs, Controller.RIGHT_SIDE);
        }

    }

    public boolean setSceneDirectory(File dir) {
        sceneFiles = dir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mms");
            }
        });

        if (sceneFiles.length == 0) {
            return false;
        }
        return true;
    }
}
