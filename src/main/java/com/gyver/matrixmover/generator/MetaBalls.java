/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.generator;

import com.gyver.matrixmover.core.MatrixData;

/**
 * The Class Metaballs.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 * 
 */
public class MetaBalls extends Generator {

    /** The Constant NUM_BLOBS. */
    private static final int NUM_BLOBS = 3;
    /** The blob px. */
    private int[] blobPx = {10, 40, 36, 33, 44, 32, 22};
    /** The blob py. */
    private int[] blobPy = {4, 60, 45, 21, 13, 41, 32};
// Movement vector for each blob
    /** The blob dx. */
    private int[] blobDx = {1, 1, 1, 1, 1, 1, 1};
    /** The blob dy. */
    private int[] blobDy = {1, 1, 1, 1, 1, 1, 1};
    /** The vx. */
    private int[][] vy, vx;
    /** The a. */
    private int a = 1;
    
    private int numBlobs = 0;
    private int size = 0;

    /**
     * Instantiates a new metaballs.
     *
     * @param controller the controller
     */
    public MetaBalls(MatrixData md) {
        super(GeneratorName.METABALLS, md);
        numBlobs = NUM_BLOBS;
        this.size = 1000;
        init();
    }
    
    private void init(){
        vy = new int[numBlobs][getInternalBufferYSize()];
        vx = new int[numBlobs][getInternalBufferXSize()];
    }
    
    public void setNumberOfBlobs(int number){
        this.numBlobs = number;
    }
    
    public int getNumberOfBlobs(){
        return this.numBlobs;
    }
    
    public void setBlobSize(int size){
        this.size = size;
    }

    @Override
    public void update() {
        float f;
        for (int i = 1; i < NUM_BLOBS; ++i) {
            f = (float) Math.sin((i + 1) * 3 + 5 * blobPx[i]);
//f = (float)Math.sin((i+1)*3+5*blobPx[i]);
            f *= 3f;
            if (f < 0) {
                f = 0 - f;
            }
            f += 0.5f;
            blobPx[i] += blobDx[i] * f;

            f = (float) Math.cos(a % 256 + (i + 3) * blobPy[i]);
// f = (float)Math.cos(a%256+3*blobPy[i]);
            f *= 3f;
            if (f < 0) {
                f = 0 - f;
            }
            f += 0.5f;
            blobPy[i] += (int) (blobDy[i] * f);

// bounce across screen
            if (blobPx[i] < 0) {
                blobDx[i] = 1;
            }
            if (blobPx[i] > internalBufferWidth) {
                blobDx[i] = -1;
            }
            if (blobPy[i] < 0) {
                blobDy[i] = 1;
            }
            if (blobPy[i] > internalBufferHeight) {
                blobDy[i] = -1;
            }

            for (int x = 0; x < internalBufferWidth; x++) {
                vx[i][x] = (blobPx[i] - x) * (blobPx[i] - x);
            }

            for (int y = 0; y < internalBufferHeight; y++) {
                vy[i][y] = (blobPy[i] - y) * (blobPy[i] - y);
            }
        }

        a++;
        if (a > 0xffff) {
            a = 1;
        }

        for (int y = 0; y < internalBufferHeight; y++) {
            for (int x = 0; x < internalBufferWidth; x++) {
                int m = 1;
                for (int i = 1; i < NUM_BLOBS; i++) {
// Increase this number to make your blobs bigger
                    m += size / (vy[i][y] + vx[i][x] + 1);
                }
//pg.pixels[x+y*pg.width] = color(0, m+x, (x+m+y)/2);
                int g = m + x;
                int b = (x + m + y) / 3;
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                this.internalBuffer[y * internalBufferWidth + x] = (0 << 16) | (g << 8) | (b);
            }
        }

    }
}