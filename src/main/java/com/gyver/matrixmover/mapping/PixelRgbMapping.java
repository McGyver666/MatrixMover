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
package com.gyver.matrixmover.mapping;

/**
 * Applys a color mapping to each pixel
 * 
 * @author Gyver
 */
public class PixelRgbMapping {

    public static final int PIXEL_MAPPING_RGB = 0;
    public static final int PIXEL_MAPPING_RBG = 1;
    public static final int PIXEL_MAPPING_GRB = 2;
    public static final int PIXEL_MAPPING_GBR = 3;
    public static final int PIXEL_MAPPING_BGR = 4;
    public static final int PIXEL_MAPPING_BRG = 5;
    public static final int PIXEL_MAPPING_STANDARD = PIXEL_MAPPING_RGB;
    private int pixelMode = PIXEL_MAPPING_STANDARD;

    public void setPixelMode(int pixelMode) {
        this.pixelMode = pixelMode;
    }

    public int[] applyMapping(int[] src) {
        // leave pixel as it is if mode is RGB
        if (pixelMode == PIXEL_MAPPING_RBG) {
            return src;
        }

        int[] ret = new int[src.length];

        for (int i = 0; i < src.length; i++) {
            ret[i] = applyMappingToPixel(src[i]);
        }

        return ret;

    }

    private int applyMappingToPixel(int pixel) {

        short red = (short) ((pixel >> 16) & 255);
        short green = (short) ((pixel >> 8) & 255);
        short blue = (short) (pixel & 255);

        int ret = 0;
        
        switch (pixelMode) {
            case PIXEL_MAPPING_RBG:
                ret = (int) ((red << 16) | (blue << 8) | green);
                break;
            case PIXEL_MAPPING_BGR:
                ret = (int) ((blue << 16) | (green << 8) | red);
                break;
            case PIXEL_MAPPING_BRG:
                ret = (int) ((blue << 16) | (red << 8) | green);
                break;
            case PIXEL_MAPPING_GBR:
                ret = (int) ((green << 16) | (blue << 8) | red);
                break;
            case PIXEL_MAPPING_GRB:
                ret = (int) ((green << 16) | (red << 8) | blue);
                break;
        }
        
        return ret;
    }
}
