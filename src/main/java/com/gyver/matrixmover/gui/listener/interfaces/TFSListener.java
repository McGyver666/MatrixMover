/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.gui.listener.interfaces;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Interface for listener listening to jTextFieldSlider components
 * 
 * @author McGyver
 */
public interface TFSListener extends ChangeListener {
    
    @Override
    public void stateChanged(ChangeEvent e);
}
