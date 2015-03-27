package com.vectionvr.jort.jogl;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 *
 * @author nico
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
