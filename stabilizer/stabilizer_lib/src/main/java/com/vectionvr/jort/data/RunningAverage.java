package com.vectionvr.jort.data;

/**
 *
 * @author nico
 * @version 1
 */
public class RunningAverage {

    private final int[] values = new int[20];
    private int currentIndex;
    private float sum = 0f;
    private float count = 0f;

    public void clear() {
        count = 0;
        currentIndex = 0;
        sum = 0;
        for (int i = 0; i < values.length; i++) {
            values[i] = 0; 
        }
    }

    public void addValue(int value) {
        sum -= values[currentIndex];
        values[currentIndex] = value;
        sum += values[currentIndex];
        currentIndex++;
        if (currentIndex == values.length) {
            currentIndex = 0;  // faster than %
        }
        if (count < values.length) {
            count++;
        }
    }

    public float getAverage() {
        if (count == 0) {
            return 0f;
        }
        return sum / (count*1000000);
    }

}
