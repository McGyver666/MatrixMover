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
package com.gyver.matrixmover.gui.listener;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.generator.ColorFade;
import com.gyver.matrixmover.generator.ColorScroll;
import com.gyver.matrixmover.generator.Fire;
import com.gyver.matrixmover.generator.Rain;
import com.gyver.matrixmover.generator.Generator;
import com.gyver.matrixmover.generator.Generator.GeneratorName;
import com.gyver.matrixmover.generator.Plasma;
import com.gyver.matrixmover.generator.Shapes;
import com.gyver.matrixmover.generator.SimpleColorGenerator;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.GeneratorPanel;
import com.gyver.matrixmover.gui.GeneratorSetup;
import com.gyver.matrixmover.gui.effect.ColorFadeConfiguration;
import com.gyver.matrixmover.gui.effect.ColorScrollConfiguration;
import com.gyver.matrixmover.gui.effect.ShapesConfiguration;
import com.gyver.matrixmover.gui.effect.DropsConfiguration;
import com.gyver.matrixmover.gui.effect.FireConfiguration;
import com.gyver.matrixmover.gui.effect.PlasmaConfiguration;
import com.gyver.matrixmover.gui.effect.SimpleColorConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Listenes to the comboboxes in the gui.
 * @author jonas
 */
public class GeneratorSetupListener implements ActionListener, ChangeListener {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(GeneratorSetupListener.class.getName());
    private int side = 0;
    private GeneratorSetup setupPanel = null;

    public GeneratorSetupListener(int side, GeneratorSetup setupPanel) {
        this.side = side;
        this.setupPanel = setupPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (setupPanel == null) {
            return;
        }

        if (ae.getSource().equals(setupPanel.getCbGenerator1())) {
            LOG.log(Level.FINE, "Performing action for generator1");
            Controller.getControllerInstance().setGenerator(side, 1, (GeneratorName) setupPanel.getCbGenerator1().getSelectedItem());
            activeButtonChanged(true);
        } else if (ae.getSource().equals(setupPanel.getCbGenerator2())) {
            LOG.log(Level.FINE, "Performing action for generator2");
            Controller.getControllerInstance().setGenerator(side, 2, (GeneratorName) setupPanel.getCbGenerator2().getSelectedItem());
            activeButtonChanged(true);
        } else if (ae.getSource().equals(setupPanel.getCbEffect1())) {
            Controller.getControllerInstance().setEffect(side, 1, setupPanel.getCbEffect1().getSelectedItem().toString());
            activeButtonChanged(true);
        } else if (ae.getSource().equals(setupPanel.getCbEffect2())) {
            Controller.getControllerInstance().setEffect(side, 2, setupPanel.getCbEffect2().getSelectedItem().toString());
            activeButtonChanged(true);
        } else if (ae.getSource().equals(setupPanel.getCbMixer())) {
            Controller.getControllerInstance().setMixer(side, setupPanel.getCbMixer().getSelectedItem().toString());
            activeButtonChanged(true);
        } else if (ae.getSource().equals(setupPanel.getBClear())) {
            setupPanel.getCbGenerator1().setSelectedIndex(0);
            setupPanel.getCbGenerator2().setSelectedIndex(0);
            setupPanel.getCbEffect1().setSelectedIndex(0);
            setupPanel.getCbEffect2().setSelectedIndex(0);
            setupPanel.getCbMixer().setSelectedIndex(0);

            Controller.getControllerInstance().setGenerator(side, 2, (GeneratorName) setupPanel.getCbGenerator2().getSelectedItem());
            Controller.getControllerInstance().setGenerator(side, 2, (GeneratorName) setupPanel.getCbGenerator2().getSelectedItem());
            Controller.getControllerInstance().setEffect(side, 1, setupPanel.getCbEffect1().getSelectedItem().toString());
            Controller.getControllerInstance().setEffect(side, 2, setupPanel.getCbEffect1().getSelectedItem().toString());
            Controller.getControllerInstance().setMixer(side, setupPanel.getCbMixer().getSelectedItem().toString());

            setupPanel.getIntensitySlider1().setValue(setupPanel.getIntensitySlider1().getMaximum());
            setupPanel.getIntensitySlider2().setValue(setupPanel.getIntensitySlider2().getMaximum());

            Controller.getControllerInstance().setGeneratorIntensity(side, 1, setupPanel.getIntensitySlider1().getValue());
            Controller.getControllerInstance().setGeneratorIntensity(side, 2, setupPanel.getIntensitySlider2().getValue());
        
            activeButtonChanged(false);
        } else if (ae.getSource().equals(setupPanel.getConfigGenerator1())) {
            Generator activGen = Controller.getControllerInstance().getGenerator(side, 1);
            openGeneratorSettingsDialog(activGen);
        } else if (ae.getSource().equals(setupPanel.getConfigGenerator2())) {
            Generator activGen = Controller.getControllerInstance().getGenerator(side, 2);
            openGeneratorSettingsDialog(activGen);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(setupPanel.getIntensitySlider1())) {
            Controller.getControllerInstance().setGeneratorIntensity(side, 1, setupPanel.getIntensitySlider1().getValue());
        } else if (e.getSource().equals(setupPanel.getIntensitySlider2())) {
            Controller.getControllerInstance().setGeneratorIntensity(side, 2, setupPanel.getIntensitySlider2().getValue());
        }
        activeButtonChanged(true);
    }

    private void openGeneratorSettingsDialog(Generator activGenerator) {
        if (activGenerator instanceof SimpleColorGenerator) {
            SimpleColorConfiguration sctDialog = new SimpleColorConfiguration(Frame.getFrameInstance(), true, (SimpleColorGenerator) activGenerator);
            sctDialog.setVisible(true);
        } else if (activGenerator instanceof ColorScroll) {
            ColorScrollConfiguration ctDialog = new ColorScrollConfiguration(Frame.getFrameInstance(), true, (ColorScroll) activGenerator);
            ctDialog.setVisible(true);
        } else if (activGenerator instanceof ColorFade) {
            ColorFadeConfiguration cfDialog = new ColorFadeConfiguration(Frame.getFrameInstance(), true, (ColorFade) activGenerator);
            cfDialog.setVisible(true);
        } else if (activGenerator instanceof Plasma) {
            PlasmaConfiguration pDialog = new PlasmaConfiguration(Frame.getFrameInstance(), true, (Plasma) activGenerator);
            pDialog.setVisible(true);
        } else if (activGenerator instanceof Fire) {
            FireConfiguration fDialog = new FireConfiguration(Frame.getFrameInstance(), true, (Fire) activGenerator);
            fDialog.setVisible(true);
        } else if (activGenerator instanceof Rain) {
            DropsConfiguration dDialog = new DropsConfiguration(Frame.getFrameInstance(), true, (Rain) activGenerator);
            dDialog.setVisible(true);
        } else if (activGenerator instanceof Shapes) {
            ShapesConfiguration sDialog = new ShapesConfiguration(Frame.getFrameInstance(), true, (Shapes) activGenerator);
            sDialog.setVisible(true);
        }
        activeButtonChanged(true);
    }

    private void activeButtonChanged(boolean changed) {
        int activeVisualNumber = Controller.getControllerInstance().getActiveVisualNumber(this.side);
        Controller.getControllerInstance().sceneChanged(this.side, activeVisualNumber, changed);
        GeneratorPanel panel = null;
        if (this.side == Controller.LEFT_SIDE) {
            panel = Frame.getFrameInstance().getLeftGeneratorPanel();
        } else if (this.side == Controller.RIGHT_SIDE) {
            panel = Frame.getFrameInstance().getRightGeneratorPanel();
        }
        panel.setButtonChanged(activeVisualNumber, changed);
    }
}
