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

import com.vectionvr.jort.data.SensorData;
import static java.lang.Float.floatToIntBits;
import java.nio.ByteBuffer;
import static java.nio.ByteBuffer.allocate;
import java.nio.ByteOrder;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
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
