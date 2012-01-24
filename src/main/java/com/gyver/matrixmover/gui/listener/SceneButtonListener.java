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
package com.gyver.matrixmover.gui.listener;

import com.gyver.matrixmover.core.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author jonas
 */
public class SceneButtonListener implements ActionListener{
    
    private int side = 0;
    
    public SceneButtonListener(int side){
        this.side = side;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int buttonText = Integer.parseInt(((JButton)ae.getSource()).getText());
        Controller.getControllerInstance().sceneSelected(buttonText, side);
    }
    
}
