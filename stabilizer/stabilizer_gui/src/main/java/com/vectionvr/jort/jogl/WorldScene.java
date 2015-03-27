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

import com.jogamp.opengl.math.Quaternion;
import com.vectionvr.jort.data.SensorData;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
public class WorldScene implements GLEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorldScene.class);
    private float cubeSize = 100f;
    private GLU glu;
    private final Quaternion currentRotation = new Quaternion(0f, 0f, 0f, 0f);
    private CameraViews cameraViews = CameraViews.FirstPerson;
    private float hRatio;
    private boolean cameraValid;
    private final Grid grid = new Grid();
    private final Cube cube = new Cube();
    private final Axis axis = new Axis();
    private final GrayAxis grayAxis = new GrayAxis();
    private float[] backgroundColor = new float[]{0.93f, 0.93f, 0.93f, 1f};
    private boolean cubeEnabled = true;
    private boolean gridEnabled;

    @Override
    public void init(GLAutoDrawable gLDrawable) {
        LOGGER.debug("In init");
        final GL2 gl = (GL2) gLDrawable.getGL();
        glu = GLU.createGLU(gl);

        gl.glClearColor(backgroundColor[0], backgroundColor[1], backgroundColor[2], backgroundColor[3]);
        gl.glEnable(GL2.GL_DEPTH_TEST);							// Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);

        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
        gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL2.GL_INDEX_ARRAY);

        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);	// Really Nice Perspective Calculations

        if (gl.isExtensionAvailable("GL_ARB_multisample")) {
            LOGGER.debug("ARB available");
            gl.glEnable(GL2.GL_MULTISAMPLE);
        }
        cube.init(gl);
        axis.init(gl);
        grayAxis.init(gl);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void display(GLAutoDrawable glad) {
        final GL2 gl = (GL2) glad.getGL();
        if (cameraValid) {
            cameraValid = true;
            setupCamera(gl, cameraViews);
        }
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        displayObjects(gl);
    }

    private void displayObjects(final GL2 gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity();
        if(gridEnabled){
            grid.draw(gl);
        }else{
            grayAxis.draw(gl);
        }
        gl.glMultTransposeMatrixf(currentRotation.toMatrix(), 0);
        if(cubeEnabled){
            cube.draw(gl);
        }
        axis.draw(gl);
        gl.glEnd();
        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        final GL2 gl = (GL2) gLDrawable.getGL();
        if (height <= 0) // avoid a divide by zero error!
        {
            height = 1;
        }
        hRatio = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        setupCamera(gl, cameraViews);
    }

    private void setupCamera(final GL2 gl, CameraViews cameraView) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45f, hRatio, 1f, 1250f);
        Camera.setupView((int) (2 * cubeSize), glu, cameraView);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    public void update(SensorData data) {
        currentRotation.setW(data.getQuaternion().getW());
        currentRotation.setX(data.getQuaternion().getX());
        // switch Z and Y axis for opengl (don't understand why yet ...)
        currentRotation.setY(data.getQuaternion().getZ());
        currentRotation.setZ(data.getQuaternion().getY());
        currentRotation.normalize();
    }

    public void update(float[] axis, float angle) {
        currentRotation.fromAxis(axis, angle);
        currentRotation.normalize();
    }

    public void setCamera(CameraViews cameraViews) {
        this.cameraViews = cameraViews;
        cameraValid = false;
    }

    public void reset() {
        currentRotation.setIdentity();
    }

    public void setLineWidth(float lineWidth) {
        axis.setLineWidth(lineWidth);
        grayAxis.setLineWidth(lineWidth);
    }

    public void setBackgroundColor(float[] backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setCubeSize(float cubeSize) {
        this.cubeSize = cubeSize;
        grid.setAbsoluteDecal(cubeSize);
        axis.setCubeSize(cubeSize);
        grayAxis.setCubeSize(cubeSize);
        cube.setCubeSize(cubeSize);
        cameraValid = false;
    }

    public void setCubeEnabled(boolean cubeEnabled) {
        this.cubeEnabled = cubeEnabled;
    }

    public void setGridEnabled(boolean gridEnabled) {
        this.gridEnabled = gridEnabled;
    }
}
