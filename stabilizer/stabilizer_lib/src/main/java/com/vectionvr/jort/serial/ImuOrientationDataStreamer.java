package com.vectionvr.jort.serial;

import com.vectionvr.jort.data.Quaternion;
import com.vectionvr.jort.data.SensorData;
import java.nio.ByteBuffer;

import static java.nio.ByteBuffer.wrap;
import jssc.SerialPort;

public final class ImuOrientationDataStreamer extends SerialClient implements Runnable {

    private boolean keepRunning;
    private StreamingSensorEventListener listener;
    private final SensorStatistics sensorStatistics = new SensorStatistics();
    private byte filterMode = 2;
    private byte directionAxis = 2;
    private boolean reverseX;
    private boolean reverseY;
    private boolean reverseZ;
    private boolean lockX;
    private boolean lockY;
    private boolean lockZ;
    private boolean initialised;
    private boolean miEnabled;
    private byte accelerometerRange;
    private byte gyroscopeRange;
    private byte compassRange;
    private float runnningAverageValue;
    private short oversampleRate = 1;
    private int interval = 1000;
    private static final byte[] QUATERNION_AND_TEMPERATURE_SLOTS = new byte[]{(byte) 0x00, (byte) 0x2B, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
    private static final byte[] EULER_AND_TEMPERATURE_SLOTS = new byte[]{(byte) 0x03, (byte) 0x2B, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
    public ImuOrientationDataStreamer(String portName) {
        super(portName);
    }

    @Override
    protected int getSerialSpeed() {
        return SerialPort.BAUDRATE_256000;
    }

    public SensorStatistics getStatistics() {
        return sensorStatistics;
    }

    @Override
    public void run() {
        try {
            openSerialPort();
            keepRunning = true;
            initialised = false;
            while (keepRunning) {
                if (!initialised) {
                    initialise();
                    startOutput();
                }
                readDatas();
                sensorStatistics.increase();
            }
        } catch (SensorException e) {
            listener.handleException(e);
        } finally {
            stopOutput();
            closeSerialPort();
        }
    }

    private void startOutput() {
        applyAction(new Action(85));
        listener.sensorStarted(this);
        initialised = true;
    }

    private void stopOutput() {
        try {
            applyAction(new Action(86));
        } catch (SensorException e) {
            // don't care about that one
        }
        listener.sensorStopped();
    }

    public void registerListener(StreamingSensorEventListener listener) {
        this.listener = listener;
    }

    public void stop() {
        keepRunning = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
    }

    private void initialise() {
        setOutputSlots();
        setFilterMode();
        setOutputFrequency();
        setAxis();
        setMIEnabledOnDevice();
        setRanges();
        setRunningAverageValue();
//        setOversampleRate();
    }

    private void readDatas() {
        try {
            Quaternion quaternion;
            ByteBuffer buf = wrap(read(20));
                final float x = (reverseX ? -1 : 1) *buf.getFloat(0);
                final float y = (reverseY ? -1 : 1) *buf.getFloat(4);
                final float z = (reverseZ ? -1 : 1) *buf.getFloat(8);
                final float w = buf.getFloat(12);
                final float temperature = buf.getFloat(16);
            if(isAnAxisLocked()){
                quaternion=getQuaternionWithLockedAxis(w,x,y,z);
            }else{
                quaternion = new Quaternion(w,x,y,z);
            }
            SensorData data = new SensorData(quaternion, temperature);
            listener.onSensorData(data);
        } catch (SensorException e) {
            throw e;
        }
    }

    private void setOutputSlots() {
        
        Action slots ;
        slots= new Action(0x50, QUATERNION_AND_TEMPERATURE_SLOTS);
        applyActionWithVerification(slots,
                new Action(0x51));
    }
    
    private void setOutputFrequency() {
        applyActionWithVerification(new Action(82, getOutpuFrequency()),// delay 
                new Action(83));
    }

    private void setAxis() {
        applyActionWithVerification(
                new Action(116, new byte[]{directionAxis}),
                new Action(143));
    }

    private void setFilterMode() {
        applyActionWithVerification(
                new Action(123, new byte[]{filterMode}),
                new Action(152));
    }

    private void setRanges() {
        applyActionWithVerification(
                new Action(121, new byte[]{accelerometerRange}),
                new Action(148));
        applyActionWithVerification(
                new Action(125, new byte[]{gyroscopeRange}),
                new Action(154));
        applyActionWithVerification(
                new Action(126, new byte[]{compassRange}),
                new Action(155));

    }

    private void setRunningAverageValue() {
        applyActionWithVerification(
                new Action(117, getRuningAverageArray()),
                new Action(145));
    }

    private void setMIEnabledOnDevice() {
        applyActionWithVerification(
                new Action(0x70, new byte[]{miEnabled ? (byte) 0x01 : (byte) 0x00}),
                new Action(0x88));
    }

    public void tare() {
        applyAction(new Action(96));
        applyAction(new Action(96));
    }

    public void setFilterMode(FilterMode filterMode) {
        this.filterMode = filterMode == FilterMode.SLOW?(byte)1:(byte)2;
    }

    public void setDirectionAxis(int directionAxis) {
        this.directionAxis = (byte) directionAxis;
    }

    public void setReverseAxis(boolean reverseX, boolean reverseY, boolean reverseZ) {
        this.reverseX = reverseX;
        this.reverseY = reverseY;
        this.reverseZ = reverseZ;
    }

    public void setMIEnabled(boolean miEnabled) {
        this.miEnabled = miEnabled;
    }

    public void setAccelerometerRange(byte accelerometerRange) {
        this.accelerometerRange = accelerometerRange;
    }

    public void setCompassRange(byte compassRange) {
        this.compassRange = compassRange;
    }

    public void setGyroscopeRange(byte gyroscopeRange) {
        this.gyroscopeRange = gyroscopeRange;
    }

    public void setRunningAverage(float runningAverageValue) {
        this.runnningAverageValue = runningAverageValue;
    }

    private byte[] getRuningAverageArray() {
        final ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putFloat(0);
        buffer.putFloat(0);
        buffer.putFloat(0);
        buffer.putFloat(runnningAverageValue);
        return buffer.array();
    }

//    public void setOversampleRate(short oversampleRate) {
//        this.oversampleRate = oversampleRate;
//    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

//    private void setOversampleRate() {
//        applyActionWithVerification(
//                new Action(0x6A, getOversampleRateValue()),
//                new Action(0x90));
//    }

//    private byte[] getOversampleRateValue() {
//        final ByteBuffer buffer = ByteBuffer.allocate(6);
//        buffer.putShort((short) (oversampleRate & 0xFFFF));
//        buffer.putShort((short) (oversampleRate & 0xFFFF));
//        buffer.putShort((short) (oversampleRate & 0xFFFF));
//        return buffer.array();
//    }

    private byte[] getOutpuFrequency() {
        final ByteBuffer buffer = ByteBuffer.allocate(12);
        buffer.putInt(interval);
        buffer.putInt(0xFFFFFFFF);
        buffer.putInt(0);
        return buffer.array();
    }

    public void setLockY(boolean locked) {
        this.lockY = locked;
    }

    public void setLockX(boolean locked) {
        this.lockX = locked;
    }

    public void setLockZ(boolean locked) {
        this.lockZ = locked;
    }

    private boolean isAnAxisLocked() {
        return lockX || lockY || lockZ;
    }
    
    private Quaternion getQuaternionWithLockedAxis(float angle, float x, float y, float z){
        Quaternion locker = new Quaternion(angle,(lockX?-1:1)*x,(lockY?-1:1)*y,(lockZ?-1:1)*z);
        return new Quaternion(angle,x,y,z).add(locker);
    }
}
