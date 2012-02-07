/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ColorTableDialog.java
 *
 * Created on 20.01.2012, 10:07:13
 */
package com.gyver.matrixmover.gui.effect;

import com.gyver.matrixmover.generator.Fire;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.ColorMapDialog;
import com.gyver.matrixmover.gui.component.JTextFieldSlider;
import com.gyver.matrixmover.gui.listener.interfaces.TFSListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Jonas
 */
public class FireConfiguration extends javax.swing.JDialog {
    
    private Fire generator = null;

    /** Creates new form ColorTableDialog */
    public FireConfiguration(java.awt.Frame parent, boolean modal, Fire generator) {
        super(parent, modal);
        this.setTitle(generator.getName().toString()+" Configuration");
        this.generator = generator;
        initComponents();
        
        tfsDecay.setValue(generator.getDecay());
        
        tfsIntensity.setValue(generator.getIntensity());
        tfsSpeed.setValue((int) (generator.getSpeed()*100));
        
        tfsDecay.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsDecayStateChanged(e);
            }
        });
        
        tfsIntensity.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsIntensityStateChanged(e);
            }
        });
        
        tfsSpeed.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsSpeedStateChanged(e);
            }
        });
        
        centerWindow();
    }

    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }
    
    private void tpsDecayStateChanged(ChangeEvent e){
        int decay = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setDecay(decay);
    }

    private void tpsSpeedStateChanged(ChangeEvent e){
        float speed = ((JTextFieldSlider) e.getSource()).getValue() / 100F;
        generator.setSpeed(speed);
    }

    private void tpsIntensityStateChanged(ChangeEvent e){
        int intensity = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setIntensity(intensity);
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
        tfsIntensity = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        tfsDecay = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bSaveExit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tfsSpeed = new com.gyver.matrixmover.gui.component.JTextFieldSlider();

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

        tfsIntensity.setMaximum(16);
        tfsIntensity.setMinimum(0);
        tfsIntensity.setValue(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsIntensity, gridBagConstraints);

        tfsDecay.setMaximum(10);
        tfsDecay.setMinimum(1);
        tfsDecay.setValue(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsDecay, gridBagConstraints);

        jLabel1.setText("Intensity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Decay:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel2, gridBagConstraints);

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

        jLabel3.setText("Speed:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel3, gridBagConstraints);

        tfsSpeed.setMaximum(100);
        tfsSpeed.setMinimum(1);
        tfsSpeed.setValue(80);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsSpeed, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsDecay;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsIntensity;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsSpeed;
    // End of variables declaration//GEN-END:variables
}
