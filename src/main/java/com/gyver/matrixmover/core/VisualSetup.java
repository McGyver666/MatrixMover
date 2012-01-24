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
import com.gyver.matrixmover.effect.PassThru;
import com.gyver.matrixmover.generator.Generator;
import com.gyver.matrixmover.generator.SimpleColorGenerator;
import com.gyver.matrixmover.mixer.Mixer;
import com.gyver.matrixmover.mixer.PassThruMixer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Visual setup holds a Setup of 2 Generators, 2 Effects and a Mixer.
 * 
 * @author Gyver
 */
public class VisualSetup {
    
    /** The log. */
    private static final Logger LOG = Logger.getLogger(VisualSetup.class.getName());
    
    private MatrixData md = null;
    private Generator g1 = null;
    private Generator g2 = null;
    private Effect e1 = null;
    private Effect e2 = null;
    private Mixer m = null;
    private int g1Intensity = 255;
    private int g2Intensity = 255;
    
    
    public VisualSetup(MatrixData md){
        this.md = md;
        
        //setup with everything to passthrou
        g1 = new SimpleColorGenerator(md);
        g2 = new SimpleColorGenerator(md);
        e1 = new PassThru(md);
        e2 = new PassThru(md);
        m = new PassThruMixer();
        g1Intensity = 255;
        g2Intensity = 255;
    }
    
    public void setGenerator1(Generator g1){
        LOG.log(Level.FINER, "New Generator for Generator1");
        this.g1 = g1;
    }
    
    public void setGenerator2(Generator g2){
        LOG.log(Level.FINER, "New Generator for Generator2");
        this.g2 = g2;
    }
    
    public void setEffect1(Effect e1){
        this.e1 = e1;
    }
    
    public void setEffect2(Effect e2){
        this.e2 = e2;
    }
    
    public void setMixer(Mixer m){
        this.m = m;
    }
    
    public void setGenerator1Intensity(int value) {
        this.g1Intensity = value;
    }
    
    public void setGenerator2Intensity(int value) {
        this.g2Intensity = value;
    }
    
    public Generator getGenerator1(){
        return g1;
    }
    
    public Generator getGenerator2(){
        return g2;
    }
    
    public Effect getEffect1(){
        return e1;
    }
    
    public Effect getEffect2(){
        return e2;
    }
    
    public Mixer getMixer(){
        return m;
    }
    
    public int getGenerator1Intensity(){
        return g1Intensity;
    }
    
    public int getGenerator2Intensity(){
        return g2Intensity;
    }
    
    public int[] getSceneOutput(){
        //update the generators
        g1.update();
        g2.update();
        
        //then get their buffers
        int[] out1 = g1.getBuffer();
        int[] out2 = g2.getBuffer();
        
        //put buffers into effects
        out1 = e1.getBuffer(out1);
        out2 = e2.getBuffer(out2);
        
        out1 = applyIntensity(out1, g1Intensity);
        out2 = applyIntensity(out2, g2Intensity);
        
        //and combine effects with mixer, return result
        return m.getBuffer(out1, out2);
        
    }
    
    private int[] applyIntensity(int[] src, int value){
        int[] ret = new int[src.length];
        short red, green, blue;
        int col;
        float ratio = (value / 255f);
        
        for(int i = 0; i < src.length; i++){
            col = src[i];
            red = (short) (Math.round(((col >> 16) & 255) * ratio));
            green = (short) (Math.round(((col >> 8) & 255) * ratio));
            blue = (short) (Math.round((col & 255) * ratio));

            ret[i] = (red << 16) | (green << 8) | blue;
        }
        return ret;
    }
}
