package com.vectionvr.jort.serial;

public final class SensorStatistics {

    private long startTime;
    private long nbrPackets;

    public void increase() {
        if (nbrPackets == 0) {
            startTime = System.currentTimeMillis();
        }
        ++nbrPackets;
    }

    public short get() {
        return (short) ((float) (nbrPackets * 1000) / (float) (System.currentTimeMillis() - startTime));
    }

    public void reset() {
        nbrPackets = 0;
    }
}
