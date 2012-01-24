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
 * The class ColorMapAwareGenerator, a parent class for all generators using 
 * a color map for their effects.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */
public abstract class ColorMapAwareGenerator extends Generator {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(ColorMapAwareGenerator.class.getName());
    /** The color map. */
    protected List<Color> colorMap;

    /**
     *
     * @param name
     * @param colorMap
     */
    public ColorMapAwareGenerator(GeneratorName name, MatrixData matrix, List<Color> colorMap) {
        super(name, matrix);
        
        //make this list thread save, used when color map is updated
        this.colorMap = new CopyOnWriteArrayList<Color>();

        if(colorMap != null){
            this.colorMap = colorMap;
        }
        
        LOG.log(Level.FINER, "add {0} colors to map", this.colorMap.size());
        
        //add default value if nothing is configured
        if (this.colorMap.isEmpty()) {
            this.colorMap.add(new Color(255, 128, 128));
            this.colorMap.add(new Color(255, 255, 128));
            this.colorMap.add(new Color(128, 255, 128));
            this.colorMap.add(new Color(128, 255, 255));
            this.colorMap.add(new Color(128, 128, 255));
            this.colorMap.add(new Color(255, 128, 255));
        }

    }

    /**
     *
     * @param colornumber
     * @param nextcolornumber
     * @param ratio
     * @return
     */
    protected int getColor(int colornumber, int nextcolornumber, float ratio) {
        Color currentColor = new Color(0);
        Color nextColor = new Color(0);
        try {
            currentColor = colorMap.get(colornumber);
            nextColor = colorMap.get(nextcolornumber);
        } catch (IndexOutOfBoundsException e){}

        int rThis = currentColor.getRed();
        int rNext = nextColor.getRed();
        int gThis = currentColor.getGreen();
        int gNext = nextColor.getGreen();
        int bThis = currentColor.getBlue();
        int bNext = nextColor.getBlue();

        int r = rThis - (int) Math.round((rThis - rNext) * ratio);
        int g = gThis - (int) Math.round((gThis - gNext) * ratio);
        int b = bThis - (int) Math.round((bThis - bNext) * ratio);

        return (r << 16) | (g << 8) | b;
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
