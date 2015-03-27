/**
 * Copyright (C) 2014 Bnome SPRL (info@bnome.be)
 *
 * This file is part of VectionVR Stabilizer.
 *
 * VectionVR Stabilizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VectionVR Stabilizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VectionVR Stabilizer.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vectionvr.jort.net;

import com.vectionvr.jort.data.RunningAverage;
import com.vectionvr.jort.data.SensorData;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
public class UDPSender implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UDPSender.class);
    private boolean keepRunning = false;
    private InetAddress serverAddress;
    private DatagramSocket socket;
    private int serverPort;
    private final NetworkStatistics statistics = new NetworkStatistics(16);
    private final PacketConverter packetConverter = new BinaryPacketConverter();
    private final RunningAverage runningAverageAfterUdp = new RunningAverage();

    @Override
    public void run() {
        initNetwork();
        runningAverageAfterUdp.clear();
        statistics.reset();
        keepRunning = true;
        while (keepRunning) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void stop() {
        if (keepRunning) {
            keepRunning = false;
            socket.close();
            socket = null;
        }
    }

    private void sendPacket(ByteBuffer byteBuffer) {
        try {
            if (socket != null && !socket.isClosed()) {
                byte[] buffer = byteBuffer.array();
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
                socket.send(dp);
                statistics.addPacket();
            }
        } catch (IOException ex) {
            LOGGER.warn("Unable to send packet ", ex);
        }
    }

    private void initNetwork() {
        try {
            socket = new DatagramSocket();
            socket.setReuseAddress(true);
        } catch (SocketException ex) {
            LOGGER.warn("Unable to create socket port");
        }
    }

    public void setDestinationAddress(String destinationAddress) throws UnknownHostException {
        this.serverAddress = InetAddress.getByName(destinationAddress);
    }

    public void setDestinationPort(int destinationPort) {
        this.serverPort = destinationPort;
    }

    public void onSensorData(SensorData sensorData) {
        sendPacket(packetConverter.convert(sensorData));
        runningAverageAfterUdp.addValue((int) (System.nanoTime() - sensorData.getTimestamp()));
    }

    public NetworkStatistics getStatistics() {
        return statistics;
    }

    public float getRunningAverage() {
        return runningAverageAfterUdp.getAverage();
    }
}
