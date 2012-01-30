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
package com.gyver.matrixmover.splash;

import java.awt.Graphics2D;
import java.awt.SplashScreen;

/**
 *
 * @author jonas
 */
public abstract class MMSplashScreen {

    private static SplashScreen splash = null;

    public static void initSplash() {
        splash = SplashScreen.getSplashScreen();
        if (splash == null) {
//            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
//            System.out.println("g is null");
            return;
        }

    }

    public static void close() {
        if (splash != null) {
            try{
                splash.close();
            } catch (IllegalStateException ise){
                return;
            }
        }
    }
}
