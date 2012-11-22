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

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.VisualSetup;
import com.gyver.matrixmover.effect.Effect.EffectName;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import com.gyver.matrixmover.gui.component.GeneratorSetupPanel;
import com.gyver.matrixmover.gui.listener.GeneratorSetupListener;
import com.gyver.matrixmover.mixer.Mixer.MixerName;

/**
 *
 * @author Gyver
 */
public final class GeneratorSetup extends javax.swing.JPanel {

    private GeneratorSetupListener gsListener = null;
    private VisualSetup vs = null;

    /** Creates new form GeneratorSetup */
    public GeneratorSetup() {
        initComponents();
    }
    
    public void setup(int side){
        this.vs = Controller.getControllerInstance().getActiveVisualSetup(side);
        gsListener = new GeneratorSetupListener(vs, this);
        buildGuiFromVisualSetup();
    }

//    public void setGeneratorListToComboBoxes(Object[] generators) {
//        cbGenerator1.setModel(new javax.swing.DefaultComboBoxModel(generators));
//        cbGenerator2.setModel(new javax.swing.DefaultComboBoxModel(generators));
//        cbGenerator3.setModel(new javax.swing.DefaultComboBoxModel(generators));
//        cbGenerator4.setModel(new javax.swing.DefaultComboBoxModel(generators));
//        cbGenerator5.setModel(new javax.swing.DefaultComboBoxModel(generators));
//    }

//    public void setGeneratorListToComboBoxes(Object[] generators) {
//        cbGenerator1.setModel(new javax.swing.DefaultComboBoxModel(generators));
//        cbGenerator2.setModel(new javax.swing.DefaultComboBoxModel(generators));
//        cbGenerator3.setModel(new javax.swing.DefaultComboBoxModel(generators));
//        cbGenerator4.setModel(new javax.swing.DefaultComboBoxModel(generators));
//        cbGenerator5.setModel(new javax.swing.DefaultComboBoxModel(generators));
//    }
//
//    public void setEffectListToComboBoxes(Object[] effects) {
//        cbEffect1.setModel(new javax.swing.DefaultComboBoxModel(effects));
//        cbEffect2.setModel(new javax.swing.DefaultComboBoxModel(effects));
//        cbEffect3.setModel(new javax.swing.DefaultComboBoxModel(effects));
//        cbEffect4.setModel(new javax.swing.DefaultComboBoxModel(effects));
//        cbEffect5.setModel(new javax.swing.DefaultComboBoxModel(effects));
//    }
//
//    public void setMixerListToComboBoxes(Object[] mixer) {
//        cbMixer2.setModel(new javax.swing.DefaultComboBoxModel(mixer));
//        cbMixer3.setModel(new javax.swing.DefaultComboBoxModel(mixer));
//        cbMixer4.setModel(new javax.swing.DefaultComboBoxModel(mixer));
//        cbMixer5.setModel(new javax.swing.DefaultComboBoxModel(mixer));
//    }
//
//    public JSlider getIntensitySlider1() {
//        return sIntensity1;
//    }
//
//    public JSlider getIntensitySlider2() {
//        return sIntensity2;
//    }
//
//    public JSlider getIntensitySlider3() {
//        return sIntensity3;
//    }
//
//    public JSlider getIntensitySlider4() {
//        return sIntensity4;
//    }
//
//    public JSlider getIntensitySlider5() {
//        return sIntensity5;
//    }
//
//    public JComboBox getCbGenerator1() {
//        return cbGenerator1;
//    }
//
//    public JComboBox getCbGenerator2() {
//        return cbGenerator2;
//    }
//
//    public JComboBox getCbGenerator3() {
//        return cbGenerator3;
//    }
//
//    public JComboBox getCbGenerator4() {
//        return cbGenerator4;
//    }
//
//    public JComboBox getCbGenerator5() {
//        return cbGenerator5;
//    }
//
//    public JComboBox getCbEffect1() {
//        return cbEffect1;
//    }
//
//    public JComboBox getCbEffect2() {
//        return cbEffect2;
//    }
//
//    public JComboBox getCbEffect3() {
//        return cbEffect3;
//    }
//
//    public JComboBox getCbEffect4() {
//        return cbEffect4;
//    }
//
//    public JComboBox getCbEffect5() {
//        return cbEffect5;
//    }
//
//    public JComboBox getCbMixer2() {
//        return cbMixer2;
//    }
//    
//    public JComboBox getCbMixer3() {
//        return cbMixer3;
//    }
//    
//    public JComboBox getCbMixer4() {
//        return cbMixer4;
//    }
//    
//    public JComboBox getCbMixer5() {
//        return cbMixer5;
//    }
//    
//    public JButton getConfigGenerator1(){
//        return bConfigGenerator1;
//    }
//    
//    public JButton getConfigGenerator2(){
//        return bConfigGenerator2;
//    }
//
//    public JButton getConfigGenerator3(){
//        return bConfigGenerator3;
//    }
//
//    public JButton getConfigGenerator4(){
//        return bConfigGenerator4;
//    }
//
//    public JButton getConfigGenerator5(){
//        return bConfigGenerator5;
//    }
//    public void setSide(int side) {
//        
//        
//        addAllToActionListener();
//        
//    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        generatorSetupPanelContainer = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(395, 215));
        setMinimumSize(new java.awt.Dimension(395, 215));
        setPreferredSize(new java.awt.Dimension(980, 230));
        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setHorizontalScrollBar(null);

        generatorSetupPanelContainer.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(generatorSetupPanelContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel generatorSetupPanelContainer;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


    public void removeAllFromActionListener() {

    }

    public void addAllToActionListener() {

    }

    public void buildGuiFromVisualSetup() {
        generatorSetupPanelContainer.removeAll();

        for (int i = 0; i < vs.getNumberOfGenerators(); i++) {
            GeneratorSetupPanel gsp = new GeneratorSetupPanel();
//            gsp.setBorder(javax.swing.BorderFactory.createTitledBorder((i+1)+". Generator"));
            gsp.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), (i+1)+". Generator"));
            gsp.setMaximumSize(new java.awt.Dimension(10, 160));
            gsp.setMinimumSize(new java.awt.Dimension(2000, 160));
            gsp.setPreferredSize(new java.awt.Dimension(400, 160));
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
            generatorSetupPanelContainer.add(gsp, gridBagConstraints);

            gsp.getCbGenerator().setModel(new javax.swing.DefaultComboBoxModel(GeneratorName.values()));
            gsp.getCbGenerator().setSelectedItem(vs.getGenerator(i).getName());

            gsp.getCbEffect().setModel(new javax.swing.DefaultComboBoxModel(EffectName.values()));
            gsp.getCbEffect().setSelectedItem(vs.getEffect(i).getName());

            gsp.getCbMixer().setModel(new javax.swing.DefaultComboBoxModel(MixerName.values()));
            gsp.getCbMixer().setSelectedItem(vs.getMixer(i).getName());

            gsp.getsIntensity().setValue(vs.getGeneratorIntensity(i));
            
            // Add all to actionlistener
            gsp.getbConfigEffect().addActionListener(gsListener);
            gsp.getbConfigGenerator().addActionListener(gsListener);
            gsp.getCbGenerator().addActionListener(gsListener);
            gsp.getCbEffect().addActionListener(gsListener);
            gsp.getCbMixer().addActionListener(gsListener);
            gsp.getsIntensity().addChangeListener(gsListener);
        }
    }
    
    public int getIndexOfGeneratorSetupBySetupPanelObject(GeneratorSetupPanel gsp) {
        for (int i = 0; i < generatorSetupPanelContainer.getComponentCount(); i++) {
            if (generatorSetupPanelContainer.getComponent(i).equals(gsp)){
                return i;
            }
        }
        return -1;
    }
}
