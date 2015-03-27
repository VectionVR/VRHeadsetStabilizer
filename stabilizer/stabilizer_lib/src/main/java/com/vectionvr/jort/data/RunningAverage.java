/**
 * Copyright (C) 2014 Bnome SPRL (info@bnome.be)
 *
 * This file is part of VectionVR Stabilizer library.
 *
 * VectionVR Stabilizer library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VectionVR Stabilizer library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VectionVR Stabilizer library.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vectionvr.jort.data;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
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
