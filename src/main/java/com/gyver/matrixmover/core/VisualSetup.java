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
package com.gyver.matrixmover.core;

import com.gyver.matrixmover.effect.*;
import com.gyver.matrixmover.generator.*;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import com.gyver.matrixmover.mixer.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Visual setup holds a Setup various Generators, Effects and Mixer.
 * 
 * @author Gyver
 */
public class VisualSetup implements Serializable {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(VisualSetup.class.getName());
    private List<Generator> generatorList = null;
    private List<Effect> effectList = null;
    private List<Mixer> mixerList = null;
    private List<Integer> intensityList = null;
    private boolean changed = false;
    
    public VisualSetup(MatrixData md) {
        
        generatorList = new ArrayList<>();
        generatorList.add(new SimpleColorGenerator(md));
        
        effectList = new ArrayList<>();
        effectList.add(new PassThru(md));
        
        mixerList = new ArrayList<>();
        mixerList.add(new PassThruMixer());
        
        intensityList = new ArrayList<>();
        intensityList.add(255);
        
    }
    
    public int getNumberOfGenerators() {
        return generatorList.size();
    }

    /**
     * Sets a new Generator and replaces the old one at position n. 
     * @param g the new generator
     * @param n the position in the list
     */
    public void setGenerator(Generator g, int n) {
        LOG.log(Level.FINER, "New Generator for Generator {0}", n);
        if (n < generatorList.size()) {
            generatorList.set(n, g);
        }
    }
    
    public void setEffect(Effect e, int n) {
        LOG.log(Level.FINER, "New Effect for Effect {0}", n);
        if (n < effectList.size()) {
            effectList.set(n, e);
        }
    }
    
    public void setMixer(Mixer m, int n) {
        LOG.log(Level.FINER, "New Mixer for Mixer {0}", n);
        if (n < mixerList.size()) {
            mixerList.set(n, m);
        }
    }
    
    public void setGeneratorIntensity(int value, int n) {
        if (n < intensityList.size()) {
            intensityList.set(n, value);
        }
    }
    
    public Generator getGenerator(int n) {
        return generatorList.get(n);
    }
    
    public Effect getEffect(int n) {
        return effectList.get(n);
    }
    
    public Mixer getMixer(int n) {
        return mixerList.get(n);
    }
    
    public int getGeneratorIntensity(int n) {
        return intensityList.get(n);
    }

    /**
     * Adds a whole generator setup to the visualSetup
     */
    public void addGeneratorSetup(MatrixData md) {
        generatorList.add(new SimpleColorGenerator(md));
        
        effectList.add(new PassThru(md));
        
        mixerList.add(new PassThruMixer());
        
        intensityList.add(255);
    }

    /**
     * Remove a whole generator setup at position n. Last setup cannot be removed
     * @param n the position in the list 
     */
    public void removeGeneratorSetup(int n) {
        if (n < generatorList.size() && n > 0) {
            generatorList.remove(n);
            effectList.remove(n);
            mixerList.remove(n);
            intensityList.remove(n);
        }
    }
    
    public void removeLastVisualSetup() {
        if (this.getNumberOfGenerators() > 1) {
            removeGeneratorSetup(this.getNumberOfGenerators() - 1);
        }
    }
    
    public void clear() {
        MatrixData md = Controller.getControllerInstance().getMatrixData();
        
        generatorList = new ArrayList<>();
        generatorList.add(new SimpleColorGenerator(md));
        
        effectList = new ArrayList<>();
        effectList.add(new PassThru(md));
        
        mixerList = new ArrayList<>();
        mixerList.add(new PassThruMixer());
        
        intensityList = new ArrayList<>();
        intensityList.add(255);
    }
    
    public void sceneChanged(boolean changed) {
        this.changed = changed;
    }
    
    public boolean isSceneChanged() {
        return changed;
    }
    
    public int[] getSceneOutput() {
        
        int[] buffer = null;
        
        for (int i = 0; i < generatorList.size(); i++) {
            generatorList.get(i).update();
            int[] tempBuffer = generatorList.get(i).getBuffer();
            tempBuffer = effectList.get(i).getBuffer(tempBuffer);
            tempBuffer = applyIntensity(tempBuffer, intensityList.get(i));
            if (i == 0) {
                buffer = tempBuffer;
            } else {
                buffer = mixerList.get(i).getBuffer(tempBuffer, buffer);
            }
        }
        
        return buffer;
        
    }
    
    private int[] applyIntensity(int[] src, int value) {
        int[] ret = new int[src.length];
        short red, green, blue;
        int col;
        float ratio = (value / 255f);
        
        for (int i = 0; i < src.length; i++) {
            col = src[i];
            red = (short) (Math.round(((col >> 16) & 255) * ratio));
            green = (short) (Math.round(((col >> 8) & 255) * ratio));
            blue = (short) (Math.round((col & 255) * ratio));
            
            ret[i] = (red << 16) | (green << 8) | blue;
        }
        return ret;
    }

    /**
     * Sets a generator to a Visual
     * @param nr Whether generator1 or generator 2 should be set
     * @param generatorString String describing the generator (see GeneratorName constants)
     */
    public void setGeneratorFromString(int nr, String generatorString) {
        Generator newGen = null;
        MatrixData md = Controller.getControllerInstance().getMatrixData();
        if (generatorString.equals(GeneratorName.SIMPLE_COLOR_GENERATOR.toString())) {
            newGen = new SimpleColorGenerator(md);
        } else if (generatorString.equals(GeneratorName.COLOR_FADE.toString())) {
            newGen = new ColorFade(md, null);
        } else if (generatorString.equals(GeneratorName.COLOR_SCROLL.toString())) {
            newGen = new ColorScroll(md, null);
        } else if (generatorString.equals(GeneratorName.PLASMA.toString())) {
            newGen = new Plasma(md, null);
        } else if (generatorString.equals(GeneratorName.FIRE.toString())) {
            newGen = new Fire(md);
        } else if (generatorString.equals(GeneratorName.RAIN.toString())) {
            newGen = new Rain(md);
        } else if (generatorString.equals(GeneratorName.SHAPES.toString())) {
            newGen = new Shapes(md);
        } else if (generatorString.equals(GeneratorName.RADAR.toString())) {
            newGen = new Radar(md, null);
        } else if (generatorString.equals(GeneratorName.METABALLS.toString())) {
            newGen = new MetaBalls(md);
        } else if (generatorString.equals(GeneratorName.TEXTWRITER.toString())) {
            newGen = new Textwriter(md);
        } else if (generatorString.equals(GeneratorName.ANALYSER.toString())) {
            newGen = new Analyser(md);
        } else if (generatorString.equals(GeneratorName.AUDIO_STROBE.toString())) {
            newGen = new AudioStrobe(md);
        } else {
            newGen = new SimpleColorGenerator(md);
        }
        
        setGenerator(newGen, nr);
    }

    /**
     * Sets a Effect to a Visual
     * @param nr Whether effect1 or effect 2 should be set
     * @param effectString String describing the Effect (see EffectName constants)
     */
    public void setEffectFromString(int nr, String effectString) {
        Effect newEff = null;
        MatrixData md = Controller.getControllerInstance().getMatrixData();
        switch (effectString) {
            case Effect.EffectName.STRING_PASSTHRU:
                newEff = new PassThru(md);
                break;
            case Effect.EffectName.STRING_INVERTER:
                newEff = new Inverter(md);
                break;
            case Effect.EffectName.STRING_EMBOSS:
                newEff = new Emboss(md);
                break;
            case Effect.EffectName.STRING_MONOCROME:
                newEff = new Monocrome(md);
                break;
            case Effect.EffectName.STRING_MONOCROME_INVERS:
                newEff = new MonocromeInvers(md);
                break;
            default:
                newEff = new PassThru(md);
                break;
        }
        
        setEffect(newEff, nr);
    }
    
    public void setMixerFromString(int nr, String mixerString) {
        Mixer mix = null;
        switch (mixerString) {
            case Mixer.MixerName.STRING_PASSTHRU:
                mix = new PassThruMixer();
                break;
            case Mixer.MixerName.STRING_MULTIPLY:
                mix = new Multiply();
                break;
            case Mixer.MixerName.STRING_ADDSAT:
                mix = new AddSat();
                break;
            case Mixer.MixerName.STRING_MIX:
                mix = new Mix();
                break;
            case Mixer.MixerName.STRING_NEGATIVE_MULTIPLY:
                mix = new NegativeMultiply();
                break;
            case Mixer.MixerName.STRING_XOR:
                mix = new Xor();
                break;
            case Mixer.MixerName.STRING_MINUS_HALF:
                mix = new MinusHalf();
                break;
            case Mixer.MixerName.STRING_EITHER:
                mix = new Either();
                break;
            case Mixer.MixerName.STRING_MAX:
                mix = new Max();
                break;
            default:
                mix = new PassThruMixer();
                break;
        }
        setMixer(mix, nr);
    }
    
    @Override
    public String toString() {
        String string = "";
        
        string += "nrOfGens=" + this.effectList.size() + "\n";
        
        for (int i = 0; i < effectList.size(); i++) {
            string += "generator" + i + "=" + generatorList.get(i).getName().toString() + "\n";
            string += generatorList.get(i).parameterToString();
            string += "effect" + i + "=" + effectList.get(i).getName().toString() + "\n";
            string += "mixer" + i + "=" + mixerList.get(i).getName().toString() + "\n";
            string += "intensity" + i + "=" + intensityList.get(i) + "\n";
            
        }
        string += "EOF";
        return string;
    }
}
