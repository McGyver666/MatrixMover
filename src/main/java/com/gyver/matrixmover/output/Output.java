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
package com.gyver.matrixmover.output;

import com.gyver.matrixmover.properties.PropertiesHelper;
import java.util.logging.Logger;

/**
 * Class Output is the parent output class for all output plugins.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 *
 * @author Gyver
 */
public abstract class Output {

    /** The log. */
    private static final Logger LOG = Logger.getLogger(Output.class.getName());
    /** The outputDeviceEnum. */
    private OutputDeviceEnum outputDeviceEnum;

    public Output(OutputDeviceEnum outputDeviceEnum, PropertiesHelper ph, int bpp) {
        this.outputDeviceEnum = outputDeviceEnum;
    }

    /**
     * Update the output device
     */
    public abstract void update();

    /**
     * Close to output device
     */
    public abstract void close();
    
}
