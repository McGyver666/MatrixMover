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

import com.gyver.matrixmover.effect.Effect;
import com.gyver.matrixmover.effect.Emboss;
import com.gyver.matrixmover.effect.Inverter;
import com.gyver.matrixmover.effect.Monocrome;
import com.gyver.matrixmover.effect.MonocromeInvers;
import com.gyver.matrixmover.effect.PassThru;
import com.gyver.matrixmover.generator.Analyser;
import com.gyver.matrixmover.generator.AudioStrobe;
import com.gyver.matrixmover.generator.ColorFade;
import com.gyver.matrixmover.generator.ColorScroll;
import com.gyver.matrixmover.generator.Rain;
import com.gyver.matrixmover.generator.Fire;
import com.gyver.matrixmover.generator.Generator;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import com.gyver.matrixmover.generator.MetaBalls;
import com.gyver.matrixmover.generator.Plasma;
import com.gyver.matrixmover.generator.RandomPixel;
import com.gyver.matrixmover.generator.Shapes;
import com.gyver.matrixmover.generator.SimpleColorGenerator;
import com.gyver.matrixmover.generator.Textwriter;
import com.gyver.matrixmover.mixer.AddSat;
import com.gyver.matrixmover.mixer.Either;
import com.gyver.matrixmover.mixer.Max;
import com.gyver.matrixmover.mixer.MinusHalf;
import com.gyver.matrixmover.mixer.Mix;
import com.gyver.matrixmover.mixer.Mixer;
import com.gyver.matrixmover.mixer.Multiply;
import com.gyver.matrixmover.mixer.NegativeMultiply;
import com.gyver.matrixmover.mixer.PassThruMixer;
import com.gyver.matrixmover.mixer.Xor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Holds an array of VisualSetup, one for every scene.
 * 
 * @author Gyver
 */
public class GeneratorVisual extends Visual {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(GeneratorVisual.class.getName());
    public static final int NUMBER_OF_SCENES = 48;
    private int activeScene = 0;
    private VisualSetup[] scenes = null;

    public GeneratorVisual(MatrixData matrix) {
        super(matrix);

        scenes = new VisualSetup[NUMBER_OF_SCENES];

        //fill all visualSetups with initial empty scenes
        for (int i = 0; i < scenes.length; i++) {
            scenes[i] = new VisualSetup(md);
        }

    }

    public void setVisualSetupArray(VisualSetup[] visualSetupArray) {
        this.scenes = visualSetupArray;
    }

    public void setActiveScene(int scene) {
        this.activeScene = scene - 1;
    }

    public int getActiveScene() {
        return this.activeScene + 1;
    }

    public VisualSetup[] getSceneArray() {
        return scenes;
    }

    public VisualSetup getVisualSetup(int i) {
        return this.scenes[i - 1];
    }

    public VisualSetup getActiveVisualSetup() {
        return scenes[activeScene];
    }

    @Override
    public int[] getVisualOutput() {
        return scenes[activeScene].getSceneOutput();
    }

    /**
     * Sets a generator to a Visual
     * @param nr Whether generator1 or generator 2 should be set
     * @param generator String describing the generator (see GeneratorName constants)
     */
    public void setGeneratorFromString(int nr, GeneratorName generator) {
        Generator newGen;
        switch (generator) {
            case SIMPLE_COLOR_GENERATOR:
                newGen = new SimpleColorGenerator(md);
                break;
            case COLOR_FADE:
                newGen = new ColorFade(md, null);
                break;
            case COLOR_SCROLL:
                newGen = new ColorScroll(md, null);
                break;
            case PLASMA:
                newGen = new Plasma(md, null);
                break;
            case FIRE:
                newGen = new Fire(md);
                break;
            case RAIN:
                newGen = new Rain(md);
                break;
            case SHAPES:
                newGen = new Shapes(md);
                break;
            case METABALLS:
                newGen = new MetaBalls(md);
                break;
            case TEXTWRITER:
                newGen = new Textwriter(md);
                break;
            case ANALYSER:
                newGen = new Analyser(md);
                break;
            case AUDIO_STROBE:
                newGen = new AudioStrobe(md);
                break;
            case RANDOM_PIXEL:
                newGen = new RandomPixel(md);
                break;
            default:
                newGen = new SimpleColorGenerator(md);
                break;
        }

        setGenerator(nr, newGen);
    }

    /**
     * Sets a Effect to a Visual
     * @param nr Whether effect1 or effect 2 should be set
     * @param effectString String describing the Effect (see EffectName constants)
     */
    public void setEffectFromString(int nr, String effectString) {
        Effect newEff = null;
        if (effectString.equals(Effect.EffectName.STRING_PASSTHRU)) {
            newEff = new PassThru(md);
        } else if (effectString.equals(Effect.EffectName.STRING_INVERTER)) {
            newEff = new Inverter(md);
        } else if (effectString.equals(Effect.EffectName.STRING_EMBOSS)) {
            newEff = new Emboss(md);
        } else if (effectString.equals(Effect.EffectName.STRING_MONOCROME)) {
            newEff = new Monocrome(md);
        } else if (effectString.equals(Effect.EffectName.STRING_MONOCROME_INVERS)) {
            newEff = new MonocromeInvers(md);
        } else {
            newEff = new PassThru(md);
        }

        setEffect(nr, newEff);
    }

    

    public void setMixerFromString(int nr, String mixerString) {
        Mixer mix = null;
        if (mixerString.equals(Mixer.MixerName.STRING_PASSTHRU)) {
            mix = new PassThruMixer();
        } else if (mixerString.equals(Mixer.MixerName.STRING_MULTIPLY)) {
            mix = new Multiply();
        } else if (mixerString.equals(Mixer.MixerName.STRING_ADDSAT)) {
            mix = new AddSat();
        } else if (mixerString.equals(Mixer.MixerName.STRING_MIX)) {
            mix = new Mix();
        } else if (mixerString.equals(Mixer.MixerName.STRING_NEGATIVE_MULTIPLY)) {
            mix = new NegativeMultiply();
        } else if (mixerString.equals(Mixer.MixerName.STRING_XOR)) {
            mix = new Xor();
        } else if (mixerString.equals(Mixer.MixerName.STRING_MINUS_HALF)) {
            mix = new MinusHalf();
        } else if (mixerString.equals(Mixer.MixerName.STRING_EITHER)) {
            mix = new Either();
        } else if (mixerString.equals(Mixer.MixerName.STRING_MAX)) {
            mix = new Max();
        } else {
            mix = new PassThruMixer();
        }
        setMixer(nr, mix);
    }

    void setGeneratorIntensity(int nr, int value) {
        scenes[activeScene].setGeneratorIntensity(value, nr);
    }

    /**
     * Setzt the generator with number nr in the visual
     * @param nr the number of the generator
     * @param generator the generator to set
     */
    private void setGenerator(int nr, Generator generator) {
        LOG.log(Level.INFO, "Setting {0} for nr {1}", new Object[]{generator.getName(), nr});
        scenes[activeScene].setGenerator(generator, nr);
    }

    /**
     * Sets the effect with number nr in the visual
     * @param nr the number of the effect
     * @param effect the effect to set
     */
    private void setEffect(int nr, Effect effect) {
        scenes[activeScene].setEffect(effect, nr);
    }

    /**
     * Sets the mixer with number nr in the visual
     * @param nr the number of the mixer
     * @param mixer the effect to set
     */
    private void setMixer(int nr, Mixer mix) {
        scenes[activeScene].setMixer(mix, nr);
    }
}
