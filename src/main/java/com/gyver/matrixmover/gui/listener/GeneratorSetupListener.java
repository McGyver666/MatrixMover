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
import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.core.VisualSetup;
import com.gyver.matrixmover.effect.Effect;
import com.gyver.matrixmover.effect.Effect.EffectName;
import com.gyver.matrixmover.effect.Emboss;
import com.gyver.matrixmover.effect.Inverter;
import com.gyver.matrixmover.effect.Monocrome;
import com.gyver.matrixmover.effect.MonocromeInvers;
import com.gyver.matrixmover.effect.PassThru;
import com.gyver.matrixmover.generator.Analyser;
import com.gyver.matrixmover.generator.AudioStrobe;
import com.gyver.matrixmover.generator.ColorFade;
import com.gyver.matrixmover.generator.ColorScroll;
import com.gyver.matrixmover.generator.Fire;
import com.gyver.matrixmover.generator.Rain;
import com.gyver.matrixmover.generator.Generator;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import com.gyver.matrixmover.generator.MetaBalls;
import com.gyver.matrixmover.generator.Plasma;
import com.gyver.matrixmover.generator.Shapes;
import com.gyver.matrixmover.generator.SimpleColorGenerator;
import com.gyver.matrixmover.generator.Textwriter;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.GeneratorPanel;
import com.gyver.matrixmover.gui.GeneratorSetup;
import com.gyver.matrixmover.gui.component.GeneratorSetupPanel;
import com.gyver.matrixmover.gui.generator.AnalyserConfiguration;
import com.gyver.matrixmover.gui.generator.ColorFadeConfiguration;
import com.gyver.matrixmover.gui.generator.ColorScrollConfiguration;
import com.gyver.matrixmover.gui.generator.ShapesConfiguration;
import com.gyver.matrixmover.gui.generator.DropsConfiguration;
import com.gyver.matrixmover.gui.generator.FireConfiguration;
import com.gyver.matrixmover.gui.generator.MetaBallsConfiguration;
import com.gyver.matrixmover.gui.generator.PlasmaConfiguration;
import com.gyver.matrixmover.gui.generator.AudioStrobeConfiguration;
import com.gyver.matrixmover.gui.generator.SimpleColorConfiguration;
import com.gyver.matrixmover.gui.generator.TextwriterConfiguration;
import com.gyver.matrixmover.mixer.AddSat;
import com.gyver.matrixmover.mixer.Either;
import com.gyver.matrixmover.mixer.Max;
import com.gyver.matrixmover.mixer.MinusHalf;
import com.gyver.matrixmover.mixer.Mix;
import com.gyver.matrixmover.mixer.Mixer;
import com.gyver.matrixmover.mixer.Mixer.MixerName;
import com.gyver.matrixmover.mixer.Multiply;
import com.gyver.matrixmover.mixer.NegativeMultiply;
import com.gyver.matrixmover.mixer.PassThruMixer;
import com.gyver.matrixmover.mixer.Xor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Listenes to the comboboxes in the gui.
 * @author jonas
 */
public class GeneratorSetupListener implements ActionListener, ChangeListener {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(GeneratorSetupListener.class.getName());
    private VisualSetup vs = null;
    private GeneratorSetup setupPanel = null;

    public GeneratorSetupListener(VisualSetup vs, GeneratorSetup setupPanel) {
        this.vs = vs;
        this.setupPanel = setupPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (setupPanel == null) {
            return;
        }

        if (ae.getSource() instanceof JButton) {
            JButton b = (JButton) ae.getSource();
            int index = setupPanel.getIndexOfGeneratorSetupBySetupPanelObject((GeneratorSetupPanel) b.getParent());
            if(b.getText().equals("Config Generator")) {
                openGeneratorSettingsDialog(vs.getGenerator(index));
            } else if(b.getText().equals("Config Effect")) {
                // not used atm.
            }
            
        } else if (ae.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) ae.getSource();
            int index = setupPanel.getIndexOfGeneratorSetupBySetupPanelObject((GeneratorSetupPanel) cb.getParent());
            MatrixData md = Controller.getControllerInstance().getMatrixData();
            
            if (cb.getSelectedItem() instanceof GeneratorName) {
                vs.setGeneratorFromString(index, ((GeneratorName) cb.getSelectedItem()).toString());
            } else if (cb.getSelectedItem() instanceof EffectName) {
                vs.setEffectFromString(index, ((EffectName) cb.getSelectedItem()).toString());
            } else if (cb.getSelectedItem() instanceof MixerName) {
                vs.setMixerFromString(index, ((MixerName) cb.getSelectedItem()).toString());
            }
        }
        setupPanel.buildGuiFromVisualSetup();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            JSlider s = (JSlider) e.getSource();
            int index = setupPanel.getIndexOfGeneratorSetupBySetupPanelObject((GeneratorSetupPanel) s.getParent());
            vs.setGeneratorIntensity(s.getValue(), index);
        }
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
        } else if (activGenerator instanceof MetaBalls) {
            MetaBallsConfiguration mbDialog = new MetaBallsConfiguration(Frame.getFrameInstance(), true, (MetaBalls) activGenerator);
            mbDialog.setVisible(true);
        } else if (activGenerator instanceof Textwriter) {
            TextwriterConfiguration twDialog = new TextwriterConfiguration(Frame.getFrameInstance(), true, (Textwriter) activGenerator);
            twDialog.setVisible(true);
        } else if (activGenerator instanceof Shapes) {
            ShapesConfiguration sDialog = new ShapesConfiguration(Frame.getFrameInstance(), true, (Shapes) activGenerator);
            sDialog.setVisible(true);
        } else if (activGenerator instanceof Analyser) {
            AnalyserConfiguration aDialog = new AnalyserConfiguration(Frame.getFrameInstance(), true, (Analyser) activGenerator);
            aDialog.setVisible(true);
        } else if (activGenerator instanceof AudioStrobe) {
            AudioStrobeConfiguration ascDialog = new AudioStrobeConfiguration(Frame.getFrameInstance(), true, (AudioStrobe) activGenerator);
            ascDialog.setVisible(true);
        } 
    }
}
