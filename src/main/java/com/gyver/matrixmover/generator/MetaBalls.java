/*
 * Copyright (C) 2011 Michael Vogt <michu@neophob.com>
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
    private static final int NUM_BLOBS = 2;
    /** The blob px. */
    private float[] blobPx;// = {10, 40, 36, 33, 44, 32, 22};
    /** The blob py. */
    private float[] blobPy;// = {4, 60, 45, 21, 13, 41, 32};
// Movement vector for each blob
    /** The blob dx. */
    private float[] blobDx;// = {0F, 0.1F, 1F, 1F, 1F, 1F, -0.5F};
    /** The blob dy. */
    private float[] blobDy;// = {1F, 0.2F, 1F, 1F, 0.5F, -0.5F, 1F};
    /** The vx. */
    private int[][] vy, vx;
    /** The a. */
    private int a = 1;
    
    private int numBlobs;
    private int size;
    private float speed;
    private int threshold;
    private boolean applyThreshold;

    /**
     * Instantiates a new metaballs.
     *
     * @param controller the controller
     */
    public MetaBalls(MatrixData md) {
        super(GeneratorName.METABALLS, md);
        numBlobs = NUM_BLOBS;
        this.size = 1000;
        this.speed = 0.1F;
        this.applyThreshold = false;
        this.threshold = 128;
        init();
    }
    
    public void init(){
        blobPx = new float[getNumBlobs()];
        blobPy = new float[getNumBlobs()];
        blobDx = new float[getNumBlobs()];
        blobDy = new float[getNumBlobs()];
        
        for (int i = 0; i < getNumBlobs(); i++){
            blobPx[i] = (int) Math.floor(Math.random()*internalBufferWidth);
            blobPy[i] = (int) Math.floor(Math.random()*internalBufferHeight);
            blobDx[i] = (float) ((Math.random()-0.5)*2);
            blobDy[i] = (float) ((Math.random()-0.5)*2);
        }
        
        vy = new int[getNumBlobs()][getInternalBufferYSize()];
        vx = new int[getNumBlobs()][getInternalBufferXSize()];
    }
    
    @Override
    public void update() {
        float f;
        for (int i = 0; i < numBlobs; ++i) {
            f = (float) Math.sin((i + 1) * 3 + 5 * blobPx[i]);

            f *= 3f;
            if (f < 0) {
                f = 0 - f;
            }
            f += 0.5f;
            blobPx[i] += blobDx[i] * f * speed;

            f = (float) Math.cos(a % 256 + (i + 3) * blobPy[i]);

            f *= 3f;
            if (f < 0) {
                f = 0 - f;
            }
            f += 0.5f;
            blobPy[i] += blobDy[i] * f * speed;

            // bounce across screen
            if (blobPx[i] < 0 && blobDx[i] < 0) {
                blobDx[i] *= -1;
            }
            if (blobPx[i] > internalBufferWidth && blobDx[i] > 0) {
                blobDx[i] *= -1;
            }
            if (blobPy[i] < 0 && blobDy[i] < 0) {
                blobDy[i] *= -1;
            }
            if (blobPy[i] > internalBufferHeight && blobDy[i] > 0) {
                blobDy[i] *= -1;
            }

            for (int x = 0; x < internalBufferWidth; x++) {
                vx[i][x] = (int) Math.floor((blobPx[i] - x) * (blobPx[i] - x));
            }

            for (int y = 0; y < internalBufferHeight; y++) {
                vy[i][y] = (int) Math.floor((blobPy[i] - y) * (blobPy[i] - y));
            }
        }

        a++;
        if (a > 0xffff) {
            a = 1;
        }

        for (int y = 0; y < internalBufferHeight; y++) {
            for (int x = 0; x < internalBufferWidth; x++) {
                int m = 1;
                for (int i = 0; i < numBlobs; i++) {
                    // Increase this number to make your blobs bigger
                    m += getSize() / (vy[i][y] + vx[i][x] + 1);
                }
                
                int intensity = (x+m+y)/3;
                if (intensity > 255) {
                    intensity = 255;
                }
                
                if(isApplyThreshold()){
                    if(intensity > threshold){
                        intensity = 255;
                    } else {
                        intensity = 0;
                    }
                }
                
                this.internalBuffer[y * internalBufferWidth + x] = (intensity << 16) | (intensity << 8) | (intensity);
            }
        }

    }

    /**
     * @return the numBlobs
     */
    public int getNumBlobs() {
        return numBlobs;
    }

    /**
     * @param numBlobs the numBlobs to set
     */
    public void setNumBlobs(int numBlobs) {
        this.numBlobs = numBlobs;
        init();
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return (int) Math.round(speed*100);
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed/100;
    }

    /**
     * @return the threshold
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * @param threshold the threshold to set
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    /**
     * @return the applyThreshold
     */
    public boolean isApplyThreshold() {
        return applyThreshold;
    }

    /**
     * @param applyThreshold the applyThreshold to set
     */
    public void setApplyThreshold(boolean applyThreshold) {
        this.applyThreshold = applyThreshold;
    }
}