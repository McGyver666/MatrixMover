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

package com.gyver.matrixmover.gui.effect;

import com.gyver.matrixmover.generator.MetaBalls;
import com.gyver.matrixmover.gui.component.JTextFieldSlider;
import com.gyver.matrixmover.gui.listener.interfaces.TFSListener;
import javax.swing.event.ChangeEvent;

/**
 * Configuration dialog for MetaBalls Generator.
 * 
 * @author Gyver
 */
public class MetaBallsConfiguration extends javax.swing.JDialog {
    
    private MetaBalls generator = null;

    /** Creates new form ColorTableDialog */
    public MetaBallsConfiguration(java.awt.Frame parent, boolean modal, MetaBalls generator) {
        super(parent, modal);
        this.setTitle(generator.getName().toString()+" Configuration");
        this.generator = generator;
        initComponents();
        
        tfsBlobCount.setValue(generator.getNumBlobs());
        tfsSpeed.setValue(generator.getSpeed());
        tfsSize.setValue(generator.getSize());
        tfsThreshold.setValue(generator.getThreshold());
        cApplyThreshold.setSelected(generator.isApplyThreshold());
        
        tfsBlobCount.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsBlobCountStateChanged(e);
            }
        });
        
        tfsSpeed.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsSpeedStateChanged(e);
            }
        });
        
        tfsSize.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsSizeStateChanged(e);
            }
        });
        
        tfsThreshold.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsThresholdStateChanged(e);
            }
        });
        
        setLocationRelativeTo(null);
    }
    
    private void tpsBlobCountStateChanged(ChangeEvent e){
        int numBlobs = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setNumBlobs(numBlobs);
    }

    private void tpsSpeedStateChanged(ChangeEvent e){
        int speed = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setSpeed(speed);
    }

    private void tpsSizeStateChanged(ChangeEvent e){
        int size = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setSize(size);
    }

    private void tpsThresholdStateChanged(ChangeEvent e){
        int threshold = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setThreshold(threshold);
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

        tfsSpeed = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        tfsBlobCount = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        tfsSize = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        tfsThreshold = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bSaveExit = new javax.swing.JButton();
        cApplyThreshold = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tfsSpeed.setMaximum(200);
        tfsSpeed.setMinimum(1);
        tfsSpeed.setValue(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsSpeed, gridBagConstraints);

        tfsBlobCount.setMaximum(20);
        tfsBlobCount.setMinimum(1);
        tfsBlobCount.setValue(3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsBlobCount, gridBagConstraints);

        tfsSize.setMaximum(5000);
        tfsSize.setMinimum(0);
        tfsSize.setValue(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsSize, gridBagConstraints);

        tfsThreshold.setMaximum(255);
        tfsThreshold.setMinimum(0);
        tfsThreshold.setValue(128);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsThreshold, gridBagConstraints);

        jLabel1.setText("Speed:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Blob Count:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Size:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel4.setText("Threshold:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel4, gridBagConstraints);

        bSaveExit.setText("Save + Exit");
        bSaveExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveExitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 10);
        getContentPane().add(bSaveExit, gridBagConstraints);

        cApplyThreshold.setText("Apply Threshold");
        cApplyThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cApplyThresholdActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(cApplyThreshold, gridBagConstraints);

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jButton1, gridBagConstraints);

        jLabel5.setText("Reset the movment vektors of all blobs.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(jLabel5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSaveExitActionPerformed

    private void cApplyThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cApplyThresholdActionPerformed
        generator.setApplyThreshold(cApplyThreshold.isSelected());
    }//GEN-LAST:event_cApplyThresholdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        generator.init();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSaveExit;
    private javax.swing.JCheckBox cApplyThreshold;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsBlobCount;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsSize;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsSpeed;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsThreshold;
    // End of variables declaration//GEN-END:variables
}
