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
package com.gyver.matrixmover.properties;

import com.gyver.matrixmover.output.OutputDeviceEnum;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 * The class PropertiesHelper.
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 * 
 * @author Gyver
 */

public class PropertiesHelper {
    
    /** The Constant FAILED_TO_PARSE. */
    private static final String FAILED_TO_PARSE = "Failed to parse {0}";

    /** The log. */
    private static final Logger LOG = Logger.getLogger(PropertiesHelper.class.getName());
    /** The config. */
    protected Properties config = null;
    /** The output device enum. */
    private OutputDeviceEnum outputDeviceEnum = null;

    /**
     * Instantiates a new properties helper.
     *
     * @param input the input
     */
    public PropertiesHelper(Properties config) {
        this.config = config;

        parsOutputDevice(config);


    }

    private void parsOutputDevice(Properties config) {

        String rawConfig = config.getProperty(ConfigConstants.OUTPUT_DEVICE);
        if (rawConfig == null || rawConfig.isEmpty()) {
            throw new IllegalConfigurationException("Output device has to be set in parameter: " + ConfigConstants.OUTPUT_DEVICE);
        }

        if (rawConfig.equals("null")) {
            this.outputDeviceEnum = OutputDeviceEnum.NULL;
        } else if (false) {
        } else {
            throw new IllegalConfigurationException("Given output device not found");
        }
    }

    public OutputDeviceEnum getOutputDevice() {
        return outputDeviceEnum;
    }

    public int getLedScreenPixelWidth() {
        return parseInt(ConfigConstants.DISPLAY_PIXEL_WIDTH, 5);
    }

    public int getLedScreenPixelHeigth() {
        return parseInt(ConfigConstants.DISPLAY_PIXEL_HEIGHT, 5);
    }
    
    public int getLedScreenPixelSpace() {
        return parseInt(ConfigConstants.DISPLAY_PIXEL_SPACE, 1);
    }
    
    public int getOutputDeviceDimensionHeight() {
        return parseInt(ConfigConstants.OUTPUT_DIMENSION_HEIGHT, 8);
    }
    
    public int getOutputDeviceDimensionWidth() {
        return parseInt(ConfigConstants.OUTPUT_DIMENSION_WIDTH, 1);
    }

    public int getFps() {
        return parseInt(ConfigConstants.OUTPUT_FPS, 1);
    }
    
    
    /**
     * get a int value from the config file.
     *
     * @param property the property
     * @return the int
     */
    private int parseInt(String property, int defaultValue) {
        String rawConfig = config.getProperty(property);
        if (StringUtils.isNotBlank(rawConfig)) {
            try {
                return Integer.parseInt(rawConfig);
            } catch (Exception e) {
                LOG.log(Level.WARNING, FAILED_TO_PARSE, rawConfig);
            }
        }
        return defaultValue;
    }
}
