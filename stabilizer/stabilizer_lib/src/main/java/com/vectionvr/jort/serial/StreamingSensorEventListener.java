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
package com.vectionvr.jort.serial;

import com.vectionvr.jort.data.SensorData;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
public interface StreamingSensorEventListener {
    public void sensorStarted(ImuOrientationDataStreamer sensor);
    public void onSensorData(SensorData data);
    public void sensorStopped();
    public void handleException(SensorException e);
}
