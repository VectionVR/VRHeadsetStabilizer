package com.vectionvr.jort.net;

import com.vectionvr.jort.data.SensorData;
import java.nio.ByteBuffer;

/**
 *
 * @author nico
 */
public interface PacketConverter {
    ByteBuffer convert(SensorData data) ;
}
