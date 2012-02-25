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
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Visual setup holds a Setup of 5 Generators, 5 Effects and 4 Mixer.
 * 
 * @author Gyver
 */
public class VisualSetup implements Serializable{
    
    /** The log. */
    private static final Logger LOG = Logger.getLogger(VisualSetup.class.getName());
    
    private Generator g1 = null;
    private Generator g2 = null;
    private Generator g3 = null;
    private Generator g4 = null;
    private Generator g5 = null;
    private Effect e1 = null;
    private Effect e2 = null;
    private Effect e3 = null;
    private Effect e4 = null;
    private Effect e5 = null;
    private Mixer m2 = null;
    private Mixer m3 = null;
    private Mixer m4 = null;
    private Mixer m5 = null;
    private int g1Intensity = 255;
    private int g2Intensity = 255;
    private int g3Intensity = 255;
    private int g4Intensity = 255;
    private int g5Intensity = 255;
    private boolean changed = false;
    
    
    public VisualSetup(MatrixData md){
        
        //setup with everything to passthrou
        g1 = new SimpleColorGenerator(md);
        g2 = new SimpleColorGenerator(md);
        g3 = new SimpleColorGenerator(md);
        g4 = new SimpleColorGenerator(md);
        g5 = new SimpleColorGenerator(md);
        e1 = new PassThru(md);
        e2 = new PassThru(md);
        e3 = new PassThru(md);
        e4 = new PassThru(md);
        e5 = new PassThru(md);
        m2 = new PassThruMixer();
        m3 = new PassThruMixer();
        m4 = new PassThruMixer();
        m5 = new PassThruMixer();
        g1Intensity = 255;
        g2Intensity = 255;
        g3Intensity = 255;
        g4Intensity = 255;
        g5Intensity = 255;
    }
    
    public void setGenerator1(Generator g1){
        LOG.log(Level.FINER, "New Generator for Generator1");
        this.g1 = g1;
    }
    
    public void setGenerator2(Generator g2){
        LOG.log(Level.FINER, "New Generator for Generator2");
        this.g2 = g2;
    }
    
    public void setGenerator3(Generator g3){
        LOG.log(Level.FINER, "New Generator for Generator3");
        this.g3 = g3;
    }
    
    public void setGenerator4(Generator g4){
        LOG.log(Level.FINER, "New Generator for Generator4");
        this.g4 = g4;
    }
    
    public void setGenerator5(Generator g5){
        LOG.log(Level.FINER, "New Generator for Generator5");
        this.g5 = g5;
    }
    
    public void setEffect1(Effect e1){
        this.e1 = e1;
    }
    
    public void setEffect2(Effect e2){
        this.e2 = e2;
    }
    
    public void setEffect3(Effect e3){
        this.e3 = e3;
    }
    
    public void setEffect4(Effect e4){
        this.e4 = e4;
    }
    
    public void setEffect5(Effect e5){
        this.e5 = e5;
    }
    
    public void setMixer2(Mixer m2){
        this.m2 = m2;
    }
    
    public void setMixer3(Mixer m3){
        this.m3 = m3;
    }
    
    public void setMixer4(Mixer m4){
        this.m4 = m4;
    }
    
    public void setMixer5(Mixer m5){
        this.m5 = m5;
    }
    
    public void setGenerator1Intensity(int value) {
        this.g1Intensity = value;
    }
    
    public void setGenerator2Intensity(int value) {
        this.g2Intensity = value;
    }
    
    public void setGenerator3Intensity(int value) {
        this.g3Intensity = value;
    }
    
    public void setGenerator4Intensity(int value) {
        this.g4Intensity = value;
    }
    
    public void setGenerator5Intensity(int value) {
        this.g5Intensity = value;
    }
    
    public Generator getGenerator1(){
        return g1;
    }
    
    public Generator getGenerator2(){
        return g2;
    }
    
    public Generator getGenerator3(){
        return g3;
    }
    
    public Generator getGenerator4(){
        return g4;
    }
    
    public Generator getGenerator5(){
        return g5;
    }
    
    public Effect getEffect1(){
        return e1;
    }
    
    public Effect getEffect2(){
        return e2;
    }
    
    public Effect getEffect3(){
        return e3;
    }
    
    public Effect getEffect4(){
        return e4;
    }
    
    public Effect getEffect5(){
        return e5;
    }
    
    public Mixer getMixer2(){
        return m2;
    }
    
    public Mixer getMixer3(){
        return m3;
    }
    
    public Mixer getMixer4(){
        return m4;
    }
    
    public Mixer getMixer5(){
        return m5;
    }
    
    public int getGenerator1Intensity(){
        return g1Intensity;
    }
    
    public int getGenerator2Intensity(){
        return g2Intensity;
    }
    
    public int getGenerator3Intensity(){
        return g3Intensity;
    }
    
    public int getGenerator4Intensity(){
        return g4Intensity;
    }
    
    public int getGenerator5Intensity(){
        return g5Intensity;
    }
    
    public void sceneChanged(boolean changed){
        this.changed = changed;
    }
    
    public boolean isSceneChanged(){
        return changed;
    }
    
    public int[] getSceneOutput(){
        //update the generators
        g1.update();
        g2.update();
        g3.update();
        g4.update();
        g5.update();
        
        //then get their buffers
        int[] out1 = g1.getBuffer();
        int[] out2 = g2.getBuffer();
        int[] out3 = g3.getBuffer();
        int[] out4 = g4.getBuffer();
        int[] out5 = g5.getBuffer();
        
        //put buffers into effects
        out1 = e1.getBuffer(out1);
        out2 = e2.getBuffer(out2);
        out3 = e3.getBuffer(out3);
        out4 = e4.getBuffer(out4);
        out5 = e5.getBuffer(out5);
        
        out1 = applyIntensity(out1, g1Intensity);
        out2 = applyIntensity(out2, g2Intensity);
        out3 = applyIntensity(out3, g3Intensity);
        out4 = applyIntensity(out4, g4Intensity);
        out5 = applyIntensity(out5, g5Intensity);
        
        //and combine effects with mixer, return result
        out2 = m2.getBuffer(out1, out2);
        out3 = m3.getBuffer(out2, out3);
        out4 = m4.getBuffer(out3, out4);
        out5 = m5.getBuffer(out4, out5);
        return out5;
        
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
