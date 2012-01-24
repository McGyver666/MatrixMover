/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.mixer;

/**
 * The Class AddSat.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class AddSat extends Mixer {

    /**
     * Instantiates a new adds the sat.
     *
     * @param controller the controller
     */
    public AddSat() {
        super(MixerName.ADDSAT);
    }

    @Override
    public int[] getBuffer(int[] src1, int[] src2) {
        int[] dst = new int[src1.length];
        short r, g, b;
        int col_s, col_d;

        for (int i = 0; i < src1.length; i++) {
            col_s = src1[i];
            r = (short) ((col_s >> 16) & 255);
            g = (short) ((col_s >> 8) & 255);
            b = (short) (col_s & 255);
            col_d = src2[i];
            r += (short) ((col_d >> 16) & 255);
            g += (short) ((col_d >> 8) & 255);
            b += (short) (col_d & 255);

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            dst[i] = (int) (r << 16) | (g << 8) | b;
        }

        return dst;
    }
}