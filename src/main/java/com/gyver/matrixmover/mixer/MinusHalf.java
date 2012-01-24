/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.mixer;

/**
 * The Class MinusHalf.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class MinusHalf extends Mixer {

    /**
     * Instantiates a new minus half.
     *
     * @param controller the controller
     */
    public MinusHalf() {
        super(MixerName.MINUS_HALF);
    }

    @Override
    public int[] getBuffer(int[] src1, int[] src2) {
        short redSource, greenSource, blueSource;
        short redDest, greenDest, bluedest;
        int col;

        int[] dst = new int [src1.length];

        for (int i = 0; i < src1.length; i++) {
            col = src1[i];
            redSource = (short) ((col >> 16) & 255);
            greenSource = (short) ((col >> 8) & 255);
            blueSource = (short) (col & 255);

            col = src2[i];
            redDest = (short) ((col >> 16) & 255);
            greenDest = (short) ((col >> 8) & 255);
            bluedest = (short) (col & 255);

            redDest = (short) (redDest - redSource / 2);
            greenDest = (short) (greenDest - greenSource / 2);
            bluedest = (short) (bluedest - blueSource / 2);

            dst[i] = (int) ((redDest << 16) | (greenDest << 8) | bluedest);
        }

        return dst;
    }
}