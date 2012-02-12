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
package com.gyver.matrixmover.generator.enums;

/**
 * The Enum TextwriterScrollMode.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 *
 * @author Gyver
 */

public enum TextwriterScrollMode {

    /**
     * Mode left to right
     */
    LEFT_TO_RIGHT(0),
    /**
     * Mode left to right continuous
     */
    LEFT_TO_RIGHT_CONTINUOUS(1),
    /**
     * Mode right to left
     */
    RIGHT_TO_LEFT(2),
    /**
     * Mode right to left continuous
     */
    RIGHT_TO_LEFT_CONTINUOUS(3),
    /**
     * Mode top to bottom
     */
    TOP_TO_BOTTOM(4),
    /**
     * Mode top to bottom continuous
     */
    TOP_TO_BOTTOM_CONTINUOUS(5),
    /**
     * Mode bottom to top
     */
    BOTTOM_TO_TOP(6),
    /**
     * Mode bottom to top continuous
     */
    BOTTOM_TO_TOP_CONTINUOUS(7);
    /** The mode. */
    private int mode;

    /**
     * Instantiates a new scroll mode.
     *
     * @param mode the mode
     */
    private TextwriterScrollMode(int mode) {
        this.mode = mode;
    }

    /**
     * Gets the mode.
     *
     * @return the mode
     */
    public int getMode() {
        return mode;
    }

    /**
     * Gets the textwriter scroll mode.
     *
     * @param nr the nr
     * @return the scroll mode
     */
    public static TextwriterScrollMode getTextwriterScrollMode(int nr) {
        for (TextwriterScrollMode s : TextwriterScrollMode.values()) {
            if (s.getMode() == nr) {
                return s;
            }
        }
        return null;
    }

    /**
     * Returns this as a descriptive string.
     * @return this as a string.
     */
    @Override
    public String toString() {
        switch (this) {
            case LEFT_TO_RIGHT:
                return "Left to Right";
            case RIGHT_TO_LEFT:
                return "Right to Left";
            case TOP_TO_BOTTOM:
                return "Top to Bottom";
            case BOTTOM_TO_TOP:
                return "Bottom to Top";
            case LEFT_TO_RIGHT_CONTINUOUS:
                return "Left to Right Continuous";
            case RIGHT_TO_LEFT_CONTINUOUS:
                return "Right to Left Continuous";
            case TOP_TO_BOTTOM_CONTINUOUS:
                return "Top to Bottom Continuous";
            case BOTTOM_TO_TOP_CONTINUOUS:
                return "Bottom to Top Continuous";
            default:
                super.toString();
        }
        // if it has no string, return the enum-string
        return super.toString();
    }
}