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
 * Frame.java
 *
 * Created on 17.01.2012, 09:58:43
 */
package com.gyver.matrixmover.gui;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.core.VisualSetup;
import com.gyver.matrixmover.effect.Effect.EffectName;
import com.gyver.matrixmover.generator.Generator.GeneratorName;
import com.gyver.matrixmover.mixer.Mixer.MixerName;
import com.gyver.matrixmover.properties.PropertiesHelper;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComboBox;

/**
 *
 * @author jonas
 */
public class Frame extends javax.swing.JFrame {

    private PropertiesHelper ph = null;
    private MatrixData md = null;
    private static Frame instance = new Frame();

    /** Creates new form Frame */
    private Frame() {
    }

    public static Frame getFrameInstance() {
        return instance;
    }

    public void initFrame(PropertiesHelper ph, Controller cont) {
        this.ph = ph;
        this.md = cont.getMatrixData();

        initComponents();
        effectPanelLeft.setSide(Controller.LEFT_SIDE);
        effectPanelRight.setSide(Controller.RIGHT_SIDE);

        generatorSetupLeft.setSide(Controller.LEFT_SIDE);
        generatorSetupRight.setSide(Controller.RIGHT_SIDE);

        effectPanelLeft.getLedScreen().init(ph, md);
        effectPanelRight.getLedScreen().init(ph, md);
        masterPanel.getLedScreen().init(ph, md);
        cont.setLedScreens(effectPanelLeft.getLedScreen(), effectPanelRight.getLedScreen(), masterPanel.getLedScreen());

        setUpComboBoxes();
    }
    
    public void centerWindow() {
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        effectPanelLeft = new com.gyver.matrixmover.gui.GeneratorPanel();
        effectPanelRight = new com.gyver.matrixmover.gui.GeneratorPanel();
        masterPanel = new com.gyver.matrixmover.gui.MasterPanel();
        logoPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        generatorSetupLeft = new com.gyver.matrixmover.gui.GeneratorSetup();
        generatorSetupRight = new com.gyver.matrixmover.gui.GeneratorSetup();
        masterSettings1 = new com.gyver.matrixmover.gui.MasterSettings();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MatrixMover");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("FrameMatrixMover"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        effectPanelLeft.setMinimumSize(new java.awt.Dimension(420, 300));
        effectPanelLeft.setPreferredSize(new java.awt.Dimension(420, 300));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(effectPanelLeft, gridBagConstraints);

        effectPanelRight.setMinimumSize(new java.awt.Dimension(420, 300));
        effectPanelRight.setPreferredSize(new java.awt.Dimension(420, 300));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(effectPanelRight, gridBagConstraints);

        masterPanel.setMinimumSize(new java.awt.Dimension(300, 350));
        masterPanel.setPreferredSize(new java.awt.Dimension(300, 350));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(masterPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 50;
        getContentPane().add(logoPanel, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(generatorSetupLeft, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(generatorSetupRight, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel1.add(masterSettings1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        effectPanelLeft.recomputeLedPixelSize();
        effectPanelRight.recomputeLedPixelSize();
        masterPanel.recomputeLedPixelSize();
    }//GEN-LAST:event_formComponentResized
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.gyver.matrixmover.gui.GeneratorPanel effectPanelLeft;
    private com.gyver.matrixmover.gui.GeneratorPanel effectPanelRight;
    private com.gyver.matrixmover.gui.GeneratorSetup generatorSetupLeft;
    private com.gyver.matrixmover.gui.GeneratorSetup generatorSetupRight;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel logoPanel;
    private com.gyver.matrixmover.gui.MasterPanel masterPanel;
    private com.gyver.matrixmover.gui.MasterSettings masterSettings1;
    // End of variables declaration//GEN-END:variables

    private void setUpComboBoxes() {

        generatorSetupLeft.setGeneratorListToComboBoxes(GeneratorName.values());
        generatorSetupRight.setGeneratorListToComboBoxes(GeneratorName.values());

        generatorSetupLeft.setEffectListToComboBoxes(EffectName.values());
        generatorSetupRight.setEffectListToComboBoxes(EffectName.values());

        generatorSetupLeft.setMixerListToComboBoxes(MixerName.values());
        generatorSetupRight.setMixerListToComboBoxes(MixerName.values());
    }

    public void setComboBoxesForChangedScene(int side, VisualSetup newActiveVisualSetup) {

        if (side == Controller.LEFT_SIDE) {
            setComboBoxes(generatorSetupLeft, newActiveVisualSetup);
        } else if (side == Controller.RIGHT_SIDE) {
            setComboBoxes(generatorSetupRight, newActiveVisualSetup);
        }
    }

    private void setComboBoxes(GeneratorSetup generatorSetup, VisualSetup newActiveVisualSetup) {
        
        generatorSetup.getCbGenerator1().setSelectedItem(newActiveVisualSetup.getGenerator1().getName());                
        generatorSetup.getCbGenerator2().setSelectedItem(newActiveVisualSetup.getGenerator2().getName());                
        generatorSetup.getCbEffect1().setSelectedItem(newActiveVisualSetup.getEffect1().getName());
        generatorSetup.getCbEffect2().setSelectedItem(newActiveVisualSetup.getEffect2().getName());
        generatorSetup.getCbMixer().setSelectedItem(newActiveVisualSetup.getMixer().getName());
        
        generatorSetup.getIntensitySlider1().setValue(newActiveVisualSetup.getGenerator1Intensity());
        generatorSetup.getIntensitySlider2().setValue(newActiveVisualSetup.getGenerator2Intensity());
        
    }

    public GeneratorPanel getLeftGeneratorPanel() {
        return this.effectPanelLeft;
    }

    public GeneratorPanel getRightGeneratorPanel() {
        return this.effectPanelRight;
    }
    
}
