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
