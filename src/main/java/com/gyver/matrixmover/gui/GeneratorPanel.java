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
 * GeneratorPanel.java
 *
 * Created on 17.01.2012, 09:59:43
 */
package com.gyver.matrixmover.gui;

import com.gyver.matrixmover.core.VisualSetup;
import com.gyver.matrixmover.gui.listener.SceneButtonListener;

/**
 *
 * @author jonas
 */
public class GeneratorPanel extends javax.swing.JPanel {
    
    private int side = 0;
    
    SceneButtonListener buttonListener = null;

    /** Creates new form GeneratorPanel */
    public GeneratorPanel() {
        initComponents();
    }
    
    public void recomputeLedPixelSize(){
        ledScreen1.recomputePixelSize();
        ledScreen1.repaint();
    }
    
    public LedScreen getLedScreen(){
        return ledScreen1;
    }
    
    public void setButtonActive(int buttonNumber, boolean active){
        switch(buttonNumber){
            case 1: bScene1.setActive(active); break;
            case 2: bScene2.setActive(active); break;
            case 3: bScene3.setActive(active); break;
            case 4: bScene4.setActive(active); break;
            case 5: bScene5.setActive(active); break;
            case 6: bScene6.setActive(active); break;
            case 7: bScene7.setActive(active); break;
            case 8: bScene8.setActive(active); break;
            case 9: bScene9.setActive(active); break;
            case 10: bScene10.setActive(active); break;
            case 11: bScene11.setActive(active); break;
            case 12: bScene12.setActive(active); break;
            case 13: bScene13.setActive(active); break;
            case 14: bScene14.setActive(active); break;
            case 15: bScene15.setActive(active); break;
            case 16: bScene16.setActive(active); break;
            case 17: bScene17.setActive(active); break;
            case 18: bScene18.setActive(active); break;
            case 19: bScene19.setActive(active); break;
            case 20: bScene20.setActive(active); break;
            case 21: bScene21.setActive(active); break;
            case 22: bScene22.setActive(active); break;
            case 23: bScene23.setActive(active); break;
            case 24: bScene24.setActive(active); break;
            case 25: bScene25.setActive(active); break;
            case 26: bScene26.setActive(active); break;
            case 27: bScene27.setActive(active); break;
            case 28: bScene28.setActive(active); break;
            case 29: bScene29.setActive(active); break;
            case 30: bScene30.setActive(active); break;
            case 31: bScene31.setActive(active); break;
            case 32: bScene32.setActive(active); break;
            case 33: bScene33.setActive(active); break;
            case 34: bScene34.setActive(active); break;
            case 35: bScene35.setActive(active); break;
            case 36: bScene36.setActive(active); break;
            case 37: bScene37.setActive(active); break;
            case 38: bScene38.setActive(active); break;
            case 39: bScene39.setActive(active); break;
            case 40: bScene40.setActive(active); break;
            case 41: bScene41.setActive(active); break;
            case 42: bScene42.setActive(active); break;
            case 43: bScene43.setActive(active); break;
            case 44: bScene44.setActive(active); break;
            case 45: bScene45.setActive(active); break;
            case 46: bScene46.setActive(active); break;
            case 47: bScene47.setActive(active); break;
            case 48: bScene48.setActive(active); break;
        }
    }
    
    public void setButtonChanged(int buttonNumber, boolean changed){
        switch(buttonNumber){
            case 1: bScene1.setChanged(changed); break;
            case 2: bScene2.setChanged(changed); break;
            case 3: bScene3.setChanged(changed); break;
            case 4: bScene4.setChanged(changed); break;
            case 5: bScene5.setChanged(changed); break;
            case 6: bScene6.setChanged(changed); break;
            case 7: bScene7.setChanged(changed); break;
            case 8: bScene8.setChanged(changed); break;
            case 9: bScene9.setChanged(changed); break;
            case 10: bScene10.setChanged(changed); break;
            case 11: bScene11.setChanged(changed); break;
            case 12: bScene12.setChanged(changed); break;
            case 13: bScene13.setChanged(changed); break;
            case 14: bScene14.setChanged(changed); break;
            case 15: bScene15.setChanged(changed); break;
            case 16: bScene16.setChanged(changed); break;
            case 17: bScene17.setChanged(changed); break;
            case 18: bScene18.setChanged(changed); break;
            case 19: bScene19.setChanged(changed); break;
            case 20: bScene20.setChanged(changed); break;
            case 21: bScene21.setChanged(changed); break;
            case 22: bScene22.setChanged(changed); break;
            case 23: bScene23.setChanged(changed); break;
            case 24: bScene24.setChanged(changed); break;
            case 25: bScene25.setChanged(changed); break;
            case 26: bScene26.setChanged(changed); break;
            case 27: bScene27.setChanged(changed); break;
            case 28: bScene28.setChanged(changed); break;
            case 29: bScene29.setChanged(changed); break;
            case 30: bScene30.setChanged(changed); break;
            case 31: bScene31.setChanged(changed); break;
            case 32: bScene32.setChanged(changed); break;
            case 33: bScene33.setChanged(changed); break;
            case 34: bScene34.setChanged(changed); break;
            case 35: bScene35.setChanged(changed); break;
            case 36: bScene36.setChanged(changed); break;
            case 37: bScene37.setChanged(changed); break;
            case 38: bScene38.setChanged(changed); break;
            case 39: bScene39.setChanged(changed); break;
            case 40: bScene40.setChanged(changed); break;
            case 41: bScene41.setChanged(changed); break;
            case 42: bScene42.setChanged(changed); break;
            case 43: bScene43.setChanged(changed); break;
            case 44: bScene44.setChanged(changed); break;
            case 45: bScene45.setChanged(changed); break;
            case 46: bScene46.setChanged(changed); break;
            case 47: bScene47.setChanged(changed); break;
            case 48: bScene48.setChanged(changed); break;
        }
    }
    
    public void setSide(int side){
        this.side = side;
        buttonListener = new SceneButtonListener(side);
        bScene1.addActionListener(buttonListener);
        bScene2.addActionListener(buttonListener);
        bScene3.addActionListener(buttonListener);
        bScene4.addActionListener(buttonListener);
        bScene5.addActionListener(buttonListener);
        bScene6.addActionListener(buttonListener);
        bScene7.addActionListener(buttonListener);
        bScene8.addActionListener(buttonListener);
        bScene9.addActionListener(buttonListener);
        bScene10.addActionListener(buttonListener);
        bScene11.addActionListener(buttonListener);
        bScene12.addActionListener(buttonListener);
        bScene13.addActionListener(buttonListener);
        bScene14.addActionListener(buttonListener);
        bScene15.addActionListener(buttonListener);
        bScene16.addActionListener(buttonListener);
        bScene17.addActionListener(buttonListener);
        bScene18.addActionListener(buttonListener);
        bScene19.addActionListener(buttonListener);
        bScene20.addActionListener(buttonListener);
        bScene21.addActionListener(buttonListener);
        bScene22.addActionListener(buttonListener);
        bScene23.addActionListener(buttonListener);
        bScene24.addActionListener(buttonListener);
        bScene25.addActionListener(buttonListener);
        bScene26.addActionListener(buttonListener);
        bScene27.addActionListener(buttonListener);
        bScene28.addActionListener(buttonListener);
        bScene29.addActionListener(buttonListener);
        bScene30.addActionListener(buttonListener);
        bScene31.addActionListener(buttonListener);
        bScene32.addActionListener(buttonListener);
        bScene33.addActionListener(buttonListener);
        bScene34.addActionListener(buttonListener);
        bScene35.addActionListener(buttonListener);
        bScene36.addActionListener(buttonListener);
        bScene37.addActionListener(buttonListener);
        bScene38.addActionListener(buttonListener);
        bScene39.addActionListener(buttonListener);
        bScene40.addActionListener(buttonListener);
        bScene41.addActionListener(buttonListener);
        bScene42.addActionListener(buttonListener);
        bScene43.addActionListener(buttonListener);
        bScene44.addActionListener(buttonListener);
        bScene45.addActionListener(buttonListener);
        bScene46.addActionListener(buttonListener);
        bScene47.addActionListener(buttonListener);
        bScene48.addActionListener(buttonListener);
        
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
        sceneSelectionPanel = new javax.swing.JPanel();
        bScene1 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene2 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene3 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene4 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene5 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene6 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene7 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene8 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene9 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene10 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene11 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene12 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene13 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene14 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene15 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene16 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene17 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene18 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene19 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene20 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene21 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene22 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene23 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene24 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene25 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene26 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene27 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene28 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene29 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene30 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene31 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene32 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene33 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene34 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene35 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene36 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene37 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene38 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene39 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene40 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene41 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene42 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene43 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene44 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene45 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene46 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene47 = new com.gyver.matrixmover.gui.component.JSceneButton();
        bScene48 = new com.gyver.matrixmover.gui.component.JSceneButton();

        setPreferredSize(new java.awt.Dimension(416, 383));
        setLayout(new java.awt.GridBagLayout());

        ledScreen1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        ledScreen1.setMinimumSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout ledScreen1Layout = new javax.swing.GroupLayout(ledScreen1);
        ledScreen1.setLayout(ledScreen1Layout);
        ledScreen1Layout.setHorizontalGroup(
            ledScreen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        ledScreen1Layout.setVerticalGroup(
            ledScreen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 2, 7);
        add(ledScreen1, gridBagConstraints);

        sceneSelectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Scenes"));
        sceneSelectionPanel.setLayout(new java.awt.GridBagLayout());

        bScene1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene1.setText("1");
        bScene1.setContentAreaFilled(false);
        bScene1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene1.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene1.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene1.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 1, 1);
        sceneSelectionPanel.add(bScene1, gridBagConstraints);

        bScene2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene2.setText("2");
        bScene2.setContentAreaFilled(false);
        bScene2.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene2.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene2.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene2.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        sceneSelectionPanel.add(bScene2, gridBagConstraints);

        bScene3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene3.setText("3");
        bScene3.setContentAreaFilled(false);
        bScene3.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene3.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene3.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene3.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        sceneSelectionPanel.add(bScene3, gridBagConstraints);

        bScene4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene4.setText("4");
        bScene4.setContentAreaFilled(false);
        bScene4.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene4.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene4.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene4.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        sceneSelectionPanel.add(bScene4, gridBagConstraints);

        bScene5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene5.setText("5");
        bScene5.setContentAreaFilled(false);
        bScene5.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene5.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene5.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene5.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 1, 1);
        sceneSelectionPanel.add(bScene5, gridBagConstraints);

        bScene6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene6.setText("6");
        bScene6.setContentAreaFilled(false);
        bScene6.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene6.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene6.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene6.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        sceneSelectionPanel.add(bScene6, gridBagConstraints);

        bScene7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene7.setText("7");
        bScene7.setContentAreaFilled(false);
        bScene7.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene7.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene7.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene7.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        sceneSelectionPanel.add(bScene7, gridBagConstraints);

        bScene8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene8.setText("8");
        bScene8.setContentAreaFilled(false);
        bScene8.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene8.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene8.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene8.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        sceneSelectionPanel.add(bScene8, gridBagConstraints);

        bScene9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene9.setText("9");
        bScene9.setContentAreaFilled(false);
        bScene9.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene9.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene9.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene9.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 6, 1, 1);
        sceneSelectionPanel.add(bScene9, gridBagConstraints);

        bScene10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene10.setText("10");
        bScene10.setContentAreaFilled(false);
        bScene10.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene10.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene10.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene10.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        sceneSelectionPanel.add(bScene10, gridBagConstraints);

        bScene11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene11.setText("11");
        bScene11.setContentAreaFilled(false);
        bScene11.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene11.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene11.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene11.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        sceneSelectionPanel.add(bScene11, gridBagConstraints);

        bScene12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene12.setText("12");
        bScene12.setContentAreaFilled(false);
        bScene12.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene12.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene12.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene12.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 4);
        sceneSelectionPanel.add(bScene12, gridBagConstraints);

        bScene13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene13.setText("13");
        bScene13.setContentAreaFilled(false);
        bScene13.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene13.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene13.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene13.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 4, 1, 1);
        sceneSelectionPanel.add(bScene13, gridBagConstraints);

        bScene14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene14.setText("14");
        bScene14.setContentAreaFilled(false);
        bScene14.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene14.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene14.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene14.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene14, gridBagConstraints);

        bScene15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene15.setText("15");
        bScene15.setContentAreaFilled(false);
        bScene15.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene15.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene15.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene15.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene15, gridBagConstraints);

        bScene16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene16.setText("16");
        bScene16.setContentAreaFilled(false);
        bScene16.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene16.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene16.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene16.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene16, gridBagConstraints);

        bScene17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene17.setText("17");
        bScene17.setContentAreaFilled(false);
        bScene17.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene17.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene17.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene17.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 1, 1);
        sceneSelectionPanel.add(bScene17, gridBagConstraints);

        bScene18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene18.setText("18");
        bScene18.setContentAreaFilled(false);
        bScene18.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene18.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene18.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene18.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene18, gridBagConstraints);

        bScene19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene19.setText("19");
        bScene19.setContentAreaFilled(false);
        bScene19.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene19.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene19.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene19.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene19, gridBagConstraints);

        bScene20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene20.setText("20");
        bScene20.setContentAreaFilled(false);
        bScene20.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene20.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene20.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene20.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene20, gridBagConstraints);

        bScene21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene21.setText("21");
        bScene21.setContentAreaFilled(false);
        bScene21.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene21.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene21.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene21.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 1, 1);
        sceneSelectionPanel.add(bScene21, gridBagConstraints);

        bScene22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene22.setText("22");
        bScene22.setContentAreaFilled(false);
        bScene22.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene22.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene22.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene22.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene22, gridBagConstraints);

        bScene23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene23.setText("23");
        bScene23.setContentAreaFilled(false);
        bScene23.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene23.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene23.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene23.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene23, gridBagConstraints);

        bScene24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene24.setText("24");
        bScene24.setContentAreaFilled(false);
        bScene24.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene24.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene24.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene24.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 4);
        sceneSelectionPanel.add(bScene24, gridBagConstraints);

        bScene25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene25.setText("25");
        bScene25.setContentAreaFilled(false);
        bScene25.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene25.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene25.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene25.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 4, 1, 1);
        sceneSelectionPanel.add(bScene25, gridBagConstraints);

        bScene26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene26.setText("26");
        bScene26.setContentAreaFilled(false);
        bScene26.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene26.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene26.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene26.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene26, gridBagConstraints);

        bScene27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene27.setText("27");
        bScene27.setContentAreaFilled(false);
        bScene27.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene27.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene27.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene27.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene27, gridBagConstraints);

        bScene28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene28.setText("28");
        bScene28.setContentAreaFilled(false);
        bScene28.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene28.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene28.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene28.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene28, gridBagConstraints);

        bScene29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene29.setText("29");
        bScene29.setContentAreaFilled(false);
        bScene29.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene29.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene29.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene29.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 1, 1);
        sceneSelectionPanel.add(bScene29, gridBagConstraints);

        bScene30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene30.setText("30");
        bScene30.setContentAreaFilled(false);
        bScene30.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene30.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene30.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene30.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene30, gridBagConstraints);

        bScene31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene31.setText("31");
        bScene31.setContentAreaFilled(false);
        bScene31.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene31.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene31.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene31.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene31, gridBagConstraints);

        bScene32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene32.setText("32");
        bScene32.setContentAreaFilled(false);
        bScene32.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene32.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene32.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene32.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene32, gridBagConstraints);

        bScene33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene33.setText("33");
        bScene33.setContentAreaFilled(false);
        bScene33.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene33.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene33.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene33.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 1, 1);
        sceneSelectionPanel.add(bScene33, gridBagConstraints);

        bScene34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene34.setText("34");
        bScene34.setContentAreaFilled(false);
        bScene34.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene34.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene34.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene34.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene34, gridBagConstraints);

        bScene35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene35.setText("35");
        bScene35.setContentAreaFilled(false);
        bScene35.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene35.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene35.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene35.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        sceneSelectionPanel.add(bScene35, gridBagConstraints);

        bScene36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene36.setText("36");
        bScene36.setContentAreaFilled(false);
        bScene36.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene36.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene36.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene36.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 4);
        sceneSelectionPanel.add(bScene36, gridBagConstraints);

        bScene37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene37.setText("37");
        bScene37.setContentAreaFilled(false);
        bScene37.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene37.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene37.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene37.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 4, 4, 1);
        sceneSelectionPanel.add(bScene37, gridBagConstraints);

        bScene38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene38.setText("38");
        bScene38.setContentAreaFilled(false);
        bScene38.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene38.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene38.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene38.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 1);
        sceneSelectionPanel.add(bScene38, gridBagConstraints);

        bScene39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene39.setText("39");
        bScene39.setContentAreaFilled(false);
        bScene39.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene39.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene39.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene39.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 1);
        sceneSelectionPanel.add(bScene39, gridBagConstraints);

        bScene40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene40.setText("40");
        bScene40.setContentAreaFilled(false);
        bScene40.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene40.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene40.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene40.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 1);
        sceneSelectionPanel.add(bScene40, gridBagConstraints);

        bScene41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene41.setText("41");
        bScene41.setContentAreaFilled(false);
        bScene41.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene41.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene41.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene41.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 4, 1);
        sceneSelectionPanel.add(bScene41, gridBagConstraints);

        bScene42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene42.setText("42");
        bScene42.setContentAreaFilled(false);
        bScene42.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene42.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene42.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene42.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 1);
        sceneSelectionPanel.add(bScene42, gridBagConstraints);

        bScene43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene43.setText("43");
        bScene43.setContentAreaFilled(false);
        bScene43.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene43.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene43.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene43.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 1);
        sceneSelectionPanel.add(bScene43, gridBagConstraints);

        bScene44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene44.setText("44");
        bScene44.setContentAreaFilled(false);
        bScene44.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene44.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene44.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene44.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 1);
        sceneSelectionPanel.add(bScene44, gridBagConstraints);

        bScene45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene45.setText("45");
        bScene45.setContentAreaFilled(false);
        bScene45.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene45.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene45.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene45.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 6, 4, 1);
        sceneSelectionPanel.add(bScene45, gridBagConstraints);

        bScene46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene46.setText("46");
        bScene46.setContentAreaFilled(false);
        bScene46.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene46.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene46.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene46.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 1);
        sceneSelectionPanel.add(bScene46, gridBagConstraints);

        bScene47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene47.setText("47");
        bScene47.setContentAreaFilled(false);
        bScene47.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene47.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene47.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene47.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 1);
        sceneSelectionPanel.add(bScene47, gridBagConstraints);

        bScene48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene48.setText("48");
        bScene48.setContentAreaFilled(false);
        bScene48.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        bScene48.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene48.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene48.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 4, 4);
        sceneSelectionPanel.add(bScene48, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(sceneSelectionPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gyver.matrixmover.gui.component.JSceneButton bScene1;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene10;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene11;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene12;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene13;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene14;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene15;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene16;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene17;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene18;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene19;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene2;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene20;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene21;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene22;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene23;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene24;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene25;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene26;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene27;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene28;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene29;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene3;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene30;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene31;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene32;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene33;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene34;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene35;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene36;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene37;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene38;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene39;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene4;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene40;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene41;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene42;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene43;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene44;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene45;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene46;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene47;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene48;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene5;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene6;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene7;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene8;
    private com.gyver.matrixmover.gui.component.JSceneButton bScene9;
    private com.gyver.matrixmover.gui.LedScreen ledScreen1;
    private javax.swing.JPanel sceneSelectionPanel;
    // End of variables declaration//GEN-END:variables

    public void setChangedScenesButtonsFromVisualSetupArray(VisualSetup[] visualArray) {
        for(int i = 0; i < visualArray.length; i++){
            this.setButtonChanged(i+1, visualArray[i].isSceneChanged());
        }
    }
}
