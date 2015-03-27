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
package com.vectionvr.jort.serial;

import java.nio.ByteBuffer;
import java.security.InvalidParameterException;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
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
