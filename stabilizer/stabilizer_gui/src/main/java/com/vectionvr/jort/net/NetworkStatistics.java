/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vectionvr.jort.net;

/**
 *
 * @author nico
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
