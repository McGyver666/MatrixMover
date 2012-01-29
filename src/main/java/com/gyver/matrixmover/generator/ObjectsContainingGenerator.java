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
package com.gyver.matrixmover.generator;

import com.gyver.matrixmover.core.MatrixData;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gyver
 */
public abstract class ObjectsContainingGenerator extends Generator {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(ObjectsContainingGenerator.class.getName());
    /** The color map. */
    protected List<Color> colorMap;
    /** The random. */
    private boolean random = false;

    /**
     *
     * @param name
     * @param colorMap
     */
    public ObjectsContainingGenerator(GeneratorName name, MatrixData matrix, List<Color> colorMap) {
        super(name, matrix);

        //make this list thread save, used when color map is updated
        this.colorMap = new CopyOnWriteArrayList<Color>();

        if (colorMap != null) {
            this.colorMap = colorMap;
        }

        LOG.log(Level.FINER, "add {0} colors to map", this.colorMap.size());

        //add default value if nothing is configured
        if (this.colorMap.isEmpty()) {
            this.colorMap.add(Color.WHITE);
        }

    }

    protected int getColor(int colornumber) {
        if(random){
            return colorMap.get((int)Math.floor(colorMap.size() * Math.random())).getRGB();
        }
        return colorMap.get(colornumber % colorMap.size()).getRGB();
    }

    public void setRandom(boolean random){
        this.random = random;
    }

    /**
     * Update the color map
     *
     * @param colorMap
     */
    public void setColorMap(List<Color> colorMap) {
        this.colorMap = colorMap;
    }

    /**
     * @return the colorMapAsString
     */
    public List<Color> getColorMap() {
        return colorMap;
    }
}
