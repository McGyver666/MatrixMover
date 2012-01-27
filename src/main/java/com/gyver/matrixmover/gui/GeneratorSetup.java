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
 * GeneratorSetup.java
 *
 * Created on 18.01.2012, 15:37:02
 */
package com.gyver.matrixmover.gui;

import com.gyver.matrixmover.gui.listener.GeneratorSetupListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSlider;

/**
 *
 * @author jonas
 */
public class GeneratorSetup extends javax.swing.JPanel {

    private GeneratorSetupListener gsListener = null;
    private int side = 0;

    /** Creates new form GeneratorSetup */
    public GeneratorSetup() {
        initComponents();
    }

    public void setGeneratorListToComboBoxes(Object[] generators) {
        cbGenerator1.setModel(new javax.swing.DefaultComboBoxModel(generators));
        cbGenerator2.setModel(new javax.swing.DefaultComboBoxModel(generators));
    }

    public void setEffectListToComboBoxes(Object[] effects) {
        cbEffect1.setModel(new javax.swing.DefaultComboBoxModel(effects));
        cbEffect2.setModel(new javax.swing.DefaultComboBoxModel(effects));
    }

    public void setMixerListToComboBoxes(Object[] mixer) {
        cbMixer.setModel(new javax.swing.DefaultComboBoxModel(mixer));
    }

    public JSlider getIntensitySlider1() {
        return sIntensity1;
    }

    public JSlider getIntensitySlider2() {
        return sIntensity2;
    }

    public JComboBox getCbGenerator1() {
        return cbGenerator1;
    }

    public JComboBox getCbGenerator2() {
        return cbGenerator2;
    }

    public JComboBox getCbEffect1() {
        return cbEffect1;
    }

    public JComboBox getCbEffect2() {
        return cbEffect2;
    }

    public JComboBox getCbMixer() {
        return cbMixer;
    }
    
    public JButton getConfigGenerator1(){
        return bConfigGenerator1;
    }
    
    public JButton getConfigGenerator2(){
        return bConfigGenerator2;
    }

    public void setSide(int side) {
        this.side = side;
        gsListener = new GeneratorSetupListener(side, this);
        
        addAllToActionListener();
        
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

        panelGenerator1 = new javax.swing.JPanel();
        cbGenerator1 = new javax.swing.JComboBox();
        bConfigGenerator1 = new javax.swing.JButton();
        cbEffect1 = new javax.swing.JComboBox();
        sIntensity1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelGenerator2 = new javax.swing.JPanel();
        cbGenerator2 = new javax.swing.JComboBox();
        bConfigGenerator2 = new javax.swing.JButton();
        cbEffect2 = new javax.swing.JComboBox();
        sIntensity2 = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelMixer = new javax.swing.JPanel();
        cbMixer = new javax.swing.JComboBox();
        bClearScene = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(395, 215));
        setMinimumSize(new java.awt.Dimension(395, 215));
        setPreferredSize(new java.awt.Dimension(395, 215));
        setLayout(null);

        panelGenerator1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Generator 1"));
        panelGenerator1.setLayout(new java.awt.GridBagLayout());

        cbGenerator1.setFont(new java.awt.Font("Dialog", 0, 12));
        cbGenerator1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbGenerator1.setMinimumSize(new java.awt.Dimension(70, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 2, 2);
        panelGenerator1.add(cbGenerator1, gridBagConstraints);

        bConfigGenerator1.setFont(new java.awt.Font("Dialog", 0, 12));
        bConfigGenerator1.setText("Config Generator");
        bConfigGenerator1.setMaximumSize(new java.awt.Dimension(140, 25));
        bConfigGenerator1.setMinimumSize(new java.awt.Dimension(140, 25));
        bConfigGenerator1.setPreferredSize(new java.awt.Dimension(140, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 2, 2);
        panelGenerator1.add(bConfigGenerator1, gridBagConstraints);

        cbEffect1.setFont(new java.awt.Font("Dialog", 0, 12));
        cbEffect1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbEffect1.setMinimumSize(new java.awt.Dimension(80, 20));
        cbEffect1.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 2, 2);
        panelGenerator1.add(cbEffect1, gridBagConstraints);

        sIntensity1.setFont(new java.awt.Font("Dialog", 0, 12));
        sIntensity1.setMaximum(255);
        sIntensity1.setOrientation(javax.swing.JSlider.VERTICAL);
        sIntensity1.setValue(255);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panelGenerator1.add(sIntensity1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel1.setText("Effect Generator:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 0, 2);
        panelGenerator1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel2.setText("Effect");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 7, 0, 2);
        panelGenerator1.add(jLabel2, gridBagConstraints);

        add(panelGenerator1);
        panelGenerator1.setBounds(0, 0, 195, 150);

        panelGenerator2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Generator 2"));
        panelGenerator2.setLayout(new java.awt.GridBagLayout());

        cbGenerator2.setFont(new java.awt.Font("Dialog", 0, 12));
        cbGenerator2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbGenerator2.setMinimumSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 2, 2);
        panelGenerator2.add(cbGenerator2, gridBagConstraints);

        bConfigGenerator2.setFont(new java.awt.Font("Dialog", 0, 12));
        bConfigGenerator2.setText("Config Generator");
        bConfigGenerator2.setMaximumSize(new java.awt.Dimension(140, 25));
        bConfigGenerator2.setMinimumSize(new java.awt.Dimension(140, 25));
        bConfigGenerator2.setPreferredSize(new java.awt.Dimension(140, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 2, 2);
        panelGenerator2.add(bConfigGenerator2, gridBagConstraints);

        cbEffect2.setFont(new java.awt.Font("Dialog", 0, 12));
        cbEffect2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbEffect2.setMinimumSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 2, 2);
        panelGenerator2.add(cbEffect2, gridBagConstraints);

        sIntensity2.setFont(new java.awt.Font("Dialog", 0, 12));
        sIntensity2.setMaximum(255);
        sIntensity2.setOrientation(javax.swing.JSlider.VERTICAL);
        sIntensity2.setValue(255);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panelGenerator2.add(sIntensity2, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel4.setText("Effect Generator:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 0, 2);
        panelGenerator2.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel5.setText("Effect");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 7, 0, 2);
        panelGenerator2.add(jLabel5, gridBagConstraints);

        add(panelGenerator2);
        panelGenerator2.setBounds(201, 0, 195, 150);

        panelMixer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "Mixer"));
        panelMixer.setLayout(new java.awt.GridBagLayout());

        cbMixer.setFont(new java.awt.Font("Dialog", 0, 12));
        cbMixer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMixer.setMinimumSize(new java.awt.Dimension(80, 20));
        cbMixer.setOpaque(false);
        cbMixer.setPreferredSize(new java.awt.Dimension(155, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 7, 2, 2);
        panelMixer.add(cbMixer, gridBagConstraints);

        add(panelMixer);
        panelMixer.setBounds(0, 155, 340, 60);

        bClearScene.setFont(new java.awt.Font("Dialog", 1, 12));
        bClearScene.setText("Clear");
        bClearScene.setMargin(new java.awt.Insets(-2, -2, -2, -2));
        bClearScene.setMaximumSize(new java.awt.Dimension(50, 50));
        bClearScene.setMinimumSize(new java.awt.Dimension(50, 50));
        bClearScene.setPreferredSize(new java.awt.Dimension(50, 50));
        add(bClearScene);
        bClearScene.setBounds(343, 163, 50, 50);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClearScene;
    private javax.swing.JButton bConfigGenerator1;
    private javax.swing.JButton bConfigGenerator2;
    private javax.swing.JComboBox cbEffect1;
    private javax.swing.JComboBox cbEffect2;
    private javax.swing.JComboBox cbGenerator1;
    private javax.swing.JComboBox cbGenerator2;
    private javax.swing.JComboBox cbMixer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel panelGenerator1;
    private javax.swing.JPanel panelGenerator2;
    private javax.swing.JPanel panelMixer;
    private javax.swing.JSlider sIntensity1;
    private javax.swing.JSlider sIntensity2;
    // End of variables declaration//GEN-END:variables

    public Object getBClear() {
        return bClearScene;
    }

    void removeAllFromActionListener() {
        
        cbGenerator1.removeActionListener(gsListener);
        cbGenerator2.removeActionListener(gsListener);
        cbEffect1.removeActionListener(gsListener);
        cbEffect2.removeActionListener(gsListener);
        cbMixer.removeActionListener(gsListener);
        
        bClearScene.removeActionListener(gsListener);
        
        sIntensity1.removeChangeListener(gsListener);
        sIntensity2.removeChangeListener(gsListener);
        
        bConfigGenerator1.removeActionListener(gsListener);
        bConfigGenerator2.removeActionListener(gsListener);
    }
    
    void addAllToActionListener() {
        cbGenerator1.addActionListener(gsListener);
        cbGenerator2.addActionListener(gsListener);
        cbEffect1.addActionListener(gsListener);
        cbEffect2.addActionListener(gsListener);
        cbMixer.addActionListener(gsListener);
        
        bClearScene.addActionListener(gsListener);
        
        sIntensity1.addChangeListener(gsListener);
        sIntensity2.addChangeListener(gsListener);
        
        bConfigGenerator1.addActionListener(gsListener);
        bConfigGenerator2.addActionListener(gsListener);
    }
}
