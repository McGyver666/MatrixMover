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
package com.gyver.matrixmover.generator.enums;

/**
 * The Enum RainDirection
 * 
 * @author Gyver
 */
public enum RainDirection {

    /**
     * Direction left to right
     */
    LEFT_TO_RIGHT(0),
    /**
     * Direction right to left
     */
    RIGHT_TO_LEFT(1),
    /**
     * Direction top to bottom
     */
    TOP_TO_BOTTOM(2),
    /**
     * Direction bottom to top
     */
    BOTTOM_TO_TOP(3);
    private int mode;

    private RainDirection(int mode) {
        this.mode = mode;
    }

    /**
     * Returns the mode of this
     * @return the Direction
     */
    public int getMode() {
        return mode;
    }

    /**
     * Gets the rain direction
     *
     * @param nr the nr
     * @return the rain direction
     */
    public static RainDirection getRainDirection(int nr) {
        for (RainDirection s : RainDirection.values()) {
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
            default:
                super.toString();
        }
        // if it has no string, return the enum-string
        return super.toString();
    }
}
