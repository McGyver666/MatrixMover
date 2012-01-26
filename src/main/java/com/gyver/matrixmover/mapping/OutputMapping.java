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

import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.gui.Frame;

/**
 * Applies a pixel wise mapping to the output. 
 * 
 * @author Gyver
 */
public class OutputMapping {

    public MatrixData md = null;
    public int[] mapping = null;

    public OutputMapping(MatrixData md) {
        this.md = md;
        this.mapping = new int[md.getHeight() * md.getWidth()];

        // initialize with default mapping
        this.mapping = getDefaultMapping();
    }

    public void setMapping(int[] newMapping) {
        if(newMapping == null){
            this.mapping = getDefaultMapping();
            return;
        }
        System.arraycopy(newMapping, 0, this.mapping, 0, Math.min(this.mapping.length, newMapping.length));
    }

    public int[] applyMapping(int[] src) {
        int[] ret = new int[src.length];

        try {
            for (int i = 0; i < src.length; i++) {
                ret[mapping[i]] = src[i];
            }
        } catch (IndexOutOfBoundsException e) {
            Frame.getFrameInstance().showWarning("Mapping maps to outside of Matrix. Using default mapping instead!");
            this.mapping = getDefaultMapping();
            return applyMapping(src);
        }

        return ret;

    }

    private int[] getDefaultMapping() {
        int[] defaultMapping = new int[md.getHeight() * md.getWidth()];

        // initialize with default mapping
        for (int i = 0; i < defaultMapping.length; i++) {
            defaultMapping[i] = i;
        }

        return defaultMapping;
    }
}
