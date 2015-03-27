package com.vectionvr.jort.net;

import com.vectionvr.jort.data.SensorData;
import static java.lang.Float.floatToIntBits;
import java.nio.ByteBuffer;
import static java.nio.ByteBuffer.allocate;
import java.nio.ByteOrder;

/**
 *
 * @author nico
 */
class BinaryPacketConverter implements PacketConverter{

    @Override
    public ByteBuffer convert(SensorData data)  {
        ByteBuffer buffer = allocate(24).order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(floatToIntBits(data.getQuaternion().getW()));
        buffer.putInt(floatToIntBits(data.getQuaternion().getX()));
        buffer.putInt(floatToIntBits(data.getQuaternion().getZ()));
        buffer.putInt(floatToIntBits(data.getQuaternion().getY()));
        buffer.putLong(data.getTimestamp());
        return buffer;
    }
}
