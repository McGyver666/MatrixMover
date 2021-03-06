/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.core.audio;

import java.util.logging.Level;
import java.util.logging.Logger;

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
            ac.startAudio(ac.getAvalibalMixer()[0]);
        }
        
    }
    
    @Override
    public void run() {
        while(true) {
            ac.captureAudio();
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(AudioCaptureThread.class.getName()).log(Level.SEVERE, null, ex);
            }
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
