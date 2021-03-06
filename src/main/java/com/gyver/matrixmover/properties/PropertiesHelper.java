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
package com.gyver.matrixmover.properties;

import com.gyver.matrixmover.gui.Frame;
import com.gyver.matrixmover.mapping.PixelRgbMapping;
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
     * @param config the config
     */
    public PropertiesHelper(Properties config) {
        this.config = config;

        parsOutputDevice(config);


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

    public int[] getOutputMapping() {
        return parseIntArray(ConfigConstants.OUTPUT_MAPPING);
    }

    public int getOutputPixeMode() {
        return parsePixelMode(ConfigConstants.OUTPUT_PIXEL_MODE);
    }
    
    public String getScenesDir() {
        return config.getProperty(ConfigConstants.SCENES_DIR);
    }

    /**
     * Get configured artnet ip for given node.
     *
     * @param nodeNumber the node
     * @return the art net ip
     */
    public String getArtNetIp(int nodeNumber) {
        String ip = config.getProperty(ConfigConstants.ARTNET_IP+"."+nodeNumber);
        return ip;
    }

    /**
     * How many pixels (=3 Channels) per DMX universe for given node number.
     * @param nodeNumber the node
     * @return the number of pixels
     */
    public int getArtNetPixelsPerUniverse(int nodeNumber) {
        return parseInt(ConfigConstants.ARTNET_PIXELS_PER_UNIVERSE+"."+nodeNumber, 170);
    }

    /**
     * Get first arnet universe id for given node.
     * @param nodeNumber the node
     * @return the start universe
     */
    public int getArtNetStartUniverseId(int nodeNumber) {
        return parseInt(ConfigConstants.ARTNET_FIRST_UNIVERSE_ID+"."+nodeNumber, 0);
    }
    
    /**
     * Gets the number of universes per node
     * @param nodeNumber the node
     * @return the number of universes
     */
    public int getUniversesPerNode(int nodeNumber) {
        return parseInt(ConfigConstants.ARTNET_UNIVERSES_PER_NODE+"."+nodeNumber, 0);
    }

    /**
     * Gets the number of subnets per universe
     * @param nodeNumber the node
     * @return the number of subnets
     */
    public int getSubnetsPerUniverse(int nodeNumber) {
        return parseInt(ConfigConstants.ARTNET_SUBNETS_PER_UNIVERSES+"."+nodeNumber, 0);
    }
    
    /**
     * Gets the number of used artnet nodes.
     * @return the number of nodes
     */
    public int getArtNetNodesCount() {
        return parseInt(ConfigConstants.ARTNET_NODES, 0);
    }

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

    private int[] parseIntArray(String property) {
        String rawString = config.getProperty(property);
        String[] rawArray = null;
        if (StringUtils.isNotBlank(rawString)) {
            rawArray = rawString.replaceAll(" ", "").split(",");
        }
        if (rawArray == null) {
            return null;
        }

        int[] retArray = new int[rawArray.length];
        for (int i = 0; i < rawArray.length; i++) {
            try {
                retArray[i] = Integer.parseInt(rawArray[i]);
            } catch (NumberFormatException e) {
                Frame.getFrameInstance().showWarning("Unable to parse output mapping number '" + rawArray[i] + "' correctly. Using default mapping instead.");
                return null;
            }
        }

        return retArray;
    }

    private int parsePixelMode(String property) {
        String rawString = config.getProperty(property).trim();
        if (StringUtils.isNotBlank(rawString)) {
            if (rawString.equals("RGB")) {
                return PixelRgbMapping.PIXEL_MAPPING_RGB;
            } else if (rawString.equals("RBG")) {
                return PixelRgbMapping.PIXEL_MAPPING_RBG;
            } else if (rawString.equals("BGR")) {
                return PixelRgbMapping.PIXEL_MAPPING_BGR;
            } else if (rawString.equals("BRG")) {
                return PixelRgbMapping.PIXEL_MAPPING_BRG;
            } else if (rawString.equals("GRB")) {
                return PixelRgbMapping.PIXEL_MAPPING_GRB;
            } else if (rawString.equals("GBR")) {
                return PixelRgbMapping.PIXEL_MAPPING_GBR;
            }
        }
        Frame.getFrameInstance().showWarning("Unable to parse pixel mode '" + rawString + "'. Using RGB mode instead.");
        return PixelRgbMapping.PIXEL_MAPPING_RGB;
    }

    private void parsOutputDevice(Properties config) {

        String rawConfig = config.getProperty(ConfigConstants.OUTPUT_DEVICE);
        if (rawConfig == null || rawConfig.isEmpty()) {
            throw new IllegalArgumentException("Output device has to be set in parameter: " + ConfigConstants.OUTPUT_DEVICE);
        }

        if (rawConfig.equals("null")) {
            this.outputDeviceEnum = OutputDeviceEnum.NULL;
        } else if (rawConfig.equals("artnet")) {
            this.outputDeviceEnum = OutputDeviceEnum.ARTNET;
        } else {
            throw new IllegalArgumentException("Given output device '"+rawConfig+"' not found");
        }
    }

    public String getChaserDir() {
        return config.getProperty(ConfigConstants.CHASER_DIR);
    }
}
