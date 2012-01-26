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

/**
 * Class NullDevice does nothing.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 *
 * @author Gyver
 */
public class NullDevice extends Output {

    /**
     * init the null devices.
     *
     * @param controller the controller
     */
    public NullDevice(PropertiesHelper ph) {
        super(OutputDeviceEnum.NULL, ph);
    }

    /* (non-Javadoc)
     * @see com.neophob.sematrix.output.Output#update()
     */
    @Override
    public void update(int[] buffer) {
    }

    /* (non-Javadoc)
     * @see com.neophob.sematrix.output.Output#close()
     */
    @Override
    public void close() {
    }
}
