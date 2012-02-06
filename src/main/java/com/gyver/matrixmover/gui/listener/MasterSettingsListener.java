/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.gui.listener;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.MasterSettings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jonas
 */
public class MasterSettingsListener implements ActionListener, ChangeListener {

    public MasterSettings parent = null;

    public MasterSettingsListener(MasterSettings parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(parent.getBlackoutButton())) {
            if (parent.getBlackoutButton().isSelected()) {
                Controller.getControllerInstance().setMasterIntensity(0);
            } else {
                Controller.getControllerInstance().setMasterIntensity(parent.getMasterIntensitySlider().getValue());
            }
        } else if (e.getSource().equals(parent.getSetupButton())) {
            Frame.getFrameInstance().showWarning("Not yet implemented.");
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(parent.getMasterIntensitySlider())) {
            Controller.getControllerInstance().setMasterIntensity(parent.getMasterIntensitySlider().getValue());
        }
    }
}
