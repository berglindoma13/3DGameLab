package com.ru.tgra.berglindoma13_Haukura14;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by Lenny on 11.10.2016.
 */
public class Obstacle {
    public float x;
    public float y;
    public float z;
    private boolean movingUp;

    Obstacle(){
        randomize();
    }

    public void drawObstacle(Shaders3D shader){
        ModelMatrix.main.pushMatrix();
        ModelMatrix.main.addTranslation(x, y, z);
        ModelMatrix.main.addScale(0.15f, 0.15f, 0.15f);
        Gdx.gl.glUniform4f(shader.getColorLoc(), 0.9f, 0.0f, 0.9f, 1.0f);
        ModelMatrix.main.setShaderMatrix();
        SphereGraphic.drawSolidSphere();
        ModelMatrix.main.popMatrix();
    }

    public void update(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        if(movingUp){
            if(this.y >= 0.4f){
                movingUp = false;
            }
            else{
                this.y += 1.2f * deltaTime;
            }
        }
        else {
            if(this.y <= -0.4f){
                movingUp = true;
            }
            else {
                this.y -= 1.2f * deltaTime;
            }
        }
    }

    public void randomize(){
        Random random = new Random();
        this.x = (float)random.nextInt(9) + 0.5f;
        this.y = 0.5f;
        this.z = (float)random.nextInt(9) + 0.5f;
        movingUp = true;
    }

}
