package com.vectionvr.jort.jogl;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 *
 * @author nico
 */
class Axis {

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
        if (lineWidth > 0) {
            gl.glLineWidth(lineWidth);
            gl.glBegin(GL2.GL_LINES);
            {
                gl.glColor3f(1, 0, 0);
                gl.glVertex3f(0, 0, 0);
                gl.glVertex3f(cubeSize, 0, 0);
                gl.glColor3f(0, 0, 1);
                gl.glVertex3f(0, 0, 0);
                gl.glVertex3f(0, cubeSize, 0);
                gl.glColor3f(0, 1, 0);
                gl.glVertex3f(0, 0, 0);
                gl.glVertex3f(0, 0, -cubeSize);
            }
            gl.glEnd();
            gl.glColor3f(0, 0, 0);
        }
    }
}
