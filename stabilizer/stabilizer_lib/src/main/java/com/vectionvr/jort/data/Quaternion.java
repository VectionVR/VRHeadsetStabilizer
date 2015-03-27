package com.vectionvr.jort.data;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class Quaternion {

    private float w;
    private float x;
    private float y;
    private float z;

    public Quaternion(float... datas) {
        this.w = datas[0];
        this.x = datas[1];
        this.y = datas[2];
        this.z = datas[3];
    }
    
    public void reset(float... datas) {
        this.w = datas[0];
        this.x = datas[1];
        this.y = datas[2];
        this.z = datas[3];
    }

    public Quaternion() {
        this(0, 0, 0, 0);
    }

    public Quaternion(float[] axis, float angle) {
        float curX = axis[0];
        float curY = axis[1];
        float curZ = axis[2];
        float norm = (float) Math.sqrt(curX * curX + curY * curY + curZ * curZ);
        float sin_half_angle = (float) sin(angle / 2.0f);
        this.x = sin_half_angle * curX / norm;
        this.y = sin_half_angle * curY / norm;
        this.z = sin_half_angle * curZ / norm;
        this.w = (float) cos(angle / 2.0f);

    }

    public float getW() {
        return w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

//    @Override
//    public String toString() {
//        return "[(Q) w:" + w + " x:" + x + " y:" + y + " z:" + z + "]";
//    }

    public EulerAngle toEulerAngle() {
        float roll, pitch, yaw;
        float sqw = w * w;
        float sqx = x * x;
        float sqy = y * y;
        float sqz = z * z;
        float unit = sqx + sqy + sqz + sqw; // if normalised is one, otherwise is correction factor 
        float test = x * y + z * w;
        if (test > 0.499 * unit) { // singularity at north pole 
            pitch = (float) (2 * Math.atan2(x, w));
            yaw = (float) (Math.PI / 2);
            roll = 0;
            return new EulerAngle(roll, pitch, yaw);
        } else if (test < -0.499 * unit) { // singularity at south pole 
            pitch = (float) (-2 * Math.atan2(x, w));
            yaw = (float) (-Math.PI / 2);
            roll = 0;
            return new EulerAngle(roll, pitch, yaw);
        } else {
            pitch = (float) Math.atan2(2 * y * w - 2 * x * z, sqx - sqy - sqz + sqw);
            yaw = (float) Math.asin(2 * test / unit);
            roll = (float) Math.atan2(2 * x * w - 2 * y * z, -sqx + sqy - sqz + sqw);
            return new EulerAngle(roll, pitch, yaw);
        }
    }

    public static Quaternion fromEulerAngles(float ... values) {
        Quaternion result = new Quaternion();
        Quaternion qx = new Quaternion(new float[]{1, 0, 0}, values[2]);
        Quaternion qy = new Quaternion(new float[]{0, 1, 0}, values[1]);
        Quaternion qz = new Quaternion(new float[]{0, 0, 1}, values[0]);
        result.set(qy);
        result.multiply(qz);
        result.multiply(qx);
        return result;
    }

    private void multiply(Quaternion q1) {
        float curW = this.w * q1.w - this.x * q1.x - this.y * q1.y - this.z * q1.z;
        float curX = this.w * q1.x + q1.w * this.x + this.y * q1.z - this.z * q1.y;
        float curY = this.w * q1.y + q1.w * this.y - this.x * q1.z + this.z * q1.x;
        this.z = this.w * q1.z + q1.w * this.z + this.x * q1.y - this.y * q1.x;
        this.w = curW;
        this.x = curX;
        this.y = curY;
    }

    private float normalize() {
        float norm = (float) sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
        if (norm > 0.0f) {
            this.x /= norm;
            this.y /= norm;
            this.z /= norm;
            this.w /= norm;
        } else {
            this.x = (float) 0.0;
            this.y = (float) 0.0;
            this.z = (float) 0.0;
            this.w = (float) 1.0;
        }
        return norm;
    }

    public Quaternion conjuguate(){
        return new Quaternion (w, -x, -y, -z);
    }
    
    public Quaternion add(Quaternion decal){
        return new Quaternion(w + decal.w,x + decal.x, y + decal.y, z + decal.z);
    }
    private void set(Quaternion q1) {
        this.x = q1.x;
        this.y = q1.y;
        this.z = q1.z;
        this.w = q1.w;
        this.normalize();
    }
}
