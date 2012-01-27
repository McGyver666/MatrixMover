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
package com.gyver.matrixmover.generator;

import com.gyver.matrixmover.core.MatrixData;
import java.awt.Color;
import java.util.Arrays;

/**
 * This Generator simply generates a black image.
 * 
 * @author jonas
 */
public class SimpleColorGenerator extends Generator {

    private Color color = null;
    
    /**
     * Instantiates a new null generator.
     *
     */
    public SimpleColorGenerator(MatrixData matrix) {
        super(GeneratorName.SIMPLE_COLOR_GENERATOR, matrix);
        color = new Color(0);
    }

    @Override
    public void update() {
        Arrays.fill(this.internalBuffer, color.getRGB());
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return this.color;
    }
}
