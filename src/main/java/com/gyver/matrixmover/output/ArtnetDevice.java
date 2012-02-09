/*
 * Copyright (C) 2011 Michael Vogt <michu@neophob.com>
 * Copyright (C) 2011 Rainer Ostendorf <mail@linlab.de>
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
    private int sequenceID = 0;
    private int artnetNodes;
    private int[] pixelsPerUniverse;
    private int[] nrOfUniverse;
    private int[] nrOfSubnet;
    private int[] universesPerNode;
    private int[] subnetsPerUniverse;
    private int[] firstUniverseId;
    private ArtNet artnet;
    private InetAddress[] targetAdress;
    private boolean initialized = false;

    public ArtnetDevice(PropertiesHelper ph) {
        super(OutputDeviceEnum.ARTNET, ph);

        this.initialized = false;
        this.artnet = new ArtNet();
        try {
            this.artnetNodes = ph.getArtNetNodesCount();

            this.pixelsPerUniverse = new int[this.artnetNodes];
            this.firstUniverseId = new int[this.artnetNodes];
            this.universesPerNode = new int[this.artnetNodes];
            this.nrOfUniverse = new int[this.artnetNodes];
            this.nrOfSubnet = new int[this.artnetNodes];
            this.subnetsPerUniverse = new int[this.artnetNodes];
            this.targetAdress = new InetAddress[this.artnetNodes];

            for (int i = 0; i < this.artnetNodes; i++) {
                this.pixelsPerUniverse[i] = ph.getArtNetPixelsPerUniverse(i);
                this.targetAdress[i] = InetAddress.getByName(ph.getArtNetIp(i));
                this.firstUniverseId[i] = ph.getArtNetStartUniverseId(i);
                this.universesPerNode[i] = ph.getUniversesPerNode(i);
                this.subnetsPerUniverse[i] = ph.getSubnetsPerUniverse(i);
            }
            this.artnet.init();
            this.artnet.start();

            //check how many universe we need per node
            int bufferSize = ph.getOutputDeviceDimensionWidth() * ph.getOutputDeviceDimensionHeight();

            for (int i = 0; i < this.artnetNodes; i++) {
                this.nrOfUniverse[i] = 0;
                while (bufferSize > 0 && this.nrOfUniverse[i] < universesPerNode[i]) {
                    this.nrOfSubnet[i] = 0;
                    while (bufferSize > 0 && this.nrOfSubnet[i] < subnetsPerUniverse[i]) {
                        if (bufferSize >= pixelsPerUniverse[i]) {
                            LOG.log(Level.FINE, "{0} Pixels in universe {1}, subnet {4} of node {2} ({3})", new Object[]{pixelsPerUniverse[i], nrOfUniverse[i], i, this.targetAdress[i].toString(), nrOfSubnet[i]});
                        } else {
                            LOG.log(Level.FINE, "{0} Pixels in universe {1}, subnet {4} of node {2} ({3})", new Object[]{bufferSize, nrOfUniverse[i], i, this.targetAdress[i].toString(), nrOfSubnet[i]});
                        }
                        bufferSize -= pixelsPerUniverse[i];
                        this.nrOfSubnet[i]++;
                    }
                    this.nrOfUniverse[i]++;
                }
            }
            this.initialized = true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "failed to initialize ArtNet port:", e);
        }
    }


    /* (non-Javadoc)
     * @see com.gyver.matrixmover.output.Output#update()
     */
    @Override
    public void update(int[] buffer) {
        if (this.initialized) {

            int remainingInt = buffer.length;
            int ofs = 0;

            for (int n = 0; n < artnetNodes; n++) {
                for (int i = 0; i < this.nrOfUniverse[n]; i++) {
                    for (int j = 0; j < this.nrOfSubnet[n]; j++) {
                        int tmp = pixelsPerUniverse[n];
                        if (remainingInt < pixelsPerUniverse[n]) {
                            tmp = remainingInt;
                        }
                        int[] sendBuffer = new int[tmp];
                        System.arraycopy(buffer, ofs, sendBuffer, 0, tmp);
                        remainingInt -= tmp;
                        ofs += tmp;
                        sendBufferToArtnetReceiver(this.firstUniverseId[n] + i, j, convertIntToByteBuffer(sendBuffer), targetAdress[n]);
                    }
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
    private void sendBufferToArtnetReceiver(int universe, int subnet, byte[] buffer, InetAddress adress) {
        ArtDmxPacket dmx = new ArtDmxPacket();
        dmx.setUniverse(subnet, universe);
        dmx.setSequenceID(sequenceID % 255);
        dmx.setDMX(buffer, buffer.length);
        this.artnet.unicastPacket(dmx, adress);
        this.sequenceID++;
    }

    @Override
    public void close() {
        if (initialized) {
            this.artnet.stop();
        }
    }
}
