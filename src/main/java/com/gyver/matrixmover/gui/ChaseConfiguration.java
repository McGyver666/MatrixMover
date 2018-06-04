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
package com.gyver.matrixmover.gui;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.timer.AutoSceneCyclerTimerTask;
import java.io.File;
import java.util.Timer;

/**
 * 
 * @author Gyver
 */
public class ChaseConfiguration extends javax.swing.JDialog {

    private static ChaseConfiguration autoSceneCycler = null;
    private Timer timer = null;
    private boolean isRunning;

    /** Make this a singelton to keep settings easily */
    private ChaseConfiguration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        isRunning = false;
        setTitle("Auto Scene Cycler");
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This is a Singelton. Returns the instance of this.
     * @return the AutoSceneCycler of this.
     */
    public static ChaseConfiguration getInstance() {
        if (autoSceneCycler == null) {
            autoSceneCycler = new ChaseConfiguration(Frame.getFrameInstance(), true);
        }
        return autoSceneCycler;
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

        jLabel1 = new javax.swing.JLabel();
        tfSecondsToWait = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfSceneDir = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lStatus = new javax.swing.JLabel();
        bStart = new javax.swing.JButton();
        bStop = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Auto Scene Cycler"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Seconds to stay on every scene:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        tfSecondsToWait.setText("60");
        tfSecondsToWait.setMaximumSize(new java.awt.Dimension(50, 22));
        tfSecondsToWait.setMinimumSize(new java.awt.Dimension(50, 22));
        tfSecondsToWait.setPreferredSize(new java.awt.Dimension(50, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        getContentPane().add(tfSecondsToWait, gridBagConstraints);

        jLabel2.setText("Directory with MatrixMover-Scene files to play:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        tfSceneDir.setText("scenes/");
        tfSceneDir.setMaximumSize(new java.awt.Dimension(300, 22));
        tfSceneDir.setMinimumSize(new java.awt.Dimension(300, 22));
        tfSceneDir.setPreferredSize(new java.awt.Dimension(300, 22));
        tfSceneDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSceneDirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 10);
        getContentPane().add(tfSceneDir, gridBagConstraints);

        jLabel4.setText("Status:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel4, gridBagConstraints);

        lStatus.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lStatus.setForeground(new java.awt.Color(255, 0, 0));
        lStatus.setText("Stopped");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 5, 0);
        getContentPane().add(lStatus, gridBagConstraints);

        bStart.setText("Start");
        bStart.setMaximumSize(new java.awt.Dimension(65, 25));
        bStart.setMinimumSize(new java.awt.Dimension(65, 25));
        bStart.setPreferredSize(new java.awt.Dimension(65, 25));
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        getContentPane().add(bStart, gridBagConstraints);

        bStop.setText("Stop");
        bStop.setMaximumSize(new java.awt.Dimension(65, 25));
        bStop.setMinimumSize(new java.awt.Dimension(65, 25));
        bStop.setPreferredSize(new java.awt.Dimension(65, 25));
        bStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStopActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        getContentPane().add(bStop, gridBagConstraints);

        bExit.setText("Hide");
        bExit.setMaximumSize(new java.awt.Dimension(65, 25));
        bExit.setMinimumSize(new java.awt.Dimension(65, 25));
        bExit.setPreferredSize(new java.awt.Dimension(65, 25));
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        getContentPane().add(bExit, gridBagConstraints);

        jLabel5.setText("(directory changes need timer restart)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 10);
        getContentPane().add(jLabel5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_bExitActionPerformed

    private void bStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartActionPerformed
        if (!isRunning) {

            File sceneDir = new File(tfSceneDir.getText());
            if (!sceneDir.isDirectory()) {
                Frame.getFrameInstance().showWarning("Incorrect direcory!");
                return;
            }

            timer = new Timer();
            AutoSceneCyclerTimerTask asctt = new AutoSceneCyclerTimerTask(Controller.getControllerInstance());
            try {
                if (!asctt.setSceneDirectory(sceneDir)) {
                    Frame.getFrameInstance().showWarning("Directory containts no scene files!");
                    return;
                }
                int timeToWait = Integer.parseInt(tfSecondsToWait.getText()) * 1000;
                timer.scheduleAtFixedRate(asctt, 0, timeToWait);
                setTextRunnig();
                isRunning = true;
            } catch (NumberFormatException nfe) {
                Frame.getFrameInstance().showWarning("Input is not valid. Check number format.");
            }
        }



    }//GEN-LAST:event_bStartActionPerformed

    private void bStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStopActionPerformed
        if (timer != null) {
            timer.cancel();
            isRunning = false;
            setTextStopped();
        }
    }//GEN-LAST:event_bStopActionPerformed

    private void tfSceneDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSceneDirActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_tfSceneDirActionPerformed

    public void setTextRunnig() {
        lStatus.setText("Running");
        lStatus.setForeground(new java.awt.Color(0, 255, 0));
    }

    public void setTextStopped() {
        lStatus.setText("Stopped");
        lStatus.setForeground(new java.awt.Color(255, 0, 0));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bExit;
    private javax.swing.JButton bStart;
    private javax.swing.JButton bStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lStatus;
    private javax.swing.JTextField tfSceneDir;
    private javax.swing.JTextField tfSecondsToWait;
    // End of variables declaration//GEN-END:variables
}