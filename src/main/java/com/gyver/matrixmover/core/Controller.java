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

import com.gyver.matrixmover.core.audio.AudioCapture;
import com.gyver.matrixmover.core.timer.FadeTimerTask;
import com.gyver.matrixmover.fader.BlackFader;
import com.gyver.matrixmover.fader.CrossFader;
import com.gyver.matrixmover.fader.Fader;
import com.gyver.matrixmover.fader.Fader.FaderName;
import com.gyver.matrixmover.fader.LinearFader;
import com.gyver.matrixmover.fader.WhiteFader;
import com.gyver.matrixmover.generator.Generator;
import com.gyver.matrixmover.generator.enums.GeneratorName;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.gui.LedScreen;
import com.gyver.matrixmover.mapping.OutputMapping;
import com.gyver.matrixmover.mapping.PixelRgbMapping;
import com.gyver.matrixmover.output.Output;
import com.gyver.matrixmover.properties.PropertiesHelper;
import java.util.ArrayList;
import java.util.List;
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
    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    private static Controller instance = new Controller();
    private PropertiesHelper ph = null;
    private Output output = null;
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
    private int crossfaderValue = 0;
    private int masterIntensity = 255;
    private OutputMapping om = null;
    private PixelRgbMapping prm = null;
    private AudioCapture ac = null;
    private boolean isFading = false;
    private Timer fadingTimer = null;

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

        //init and hold the Visuals
        leftVisual = new GeneratorVisual(matrixData);
        rightVisual = new GeneratorVisual(matrixData);

        // CrossFader button in Gui is selected in postInit();
        fader = new CrossFader();

        om = new OutputMapping(matrixData);
        prm = new PixelRgbMapping();

        ac = new AudioCapture();
        if (ac.getAvalibalMixer() != null && ac.getAvalibalMixer().length > 0) {
            ac.startAudio(ac.getAvalibalMixer()[0]);
        }
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

    /**
     * Sets the effect "nr" of side "side" for String "effectString"
     * @param side the side
     * @param nr the number of the effect to be set
     * @param effectString the effect to set to
     */
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

    /**
     * Sets the mixer for side "side" from String "mixerString"
     * @param side the side
     * @param mixerString the mixer to be set to
     */
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
//        LOG.log(Level.FINER, "Set crossfader value to {0}", new Object[]{value});
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
            if (nr == 1) {
                return leftVisual.getActiveVisualSetup().getGenerator1();
            } else if (nr == 2) {
                return leftVisual.getActiveVisualSetup().getGenerator2();
            }
        } else if (side == RIGHT_SIDE) {
            if (nr == 1) {
                return rightVisual.getActiveVisualSetup().getGenerator1();
            } else if (nr == 2) {
                return rightVisual.getActiveVisualSetup().getGenerator2();
            }
        }
        throw new IllegalArgumentException("There is no Generator nr " + nr + " present for side " + side);
    }

    public void sceneSelected(int scene, int side) {
        if (side == LEFT_SIDE) {
            LOG.log(Level.FINE, "Scene {0} selected for left side.", scene);
            Frame.getFrameInstance().getLeftGeneratorPanel().setButtonActive(leftVisual.getActiveScene(), false);
            Frame.getFrameInstance().getLeftGeneratorPanel().setButtonActive(scene, true);
            leftVisual.setActiveScene(scene);
            Frame.getFrameInstance().setComboBoxesForChangedScene(side, leftVisual.getActiveVisualSetup());
        } else if (side == RIGHT_SIDE) {
            LOG.log(Level.FINE, "Scene {0} selected for right side.", scene);
            Frame.getFrameInstance().getRightGeneratorPanel().setButtonActive(rightVisual.getActiveScene(), false);
            Frame.getFrameInstance().getRightGeneratorPanel().setButtonActive(scene, true);
            rightVisual.setActiveScene(scene);
            Frame.getFrameInstance().setComboBoxesForChangedScene(side, rightVisual.getActiveVisualSetup());
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    public void captureAudio() {
        //readout newest audio
        ac.captureAudio();
        //get the levels
        Frame.getFrameInstance().setAudioLevel(ac.getLevel());
    }

    public void computeLeftVisual() {
        //get the calculated image
        leftLedImage = leftVisual.getVisualOutput();
        //push it to led-screen
        leftLedScreen.setPixelImage(leftLedImage);

    }

    public void computeRightVisual() {
        //get the calculated image
        rightLedImage = rightVisual.getVisualOutput();
        //push it to led-screen
        rightLedScreen.setPixelImage(rightLedImage);
    }

    public void computeMasterVisual() {
        //mix left and right image with fader
        outputLedImage = fader.fade(leftLedImage, rightLedImage, crossfaderValue);
        //apply master intensity
        outputLedImage = applyIntensity(outputLedImage, masterIntensity);
        //write image to MasterLedScreen
        masterLedScreen.setPixelImage(outputLedImage);
        //apply the pixel mapping to the image
        int[] outputDeviceImage = om.applyMapping(outputLedImage);
        //apply the rgb mapping
//        outputDeviceImage = prm.applyMapping(outputDeviceImage);
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

    public int getActiveVisualNumber(int side) {
        if (side == LEFT_SIDE) {
            return leftVisual.getActiveScene();
        } else if (side == RIGHT_SIDE) {
            return rightVisual.getActiveScene();
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
    }

    /** 
     * Do stuff that needs a Gui.
     */
    public void postInit() {

        om.setMapping(ph.getOutputMapping());
        prm.setPixelMode(ph.getOutputPixeMode());

        Frame.getFrameInstance().getLeftGeneratorPanel().setButtonActive(leftVisual.getActiveScene(), true);
        Frame.getFrameInstance().getRightGeneratorPanel().setButtonActive(rightVisual.getActiveScene(), true);

        Frame.getFrameInstance().getMasterPanel().setSelectedButton(Frame.getFrameInstance().getMasterPanel().getTbCross());
    }

    public void sceneChanged(int side, int activeVisualNumber, boolean changed) {
        if (side == LEFT_SIDE) {
            leftVisual.getVisualSetup(activeVisualNumber).sceneChanged(changed);
        } else if (side == RIGHT_SIDE) {
            rightVisual.getVisualSetup(activeVisualNumber).sceneChanged(changed);
        } else {
            throw new IllegalArgumentException("Side with ID " + side + " is not existing.");
        }
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

    public void loadScenes() {
        List<VisualSetup[]> arrays = SceneReader.loadScenes(ph.getScenesFile(), matrixData);
        leftVisual.setVisualSetupArray(arrays.get(0));
        rightVisual.setVisualSetupArray(arrays.get(1));

        Frame.getFrameInstance().setComboBoxesForChangedScene(LEFT_SIDE, leftVisual.getActiveVisualSetup());
        Frame.getFrameInstance().setComboBoxesForChangedScene(RIGHT_SIDE, rightVisual.getActiveVisualSetup());

        Frame.getFrameInstance().getLeftGeneratorPanel().setChangedScenesButtonsFromVisualSetupArray(leftVisual.getSceneArray());
        Frame.getFrameInstance().getRightGeneratorPanel().setChangedScenesButtonsFromVisualSetupArray(rightVisual.getSceneArray());


    }

    public void saveScenes() {
        ArrayList<VisualSetup[]> arrays = new ArrayList<VisualSetup[]>();
        arrays.add(leftVisual.getSceneArray());
        arrays.add(rightVisual.getSceneArray());
        SceneReader.writeScenes(arrays, ph.getScenesFile());
    }

    public void autoFade(int fadeTime) {
        if (!isFading) {
            isFading = true;
            fadingTimer = new Timer();
            int currentPosition = Frame.getFrameInstance().getMasterPanel().getSFadePosition().getValue();
            int[] fadeSteps = null;
            float secondsForFading = fadeTime / 1000F;
            
            int numberOfFadeSteps = (int) Math.round(ph.getFps() * secondsForFading);
            
            if(numberOfFadeSteps < 2) {
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

    public void setIsFading(boolean isFading) {
        this.isFading = isFading;
    }

    public void shutDown() {
        if (fadingTimer != null) {
            fadingTimer.cancel();
            fadingTimer.purge();
            fadingTimer = null;
        }
    }
}
