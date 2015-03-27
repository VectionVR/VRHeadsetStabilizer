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

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
class GrayAxis {

    private float cubeSize;
    private float lineWidth;

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setCubeSize(float cubeSize) {
        this.cubeSize = cubeSize;
    }

    public void init(GL gl) {
    }

    public void draw(GL2 gl) {
        gl.glLineWidth(1f);
        gl.glBegin(GL2.GL_LINES);
        {
            gl.glColor3f(.6f, .6f, .6f);
            gl.glVertex3f(0, 0, 0);
            gl.glVertex3f(cubeSize, 0, 0);
            gl.glColor3f(.6f, .6f, .6f);
            gl.glVertex3f(0, 0, 0);
            gl.glVertex3f(0, cubeSize, 0);
            gl.glColor3f(.6f, .6f, .6f);
            gl.glVertex3f(0, 0, 0);
            gl.glVertex3f(0, 0, -cubeSize);
        }
        gl.glEnd();
        gl.glColor3f(0, 0, 0);
    }
}
