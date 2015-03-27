package com.vectionvr.jort.data;

public class EulerAngle {
    private final float xAxis;
    private final float yAxis;
    private final float zAxis;

    public EulerAngle(float xAxis,float yAxis, float zAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.zAxis = zAxis;
    }

    public float getxAxis() {
        return xAxis;
    }

    public float getyAxis() {
        return yAxis;
    }

    public float getzAxis() {
        return zAxis;
    }

//    @Override
//    public String toString() {
//        return "[(E) xa:"+xAxis+" ya:"+yAxis+" za:"+zAxis+"]";
//    }
}
