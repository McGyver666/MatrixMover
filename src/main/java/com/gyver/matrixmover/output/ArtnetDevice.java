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

import artnet4j.ArtNet;
import artnet4j.packets.ArtDmxPacket;
import com.gyver.matrixmover.core.MatrixData;
import com.gyver.matrixmover.properties.PropertiesHelper;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class ArtnetDevice
 * 
 * Code-parts copied from http://github.com/neophob/PixelController
 *
 * @author Gyver
 */
public class ArtnetDevice extends Output {

    private static final Logger LOG = Logger.getLogger(ArtnetDevice.class.getName());
    private int sequenceID;
    private int pixelsPerUniverse;
    private int nrOfUniverse;
    private int firstUniverseId;
    private ArtNet artnet;
    private InetAddress targetAdress;
    private boolean initialized = false;

    /**
     *
     * @param controller
     */
    public ArtnetDevice(PropertiesHelper ph) {
        super(OutputDeviceEnum.ARTNET, ph);

        this.initialized = false;
        this.artnet = new ArtNet();
        try {
            this.pixelsPerUniverse = ph.getArtNetPixelsPerUniverse();
            this.targetAdress = InetAddress.getByName(ph.getArtNetIp());
            this.firstUniverseId = ph.getArtNetStartUniverseId();
            this.artnet.init();
            this.artnet.start();

            //check how many universe we need
            this.nrOfUniverse = 1;
            int bufferSize = ph.getOutputDeviceDimensionWidth() * ph.getOutputDeviceDimensionHeight();
            if (bufferSize > pixelsPerUniverse) {
                while (bufferSize > pixelsPerUniverse) {
                    this.nrOfUniverse++;
                    bufferSize -= pixelsPerUniverse;
                }
            }

            this.initialized = true;
            LOG.log(Level.INFO, "ArtNet device initialized at {0}, using {1} universe with {2} pixels.",
                    new Object[]{this.targetAdress.toString(), this.nrOfUniverse, this.pixelsPerUniverse});
        } catch (Exception e) {
            LOG.log(Level.WARNING, "failed to initialize ArtNet port:", e);
        }
    }


    /* (non-Javadoc)
     * @see com.neophob.sematrix.output.Output#update()
     */
    @Override
    public void update(int[] buffer) {
        if (this.initialized) {
            if (this.nrOfUniverse == 1) {
                sendBufferToArtnetReceiver(0, convertIntToByteBuffer(buffer));
            } else {
                int remainingInt = buffer.length;
                int ofs = 0;
                for (int i = 0; i < this.nrOfUniverse; i++) {
                    int tmp = pixelsPerUniverse;
                    if (remainingInt < pixelsPerUniverse) {
                        tmp = remainingInt;
                    }
                    int[] sendBuffer = new int[tmp];
                    System.arraycopy(buffer, ofs, sendBuffer, 0, tmp);
                    remainingInt -= tmp;
                    ofs += tmp;
                    sendBufferToArtnetReceiver(i, convertIntToByteBuffer(sendBuffer));
                }
            }

        }
    }

    /**
     * send buffer to a dmx universe
     * a DMX universe can address up to 512 channels - this means up to
     * 170 RGB LED (510 Channels)
     *
     * Just for myself:
     * ArtNet packets are made up of the Ethernet data (source and destination IP addresses), followed by
     * the ArtNet Subnet (0 to 15) and the ArtNet universe (0 to 15), and finally the DMX data for that
     * universe).
     *
     * @param artnetReceiver
     * @param frameBuf
     */
    private void sendBufferToArtnetReceiver(int universeOffset, byte[] buffer) {
        ArtDmxPacket dmx = new ArtDmxPacket();

        //parameter: int subnetID, int universeID
        dmx.setUniverse(0, this.firstUniverseId + universeOffset);
        dmx.setSequenceID(sequenceID % 255);
        dmx.setDMX(buffer, buffer.length);
        this.artnet.unicastPacket(dmx, this.targetAdress);
        this.sequenceID++;
    }

    @Override
    public void close() {
        if (initialized) {
            this.artnet.stop();
        }
    }
}
