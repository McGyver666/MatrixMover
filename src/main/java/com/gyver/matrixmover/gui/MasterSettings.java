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

/*
 * MasterSettings.java
 *
 * Created on 19.01.2012, 09:10:02
 */
package com.gyver.matrixmover.gui;

import com.gyver.matrixmover.gui.listener.MasterSettingsListener;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

/**
 *
 * @author jonas
 */
public class MasterSettings extends javax.swing.JPanel {
    
    private MasterSettingsListener msListener = null;

    /** Creates new form MasterSettings */
    public MasterSettings() {
        initComponents();
        msListener = new MasterSettingsListener(this);
        sMasterIntensity.addChangeListener(msListener);
        tbBlackout.addActionListener(msListener);
        bSetup.addActionListener(msListener);
        pbVolumeLeft.setMinimum(0);
        pbVolumeLeft.setMaximum(80);
        pbVolumeRight.setMinimum(0);
        pbVolumeRight.setMaximum(80);
        
    }
    
    public JSlider getMasterIntensitySlider(){
        return sMasterIntensity;
    }
    
    public JToggleButton getBlackoutButton(){
        return tbBlackout;
    }
    
    public void setAudioLevel(int[] level){
        pbVolumeLeft.setValue(level[0]);
        pbVolumeRight.setValue(level[1]);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sMasterIntensity = new javax.swing.JSlider();
        sVolumeLevel = new javax.swing.JSlider();
        pbVolumeLeft = new javax.swing.JProgressBar();
        pbVolumeRight = new javax.swing.JProgressBar();
        tbBlackout = new javax.swing.JToggleButton();
        tbAudioMute = new javax.swing.JToggleButton();
        bSetup = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Master Settings"));
        setLayout(new java.awt.GridBagLayout());

        sMasterIntensity.setMaximum(255);
        sMasterIntensity.setOrientation(javax.swing.JSlider.VERTICAL);
        sMasterIntensity.setValue(255);
        sMasterIntensity.setPreferredSize(new java.awt.Dimension(34, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.1;
        add(sMasterIntensity, gridBagConstraints);

        sVolumeLevel.setOrientation(javax.swing.JSlider.VERTICAL);
        sVolumeLevel.setPreferredSize(new java.awt.Dimension(34, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(sVolumeLevel, gridBagConstraints);

        pbVolumeLeft.setOrientation(1);
        pbVolumeLeft.setValue(50);
        pbVolumeLeft.setPreferredSize(new java.awt.Dimension(14, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(pbVolumeLeft, gridBagConstraints);

        pbVolumeRight.setOrientation(1);
        pbVolumeRight.setValue(50);
        pbVolumeRight.setPreferredSize(new java.awt.Dimension(14, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(pbVolumeRight, gridBagConstraints);

        tbBlackout.setText("Black");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(tbBlackout, gridBagConstraints);

        tbAudioMute.setText("Mute");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(tbAudioMute, gridBagConstraints);

        bSetup.setText("Setup");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        add(bSetup, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSetup;
    private javax.swing.JProgressBar pbVolumeLeft;
    private javax.swing.JProgressBar pbVolumeRight;
    private javax.swing.JSlider sMasterIntensity;
    private javax.swing.JSlider sVolumeLevel;
    private javax.swing.JToggleButton tbAudioMute;
    private javax.swing.JToggleButton tbBlackout;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JButton getSetupButton() {
        return bSetup;
    }
}
