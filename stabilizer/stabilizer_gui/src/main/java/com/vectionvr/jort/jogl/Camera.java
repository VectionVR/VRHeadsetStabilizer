/**
 * Copyright (C) 2014 Bnome SPRL (info@bnome.be)
 *
 * This file is part of VectionVR Stabilizer.
 *
 * VectionVR Stabilizer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VectionVR Stabilizer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VectionVR Stabilizer.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vectionvr.jort.jogl;

import javax.media.opengl.glu.GLU;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
class Camera {

    static void setupView(int distance, GLU glu, CameraViews cameraView) {
        switch (cameraView) {
            case Right:
                glu.gluLookAt(distance, 0, 0, 0, 0, 0, 0, 1, 0);
                break;
            case Left:
                glu.gluLookAt(-distance, 0, 0, 0, 0, 0, 0, 1, 0);
                break;
            case Back:
                glu.gluLookAt(0, 0, -distance, 0, 0, 0, 0, 1, 0);
                break;
            case Front:
                glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);
                break;
            case Top:
                glu.gluLookAt(0, distance, 0, 0, 0, 0, 0, 0, -1);
                break;
            case Bottom:
                glu.gluLookAt(0, -distance, 0, 0, 0, 0, 0, 0, 1);
                break;
            case Subjective:
                glu.gluLookAt(distance*2/3, distance*2/3, distance*2/3, 0, 0, 0, -1f, 1, -1);
                break;
            case FirstPerson:
                glu.gluLookAt(0, distance*2/3, distance*2/3, 0, 0, 0, 0, 1, -1);
                break;
        }
    }
}
