/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SimpleColorConfiguration.java
 *
 * Created on 22.01.2012, 21:07:14
 */
package com.gyver.matrixmover.gui.effect;

import com.gyver.matrixmover.generator.Generator.GeneratorName;
import com.gyver.matrixmover.generator.SimpleColorGenerator;
import com.gyver.matrixmover.gui.listener.interfaces.TFSListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Jonas
 */
public class SimpleColorConfiguration extends javax.swing.JDialog {

    public SimpleColorGenerator generator = null;

    /** Creates new form SimpleColorConfiguration */
    public SimpleColorConfiguration(java.awt.Frame parent, boolean modal, SimpleColorGenerator generator) {
        super(parent, modal);
        this.setTitle(GeneratorName.STRING_SIMPLE_COLOR_GENERATOR+" Configuration");
        this.generator = generator;
        initComponents();
        
        tfsRed.setMinimum(0);
        tfsRed.setMaximum(255);
        tfsGreen.setMinimum(0);
        tfsGreen.setMaximum(255);
        tfsBlue.setMinimum(0);
        tfsBlue.setMaximum(255);
        
        tfsRed.setValue(generator.getColor().getRed());
        tfsGreen.setValue(generator.getColor().getGreen());
        tfsBlue.setValue(generator.getColor().getBlue());
        
        tfsRed.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeRed(tfsRed.getValue());
            }
        });
        
        
        tfsGreen.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeGreen(tfsGreen.getValue());
            }
        });
        
        tfsBlue.addTFSListener(new TFSListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeBlue(tfsBlue.getValue());
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
    
    private void changeRed(int red){
        generator.setColor(new Color(red, generator.getColor().getGreen(), generator.getColor().getBlue()));
    }

    private void changeGreen(int green){
        generator.setColor(new Color(generator.getColor().getRed(), green, generator.getColor().getBlue()));
    }

    private void changeBlue(int blue){
        generator.setColor(new Color(generator.getColor().getRed(), generator.getColor().getGreen(), blue));
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

        tfsRed = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        tfsGreen = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        tfsBlue = new com.gyver.matrixmover.gui.component.JTextFieldSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bSaveExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        getContentPane().add(tfsRed, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        getContentPane().add(tfsGreen, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        getContentPane().add(tfsBlue, gridBagConstraints);

        jLabel1.setText("Red:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Green:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Blue:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 10);
        getContentPane().add(bSaveExit, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSaveExitActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSaveExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsBlue;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsGreen;
    private com.gyver.matrixmover.gui.component.JTextFieldSlider tfsRed;
    // End of variables declaration//GEN-END:variables
}
