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

package com.gyver.matrixmover.gui.generator;

import com.gyver.matrixmover.generator.ColorScroll;
import com.gyver.matrixmover.generator.enums.ScrollMode;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.ColorMapDialog;
import com.gyver.matrixmover.gui.component.JTextFieldSlider;
import com.gyver.matrixmover.gui.listener.interfaces.TFSListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;

/**
 * Configuration dialog for ColorScroll generator
 * @author Gyver
 */
public class ColorScrollConfiguration extends javax.swing.JDialog {
    
    private ColorScroll generator = null;

    /** Creates new form ColorTableDialog */
    public ColorScrollConfiguration(java.awt.Frame parent, boolean modal, ColorScroll generator) {
        super(parent, modal);
        this.setTitle(generator.getName().toString()+" Configuration");
        this.generator = generator;
        initComponents();
        
        tfsDistance.setValue(generator.getFadeLength());
        
        tfsSpeed.setValue(generator.getSpeed());
        
        cbScrollMode.setModel(new DefaultComboBoxModel(ScrollMode.values()));
        
        cbScrollMode.setSelectedItem(generator.getScrollMode());
        
        tfsDistance.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsDistanceStateChanged(e);
            }
        });
        
        tfsSpeed.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsSpeedStateChanged(e);
            }
        });
        
        setLocationRelativeTo(null);
    }

    private void tpsDistanceStateChanged(ChangeEvent e){
        int distance = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setFadeLength(distance);
    }

    private void tpsSpeedStateChanged(ChangeEvent e){
        int speed = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setSpeed(speed);
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

        bColorMap = new javax.swing.JButton();
        tfsSpeed = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        tfsDistance = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbScrollMode = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        bSaveExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        bColorMap.setText("Color Map");
        bColorMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bColorMapActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        getContentPane().add(bColorMap, gridBagConstraints);

        tfsSpeed.setMaximum(6000);
        tfsSpeed.setMinimum(1);
        tfsSpeed.setValue(120);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsSpeed, gridBagConstraints);

        tfsDistance.setMaximum(100);
        tfsDistance.setMinimum(1);
        tfsDistance.setValue(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsDistance, gridBagConstraints);

        jLabel1.setText("BPM:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Distance:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel2, gridBagConstraints);

        cbScrollMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbScrollMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbScrollModeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 15);
        getContentPane().add(cbScrollMode, gridBagConstraints);

        jLabel3.setText("Scroll Direction:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        getContentPane().add(jLabel3, gridBagConstraints);

        bSaveExit.setText("Save + Exit");
        bSaveExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveExitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 10);
        getContentPane().add(bSaveExit, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbScrollModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbScrollModeActionPerformed
        generator.setScrollMode((ScrollMode) cbScrollMode.getSelectedItem());
    }//GEN-LAST:event_cbScrollModeActionPerformed

    private void bColorMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bColorMapActionPerformed
        ColorMapDialog cmDialog = new ColorMapDialog(Frame.getFrameInstance(), true, generator.getColorMap());
        cmDialog.setVisible(true);
        generator.setColorMap(cmDialog.getColorMap());
    }//GEN-LAST:event_bColorMapActionPerformed

    private void bSaveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSaveExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bColorMap;
    private javax.swing.JButton bSaveExit;
    private javax.swing.JComboBox cbScrollMode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsDistance;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsSpeed;
    // End of variables declaration//GEN-END:variables
}