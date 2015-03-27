package com.vectionvr.jort.serial;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nico
 * @version 1
 */
public class SampleData {
    public static final SampleData EMPTY = new SampleData();
    
    private List<float[]> magnetometerValues = new ArrayList<float[]>();
    private List<float[]> accelerometerValues = new ArrayList<float[]>();

    public List<float[]> getMagnetometerValues() {
        return magnetometerValues;
    }

    public SampleData withMagnetometerValues(float[] magnetometerValues) {
        this.magnetometerValues.add(magnetometerValues);
        return this;
    }

    public List<float[]> getAccelerometerValues() {
        return accelerometerValues;
    }

    public SampleData withAccelerometerValues(float[] accelerometerValues) {
        this.accelerometerValues.add(accelerometerValues);
        return this;
    }

    public void clear() {
        accelerometerValues.clear();
        magnetometerValues.clear();
                
    }

    public int size() {
        return accelerometerValues.size();
    }
    
}
