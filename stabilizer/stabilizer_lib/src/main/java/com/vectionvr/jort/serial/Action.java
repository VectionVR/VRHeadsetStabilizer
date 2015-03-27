package com.vectionvr.jort.serial;

/**
 *
 * @author nico
 */
class Action {

    public byte command;
    public byte[] data;

    Action(int command, byte[] data) {
        this.command = (byte) command;
        this.data = data;
    }

    Action(int command) {
        this.command = (byte) command;
        this.data = new byte[0];
    }

    @Override
    public String toString() {
        return Integer.toHexString(command);
    }
}
