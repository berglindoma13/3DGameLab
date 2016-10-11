package com.ru.tgra.berglindoma13_Haukura14;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by Berglind on 11.10.2016.
 */
public class Shaders3D {
    private int renderingProgramID;
    private int vertexShaderID;
    private int fragmentShaderID;

    private int positionLoc;
    private int normalLoc;

    private int modelMatrixLoc;
    private int viewMatrixLoc;
    private int projectionMatrixLoc;

    private int eyePosLoc;
    private int matShineLoc;

    private int lightPosLoc;
    private int lightPosLoc2;
    private int lightPosLoc3;
    private int lightDifLoc;
    private int matDifLoc;

    public Shaders3D(){
        String vertexShaderString;
        String fragmentShaderString;

        vertexShaderString = Gdx.files.internal("core/assets/shaders/simple3D.vert").readString();
        fragmentShaderString =  Gdx.files.internal("core/assets/shaders/simple3D.frag").readString();

        vertexShaderID = Gdx.gl.glCreateShader(GL20.GL_VERTEX_SHADER);
        fragmentShaderID = Gdx.gl.glCreateShader(GL20.GL_FRAGMENT_SHADER);

        Gdx.gl.glShaderSource(vertexShaderID, vertexShaderString);
        Gdx.gl.glShaderSource(fragmentShaderID, fragmentShaderString);

        Gdx.gl.glCompileShader(vertexShaderID);
        Gdx.gl.glCompileShader(fragmentShaderID);

        renderingProgramID = Gdx.gl.glCreateProgram();

        Gdx.gl.glAttachShader(renderingProgramID, vertexShaderID);
        Gdx.gl.glAttachShader(renderingProgramID, fragmentShaderID);

        Gdx.gl.glLinkProgram(renderingProgramID);

        positionLoc				= Gdx.gl.glGetAttribLocation(renderingProgramID, "a_position");
        Gdx.gl.glEnableVertexAttribArray(positionLoc);

        normalLoc				= Gdx.gl.glGetAttribLocation(renderingProgramID, "a_normal");
        Gdx.gl.glEnableVertexAttribArray(normalLoc);

        modelMatrixLoc			= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_modelMatrix");
        viewMatrixLoc			= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_viewMatrix");
        projectionMatrixLoc	    = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_projectionMatrix");

        eyePosLoc			    = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_eyePosition");
        matShineLoc				= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_materialShininess");

        //colorLoc				= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_color");
        lightPosLoc				= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_lightPosition");
        lightPosLoc2			= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_lightPosition1");
        lightPosLoc3			= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_lightPosition3");
        lightDifLoc			    = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_lightDiffuse");
        matDifLoc				= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_materialDiffuse");

        Gdx.gl.glUseProgram(renderingProgramID);
    }

    public void setEyePosition(float x, float y,  float z, float w){
        Gdx.gl.glUniform4f(eyePosLoc,x,y,z,w);
    }
    public void setShininess(float shine){
        Gdx.gl.glUniform1f(matShineLoc,shine);
    }
    public void setMaterialDiffuse(float r, float g,  float b, float a){
        Gdx.gl.glUniform4f(matDifLoc,r,g,b,a);
    }
    public void setLightDiffuse(float r, float g, float b, float a){
        Gdx.gl.glUniform4f(lightDifLoc,r,g,b,a);
    }
    public void setLightPosition(float x, float y, float z, float w){
        Gdx.gl.glUniform4f(lightPosLoc,x,y,z,w);
    }
    public void setLightPosition2(float x, float y, float z, float w){
        Gdx.gl.glUniform4f(lightPosLoc2,x,y,z,w);
    }
    public void setLightPosition3(float x, float y, float z, float w){
        Gdx.gl.glUniform4f(lightPosLoc3,x,y,z,w);
    }

    public int getVertexPointer(){
        return positionLoc;
    }
    public int getNormalPointer(){
        return normalLoc;
    }
    public int getModelMatrixLoc(){
        return modelMatrixLoc;
    }
    public int getProjectionMatrixLoc(){
        return projectionMatrixLoc;
    }
    public int getViewMatrixLoc(){
        return viewMatrixLoc;
    }

}
