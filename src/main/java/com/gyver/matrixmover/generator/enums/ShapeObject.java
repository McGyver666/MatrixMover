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
 * The Enum ShapeObject for Generator Shapes
 * @author Gyver
 */
public enum ShapeObject {

    /**
     * A empty square
     */
    SQUARE_EMPTY(0),
    /**
     * A filled square
     */
    SQUARE_FILLED(1),
    /**
     * A empty circle
     */
    CIRCLE_EMPTY(2),
    /**
     * A filled circle
     */
    CIRCLE_FILLED(3);
    private int mode;

    private ShapeObject(int mode) {
        this.mode = mode;
    }

    /**
     * Returns the mode of this
     * @return the Shape Object
     */
    public int getMode() {
        return mode;
    }

    /**
     * Gets the shape object
     *
     * @param nr the nr
     * @return the shape object
     */
    public static ShapeObject getObjectShape(int nr) {
        for (ShapeObject s : ShapeObject.values()) {
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
            case SQUARE_EMPTY:
                return "Squares Empty";
            case SQUARE_FILLED:
                return "Squares Filled";
            case CIRCLE_EMPTY:
                return "Circles Empty";
            case CIRCLE_FILLED:
                return "Circles Filled";
            default:
                super.toString();
        }
        // if it has no string, return the enum-string
        return super.toString();
    }
}