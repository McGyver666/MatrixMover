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
package com.gyver.matrixmover.core;

import com.gyver.matrixmover.fader.CrossFader;
import com.gyver.matrixmover.fader.Fader;
import com.gyver.matrixmover.generator.Generator;
import com.gyver.matrixmover.generator.Generator.GeneratorName;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.LedScreen;
import com.gyver.matrixmover.properties.PropertiesHelper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a singelton. Holds the two GeneratorVisuals, the MasterVisuals and
 * performs all the calculations.
 * 
 * @author Gyver
 */
public class Controller {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    public static final int LEFT_SIDE = 1;
    public static final int RIGHT_SIDE = 2;
    private static Controller instance = new Controller();
    private PropertiesHelper ph = null;
    private MatrixData matrixData = null;
    private LedScreen leftLedScreen = null;
    private LedScreen rightLedScreen = null;
    private LedScreen masterLedScreen = null;
    private GeneratorVisual leftVisual = null;
    private GeneratorVisual rightVisual = null;
    private Fader fader = null;
    private int[] leftLedImage = null;
    private int[] rightLedImage = null;
    private int[] outputLedImage = null;
    private int crossfaderValue = 500;
    private int masterIntensity = 255;

    /**
     * Instantiates a new controller.
     */
    private Controller() {
    }

    public void initController(PropertiesHelper ph) {
        this.ph = ph;
        matrixData = new MatrixData(ph.getOutputDeviceDimensionWidth(), ph.getOutputDeviceDimensionHeight());

        //init and hold the Visuals
        leftVisual = new GeneratorVisual(matrixData);
        rightVisual = new GeneratorVisual(matrixData);

        fader = new CrossFader();
    }

    public void setLedScreens(LedScreen leftVisual, LedScreen rightVisual, LedScreen masterVisual) {
        this.leftLedScreen = leftVisual;
        this.rightLedScreen = rightVisual;
        this.masterLedScreen = masterVisual;
    }

    public void setGenerator(int side, int nr, GeneratorName generator) {
        if (side == LEFT_SIDE) {
            LOG.log(Level.FINE, "Generator{0} on left side set to {1}", new Object[]{nr, generator});
            leftVisual.setGeneratorFromString(nr, generator);
        } else if (side == RIGHT_SIDE) {
            LOG.log(Level.FINE, "Generator{0} on right side set to {1}", new Object[]{nr, generator});
            rightVisual.setGeneratorFromString(nr, generator);
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    public void setEffect(int side, int nr, String effectString) {
        if (side == LEFT_SIDE) {
            LOG.log(Level.FINE, "Effect{0} on left side set to {1}", new Object[]{nr, effectString});
            leftVisual.setEffectFromString(nr, effectString);
        } else if (side == RIGHT_SIDE) {
            LOG.log(Level.FINE, "Effect{0} on right side set to {1}", new Object[]{nr, effectString});
            rightVisual.setEffectFromString(nr, effectString);
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    public void setMixer(int side, String mixerString) {
        if (side == LEFT_SIDE) {
            LOG.log(Level.FINE, "Mixer on left side set to {0}", new Object[]{mixerString});
            leftVisual.setMixerFromString(mixerString);
        } else if (side == RIGHT_SIDE) {
            LOG.log(Level.FINE, "Mixer on right side set to {0}", new Object[]{mixerString});
            rightVisual.setMixerFromString(mixerString);
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    public void setGeneratorIntensity(int side, int nr, int value) {
        if (side == LEFT_SIDE) {
            leftVisual.setGeneratorIntensity(nr, value);
        } else if (side == RIGHT_SIDE) {
            rightVisual.setGeneratorIntensity(nr, value);
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    public void setCrossfaderValue(int value) {
        LOG.log(Level.FINER, "Set crossfader value to {0}", new Object[]{value});
        this.crossfaderValue = value;
    }

    public void setMasterIntensity(int value) {
        masterIntensity = value;
    }

    public static Controller getControllerInstance() {
        return instance;
    }

    public MatrixData getMatrixData() {
        return matrixData;
    }
    
    public Generator getGenerator(int side, int nr){
        if(side == LEFT_SIDE){
            if(nr == 1){
                return leftVisual.getActiveVisualSetup().getGenerator1();
            } else if(nr == 2){
                return leftVisual.getActiveVisualSetup().getGenerator2();
            }
        } else if(side == RIGHT_SIDE){
            if(nr == 1){
                return rightVisual.getActiveVisualSetup().getGenerator1();
            } else if(nr == 2){
                return rightVisual.getActiveVisualSetup().getGenerator2();
            }
        }
        throw new IllegalArgumentException("There is no Generator nr "+nr+" present for side "+side);
    }

    public void sceneSelected(int scene, int side) {
        if (side == LEFT_SIDE) {
            LOG.log(Level.FINE, "Scene {0} selected for left side.", scene);
            leftVisual.setActiveScene(scene);
            Frame.getFrameInstance().setComboBoxesForChangedScene(side, leftVisual.getActiveVisualSetup());
        } else if (side == RIGHT_SIDE) {
            LOG.log(Level.FINE, "Scene {0} selected for right side.", scene);
            rightVisual.setActiveScene(scene);
            Frame.getFrameInstance().setComboBoxesForChangedScene(side, rightVisual.getActiveVisualSetup());
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    void computeLeftVisual() {
        //get the calculated image
        leftLedImage = leftVisual.getVisualOutput();
        //push it to led-screen
        leftLedScreen.setPixelImage(leftLedImage);

    }

    void computeRightVisual() {
        //get the calculated image
        rightLedImage = rightVisual.getVisualOutput();
        //push it to led-screen
        rightLedScreen.setPixelImage(rightLedImage);
    }

    void computeMasterVisual() {
        //mix left and right image with fader
        outputLedImage = fader.fade(leftLedImage, rightLedImage, crossfaderValue);
        //apply master intensity
        outputLedImage = applyIntensity(outputLedImage, masterIntensity);
        //write image to MasterLedScreen
        masterLedScreen.setPixelImage(outputLedImage);
        //apply the mapping to the image

        //give image to outputDevice

    }

    private int[] applyIntensity(int[] src, int value) {
        int[] ret = new int[src.length];
        short red, green, blue;
        int col;
        float ratio = (value / 255f);

        for (int i = 0; i < src.length; i++) {
            col = src[i];
            red = (short) (Math.round(((col >> 16) & 255) * ratio));
            green = (short) (Math.round(((col >> 8) & 255) * ratio));
            blue = (short) (Math.round((col & 255) * ratio));

            ret[i] = (red << 16) | (green << 8) | blue;
        }
        return ret;
    }

    public int getFps() {
        return ph.getFps();
    }
}
