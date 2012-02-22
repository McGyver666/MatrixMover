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
 * The Enum ScrollMode.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 *
 * @author Gyver
 */

public enum ScrollMode {

    /**
     * Mode left to right
     */
    LEFT_TO_RIGHT(0),
    /**
     * Mode right to left
     */
    RIGHT_TO_LEFT(1),
    /**
     * Mode top to bottom
     */
    TOP_TO_BOTTOM(2),
    /**
     * Mode bottom to top
     */
    BOTTOM_TO_TOP(3),
    /**
     * Mode right bottom to left top
     */
    RIGHT_BOTTOM_TO_LEFT_TOP(4),
    /**
     * Mode left bottom to right top
     */
    LEFT_BOTTOM_TO_RIGHT_TOP(5),
    /**
     * Mode right top to left bottom
     */
    RIGHT_TOP_TO_LEFT_BOTTOM(6),
    /**
     * Mode left top to right bottom
     */
    LEFT_TOP_TO_RIGHT_BOTTOM(7),
    /**
     * Mode moddle to sides vertical
     */
    MIDDLE_TO_SIDES_VERTICAL(8),
    /**
     * Mode sides to middle vertical
     */
    SIDES_TO_MIDDLE_VERTICAL(9),
    /**
     * Mode middle to sides horizontal
     */
    MIDDLE_TO_SIDES_HORIZONTAL(10),
    /**
     * Mode sides to middle horizontal
     */
    SIDES_TO_MIDDLE_HORIZONTAL(11),
    /**
     * Mode explode circle
     */
    EXPLODE_CIRCLE(12),
    /**
     * Mode implode circle
     */
    IMPLODE_CIRCLE(13),
    /**
     * Mode explode diamond
     */
    EXPLODE_DIAMOND(14),
    /**
     * Mode implode diamond
     */
    IMPLODE_DIAMOND(15),
    /**
     * Mode explode diamond
     */
    EXPLODE_HYPERBEL(16),
    /**
     * Mode implode diamond
     */
    IMPLODE_HYPERBEL(17);
    
    
    /** The mode. */
    private int mode;

    /**
     * Instantiates a new scroll mode.
     *
     * @param mode the mode
     */
    private ScrollMode(int mode) {
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
     * Gets the scroll mode.
     *
     * @param nr the nr
     * @return the scroll mode
     */
    public static ScrollMode getScrollMode(int nr) {
        for (ScrollMode s : ScrollMode.values()) {
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
            case RIGHT_BOTTOM_TO_LEFT_TOP:
                return "Right-Bottom to Left-Top";
            case LEFT_BOTTOM_TO_RIGHT_TOP:
                return "Left-Bottom to Right-Top";
            case RIGHT_TOP_TO_LEFT_BOTTOM:
                return "Right-Top to Left-Bottom";
            case LEFT_TOP_TO_RIGHT_BOTTOM:
                return "Left-Top to Right-Bottom";
            case MIDDLE_TO_SIDES_VERTICAL:
                return "Middle to Sides Vertical";
            case SIDES_TO_MIDDLE_VERTICAL:
                return "Sides to Middle Vertical";
            case MIDDLE_TO_SIDES_HORIZONTAL:
                return "Middle to Sides Horizontal";
            case SIDES_TO_MIDDLE_HORIZONTAL:
                return "Sides to Biddle Horizontal";
            case EXPLODE_CIRCLE:
                return "Explode Circle";
            case IMPLODE_CIRCLE:
                return "Implode Circle";
            case EXPLODE_DIAMOND:
                return "Explode Diamond";
            case IMPLODE_DIAMOND:
                return "Implode Diamond";
            case EXPLODE_HYPERBEL:
                return "Explode Hyperbel";
            case IMPLODE_HYPERBEL:
                return "Implode Hyperbel";
            default:
                super.toString();
        }
        // if it has no string, return the enum-string
        return super.toString();
    }
}