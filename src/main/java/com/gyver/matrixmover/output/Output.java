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

    public Output(OutputDeviceEnum outputDeviceEnum, PropertiesHelper ph) {
        this.outputDeviceEnum = outputDeviceEnum;
    }

    /**
     * Update the output device
     */
    public abstract void update(int[] buffer);

    /**
     * Close to output device
     */
    public abstract void close();

    /**
     *  Splits every LED out of the int buffer
     * 
     * @param buffer the buffer
     * @return a byte buffer with every number is one led color
     */
    public static byte[] convertIntToByteBuffer(int[] buffer) {
        byte[] bufferReturn = new byte[buffer.length * 3];
        int ofs;
        for (int i = 0; i < buffer.length; i++) {
            ofs = i * 3;
            bufferReturn[ofs++] = (byte) ((buffer[i] >> 16) & 0xff);
            bufferReturn[ofs++] = (byte) ((buffer[i] >> 8) & 0xff);
            bufferReturn[ofs] = (byte) (buffer[i] & 0xff);
        }

        return bufferReturn;
    }
}
