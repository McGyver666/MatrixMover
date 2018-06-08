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
package com.gyver.matrixmover.gui.listener;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.fader.Fader.FaderName;
import com.gyver.matrixmover.gui.AutoSceneCycler;
import com.gyver.matrixmover.gui.ChaseConfiguration;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.MasterPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Gyver
 */
public class MasterPanelListener implements ActionListener, ChangeListener {
    
    private MasterPanel parent = null;
    
    public MasterPanelListener(MasterPanel parent){
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(parent.getTbCross())) {
            Controller.getControllerInstance().changeFaderMode(FaderName.CROSSFADE);
        } else if (ae.getSource().equals(parent.getTbLinear())) {
            Controller.getControllerInstance().changeFaderMode(FaderName.LINEAR);
        } else if (ae.getSource().equals(parent.getTbWhite())) {
            Controller.getControllerInstance().changeFaderMode(FaderName.WHITE);
        } else if (ae.getSource().equals(parent.getTbBlack())) {
            Controller.getControllerInstance().changeFaderMode(FaderName.BLACK);
        } else if (ae.getSource().equals(parent.getbFade())) {
            try {
                Controller.getControllerInstance().autoFade(Integer.parseInt(parent.getTfFadeTime().getText()), 0);
            } catch (NumberFormatException nfe) {
                Frame.getFrameInstance().showWarning("Fadetime has to be an integer number.");
            }
            
        } else if (ae.getSource().equals(parent.getbAuto())) {
            //AutoSceneCycler.getInstance().setVisible(true);
            ChaseConfiguration.getInstance().setVisible(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        if(ce.getSource().equals(parent.getCrossfaderSlider())){
            int value = ((JSlider) ce.getSource()).getValue();
            Controller.getControllerInstance().setCrossfaderValue(value);
        }
    }
    
}
