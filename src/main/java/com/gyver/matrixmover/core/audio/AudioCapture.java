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
package com.gyver.matrixmover.core.audio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author jonas
 */
public class AudioCapture {

    private final int CHANNEL_BUFFER_LENGTH = 512;
    // set the audio format
    private final float SAMPLE_RATE = 44100F;
    private final int SAMPLE_SIZE_IN_BITS = 16;
    private final int CHANNELS = 2;
    private final boolean SIGNED = true;
    private final boolean BIG_ENDIAN = false;

    private AudioFormat format = null;
    private Mixer.Info[] mixerInfo = null;
    TargetDataLine line = null;
    byte[] buffer = null;
    float[] leftChanel = null;
    float[] rightChanel = null;
    
    int dbMax = 0;
    
    public AudioCapture() {

        // load all supported Mixers into mixerinfo
        Line.Info lineInfo = new Line.Info(TargetDataLine.class);

        mixerInfo = AudioSystem.getMixerInfo();

        ArrayList<Mixer.Info> supportedMixers = new ArrayList<Mixer.Info>();

        for (Mixer.Info mixerInfoItem : mixerInfo) {
            Mixer testMixer = AudioSystem.getMixer(mixerInfoItem);
            if (testMixer.isLineSupported(lineInfo)) {
                supportedMixers.add(mixerInfoItem);
                System.out.println("\n" + mixerInfoItem.getName() + "\n" + mixerInfoItem.getDescription() + "\n");
            }
        }

        mixerInfo = supportedMixers.toArray(new Mixer.Info[supportedMixers.size()]);

        format = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, SIGNED, BIG_ENDIAN);

        buffer = new byte[CHANNEL_BUFFER_LENGTH * 4];
        leftChanel = new float[CHANNEL_BUFFER_LENGTH];
        rightChanel = new float[CHANNEL_BUFFER_LENGTH];

    }

    public void startAudio(Mixer.Info mixerInfo) {

        AudioSystem.getMixer(mixerInfo);

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        line = null;

        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioCapture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mixer.Info[] getAvalibalMixer() {
        return mixerInfo;
    }

    public void captureAudio() {
        if (line == null) {
            return;
        }

        line.read(buffer, 0, buffer.length);

        //now split the two signals
        int n = 0;
        for (int i = 0; i < leftChanel.length; n += 4, i++) {
            leftChanel[i] = (((buffer[(n + 1)] << 8) + buffer[n]) / 32767.0F);
            rightChanel[i] = (((buffer[(n + 3)] << 8) + buffer[(n + 2)]) / 32767.0F);
        }
    }

    public float[] getLevel() {
        double leftChan = calculateRMSLevel(leftChanel);
        double rightChan = calculateRMSLevel(rightChanel);
        float[] level = new float[2];
        
        level[0] = (float) (Math.log((double)((leftChan==0.0)?0.0001:leftChan))/Math.log(10.0) * 20.0);
        level[1] = (float) (Math.log((double)((rightChan==0.0)?0.0001:rightChan))/Math.log(10.0) * 20.0);
        
//        // maximum db is 35. So add 65 to scale from 0 to 100 (-65 to +35 db)
//        level[0] = (int) (20*Math.log10(level[0]))+65;
//        if(level[0] < 0){
//           level[0] = 0;
//        }
//        
//        level[1] = (int) (20*Math.log10(level[1]))+65;
//        if(level[1] < 0){
//           level[1] = 0;
//        }
        
        return level;
    }

    private double calculateRMSLevel(float[] data) {
        //calculade the rms
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum = sum + data[i];
        }

        double avg = sum / data.length;

        double sumMeanSquare = 0d;
        for (int j = 0; j < data.length; j++) {
            sumMeanSquare = sumMeanSquare + Math.pow(data[j] - avg, 2d);
        }

        double avgMeanSquare = sumMeanSquare / data.length;
        return Math.pow(avgMeanSquare, 0.5d) * 100d;
    }
}
