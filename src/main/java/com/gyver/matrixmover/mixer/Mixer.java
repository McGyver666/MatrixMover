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
package com.gyver.matrixmover.mixer;

/**
 * The class Mixer, a parent class for all mixer classes, mixes two buffers 
 * together.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 *
 */
public abstract class Mixer {

    /**
     * The Enum MixerName.
     */
    public enum MixerName {

        /** The PASSTHRU. */
        PASSTHRU(0),
        /** The ADDSAT. */
        ADDSAT(1),
        /** The MULTIPLY. */
        MULTIPLY(2),
        /** The MIX. */
        MIX(3),
        /** The NEGATIV e_ multiply. */
        NEGATIVE_MULTIPLY(4),
        /** The XOR. */
        XOR(5),
        /** The MINU s_ half. */
        MINUS_HALF(6),
        /** The EITHER. */
        EITHER(7);
        
        
        /*
         * If you add a mixer, keep in mind to add a case in 
         * GeneratorVisual.setMixerFromString(int, String), so that a 
         * change via the gui reaches the Visuals. Without changes, 
         * new Mixers are not executed.
         */
        public static final String STRING_PASSTHRU = "Pass Thru";
        public static final String STRING_ADDSAT = "Add Sat";
        public static final String STRING_MULTIPLY = "Multiply";
        public static final String STRING_MIX = "Mix";
        public static final String STRING_NEGATIVE_MULTIPLY = "Negative Multiply";
        public static final String STRING_XOR = "Xor";
        public static final String STRING_MINUS_HALF = "Minus Half";
        public static final String STRING_EITHER = "Either";
        
        
        /** The id. */
        private int id;

        /**
         * Instantiates a new mixer name.
         *
         * @param id the id
         */
        MixerName(int id) {
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
         * Returns a human readable string for the Mixer
         */
        @Override
        public String toString() {
            switch (this) {
                case PASSTHRU:
                    return STRING_PASSTHRU;
                case MULTIPLY:
                    return STRING_MULTIPLY;
                case ADDSAT:
                    return STRING_ADDSAT;
                case MIX:
                    return STRING_MIX;
                case NEGATIVE_MULTIPLY:
                    return STRING_NEGATIVE_MULTIPLY;
                case XOR:
                    return STRING_XOR;
                case MINUS_HALF:
                    return STRING_MINUS_HALF;
                case EITHER:
                    return STRING_EITHER;
                    
            }
            // if it has no string, return the enum-string
            return super.toString();
        }
    }
    /** The mixer name. */
    private MixerName mixerName;

    /**
     * Instantiates a new mixer.
     *
     * @param controller the controller
     * @param mixerName the mixer name
     * @param resizeOption the resize option
     */
    public Mixer(MixerName mixerName) {
        this.mixerName = mixerName;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public MixerName getName() {
        return mixerName;
    }

    /**
     * Gets the buffer.
     *
     * @param visual the visual
     * @return the buffer
     */
    public abstract int[] getBuffer(int[] src1, int[] src2);

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.mixerName.getId();
    }
}