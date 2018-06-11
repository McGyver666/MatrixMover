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

import com.gyver.matrixmover.core.ChaseItem;
import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.SceneReader;
import com.gyver.matrixmover.core.VisualSetup;
import com.gyver.matrixmover.gui.AutoSceneCycler;
import com.gyver.matrixmover.gui.Frame;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ExecutionTimerTask is called for every frame and hits the controller
 * to calculate the output.
 * 
 * @author Gyver
 */
public class ChaserTimerTask extends TimerTask {

    private Controller controller = null;
    private boolean left = true;
    private boolean firstRun = false;
    private ArrayList<ChaseItem> chaseList;
    private int currentChaseListIndex = 0;
    private Timer timer;
    
    
    public ChaserTimerTask(Controller controller, ArrayList<ChaseItem> chaseList, boolean firstRun, Timer timer, int currentChaseListIndex, boolean left) {
        this.controller = controller;
        this.chaseList = chaseList;
        this.firstRun = firstRun;
        this.timer = timer;
        this.currentChaseListIndex = currentChaseListIndex;
        this.left = left;
    }

    @Override
    public void run() {
        
        //set next timer target
        int pauseTimeForNextVisual = (int)(chaseList.get(currentChaseListIndex).sceneTime*1000);
        System.out.println("Time until next visual: "+pauseTimeForNextVisual);
        
        int nextChaseListIndex = currentChaseListIndex + 1;
        if (nextChaseListIndex >= chaseList.size()) {
            nextChaseListIndex = 0;
        }
        Controller.getControllerInstance().getChaseControllerInstance().setTimerForNextChaseStep(pauseTimeForNextVisual, nextChaseListIndex, !left);
        
        
        // if we run the first time: load the first scene before fading into the target visual setup!
        /*if (firstRun) {
            File sceneFile = new File(chaseList.get(currentChaseListIndex).sceneFileName);
            VisualSetup vstmp = SceneReader.loadVisualSetup(sceneFile, Controller.getControllerInstance().getMatrixData());
            if (!left) {
                controller.setVisualSetup(vstmp, Controller.LEFT_SIDE);
            } else { 
                controller.setVisualSetup(vstmp, Controller.RIGHT_SIDE);
            }
        }*/
        
        File sceneFile = new File(chaseList.get(currentChaseListIndex).sceneFileName);
        VisualSetup vs = SceneReader.loadVisualSetup(sceneFile, Controller.getControllerInstance().getMatrixData());
            
        if (left) {
            controller.setVisualSetup(vs, Controller.RIGHT_SIDE);
        } else {
            controller.setVisualSetup(vs, Controller.LEFT_SIDE);
        }
            
        
        // do a fade in to the 
        int fadeTimeMS = (int)(chaseList.get(currentChaseListIndex).fadeInTime*1000);
        if(left){
            Controller.getControllerInstance().autoFade(fadeTimeMS, 1000);
            left = false;
        } else {
            Controller.getControllerInstance().autoFade(fadeTimeMS, 0);
            left = true;
        }
        
//        try {
//            Thread.sleep(fadeTimeMS + 100);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ChaserTimerTask.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        timer.cancel();
    }
}
