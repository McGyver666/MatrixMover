/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.mixer;

/**
 * The Class Xor.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class Xor extends Mixer {

    /**
     * Instantiates a new xor.
     *
     * @param controller the controller
     */
    public Xor() {
        super(MixerName.XOR);
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

            redDest = (short) (redDest ^ redSource);
            greenDest = (short) (greenDest ^ greenSource);
            blueDest = (short) (blueDest ^ blueSource);

            dst[i] = (int) ((redDest << 16) | (greenDest << 8) | blueDest);
        }

        return dst;
    }
}