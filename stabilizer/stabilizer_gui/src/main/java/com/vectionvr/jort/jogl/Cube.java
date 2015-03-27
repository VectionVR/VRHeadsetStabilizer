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

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.IOException;
import java.io.InputStream;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
class Cube {

    private final Texture[] textures = new Texture[6];
    private float cubeSize;

    public void init(GL gl) {
        textures[0] = loadTextureFile(gl, "/datas/Front.png");
        textures[1] = loadTextureFile(gl, "/datas/Back.png");
        textures[2] = loadTextureFile(gl, "/datas/Left.png");
        textures[3] = loadTextureFile(gl, "/datas/Right.png");
        textures[4] = loadTextureFile(gl, "/datas/Top.png");
        textures[5] = loadTextureFile(gl, "/datas/Bottom.png");

    }

    public void draw(GL2 gl) {
        gl.glEnable(GL2.GL_TEXTURE_2D);

        gl.glColor3f(1f, 1f, 1f);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0].getTextureObject());
        // Front Face
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-cubeSize, -cubeSize, cubeSize);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(cubeSize, -cubeSize, cubeSize);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(cubeSize, cubeSize, cubeSize);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-cubeSize, cubeSize, cubeSize);
        gl.glEnd();

        // Back Face
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[1].getTextureObject());
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-cubeSize, -cubeSize, -cubeSize);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-cubeSize, cubeSize, -cubeSize);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(cubeSize, cubeSize, -cubeSize);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(cubeSize, -cubeSize, -cubeSize);
        gl.glEnd();
        // Top Face
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[4].getTextureObject());
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-cubeSize, cubeSize, -cubeSize);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-cubeSize, cubeSize, cubeSize);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(cubeSize, cubeSize, cubeSize);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(cubeSize, cubeSize, -cubeSize);
        gl.glEnd();
        // Bottom Face
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[5].getTextureObject());
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-cubeSize, -cubeSize, -cubeSize);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(cubeSize, -cubeSize, -cubeSize);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(cubeSize, -cubeSize, cubeSize);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-cubeSize, -cubeSize, cubeSize);
        gl.glEnd();
        // Right face
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[3].getTextureObject());
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(cubeSize, -cubeSize, -cubeSize);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(cubeSize, cubeSize, -cubeSize);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(cubeSize, cubeSize, cubeSize);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(cubeSize, -cubeSize, cubeSize);
        gl.glEnd();

        // Left Face
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[2].getTextureObject());
        gl.glBegin(GL2.GL_QUADS);
        {
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-cubeSize, -cubeSize, -cubeSize);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(-cubeSize, -cubeSize, cubeSize);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(-cubeSize, cubeSize, cubeSize);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-cubeSize, cubeSize, -cubeSize);
        }
        gl.glEnd();

        gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
        gl.glDisable(GL2.GL_TEXTURE_2D);

    }
    protected Texture loadTextureFile(GL gl, String resourceName) {
        try {
            InputStream stream = getClass().getResourceAsStream(resourceName);
            TextureData data = TextureIO.newTextureData(gl.getGLProfile(), stream, false, "png");
            Texture texture = TextureIO.newTexture(data);
            texture.setTexParameteri(gl, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
            texture.setTexParameteri(gl, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
            texture.setTexParameterf(gl, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
            texture.setTexParameterf(gl, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
            return texture;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCubeSize(float cubeSize) {
        this.cubeSize = cubeSize/4;
    }
    
}
