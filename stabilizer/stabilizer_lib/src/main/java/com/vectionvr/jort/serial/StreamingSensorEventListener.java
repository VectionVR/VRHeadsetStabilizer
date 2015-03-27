package com.vectionvr.jort.serial;

import com.vectionvr.jort.data.SensorData;

public interface StreamingSensorEventListener {
    public void sensorStarted(ImuOrientationDataStreamer sensor);
    public void onSensorData(SensorData data);
    public void sensorStopped();
    public void handleException(SensorException e);
}
