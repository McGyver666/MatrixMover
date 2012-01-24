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
package com.gyver.matrixmover.gui.component;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * The class JSceneButton. This is a button with the ability to easiely highlight the background and give a button a
 * triangular in the background, when there was a change set.
 *
 * @author Gyver
 */
public class JSceneButton extends JButton {

    private boolean active;
    private boolean changed;
    private final Color background = new Color(180, 50, 50);
    private final Color triangle = new Color(0, 192, 0);
    private final int TRIANGLE_SIZE = 6;

    public JSceneButton() {
        super();
        this.active = false;
        this.changed = false;
    }
    
    public void setActive(boolean active){
        this.active = active;
        this.repaint();
    }
    
    public void setChanged(boolean changed){
        this.changed = changed;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        if (active) {
            g.setColor(background);
            g.fillRect(0, 0, getSize().width, getSize().height);
        } 
        
        if (changed) {
            g.setColor(triangle);
            int[] x = {getSize().width, getSize().width, getSize().width-TRIANGLE_SIZE, getSize().width};
            int[] y = {0, TRIANGLE_SIZE, 0, 0};
            g.fillPolygon(x, y, x.length);
        }
        
        super.paintComponent(g);

    }
}
