package com.vectionvr.jort.serial;

import java.nio.ByteBuffer;

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
