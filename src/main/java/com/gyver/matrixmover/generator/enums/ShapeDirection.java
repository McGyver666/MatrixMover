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
 * The Enum ShapeDirection for Generator Shapes
 * @author Gyver
 */
public enum ShapeDirection {

    /**
     * Shapes exploding
     */
    EXPLODE(0),
    /**
     * Shapes imploding
     */
    IMPODE(1);
    private int mode;

    private ShapeDirection(int mode) {
        this.mode = mode;
    }

    /**
     * Returns the ShapeDirection mode of this
     * @return the ShapeDirection mode
     */
    public int getMode() {
        return mode;
    }

    /**
     * Gets the shape direction
     *
     * @param nr the nr
     * @return the shape direction
     */
    public static ShapeDirection getObjectDirection(int nr) {
        for (ShapeDirection s : ShapeDirection.values()) {
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
            case IMPODE:
                return "Implode";
            case EXPLODE:
                return "Explode";
            default:
                super.toString();
        }
        // if it has no string, return the enum-string
        return super.toString();
    }
    
    public static ShapeDirection fromString(String dir) {
        switch (dir) {
            case "Implode":
                return IMPODE;
            case "Explode":
                return EXPLODE;
            default:
                return null;
        }        
    }
}