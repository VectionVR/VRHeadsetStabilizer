package com.vectionvr.jort.serial;

import static com.vectionvr.jort.utils.ByteArrayUtils.toFloatBytes;
import java.nio.ByteBuffer;
import static java.nio.ByteBuffer.wrap;
import java.util.List;
import jssc.SerialPort;

/**
 *
 * @author nico
 * @version 1
 */
public final class ImuCalibration extends SerialClient {

    public ImuCalibration(String portName) {
        super(portName);
    }

    @Override
    protected int getSerialSpeed() {
        return SerialPort.BAUDRATE_256000;
    }

    public SampleData getSample() {
        final byte[] sampleDatas = new byte[36];
        applyActionWithAnswer(new Action(0x40), sampleDatas);
        ByteBuffer sampleDatasBuf = wrap(sampleDatas);
        return new SampleData().withAccelerometerValues(new float[]{
            sampleDatasBuf.getFloat(3),
            sampleDatasBuf.getFloat(4),
            sampleDatasBuf.getFloat(5)
        }).withMagnetometerValues(new float[]{
            sampleDatasBuf.getFloat(6),
            sampleDatasBuf.getFloat(7),
            sampleDatasBuf.getFloat(8)
        });
    }

    public void connect() {
        openSerialPort();
    }

    public void disconnect() {
        closeSerialPort();
    }

    public void saveAccelerometerCalibrationData(List<Double> accelerometerData) {
        applyActionWithVerification(new Action(0xa0, toFloatBytes(accelerometerData)), new Action(0xa2));
    }

    public void saveMagnetometerCalibrationData(List<Double> magnetometerData) {
        applyActionWithVerification(new Action(0xa1, toFloatBytes(magnetometerData)), new Action(0xa3));
    }

    public void calibrateMI() {
        applyAction(new Action(0x72));
        throttleSerialWrites(5000);
    }

    public void calibrateGyro() {
        applyAction(new Action(0xa5));
        throttleSerialWrites(5000);
    }
    
}
