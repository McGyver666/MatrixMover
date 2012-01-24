/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.mixer;

/**
 * The Class NegativeMultiply.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class NegativeMultiply extends Mixer {

    /**
     * Instantiates a new negative multiply.
     *
     * @param controller the controller
     */
    public NegativeMultiply() {
        super(MixerName.NEGATIVE_MULTIPLY);
    }

    @Override
    public int[] getBuffer(int[] src1, int[] src2) {
        short redSource, greenSource, blueSource;
        short redDest, greenDest, blueDest;
        int col;

        int[] dst = new int[src1.length];

        for (int i = 0; i < src1.length; i++) {
            col = src1[i];
            redSource = (short) ((col >> 16) & 255);
            greenSource = (short) ((col >> 8) & 255);
            blueSource = (short) (col & 255);

            col = src2[i];
            redDest = (short) ((col >> 16) & 255);
            greenDest = (short) ((col >> 8) & 255);
            blueDest = (short) (col & 255);

            redDest = (short) (255 - ((255 - redDest) * (255 - redSource) / 256));
            greenDest = (short) (255 - ((255 - greenDest) * (255 - greenSource) / 256));
            blueDest = (short) (255 - ((255 - blueDest) * (255 - blueSource) / 256));

            dst[i] = (int) ((redDest << 16) | (greenDest << 8) | blueDest);
        }

        return dst;
    }
}