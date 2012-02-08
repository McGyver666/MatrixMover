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
package com.gyver.matrixmover;

import com.gyver.matrixmover.core.timer.AudioTimerTask;
import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.timer.ExecutionTimerTask;
import com.gyver.matrixmover.gui.DebugFrame;
import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.output.ArtnetDevice;
import com.gyver.matrixmover.output.NullDevice;
import com.gyver.matrixmover.output.Output;
import com.gyver.matrixmover.output.OutputDeviceEnum;
import com.gyver.matrixmover.properties.PropertiesHelper;
import com.gyver.matrixmover.splash.MMSplashScreen;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.sf.nimrod.NimRODLookAndFeel;
import net.sf.nimrod.NimRODTheme;
import say.swing.JFontChooser;

/**
 * Class MatrixMover starting the MatrixMover Programm.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public class MatrixMover {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(MatrixMover.class.getName());
    /** The Constant CONFIG_FILENAME. */
    private static final String CONFIG_FILENAME = "data/config.properties";
    /** The Constant LAF_THEME. */
    private static final String LAF_THEME = "data/matrixmover.theme";
    /** The output. */
    private Output output;
    private static boolean guiReady = false;
    private static Frame guiFrame = null;
    private static Properties config = null;
    private static Timer fpsTimer = null;
    private static Timer audioTimer = null;
    private static Controller controller = null;

    /** 
     * Prepare MatrixMover to start
     */
    private void setup() {

        LOG.log(Level.INFO, "MatrixMover Setup START");
        MMSplashScreen.initSplash();

        MMSplashScreen.setProgress(10, "loading properties");
        config = new Properties();
        try {
            InputStream is = new FileInputStream(CONFIG_FILENAME);
            config.load(is);
            LOG.log(Level.INFO, "Config loaded, {0} entries", config.size());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Failed to load Config", e);
            throw new IllegalArgumentException("Configuration error!", e);
        }
        final PropertiesHelper ph = new PropertiesHelper(config);


        MMSplashScreen.setProgress(25, "setting up output device");
        OutputDeviceEnum outputDeviceEnum = ph.getOutputDevice();
        try {
            switch (outputDeviceEnum) {
                case NULL:
                    this.output = new NullDevice(ph);
                    break;
                case ARTNET:
                    this.output = new ArtnetDevice(ph);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Unable to initialize output device: " + outputDeviceEnum + ". Using Null device instead.", e);
            this.output = new NullDevice(ph);
        }


        LOG.log(Level.INFO, "Starting Core");
        MMSplashScreen.setProgress(35, "startomg programm core");
        controller = Controller.getControllerInstance();
        controller.initController(ph, output);


        LOG.log(Level.FINER, "Loading NimROD LAF");
        MMSplashScreen.setProgress(50, "loading look and feel");
        try {
            NimRODTheme nt = new NimRODTheme(LAF_THEME);

            NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
            NimRODLookAndFeel.setCurrentTheme(nt);
            UIManager.setLookAndFeel(NimRODLF);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MatrixMover.class.getName()).log(Level.SEVERE, null, ex);
        }


        LOG.log(Level.INFO, "Starting Gui");
        MMSplashScreen.setProgress(60, "starting gui");
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                guiFrame = Frame.getFrameInstance();
                guiFrame.initFrame(ph, controller);
                guiFrame.setSize(1000, 600);
                guiFrame.setLocationRelativeTo(null);
                MatrixMover.guiReady(true);
            }
        });
        //wait for gui to fully initialize
        while (!guiReady) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MatrixMover.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        MMSplashScreen.setProgress(75, "controller post init");
        controller.postInit();


        LOG.log(Level.INFO, "Trying to load scenes from scene file");
        MMSplashScreen.setProgress(80, "loading scenes");
        controller.loadScenes();


        MMSplashScreen.setProgress(90, "starting timer");
        LOG.log(Level.INFO, "Starting timer with {0} FPS", ph.getFps());
        fpsTimer = new Timer();
        audioTimer = new Timer();
        long millisecondsDelay = 1000 / ph.getFps();
        fpsTimer.scheduleAtFixedRate(new ExecutionTimerTask(controller), 1, millisecondsDelay);
        audioTimer.schedule(new AudioTimerTask(controller), 1000, millisecondsDelay / 2);


        LOG.log(Level.INFO, "MatrixMover Setup END");
        MMSplashScreen.setProgress(100, "done loading");
        MMSplashScreen.close();
        if (guiFrame != null) {
            guiFrame.setVisible(true);
            JFontChooser fchooser = new JFontChooser();
            int option = fchooser.showDialog(guiFrame);
            if(option == JFontChooser.OK_OPTION) {
                Font font = fchooser.getSelectedFont();
            }
        }
    }

    private static void guiReady(boolean ready) {
        MatrixMover.guiReady = ready;
    }

    /**
     * Main method lunching Matrixmover
     * @param args
     */
    public static void main(String[] args) {
        // Set uncaught exception handler for controlled crashing
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                showCrashMessage(e);
            }
        });

        MatrixMover matmove = new MatrixMover();
        matmove.setup();
    }

    /**
     * Shows an error massage to the user if MatrixMover has chrashed.
     * @param e the throwable to display the massage of
     */
    private static void showCrashMessage(Throwable e) {
        //shut everything down!
        if (fpsTimer != null) {
            fpsTimer.cancel();
            fpsTimer.purge();
            fpsTimer = null;
        }

        if (audioTimer != null) {
            audioTimer.cancel();
            audioTimer.purge();
            audioTimer = null;
        }

        if (controller != null) {
            controller.shutDown();
            controller = null;
        }

        if (guiFrame != null) {
            guiFrame.dispose();
            guiFrame = null;
        }

        System.gc();

        DebugFrame df = new DebugFrame(e);
        df.setSize(705, 405);
        df.setLocationRelativeTo(null);
        df.setVisible(true);
    }
}
