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

        setPreferredSize(new java.awt.Dimension(416, 383));
        setLayout(new java.awt.GridBagLayout());

        ledScreen1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        ledScreen1.setMinimumSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout ledScreen1Layout = new javax.swing.GroupLayout(ledScreen1);
        ledScreen1.setLayout(ledScreen1Layout);
        ledScreen1Layout.setHorizontalGroup(
            ledScreen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );
        ledScreen1Layout.setVerticalGroup(
            ledScreen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
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
        gridBagConstraints.insets = new java.awt.Insets(13, 7, 7, 7);
        add(ledScreen1, gridBagConstraints);

        sceneSelectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Scenes"));
        sceneSelectionPanel.setLayout(new java.awt.GridBagLayout());

        bScene1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene1.setText("1");
        bScene1.setContentAreaFilled(false);
        bScene1.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene1.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene1.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene1.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene1, gridBagConstraints);

        bScene2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene2.setText("2");
        bScene2.setContentAreaFilled(false);
        bScene2.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene2.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene2.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene2.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene2, gridBagConstraints);

        bScene3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene3.setText("3");
        bScene3.setContentAreaFilled(false);
        bScene3.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene3.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene3.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene3.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene3, gridBagConstraints);

        bScene4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene4.setText("4");
        bScene4.setContentAreaFilled(false);
        bScene4.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene4.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene4.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene4.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene4, gridBagConstraints);

        bScene5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene5.setText("5");
        bScene5.setContentAreaFilled(false);
        bScene5.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene5.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene5.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene5.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene5, gridBagConstraints);

        bScene6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene6.setText("6");
        bScene6.setContentAreaFilled(false);
        bScene6.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene6.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene6.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene6.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene6, gridBagConstraints);

        bScene7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene7.setText("7");
        bScene7.setContentAreaFilled(false);
        bScene7.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene7.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene7.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene7.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene7, gridBagConstraints);

        bScene8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene8.setText("8");
        bScene8.setContentAreaFilled(false);
        bScene8.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene8.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene8.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene8.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene8, gridBagConstraints);

        bScene9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene9.setText("9");
        bScene9.setContentAreaFilled(false);
        bScene9.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene9.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene9.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene9.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene9, gridBagConstraints);

        bScene10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene10.setText("10");
        bScene10.setContentAreaFilled(false);
        bScene10.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene10.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene10.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene10.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene10, gridBagConstraints);

        bScene11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene11.setText("11");
        bScene11.setContentAreaFilled(false);
        bScene11.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene11.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene11.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene11.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene11, gridBagConstraints);

        bScene12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene12.setText("12");
        bScene12.setContentAreaFilled(false);
        bScene12.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene12.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene12.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene12.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene12, gridBagConstraints);

        bScene13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene13.setText("13");
        bScene13.setContentAreaFilled(false);
        bScene13.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene13.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene13.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene13.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene13, gridBagConstraints);

        bScene14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene14.setText("14");
        bScene14.setContentAreaFilled(false);
        bScene14.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene14.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene14.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene14.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene14, gridBagConstraints);

        bScene15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene15.setText("15");
        bScene15.setContentAreaFilled(false);
        bScene15.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene15.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene15.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene15.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene15, gridBagConstraints);

        bScene16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene16.setText("16");
        bScene16.setContentAreaFilled(false);
        bScene16.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene16.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene16.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene16.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene16, gridBagConstraints);

        bScene17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene17.setText("17");
        bScene17.setContentAreaFilled(false);
        bScene17.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene17.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene17.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene17.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene17, gridBagConstraints);

        bScene18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene18.setText("18");
        bScene18.setContentAreaFilled(false);
        bScene18.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene18.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene18.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene18.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene18, gridBagConstraints);

        bScene19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene19.setText("19");
        bScene19.setContentAreaFilled(false);
        bScene19.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene19.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene19.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene19.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene19, gridBagConstraints);

        bScene20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene20.setText("20");
        bScene20.setContentAreaFilled(false);
        bScene20.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene20.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene20.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene20.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene20, gridBagConstraints);

        bScene21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene21.setText("21");
        bScene21.setContentAreaFilled(false);
        bScene21.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene21.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene21.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene21.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene21, gridBagConstraints);

        bScene22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene22.setText("22");
        bScene22.setContentAreaFilled(false);
        bScene22.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene22.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene22.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene22.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene22, gridBagConstraints);

        bScene23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene23.setText("23");
        bScene23.setContentAreaFilled(false);
        bScene23.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene23.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene23.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene23.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene23, gridBagConstraints);

        bScene24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene24.setText("24");
        bScene24.setContentAreaFilled(false);
        bScene24.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene24.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene24.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene24.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene24, gridBagConstraints);

        bScene25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene25.setText("25");
        bScene25.setContentAreaFilled(false);
        bScene25.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene25.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene25.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene25.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene25, gridBagConstraints);

        bScene26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene26.setText("26");
        bScene26.setContentAreaFilled(false);
        bScene26.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene26.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene26.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene26.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene26, gridBagConstraints);

        bScene27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene27.setText("27");
        bScene27.setContentAreaFilled(false);
        bScene27.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene27.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene27.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene27.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene27, gridBagConstraints);

        bScene28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene28.setText("28");
        bScene28.setContentAreaFilled(false);
        bScene28.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene28.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene28.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene28.setPreferredSize(new java.awt.Dimension(25, 15));
        bScene28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bScene28ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene28, gridBagConstraints);

        bScene29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene29.setText("29");
        bScene29.setContentAreaFilled(false);
        bScene29.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene29.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene29.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene29.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene29, gridBagConstraints);

        bScene30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene30.setText("30");
        bScene30.setContentAreaFilled(false);
        bScene30.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene30.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene30.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene30.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 2, 2);
        sceneSelectionPanel.add(bScene30, gridBagConstraints);

        bScene31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene31.setText("31");
        bScene31.setContentAreaFilled(false);
        bScene31.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene31.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene31.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene31.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene31, gridBagConstraints);

        bScene32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene32.setText("32");
        bScene32.setContentAreaFilled(false);
        bScene32.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene32.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene32.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene32.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene32, gridBagConstraints);

        bScene33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene33.setText("33");
        bScene33.setContentAreaFilled(false);
        bScene33.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene33.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene33.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene33.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene33, gridBagConstraints);

        bScene34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene34.setText("34");
        bScene34.setContentAreaFilled(false);
        bScene34.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene34.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene34.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene34.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene34, gridBagConstraints);

        bScene35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene35.setText("35");
        bScene35.setContentAreaFilled(false);
        bScene35.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene35.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene35.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene35.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene35, gridBagConstraints);

        bScene36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        bScene36.setText("36");
        bScene36.setContentAreaFilled(false);
        bScene36.setFont(new java.awt.Font("Dialog", 1, 10));
        bScene36.setMaximumSize(new java.awt.Dimension(25, 15));
        bScene36.setMinimumSize(new java.awt.Dimension(25, 15));
        bScene36.setPreferredSize(new java.awt.Dimension(25, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        sceneSelectionPanel.add(bScene36, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(sceneSelectionPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void bScene28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bScene28ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_bScene28ActionPerformed

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
    private com.gyver.matrixmover.gui.component.JSceneButton bScene4;
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
