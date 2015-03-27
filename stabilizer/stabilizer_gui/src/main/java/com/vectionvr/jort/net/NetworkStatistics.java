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

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
public class NetworkStatistics {
    private long startTime;
    private long nbrPackets;
    private final int packetSize;

    public NetworkStatistics(int packetSize) {
        this.packetSize = packetSize;
    }
    
    public void addPacket(){
        if(nbrPackets == 0){
            startTime = System.currentTimeMillis();
        }
        nbrPackets++;// +=packetSize;
    }
    
    public int get(){
        return (int) ((float) (nbrPackets * 1000) / (float) (System.currentTimeMillis() - startTime));
    }
    
    public void reset(){
        nbrPackets = 0;
    }
}
