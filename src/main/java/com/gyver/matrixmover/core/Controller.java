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

import com.gyver.matrixmover.core.audio.AudioCaptureThread;
import com.gyver.matrixmover.core.timer.AutoSceneCyclerTimerTask;
import com.gyver.matrixmover.core.timer.FadeTimerTask;
import com.gyver.matrixmover.effect.HistoryMean;
import com.gyver.matrixmover.fader.BlackFader;
import com.gyver.matrixmover.fader.CrossFader;
import com.gyver.matrixmover.fader.Fader;
import com.gyver.matrixmover.fader.Fader.FaderName;
import com.gyver.matrixmover.fader.LinearFader;
import com.gyver.matrixmover.fader.WhiteFader;
import com.gyver.matrixmover.generator.Generator;
import com.gyver.matrixmover.generator.RandomPixel;
import com.gyver.matrixmover.generator.SimpleColorGenerator;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.LedScreen;
import com.gyver.matrixmover.gui.MasterPanel;
import com.gyver.matrixmover.mapping.OutputMapping;
import com.gyver.matrixmover.mapping.PixelRgbMapping;
import com.gyver.matrixmover.mixer.AddSat;
import com.gyver.matrixmover.output.Output;
import com.gyver.matrixmover.properties.PropertiesHelper;
import java.awt.Color;
import java.io.File;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a singelton. Performs all the calculations.
 * 
 * @author Gyver
 */
public class Controller {

    /**
     * The left side generator setup
     */
    public static final int LEFT_SIDE = 1;
    /**
     * The right side generator setup
     */
    public static final int RIGHT_SIDE = 2;
    
    private final float VU_DECAY = 2.5F;
    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    private static Controller instance = new Controller();
    private PropertiesHelper ph = null;
    private Output output = null;
    private MatrixData matrixData = null;
    private LedScreen leftLedScreen = null;
    private LedScreen rightLedScreen = null;
    private LedScreen masterLedScreen = null;
    private VisualSetup leftVisual = null;
    private VisualSetup rightVisual = null;
    private Fader fader = null;
    private int[] leftLedImage = null;
    private int[] rightLedImage = null;
    private int[] outputLedImage = null;
    private int crossfaderValue = 0;
    private int masterIntensity = 255;
    private float[] decayedLevel = null;
    private OutputMapping om = null;
    private PixelRgbMapping prm = null;
    private boolean isFading = false;
    private Timer fadingTimer = null;
    private AudioCaptureThread act = null;
    private Thread actThread = null;
    private Timer autoSceneCyclerTimer = null;
    private boolean isRunning;
    private boolean isPlayer = false;
    private Color GlobalColor = new Color(64, 128, 128);
    private ChaseController chaseController;

    /**
     * Instantiates a new controller.
     */
    private Controller() {
    }

    /**
     * Inits a new Controller.
     * @param ph the PropertiesHelper
     * @param output The Output to use
     */
    public void initController(PropertiesHelper ph, Output output) {
        this.ph = ph;
        this.output = output;
        matrixData = new MatrixData(ph.getOutputDeviceDimensionWidth(), ph.getOutputDeviceDimensionHeight());

        this.decayedLevel = new float[2];

        //init and hold the Visuals
        leftVisual = new VisualSetup(matrixData);
        rightVisual = new VisualSetup(matrixData);

        // CrossFader button in Gui is selected in postInit();
        fader = new CrossFader();

        om = new OutputMapping(matrixData);
        prm = new PixelRgbMapping();

        chaseController = new ChaseController();
    }

    /**
     * Sets the LED-Simulation Screens
     * @param leftVisual
     * @param rightVisual
     * @param masterVisual
     */
    public void setLedScreens(LedScreen leftVisual, LedScreen rightVisual, LedScreen masterVisual) {
        this.leftLedScreen = leftVisual;
        this.rightLedScreen = rightVisual;
        this.masterLedScreen = masterVisual;
    }

    /**
     * Sets the generator "nr" of side "side" for GeneratorName "generator"
     * @param side the side
     * @param nr the number of the generator to be set
     * @param generator the generator to set to
     */
    public void setGenerator(int side, int nr, GeneratorName generator) {
        switch (side) {
            case LEFT_SIDE:
                LOG.log(Level.FINE, "Generator{0} on left side set to {1}", new Object[]{nr, generator});
                leftVisual.setGeneratorFromString(nr, generator.toString());
                break;
            case RIGHT_SIDE:
                LOG.log(Level.FINE, "Generator{0} on right side set to {1}", new Object[]{nr, generator});
                rightVisual.setGeneratorFromString(nr, generator.toString());
                break;
            default:
                throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    /**
     * Sets the effect "nr" of side "side" for String "effectString"
     * @param side the side
     * @param nr the number of the effect to be set
     * @param effectString the effect to set to
     */
    public void setEffect(int side, int nr, String effectString) {
        switch (side) {
            case LEFT_SIDE:
                LOG.log(Level.FINE, "Effect{0} on left side set to {1}", new Object[]{nr, effectString});
                leftVisual.setEffectFromString(nr, effectString);
                break;
            case RIGHT_SIDE:
                LOG.log(Level.FINE, "Effect{0} on right side set to {1}", new Object[]{nr, effectString});
                rightVisual.setEffectFromString(nr, effectString);
                break;
            default:
                throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    /**
     * Sets the mixer for side "side" from String "mixerString"
     * @param side the side
     * @param mixerName the mixer to be set to
     */
    public void setMixer(int side, int nr, String mixerName) {
        switch (side) {
            case LEFT_SIDE:
                LOG.log(Level.FINE, "Mixer on left side set to {0}", new Object[]{mixerName});
                leftVisual.setMixerFromString(nr, mixerName);
                break;
            case RIGHT_SIDE:
                LOG.log(Level.FINE, "Mixer on right side set to {0}", new Object[]{mixerName});
                rightVisual.setMixerFromString(nr, mixerName);
                break;
            default:
                throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    public void setGeneratorIntensity(int side, int nr, int value) {
        switch (side) {
            case LEFT_SIDE:
                leftVisual.setGeneratorIntensity(nr, value);
                break;
            case RIGHT_SIDE:
                rightVisual.setGeneratorIntensity(nr, value);
                break;
            default:
                throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }
    
    

    public void setCrossfaderValue(int value) {
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

    public Generator getGenerator(int side, int nr) {
        if (side == LEFT_SIDE) {
            return leftVisual.getGenerator(nr);
        } else if (side == RIGHT_SIDE) {
            return rightVisual.getGenerator(nr);
        }
        throw new IllegalArgumentException("There is no Generator nr " + nr + " present for side " + side);
    }

//    public void sceneSelected(int scene, int side) 
//        if (side == BOTTOM_SIDE) {
//            LOG.log(Level.FINE, "Scene {0} selected for left side.", scene);
//            Frame.getFrameInstance().getLeftGeneratorPanel().setButtonActive(leftVisual.getActiveScene(), false);
//            Frame.getFrameInstance().getLeftGeneratorPanel().setButtonActive(scene, true);
//            leftVisual.setActiveScene(scene);
//            Frame.getFrameInstance().setComboBoxesForChangedScene(side, leftVisual.getActiveVisualSetup());
//        } else if (side == TOP_SIDE) {
//            LOG.log(Level.FINE, "Scene {0} selected for right side.", scene);
//            Frame.getFrameInstance().getRightGeneratorPanel().setButtonActive(rightVisual.getActiveScene(), false);
//            Frame.getFrameInstance().getRightGeneratorPanel().setButtonActive(scene, true);
//            rightVisual.setActiveScene(scene);
//            Frame.getFrameInstance().setComboBoxesForChangedScene(side, rightVisual.getActiveVisualSetup());
//        } else {
//            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
//        }
//    }

    public void computeLeftVisual() {
        //get the calculated image
        leftLedImage = leftVisual.getSceneOutput();
        //push it to led-screen
        if (leftLedScreen != null) {
            leftLedScreen.setPixelImage(leftLedImage);
        }
    }

    public void computeRightVisual() {
        //get the calculated image
        rightLedImage = rightVisual.getSceneOutput();
        //push it to led-screen
        if (rightLedScreen != null) {
            rightLedScreen.setPixelImage(rightLedImage);
        }
    }

    public void computeMasterVisual() {
        //mix left and right image with fader
        outputLedImage = fader.fade(leftLedImage, rightLedImage, crossfaderValue);
        //apply master intensity
        outputLedImage = applyIntensity(outputLedImage, masterIntensity);
        //write image to MasterLedScreen
        if (masterLedScreen != null) {
            masterLedScreen.setPixelImage(outputLedImage);
        }
        //apply the pixel mapping to the image
        int[] outputDeviceImage = om.applyMapping(outputLedImage);
        //apply the rgb mapping
        outputDeviceImage = prm.applyMapping(outputDeviceImage);
        //give image to outputDevice
        output.update(outputDeviceImage);
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
    
    public VisualSetup getActiveVisualSetup(int side) {
        if (side == LEFT_SIDE) {
            return leftVisual;
        } else if (side == RIGHT_SIDE) {
            return rightVisual;
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }
    
    public void setVisualSetup(VisualSetup vs, int side) {
        if (side == LEFT_SIDE) {
            leftVisual = vs;
            if (!Controller.getControllerInstance().isPlayer()) {
                Frame.getFrameInstance().getLeftGeneratorSetup().setVisualSetup(vs);
            }
        } else if (side == RIGHT_SIDE) {
            rightVisual = vs;
            if (!Controller.getControllerInstance().isPlayer()) {
                Frame.getFrameInstance().getRightGeneratorSetup().setVisualSetup(vs);
            }
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    /** 
     * Do stuff that needs a Gui.
     */
    public void postInit() {
        MasterPanel masterPanel = Frame.getFrameInstance().getMasterPanel();
        masterPanel.setSelectedButton(masterPanel.getTbCross());
        om.setMapping(ph.getOutputMapping());
        prm.setPixelMode(ph.getOutputPixeMode());
    }
    
    public void postInitPlayer() {
        //MasterPanel masterPanel = Frame.getFrameInstance().getMasterPanel();
        //masterPanel.setSelectedButton(masterPanel.getTbCross());
        om.setMapping(ph.getOutputMapping());
        prm.setPixelMode(ph.getOutputPixeMode());
    }


    public void changeFaderMode(FaderName faderName) {
        if (faderName.compareTo(FaderName.CROSSFADE) == 0) {
            fader = new CrossFader();
        } else if (faderName.compareTo(FaderName.LINEAR) == 0) {
            fader = new LinearFader();
        } else if (faderName.compareTo(FaderName.WHITE) == 0) {
            fader = new WhiteFader();
        } else if (faderName.compareTo(FaderName.BLACK) == 0) {
            fader = new BlackFader();
        }

    }

    public void autoFade(int fadeTime, int currentPosition) {
        if (!isFading) {
            isFading = true;
            fadingTimer = new Timer();
            if (!Controller.getControllerInstance().isPlayer()) {
                currentPosition = Frame.getFrameInstance().getMasterPanel().getSFadePosition().getValue();
            }
            int[] fadeSteps = null;
            float secondsForFading = fadeTime / 1000F;

            int numberOfFadeSteps = (int) Math.round(ph.getFps() * secondsForFading);

            if (numberOfFadeSteps < 2) {
                Frame.getFrameInstance().showWarning("Fadetime is to small. Give it more time!");
                isFading = false;
                return;
            }

            fadeSteps = new int[numberOfFadeSteps];

            //fade to right side
            if (currentPosition < 500) {
                for (int i = 0; i < fadeSteps.length; i++) {
                    fadeSteps[i] = currentPosition + Math.round((1000 - currentPosition) * (i / (float) (fadeSteps.length - 1)));
                }
            } else {
                for (int i = 0; i < fadeSteps.length; i++) {
                    fadeSteps[i] = currentPosition - Math.round(currentPosition * (i / (float) (fadeSteps.length - 1)));
                }
            }
            long millisecondsDelay = 1000 / ph.getFps();
            fadingTimer.scheduleAtFixedRate(new FadeTimerTask(this, fadeSteps), 0, millisecondsDelay);


        }

    }

    public void setAudioCapture(AudioCaptureThread act, Thread actThread) {
        this.act = act;
        this.actThread = actThread;
    }

    public void setIsFading(boolean isFading) {
        this.isFading = isFading;
    }

    public float[] getSpectrum(int bands) {
        if (act != null) {
            return act.getSpectrum(bands);
        }
        return new float[bands];
    }

    public void shutDown() {
        if (fadingTimer != null) {
            fadingTimer.cancel();
            fadingTimer.purge();
            fadingTimer = null;
        }
    }

    public void updateVuMeter() {
        float[] newLevel = new float[2];

        if (act != null) {
            newLevel = act.getRmsLevel();
        }

        // decay left channel
        if (newLevel[0] >= decayedLevel[0]) {
            decayedLevel[0] = newLevel[0];
        } else {
            decayedLevel[0] -= VU_DECAY;
            if (newLevel[0] >= decayedLevel[0]) {
                decayedLevel[0] = newLevel[0];
            }
        }

        // right channel
        if (newLevel[1] >= decayedLevel[1]) {
            decayedLevel[1] = newLevel[1];
        } else {
            decayedLevel[1] -= VU_DECAY;
            if (newLevel[1] >= decayedLevel[1]) {
                decayedLevel[1] = newLevel[1];
            }
        }

        Frame.getFrameInstance().setAudioLevel(decayedLevel);
    }

    public void startAutoSceneCycler(int sec, File sceneDir) {
        // when reading a new directory the cycler maybe already running.
        stopAutoSceneCycler();
        
        autoSceneCyclerTimer = new Timer();
        AutoSceneCyclerTimerTask asctt = new AutoSceneCyclerTimerTask(Controller.getControllerInstance());
        try {
            if (!asctt.setSceneDirectory(sceneDir)) {
                if(isPlayer) {
                    LOG.log(Level.WARNING, "Directory contains no scene files!");
                }
                else {
                    Frame.getFrameInstance().showWarning("Directory containts no scene files!");
                }
                return;
            }
            int timeToWait = sec * 1000;
            autoSceneCyclerTimer.scheduleAtFixedRate(asctt, 0, timeToWait);
            isRunning = true;
        } catch (NumberFormatException nfe) {
            if(isPlayer) {
                LOG.log(Level.WARNING, "Input is not valid. Check number format.");
            }
            else {
                Frame.getFrameInstance().showWarning("Input is not valid. Check number format.");
            }
        }
    }
    
    public void stopAutoSceneCycler() {
        if (autoSceneCyclerTimer != null) {
            autoSceneCyclerTimer.cancel();
            isRunning = false;
        }
    }

    public void isPlayer(boolean b) {
        this.isPlayer = b;
    }

    public boolean isPlayer() {
        return this.isPlayer;
    }

    /**
     * Sets a default Simple Color Generator to the left visual setup
     */
    public void loadSimpleScene() {
        //VisualSetup vs = new VisualSetup(matrixData);
        //vs.setGenerator(new SimpleColorGenerator(matrixData), LEFT_SIDE);
        LOG.log(Level.INFO, "Loading simple scene");
        leftVisual.clear();
        leftVisual.setGenerator(new SimpleColorGenerator(matrixData), 0);
        ((SimpleColorGenerator)leftVisual.getGenerator(0)).setColor(GlobalColor);
        setCrossfaderValue(0);
        
    }
    
    /**
     * Sets a default Simple Color Generator and a random pixel generator to the left visual setup
     */
    public void loadNoiseScene() {
        //VisualSetup vs = new VisualSetup(matrixData);
        //vs.setGenerator(new SimpleColorGenerator(matrixData), LEFT_SIDE);
        LOG.log(Level.INFO, "Loading noise scene");
        
        leftVisual.clear();
        setCrossfaderValue(0);
        
        leftVisual.setGenerator(new SimpleColorGenerator(matrixData), 0);
        ((SimpleColorGenerator)leftVisual.getGenerator(0)).setColor(GlobalColor);

        
        leftVisual.addGeneratorSetup(matrixData);
        leftVisual.setGenerator(new RandomPixel(matrixData), 1);
        ((RandomPixel)leftVisual.getGenerator(1)).setColorNoise(128);
        ((RandomPixel)leftVisual.getGenerator(1)).setTicksToRefresh(25);
        
        leftVisual.setEffect(new HistoryMean(matrixData), 1);
        ((HistoryMean)leftVisual.getEffect(1)).setHistoryLength(25);
        
        leftVisual.setMixer(new AddSat(), 1);
        
        leftVisual.setGeneratorIntensity(64, 1);
        
    }
    

    public void setGlobalColor(Color newCol) {
        GlobalColor = newCol;
        ((SimpleColorGenerator)leftVisual.getGenerator(0)).setColor(GlobalColor);
    }
    
    public ChaseController getChaseControllerInstance() {
        return this.chaseController;
    }
}
