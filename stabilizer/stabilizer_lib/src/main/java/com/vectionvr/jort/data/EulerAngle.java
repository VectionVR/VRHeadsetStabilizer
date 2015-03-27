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
