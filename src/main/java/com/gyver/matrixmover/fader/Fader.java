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
package com.gyver.matrixmover.fader;

import java.util.logging.Logger;

/**
 * The class Fader is a parent class for all fader classes to mix two 
 * screens to a master output.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public abstract class Fader {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(Fader.class.getName());

    /**
     * The Enum FaderName.
     */
    public enum FaderName {

        /** The SWITCH. */
        LINEAR(0),
        /** The CROSSFADE. */
        CROSSFADE(1),
        WHITE(2),
        BLACK(3),
        /** The SLIDE upside down. */
        SLIDE_UPSIDE_DOWN(4),
        /** The SLIDE left right. */
        SLIDE_LEFT_RIGHT(5);
        /** The id. */
        private int id;

        /**
         * Instantiates a new fader name.
         *
         * @param id the id
         */
        FaderName(int id) {
            this.id = id;
        }

        /**
         * Gets the id.
         *
         * @return the id
         */
        public int getId() {
            return id;
        }
    }
    
    /** The fader name. */
    private FaderName faderName;
    /** fade time in ms. */
    protected int fadeTime;
    /** The new visual. */
    protected int newVisual;
    /** The screen output. */
    protected int screenOutput;
    /** The steps. */
    protected int steps;
    /** The current step. */
    protected int currentStep;
    /** The internal buffer x size. */
    protected int internalBufferXSize;
    /** The internal buffer y size. */
    protected int internalBufferYSize;
    /** The started. */
    private boolean started;

    /**
     * Instantiates a new fader.
     *
     * @param faderName the fader name
     * @param fadeTime the fade time
     */
    public Fader(FaderName faderName) {
        this.faderName = faderName;
    }

    /**
     * Gets the buffer.
     * 
     * 
     * @param position the position of the fader from 0 to 1000
     * @return the buffer
     */
    public abstract int[] fade(int[] left, int[] right, int position);

    

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.faderName.getId();
    }

}