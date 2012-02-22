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

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import java.util.ArrayList;
import java.util.LinkedList;
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
 * @author Gyver
 */
public class AudioCapture {

    private final int CHANNEL_BUFFER_LENGTH = 512;
    private final int listBuffer = 2;
    // set the audio format
//    private final float SAMPLE_RATE = 44100F;
    private final float SAMPLE_RATE = 32000F;
    
    private final int SAMPLE_SIZE_IN_BITS = 16;
    private final int CHANNELS = 2;
    private final boolean SIGNED = true;
    private final boolean BIG_ENDIAN = false;
    private static final Object stackLock = new Object();
    private byte[] buffer = null;
    private float[] leftChanel = null;
    private float[] rightChanel = null;
    private double[] monoChanel = null;
    private LinkedList<double[]> fftStack = null;
    private DoubleFFT_1D fft = null;
    private AudioFormat format = null;
    private Mixer.Info[] mixerInfo = null;
    private TargetDataLine line = null;

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
        monoChanel = new double[CHANNEL_BUFFER_LENGTH];
        
        fftStack = new LinkedList<double[]>();

        fft = new DoubleFFT_1D(CHANNEL_BUFFER_LENGTH);

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
            monoChanel[i] = (leftChanel[i] + rightChanel[i]) / 2.0;
        }

        //calculate fft
        fft.realForward(monoChanel);

        //extract amplitudes
        double[] amplitude = computeAmplitude(monoChanel);

        //stack result until its read.
        addToStack(amplitude);

    }

    public float[] getLevel() {
        double leftChan = calculateRMSLevel(leftChanel);
        double rightChan = calculateRMSLevel(rightChanel);
        float[] level = new float[2];

        level[0] = (float) (Math.log((double) ((leftChan == 0.0) ? 0.0001 : leftChan)) / Math.log(10.0) * 20.0);
        level[1] = (float) (Math.log((double) ((rightChan == 0.0) ? 0.0001 : rightChan)) / Math.log(10.0) * 20.0);

        return level;
    }

    public float[] getFftOutput(int bands) {
        // save stack to local copy and clear it
        double[] a = getStack();

        float[] spectrum = stripToBands(a, bands);

        for (int i = 0; i < spectrum.length; i++) {
            // to logarithmic scale
            spectrum[i] = (float) (Math.log10(spectrum[i]) + 0.99999F);
            // bring all frequencies to same level
            spectrum[i] = (float) (spectrum[i] / 2.8);
        }

        return spectrum;
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

    private double[] computeAmplitude(double[] a) {
        double subFactorForNoise = 0.1;
        
        double[] amplitude = new double[a.length / 2];

        amplitude[amplitude.length - 1] = (double) Math.abs(a[1]);
        amplitude[amplitude.length - 1] -= subFactorForNoise;
        if (amplitude[amplitude.length - 1] < 0) {
            amplitude[amplitude.length - 1] = 0;
        }

        for (int k = 1; k < amplitude.length; k++) {
            amplitude[k - 1] = Math.sqrt((a[2 * k] * a[2 * k]) + (a[2 * k + 1] * a[2 * k + 1]));
            amplitude[k - 1] -= subFactorForNoise;
            if (amplitude[k - 1] < 0) {
                amplitude[k - 1] = 0;
            }
        }
        return amplitude;
    }

    private float[] stripToBands(double[] amplitude, int bands) {

        double band_elong = 1;

        double x = Math.pow(Math.sqrt(2 * amplitude.length - 1.75), 1 / ((double) bands * band_elong - 1));

        float[] spectrum = new float[bands];

        double freqPerBand[] = new double[bands];
        for (int i = 0; i < bands; i++) {
            freqPerBand[i] = Math.pow(x, (double) i);
            if (freqPerBand[i] < 1) {
                freqPerBand[i] = 1;
            }
        }

        int aplitudeIndex = 0;
        double runningSum = 0;

        for (int i = 0; i < bands; i++) {
            runningSum += freqPerBand[i];
            while (runningSum > aplitudeIndex) {
                aplitudeIndex++;
                spectrum[i] += amplitude[aplitudeIndex]; //skip first amplitude
            }
        }
        
        return spectrum;
    }

    private void addToStack(double[] amplitude) {
        synchronized (stackLock) {
            fftStack.add(amplitude);
            if (fftStack.size() > listBuffer) {
                fftStack.remove();
            }
        }
    }

    private double[] getStack() {
        if (fftStack.size() == 0) {
            return new double[CHANNEL_BUFFER_LENGTH];
        }
        synchronized (stackLock) {
            double[] ret = new double[fftStack.get(0).length];
            for (double[] elem : fftStack) {
                for (int i = 0; i < elem.length; i++) {
                    ret[i] += elem[i];
                }
            }
            for (int i = 0; i < ret.length; i++) {
                ret[i] /= fftStack.size();
            }
            return ret;
        }
    }
}
