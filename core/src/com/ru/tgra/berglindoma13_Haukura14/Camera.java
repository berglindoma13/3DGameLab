package com.ru.tgra.berglindoma13_Haukura14;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created by Berglind on 06/10/2016.
 */
public class Camera {
    Point3D eye;
    Vector3D u;
    Vector3D v;
    Vector3D n;

    private int viewMatrixPointer;
    private FloatBuffer matrixBuffer;
    public boolean hitWall;

    public Camera(int matrixPointer){
        this.viewMatrixPointer = matrixPointer;
        matrixBuffer = BufferUtils.newFloatBuffer(16);

        eye = new Point3D();
        u = new Vector3D(1,0,0);
        v = new Vector3D(0,1,0);
        n = new Vector3D(0,0,1);

        hitWall = false;
    }

    public void look(Point3D eye, Point3D center, Vector3D up){
        this.eye.set(eye.x, eye.y, eye.z);
        n = Vector3D.difference(eye,center);
        u = up.cross(n);
        n.normalize();
        u.normalize();
        v = n.cross(u);
    }

    public void slide(float delU, float delV, float delN){
        eye.x += delU*u.x + delV*v.x + delN*n.x;
        eye.y += delU*u.y + delV*v.y + delN*n.y;
        eye.z += delU*u.z + delV*v.z + delN*n.z;
    }

    public void roll(float angle)
    {
        float radians = angle * (float)Math.PI / 180.0f;
        float c = (float)Math.cos(radians);
        float s = (float)Math.sin(radians);
        Vector3D t = new Vector3D(u.x, u.y, u.z);

        u.set(t.x * c - v.x * s, t.y * c - v.y * s, t.z * c - v.z * s);
        v.set(t.x * s + v.x * c, t.y * s + v.y * c, t.z * s + v.z * c);
    }

    public void yaw(float angle)
    {
        float radians = angle * (float)Math.PI / 180.0f;
        float c = (float)Math.cos(radians);
        float s = (float)Math.sin(radians);
        Vector3D t = new Vector3D(u.x, u.y, u.z);

        u.set(t.x * c - n.x * s, t.y * c - n.y * s, t.z * c - n.z * s);
        n.set(t.x * s + n.x * c, t.y * s + n.y * c, t.z * s + n.z * c);
    }

    public void pitch(float angle)
    {
        float radians = angle * (float)Math.PI / 180.0f;
        float c = (float)Math.cos(radians);
        float s = (float)Math.sin(radians);
        Vector3D t = new Vector3D(n.x, n.y, n.z);

        n.set(t.x * c - v.x * s, t.y * c - v.y * s, t.z * c - v.z * s);
        v.set(t.x * s + v.x * c, t.y * s + v.y * c, t.z * s + v.z * c);
    }

    public void setShaderMatrix(){
        Vector3D minusEye = new Vector3D(-eye.x, -eye.y, -eye.z);

        float[] pm = new float[16];

        pm[0] = u.x; pm[4] = u.y; pm[8] = u.z; pm[12] = minusEye.dot(u);
        pm[1] = v.x; pm[5] = v.y; pm[9] = v.z; pm[13] = minusEye.dot(v);
        pm[2] = n.x; pm[6] = n.y; pm[10] = n.z; pm[14] = minusEye.dot(n);
        pm[3] = 0.0f; pm[7] = 0.0f; pm[11] = 0.0f; pm[15] = 1.0f;

        matrixBuffer.put(pm);
        matrixBuffer.rewind();
        Gdx.gl.glUniformMatrix4fv(viewMatrixPointer, 1, false, matrixBuffer);
    }

    public void checkCollision(){
        float deltaTime = Gdx.graphics.getDeltaTime();

        //current position = eye
        //System.out.println("eye" + (int)eye.x + ", " + (int)eye.y + ", " + (int)eye.z);

        //System.out.println("Eye: " + eye.x + " " + eye.y + " " + eye.z);

        int i = (int)eye.x;
        int j = (int)eye.z;

        //System.out.println("i: " + i + " j: " + j + "\neye.x: " + eye.x + " eye.z: " + eye.z);

        if(i >= 0 && i < 10 && j > 0 && j <= 10){
            //Bottom wall in current cell
            if(LabFirst3DGame.getCells()[i][j+1].northwall){
                if(eye.z >= j + 1 - 0.15){
                    //System.out.println("colliding with bottom wall in current cell");
                    eye.z = (float)j + 1 - 0.20f;
                }
            }
            //Left wall in current cell
            if(LabFirst3DGame.getCells()[i][j].westwall){
                if(eye.x <= i + 0.15){
                    //System.out.println("colliding with left wall in current cell");
                    eye.x = (float)i + 0.15f;
                }
            }
            //Bottom wall in cell above
            if(LabFirst3DGame.getCells()[i][j].northwall){
                if(eye.z <= j + 0.15){
                    //System.out.println("colliding bottom wall in above cell");
                    eye.z = (float)j + 0.15f;
                }
            }
            //Left wall in cell on right
            if(LabFirst3DGame.getCells()[i + 1][j].westwall){
                if(eye.x >= i + 1 - 0.15){
                    //System.out.println("colliding with wall in cell on right");
                    eye.x = (float)i + 1 - 0.15f;
                }
            }
        }
    }
}