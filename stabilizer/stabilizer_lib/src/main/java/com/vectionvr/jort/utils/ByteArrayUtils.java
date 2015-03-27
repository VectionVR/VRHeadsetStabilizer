/**
 * Copyright (C) 2014 Bnome SPRL (info@bnome.be)
 *
 * This file is part of VectionVR Stabilizer library.
 *
 * VectionVR Stabilizer library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VectionVR Stabilizer library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VectionVR Stabilizer library.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vectionvr.jort.utils;

import static java.lang.Integer.toHexString;
import java.nio.ByteBuffer;
import static java.nio.ByteBuffer.allocate;
import java.util.List;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
public final class ByteArrayUtils {

    private ByteArrayUtils() {
    }

    public static final boolean byteArrayEquals(final byte[] actual, final byte[] expected) {
        if (actual != null && expected != null && actual.length == expected.length) {
            for (int index = 0; index < actual.length; ++index) {
                if (actual[index] != expected[index]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static final byte[] toHexaByteArray(final int inverterNbr) {
        String hexString = toHexString(inverterNbr);
        if (hexString.length() % 2 == 1) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase().getBytes();
    }

    public static final byte[] toHexaByteArray(final String data) {
        return toHexaByteArray(Integer.parseInt(data));
    }

    public static byte[] toFloatBytes(List<Double> datas) {
        ByteBuffer buf = allocate(48);
        for (Double data : datas) {
            buf.putFloat(data.floatValue());
        }
        buf.flip();
        return buf.array();
    }

}
