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

import com.gyver.matrixmover.generator.Analyser;
import com.gyver.matrixmover.generator.enums.AnalyserDirection;
import com.gyver.matrixmover.generator.enums.ScrollMode;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.ColorMapDialog;
import com.gyver.matrixmover.gui.component.JTextFieldSlider;
import com.gyver.matrixmover.gui.listener.interfaces.TFSListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;

/**
 * Configuration dialog for Analyser generator
 * @author Gyver
 */
public class AnalyserConfiguration extends javax.swing.JDialog {
    
    private Analyser generator = null;

    /** Creates new form ColorTableDialog */
    public AnalyserConfiguration(java.awt.Frame parent, boolean modal, Analyser generator) {
        super(parent, modal);
        this.setTitle(generator.getName().toString()+" Configuration");
        this.generator = generator;
        initComponents();
        
        tfsGain.setValue(generator.getGain());
        tfsGain.setMinimum(0);
        tfsGain.setMaximum(100);
        
        cbAnalyserDirection.setModel(new DefaultComboBoxModel(AnalyserDirection.values()));
        
        cbAnalyserDirection.setSelectedItem(generator.getAnalyserDirection());
        
        tfsGain.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsGainStateChanged(e);
            }
        });
        
        setLocationRelativeTo(null);
    }

    private void tpsGainStateChanged(ChangeEvent e){
        int gain = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setGain(gain);
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
        tfsGain = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel1 = new javax.swing.JLabel();
        cbAnalyserDirection = new javax.swing.JComboBox();
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

        tfsGain.setMaximum(6000);
        tfsGain.setMinimum(1);
        tfsGain.setValue(120);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsGain, gridBagConstraints);

        jLabel1.setText("Height:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        cbAnalyserDirection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbAnalyserDirection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAnalyserDirectionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 15);
        getContentPane().add(cbAnalyserDirection, gridBagConstraints);

        jLabel3.setText("Analyser Direction:");
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

    private void cbAnalyserDirectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAnalyserDirectionActionPerformed
        generator.setAnalyserDirection((AnalyserDirection) cbAnalyserDirection.getSelectedItem());
    }//GEN-LAST:event_cbAnalyserDirectionActionPerformed

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
    private javax.swing.JComboBox cbAnalyserDirection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsGain;
    // End of variables declaration//GEN-END:variables
}