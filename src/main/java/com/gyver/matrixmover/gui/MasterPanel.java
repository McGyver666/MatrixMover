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
 * MasterPanel.java
 *
 * Created on 17.01.2012, 10:00:05
 */
package com.gyver.matrixmover.gui;

import com.gyver.matrixmover.gui.listener.MasterPanelListener;
import javax.swing.ButtonGroup;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

/**
 *
 * @author Gyver
 */
public class MasterPanel extends javax.swing.JPanel {

    private MasterPanelListener mpListener = null;
    private ButtonGroup tbGroup = null;
    
    /** Creates new form MasterPanel */
    public MasterPanel() {
        initComponents();
        mpListener = new MasterPanelListener(this);
        jSlider1.addChangeListener(mpListener);
        tbGroup = new ButtonGroup();
        tbGroup.add(tbBlack);
        tbGroup.add(tbWhite);
        tbGroup.add(tbCross);
        tbGroup.add(tbLinear);
        
        tbBlack.addActionListener(mpListener);
        tbWhite.addActionListener(mpListener);
        tbCross.addActionListener(mpListener);
        tbLinear.addActionListener(mpListener);
        
    }
    
    public JSlider getCrossfaderSlider(){
        return jSlider1;
    }
    
    public LedScreen getLedScreen(){
        return ledScreen1;
    }
    
    public void setSelectedButton(JToggleButton button){
        tbGroup.setSelected(button.getModel(), true);
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

        ledScreen1 = new com.gyver.matrixmover.gui.LedScreen();
        jSlider1 = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        tbLinear = new javax.swing.JToggleButton();
        tbCross = new javax.swing.JToggleButton();
        tbWhite = new javax.swing.JToggleButton();
        tbBlack = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        bFade = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        ledScreen1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout ledScreen1Layout = new javax.swing.GroupLayout(ledScreen1);
        ledScreen1.setLayout(ledScreen1Layout);
        ledScreen1Layout.setHorizontalGroup(
            ledScreen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );
        ledScreen1Layout.setVerticalGroup(
            ledScreen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(ledScreen1, gridBagConstraints);

        jSlider1.setMajorTickSpacing(1000);
        jSlider1.setMaximum(1000);
        jSlider1.setMinorTickSpacing(250);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(jSlider1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        tbLinear.setText("Linear");
        tbLinear.setMargin(new java.awt.Insets(0, -2, 0, -2));
        tbLinear.setMaximumSize(new java.awt.Dimension(55, 30));
        tbLinear.setMinimumSize(new java.awt.Dimension(55, 30));
        tbLinear.setPreferredSize(new java.awt.Dimension(55, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(tbLinear, gridBagConstraints);

        tbCross.setText("Cross");
        tbCross.setMargin(new java.awt.Insets(0, -2, 0, -2));
        tbCross.setMaximumSize(new java.awt.Dimension(55, 30));
        tbCross.setMinimumSize(new java.awt.Dimension(55, 30));
        tbCross.setPreferredSize(new java.awt.Dimension(55, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(tbCross, gridBagConstraints);

        tbWhite.setText("White");
        tbWhite.setMargin(new java.awt.Insets(0, -2, 0, -2));
        tbWhite.setMaximumSize(new java.awt.Dimension(55, 30));
        tbWhite.setMinimumSize(new java.awt.Dimension(55, 30));
        tbWhite.setPreferredSize(new java.awt.Dimension(55, 30));
        tbWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbWhiteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(tbWhite, gridBagConstraints);

        tbBlack.setText("Black");
        tbBlack.setMargin(new java.awt.Insets(0, -2, 0, -2));
        tbBlack.setMaximumSize(new java.awt.Dimension(55, 30));
        tbBlack.setMinimumSize(new java.awt.Dimension(55, 30));
        tbBlack.setPreferredSize(new java.awt.Dimension(55, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(tbBlack, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        add(jPanel1, gridBagConstraints);

        jLabel1.setText("Time (ms)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        add(jLabel1, gridBagConstraints);

        jTextField1.setText("10900");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        add(jTextField1, gridBagConstraints);

        bFade.setText("Fade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        add(bFade, gridBagConstraints);

        jButton6.setText("Auto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        add(jButton6, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void tbWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbWhiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbWhiteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bFade;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1;
    private com.gyver.matrixmover.gui.LedScreen ledScreen1;
    private javax.swing.JToggleButton tbBlack;
    private javax.swing.JToggleButton tbCross;
    private javax.swing.JToggleButton tbLinear;
    private javax.swing.JToggleButton tbWhite;
    // End of variables declaration//GEN-END:variables

    void recomputeLedPixelSize() {
        ledScreen1.recomputePixelSize();
        ledScreen1.repaint();
    }

    /**
     * @return the tbBlack
     */
    public javax.swing.JToggleButton getTbBlack() {
        return tbBlack;
    }

    /**
     * @return the tbCross
     */
    public javax.swing.JToggleButton getTbCross() {
        return tbCross;
    }

    /**
     * @return the tbLinear
     */
    public javax.swing.JToggleButton getTbLinear() {
        return tbLinear;
    }

    /**
     * @return the tbWhite
     */
    public javax.swing.JToggleButton getTbWhite() {
        return tbWhite;
    }
}
