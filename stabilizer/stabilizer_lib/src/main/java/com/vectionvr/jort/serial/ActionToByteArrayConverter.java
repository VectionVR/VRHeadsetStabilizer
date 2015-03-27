package com.vectionvr.jort.serial;

import java.nio.ByteBuffer;
import java.security.InvalidParameterException;

final class ActionToByteArrayConverter {

    private final Checksum checksum = new Checksum();

    final byte[] convert(Action action) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(action.data.length + 3);
            buffer.put((byte) 247);
            buffer.put(action.command);
            buffer.put(action.data);
            buffer.put(checksum.get(action));
            return buffer.array();
        } catch (Exception e) {
        }
        throw new InvalidParameterException();
    }
}
