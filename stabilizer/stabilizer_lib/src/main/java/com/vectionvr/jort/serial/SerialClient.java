package com.vectionvr.jort.serial;

import static com.vectionvr.jort.utils.ByteArrayUtils.byteArrayEquals;
import static java.lang.System.arraycopy;

import java.nio.ByteBuffer;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

/**
 * @author nico
 */
abstract class SerialClient {

    private static final int DEFAULT_THROTTLE_WAIT_TIME = 20;
    protected static final int DEFAULT_TIMEOUT = 1250;
    private final ActionToByteArrayConverter converter = new ActionToByteArrayConverter();
    private SerialPort serialPort;
    
    private final Checksum checksum = new Checksum();
    
    private final String portName;

    public SerialClient(String portName) {
        this.portName = portName;
    }

    protected final void openSerialPort() {
        try {
            if (serialPort != null && serialPort.isOpened()) {
                closeSerialPort();
                
            }
            if (portName.startsWith("/dev/tty.usbserial") || portName.startsWith("COM")) {
                serialPort = new SerialPort(portName);
                serialPort.openPort();
                if (serialPort.isOpened()) {
                    serialPort.setParams(getSerialSpeed(),
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE,
                            false, false);
                }
            }
            if (serialPort == null || !serialPort.isOpened()) {
                throw new SensorException("Check your cable please", SensorException.ExceptionType.UNABLE_TO_CONNECT);
            }
        } catch (SerialPortException e){
        	throw new SensorException("Check your cable please", e, SensorException.ExceptionType.UNABLE_TO_CONNECT);
        } catch (SensorException e) {
            throw new SensorException("Check your cable please", e, SensorException.ExceptionType.UNABLE_TO_CONNECT);
        }
    }

    protected final void closeSerialPort() {
        try {
            if (serialPort != null && serialPort.isOpened()) {
                serialPort.closePort();
                serialPort = null;
            }
        } catch (SerialPortException e) {
        }
    }

    protected SerialPort getSerialPort() {
        return serialPort;
    }

    protected final void applyAction(Action action) {
        try {
            throttleSerialWrites(DEFAULT_THROTTLE_WAIT_TIME);
            getSerialPort().writeBytes(converter.convert(action));
        } catch (SerialPortException ex) {
            throw new SensorException("Unable to send command to sensor", ex, SensorException.ExceptionType.CANNOT_WRITE);
        }
    }

    protected final void throttleSerialWrites(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
    }

    protected final void applyActionWithVerification(Action command, Action verificationCommand) {
        try {
            applyAction(command);
            applyAction(verificationCommand);
            byte[] answerBuffer = getSerialPort().readBytes(command.data.length, DEFAULT_TIMEOUT);
            if (!byteArrayEquals(answerBuffer, command.data)) {
                throw new SensorException("Invalid answer from device", SensorException.ExceptionType.CANNOT_VALIDATE);
            }
        } catch (SerialPortException e) {
            throw new SensorException("Invalid answer from device", e, SensorException.ExceptionType.CANNOT_READ);
        } catch (SerialPortTimeoutException ex) {
            throw new SensorException("No answer from device", ex, SensorException.ExceptionType.NO_ANSWER);
        }
    }

    protected final void applyActionWithAnswer(Action action, byte[] answer) {
        applyAction(action);
        readToBuffer(answer);
    }

    private final void readToBuffer(byte[] answer) {
        arraycopy(read(answer.length), 0, answer, 0, answer.length);
    }
    
    protected final byte[] read(int length) {
        try {
            return getSerialPort().readBytes(length, DEFAULT_TIMEOUT);
        } catch (SerialPortException e) {
        	throw new SensorException("Unable to read form device", e, SensorException.ExceptionType.CANNOT_READ);
        } catch (SerialPortTimeoutException e) {
            throw new SensorException("Unable to read form device", e, SensorException.ExceptionType.CANNOT_READ);
        }
    }

    public void commitToEeprom() {
        applyAction(new Action(0xe1));
    }

    protected abstract int getSerialSpeed();
    
    static final byte getChecksum(Object getChecksum){
        if(getChecksum instanceof ByteBuffer){
            return new Checksum().get((ByteBuffer)getChecksum);
        }
        return new Checksum().get((Action)getChecksum);
    }

}
