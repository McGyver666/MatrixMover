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

import com.gyver.matrixmover.generator.Generator.GeneratorName;
import com.gyver.matrixmover.generator.Shapes;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.ColorMapDialog;
import com.gyver.matrixmover.gui.component.JTextFieldSlider;
import com.gyver.matrixmover.gui.listener.interfaces.TFSListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Gyver
 */
public class ShapesConfiguration extends javax.swing.JDialog {
    
    private Shapes generator = null;

    /** Creates new form ColorTableDialog */
    public ShapesConfiguration(java.awt.Frame parent, boolean modal, Shapes generator) {
        super(parent, modal);
        this.setTitle(GeneratorName.STRING_COLOR_SCROLL+" Configuration");
        this.generator = generator;
        initComponents();
        
        tfsSpeed.setValue(generator.getSpeed());
        tfsCount.setValue(generator.getObjectCount());
        tfsSize.setValue(generator.getSize());
        tfsAlive.setValue(generator.getAlive());
        tfsFade.setValue(generator.getFade());
        tfsExpand.setValue(generator.getExpand());
        
        
        cbObjectShape.setModel(new DefaultComboBoxModel(Shapes.ObjectShape.values()));
        cbObjectShape.setSelectedItem(generator.getObjectShape());
        
        cbObjectDirection.setModel(new DefaultComboBoxModel(Shapes.ObjectDirection.values()));
        cbObjectDirection.setSelectedItem(generator.getObjectDirection());
        
        tfsSpeed.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsSpeedStateChanged(e);
            }
        });
        
        tfsCount.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsCountStateChanged(e);
            }
        });
        
        tfsSize.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsSizeStateChanged(e);
            }
        });
        
        tfsAlive.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tpsAliveStateChanged(e);
            }
        });
        
        tfsFade.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tfsFadeStateChanged(e);
            }
        });
        
        tfsExpand.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tfsExpandStateChanged(e);
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
    
    private void tpsSpeedStateChanged(ChangeEvent e){
        int speed = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setSpeed(speed);
    }

    private void tpsCountStateChanged(ChangeEvent e){
        int count = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setObbjectCount(count);
    }

    private void tpsSizeStateChanged(ChangeEvent e){
        int size = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setSize(size);
    }

    private void tpsAliveStateChanged(ChangeEvent e){
        int alive = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setAlive(alive);
    }

    private void tfsFadeStateChanged(ChangeEvent e){
        int fade = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setFade(fade);
    }

    private void tfsExpandStateChanged(ChangeEvent e){
        int expand = ((JTextFieldSlider) e.getSource()).getValue();
        generator.setExpand(expand);
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
        tfsSize = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbObjectShape = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        bSaveExit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbObjectDirection = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        tfsAlive = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel6 = new javax.swing.JLabel();
        tfsFade = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel7 = new javax.swing.JLabel();
        tfsExpand = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        tfsCount = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsSpeed, gridBagConstraints);

        tfsSize.setMaximum(50);
        tfsSize.setMinimum(1);
        tfsSize.setValue(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsSize, gridBagConstraints);

        jLabel1.setText("BPM:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Size:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel2, gridBagConstraints);

        cbObjectShape.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbObjectShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbObjectShapeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 15);
        getContentPane().add(cbObjectShape, gridBagConstraints);

        jLabel3.setText("Shape:");
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 10);
        getContentPane().add(bSaveExit, gridBagConstraints);

        jLabel4.setText("Mode:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        getContentPane().add(jLabel4, gridBagConstraints);

        cbObjectDirection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbObjectDirection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbObjectDirectionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 15);
        getContentPane().add(cbObjectDirection, gridBagConstraints);

        jLabel5.setText("Lifetime:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel5, gridBagConstraints);

        tfsAlive.setMaximum(250);
        tfsAlive.setMinimum(1);
        tfsAlive.setValue(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsAlive, gridBagConstraints);

        jLabel6.setText("Fadetime:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel6, gridBagConstraints);

        tfsFade.setMaximum(250);
        tfsFade.setMinimum(1);
        tfsFade.setValue(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsFade, gridBagConstraints);

        jLabel7.setText("Expansion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel7, gridBagConstraints);

        tfsExpand.setMaximum(50);
        tfsExpand.setMinimum(0);
        tfsExpand.setValue(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsExpand, gridBagConstraints);

        tfsCount.setMaximum(20);
        tfsCount.setMinimum(1);
        tfsCount.setValue(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(tfsCount, gridBagConstraints);

        jLabel8.setText("Count:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        getContentPane().add(jLabel8, gridBagConstraints);

        jCheckBox1.setText("Random Color");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 0);
        getContentPane().add(jCheckBox1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbObjectShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbObjectShapeActionPerformed
        generator.setObjectShape((Shapes.ObjectShape) cbObjectShape.getSelectedItem());
    }//GEN-LAST:event_cbObjectShapeActionPerformed

    private void bColorMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bColorMapActionPerformed
        ColorMapDialog cmDialog = new ColorMapDialog(Frame.getFrameInstance(), true, generator.getColorMap());
        cmDialog.setVisible(true);
        generator.setColorMap(cmDialog.getColorMap());
    }//GEN-LAST:event_bColorMapActionPerformed

    private void bSaveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSaveExitActionPerformed

    private void cbObjectDirectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbObjectDirectionActionPerformed
        generator.setObjectDirection((Shapes.ObjectDirection) cbObjectDirection.getSelectedItem());
    }//GEN-LAST:event_cbObjectDirectionActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        generator.setRandom(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bColorMap;
    private javax.swing.JButton bSaveExit;
    private javax.swing.JComboBox cbObjectDirection;
    private javax.swing.JComboBox cbObjectShape;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsAlive;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsCount;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsExpand;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsFade;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsSize;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsSpeed;
    // End of variables declaration//GEN-END:variables
}