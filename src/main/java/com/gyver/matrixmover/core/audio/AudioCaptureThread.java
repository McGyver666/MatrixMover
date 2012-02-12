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
        }
    }

    /**
     * @return the rmsLevel
     */
    public float[] getRmsLevel() {
        return ac.getLevel();
    }

    /**
     * @return the spectrum
     */
    public float[] getSpectrum(int bands) {
        return ac.getFftOutput(bands);
    }
    
}
