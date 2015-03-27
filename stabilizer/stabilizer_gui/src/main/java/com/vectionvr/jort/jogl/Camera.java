/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vectionvr.jort.jogl;

import javax.media.opengl.glu.GLU;

/**
 *
 * @author nico
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
