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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonas
 */
public abstract class MMSplashScreen {

    private static final int BAR_OFFSET = 10;
    private static SplashScreen splash = null;
    private static Graphics2D g = null;

    public static void initSplash() {
        splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            return;
        }
        g = splash.createGraphics();
        if (g == null) {
            return;
        }

    }
    
    public static void setProgress(int i, String text){
        
        int barWidth = splash.getBounds().width - BAR_OFFSET - BAR_OFFSET;
        int barYPos = splash.getBounds().height - 25;
        
        //clear everything
        g.setBackground(new Color(0, 0, 0, 0));
        g.clearRect(0, 0, splash.getBounds().width, splash.getBounds().height);
        
        g.setColor(new Color(192,0,0));
        g.fillRect(BAR_OFFSET, barYPos, (int) Math.round(barWidth / 100F * (float) i), 5);
        
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Dialog", Font.PLAIN, 10));
        g.drawString(text, BAR_OFFSET, barYPos+15);
        
        splash.update();
        try {
            Thread.sleep(75);
        } catch (InterruptedException ex) {
            Logger.getLogger(MMSplashScreen.class.getName()).log(Level.SEVERE, null, ex);
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
