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

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
final class Checksum {

    final byte get(Action action) {
        long checksum = action.command;
        for (int idx = 0; idx < action.data.length; ++idx) {
            checksum += action.data[idx];
        }
        return (byte) (checksum % 256);
    }
    
    final byte get(ByteBuffer buffer){
        int csum = 0;
        for (int i = 0; i < 7; ++i) {
            if(i<4){
                csum += buffer.get(i);
            }else{
                csum += buffer.get(i+1);
            }
        }
        return (byte) (csum % 200);    
    }
}
