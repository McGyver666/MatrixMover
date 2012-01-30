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
import com.gyver.matrixmover.generator.ColorFade;
import com.gyver.matrixmover.generator.ColorScroll;
import com.gyver.matrixmover.generator.Drops;
import com.gyver.matrixmover.generator.Fire;
import com.gyver.matrixmover.generator.Generator;
import com.gyver.matrixmover.generator.Generator.GeneratorName;
import com.gyver.matrixmover.generator.MetaBalls;
import com.gyver.matrixmover.generator.Plasma;
import com.gyver.matrixmover.generator.SimpleColorGenerator;
import com.gyver.matrixmover.generator.Textwriter;
import com.gyver.matrixmover.mixer.AddSat;
import com.gyver.matrixmover.mixer.Either;
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
    
    public GeneratorVisual(MatrixData matrix){
        super(matrix);
        
        scenes = new VisualSetup[NUMBER_OF_SCENES];
        
        //fill all visualSetups with initial empty scenes
        for (int i = 0; i < scenes.length; i++) {
            scenes[i] = new VisualSetup(md);
        }
        
    }
    
    public void setVisualSetupArray(VisualSetup[] visualSetupArray){
        this.scenes = visualSetupArray;
    }
    
    public void setActiveScene(int scene){
        this.activeScene = scene-1;
    }
    
    public int getActiveScene(){
        return this.activeScene+1;
    }
    
    public VisualSetup[] getSceneArray(){
        return scenes;
    }
    
    public VisualSetup getVisualSetup(int i){
        return this.scenes[i-1];
    }
    
    public VisualSetup getActiveVisualSetup(){
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
    public void setGeneratorFromString(int nr, GeneratorName generator){
        Generator newGen = null;
        if(generator.equals(Generator.GeneratorName.SIMPLE_COLOR_GENERATOR)){
            newGen = new SimpleColorGenerator(md);
        } else if (generator.equals(Generator.GeneratorName.COLOR_FADE)){
            newGen = new ColorFade(md, null);
        } else if (generator.equals(Generator.GeneratorName.COLOR_SCROLL)){
            newGen = new ColorScroll(md, null);
        } else if (generator.equals(Generator.GeneratorName.PLASMA)){
            newGen = new Plasma(md, null);
        } else if (generator.equals(Generator.GeneratorName.FIRE)){
            newGen = new Fire(md);
        } else if (generator.equals(Generator.GeneratorName.DROPS)){
            newGen = new Drops(md);
        } else if (generator.equals(Generator.GeneratorName.METABALLS)){
            newGen = new MetaBalls(md);
        } else if (generator.equals(Generator.GeneratorName.TEXTWRITER)){
            newGen = new Textwriter(md);
        } else {
            newGen = new SimpleColorGenerator(md);
        }
        
        setGenerator(nr, newGen);
    }
    
    /**
     * Sets a Effect to a Visual
     * @param nr Whether effect1 or effect 2 should be set
     * @param effectString String describing the Effect (see EffectName constants)
     */
    public void setEffectFromString(int nr, String effectString){
        Effect newEff = null;
        if(effectString.equals(Effect.EffectName.STRING_PASSTHRU)){
            newEff = new PassThru(md);
        } else if (effectString.equals(Effect.EffectName.STRING_INVERTER)){
            newEff = new Inverter(md);
        } else if (effectString.equals(Effect.EffectName.STRING_EMBOSS)){
            newEff = new Emboss(md);
        } else if (effectString.equals(Effect.EffectName.STRING_MONOCROME)){
            newEff = new Monocrome(md);
        } else if (effectString.equals(Effect.EffectName.STRING_MONOCROME_INVERS)){
            newEff = new MonocromeInvers(md);
        } else {
            newEff = new PassThru(md);
        }
        
        setEffect(nr, newEff);
    }
    
    

    public void setMixerFromString(String mixerString) {
        if(mixerString.equals(Mixer.MixerName.STRING_PASSTHRU)){
            scenes[activeScene].setMixer(new PassThruMixer());
        } else if(mixerString.equals(Mixer.MixerName.STRING_MULTIPLY)){
            scenes[activeScene].setMixer(new Multiply());
        } else if(mixerString.equals(Mixer.MixerName.STRING_ADDSAT)){
            scenes[activeScene].setMixer(new AddSat());
        } else if(mixerString.equals(Mixer.MixerName.STRING_MIX)){
            scenes[activeScene].setMixer(new Mix());
        } else if(mixerString.equals(Mixer.MixerName.STRING_NEGATIVE_MULTIPLY)){
            scenes[activeScene].setMixer(new NegativeMultiply());
        } else if(mixerString.equals(Mixer.MixerName.STRING_XOR)){
            scenes[activeScene].setMixer(new Xor());
        } else if(mixerString.equals(Mixer.MixerName.STRING_MINUS_HALF)){
            scenes[activeScene].setMixer(new MinusHalf());
        } else if(mixerString.equals(Mixer.MixerName.STRING_EITHER)){
            scenes[activeScene].setMixer(new Either());
        } else {
            scenes[activeScene].setMixer(new PassThruMixer());
        }
    }
    
    void setGeneratorIntensity(int nr, int value) {
        if(nr == 1){
            scenes[activeScene].setGenerator1Intensity(value);
        } else if (nr == 2){
            scenes[activeScene].setGenerator2Intensity(value);
        }
    }
    
    /**
     * Sets a generator to the visual for generator with the nr
     * @param nr Whether generator1 or generator2 is set
     * @param generator The generator to be set
     */
    private void setGenerator(int nr, Generator generator){
        LOG.log(Level.INFO, "Setting {0} for nr {1}", new Object[]{generator.getName(), nr});
        switch(nr){
            case 1: {
                scenes[activeScene].setGenerator1(generator);
                break;
            }
            case 2: {
                scenes[activeScene].setGenerator2(generator);
                break;
            }
        }
    }
    
    private void setEffect(int nr, Effect effect){
        switch(nr){
            case 1: {
                scenes[activeScene].setEffect1(effect);
                break;
            }
            case 2: {
                scenes[activeScene].setEffect2(effect);
                break;
            }
        }
    }

    
}
