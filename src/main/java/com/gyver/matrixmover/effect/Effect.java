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
package com.gyver.matrixmover.effect;

import com.gyver.matrixmover.core.MatrixData;
import java.io.Serializable;

/**
 * The Class Effect is a parent class for every Effect class.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 *  @author Gyver
 */
public abstract class Effect implements Serializable{

    /**
     * The Enum EffectName.
     */
    public enum EffectName {

        /** The PASSTHRU. */
        PASSTHRU(0),
        /** The INVERTER. */
        INVERTER(1),
        /** The EMBOSS */
        EMBOSS(2),
        /** The MONOCROME */
        MONOCROME(3),
        /** The MONOCROME_INVERS */
        MONOCROME_INVERS(4);
        
        /*
         * If you add effect, keep in mind to add a case in 
         * GeneratorVisual.setEffectFromString(int, String), so that a 
         * change via the gui reaches the Visuals. Without changes, 
         * new Effects are not executed.
         */
        public static final String STRING_PASSTHRU = "Pass Thru";
        public static final String STRING_INVERTER = "Inverter";
        public static final String STRING_EMBOSS = "Emboss";
        public static final String STRING_MONOCROME = "Monocrome";
        public static final String STRING_MONOCROME_INVERS = "Monocrome Invers";
        

        /** The id. */
        private int id;

        /**
         * Instantiates a new effect name.
         *
         * @param id the id
         */
        EffectName(int id) {
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
        
        /**
         * Returns a human readable string for the Generator
         */
        @Override
        public String toString(){
            switch (this) {
                case PASSTHRU:      return STRING_PASSTHRU;
                case INVERTER:      return STRING_INVERTER;
                case EMBOSS:        return STRING_EMBOSS;
                case MONOCROME:     return STRING_MONOCROME;
                case MONOCROME_INVERS: return STRING_MONOCROME_INVERS;
            }
            // if it has no string, return the enum-string
            return super.toString();
        }
    }
    /** The effect name. */
    private EffectName effectName;
    /** The internal buffer x size. */
    protected int internalBufferWidth;
    /** The internal buffer y size. */
    protected int internalBufferHeight;

    /**
     * Instantiates a new effect.
     *
     * @param effectName the effect name
     * @param MatrixData the matrixdata
     */
    public Effect(EffectName effectName, MatrixData md) {
        this.effectName = effectName;
        this.internalBufferWidth = md.getWidth();
        this.internalBufferHeight = md.getHeight();
    }

    /**
     * return the image buffer.
     *
     * @param buffer the buffer
     * @return the buffer
     */
    public abstract int[] getBuffer(int[] buffer);

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.effectName.getId();
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public EffectName getName() {
        return effectName;
    }
}