package com.vectionvr.jort.utils;

import static java.lang.Integer.toHexString;
import java.nio.ByteBuffer;
import static java.nio.ByteBuffer.allocate;
import java.util.List;

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
