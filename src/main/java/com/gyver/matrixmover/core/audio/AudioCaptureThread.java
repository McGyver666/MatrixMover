/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.core.audio;

import com.gyver.matrixmover.core.Controller;

/**
 *
 * @author Gyver
 */
public class AudioCaptureThread implements Runnable {

    private AudioCapture ac = null;
    private float rmsLevel[] = {0, 0};
    private int spectrum[] = null;
    
    public AudioCaptureThread(){
        
        ac = new AudioCapture();
        if (ac.getAvalibalMixer() != null && ac.getAvalibalMixer().length > 0) {
            ac.startAudio(ac.getAvalibalMixer()[1]);
        }
        
    }
    
    @Override
    public void run() {
        while(true) {
            ac.captureAudio();
            this.rmsLevel = ac.getLevel();
        }
    }

    /**
     * @return the rmsLevel
     */
    public float[] getRmsLevel() {
        return rmsLevel;
    }

    /**
     * @return the spectrum
     */
    public int[] getSpectrum() {
        return spectrum;
    }
    
}
