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
class Grid {
    private static final float CRIMSON_B = 200f/255f;
    private static final float CRIMSON_G = 200f/255f;
    private static final float CRIMSON_R = 200f/255f;
    private static final int GRID_SIZE = 256;
    private static final int DECAL_TO_CENTER = -GRID_SIZE/2;
    private static final int CELL_SIZE = 8;
    private float absoluteDecal=1;

    public void setAbsoluteDecal(float absoluteDecal) {
        this.absoluteDecal = absoluteDecal;
    }
    
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glRotatef(90f, 1f, 0f, 0f);
        gl.glTranslatef(DECAL_TO_CENTER, DECAL_TO_CENTER,absoluteDecal);
        gl.glColor3f(CRIMSON_R, CRIMSON_G, CRIMSON_B);
        gl.glLineWidth(1f);
        gl.glBegin(GL.GL_LINES);
        for (float i = 0; i < GRID_SIZE + 1; i+=CELL_SIZE) {
            gl.glVertex3f(i, 0, 0);
            gl.glVertex3f(i, GRID_SIZE, 0);

            gl.glVertex3f(0, i, 0);
            gl.glVertex3f(GRID_SIZE, i, 0);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

}
