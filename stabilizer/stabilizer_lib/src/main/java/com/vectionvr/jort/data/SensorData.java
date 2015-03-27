package com.vectionvr.jort.data;

public class SensorData {

    private final long timestamp;
    private final Quaternion quaternion;
    private EulerAngle angle = null;
    private float temperature;

    public SensorData(Quaternion quaternion) {
        this.quaternion = quaternion;
        angle = quaternion.toEulerAngle();
        timestamp = System.nanoTime();
    }

    public SensorData(Quaternion quaternion, float temperature) {
        this(quaternion);
        this.temperature = temperature;
    }

    public Quaternion getQuaternion() {
        return quaternion;
    }

    public EulerAngle getEulerAngle() {
        return angle;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getTemperature() {
        return temperature;
    }

//    @Override
//    public String toString() {
//        return reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//    }

//    public String toCsv() {
//        return angle.getxAxis() + ";" + angle.getyAxis() + ";" + angle.getzAxis() + ";";
//    }
}
