package com.ru.tgra.berglindoma13_Haukura14;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

import java.nio.FloatBuffer;
import java.util.Random;

import com.badlogic.gdx.utils.BufferUtils;
import com.sun.javafx.sg.prism.NGShape;

public class LabFirst3DGame extends ApplicationAdapter implements InputProcessor {

	private FloatBuffer matrixBuffer;

	private int renderingProgramID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private int positionLoc;
	private int normalLoc;

	private int modelMatrixLoc;
	private int viewMatrixLoc;
	private int projectionMatrixLoc;

	private int colorLoc;

    private Camera cam;

	private static Cell[][] cells;
	private boolean[] randomSouth;
	private boolean[] randomWest;

	@Override
	public void create () {

		cells = new Cell[10][10];
		randomSouth = new boolean[100];
		randomWest = new boolean[100];

		for(int i = 0; i < 100; i++){
			randomSouth[i] = (Math.random() < 0.5);
			randomWest[i] = (Math.random() < 0.5);
		}

		Gdx.input.setInputProcessor(this);

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
		projectionMatrixLoc	= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_projectionMatrix");

		colorLoc				= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_color");

		Gdx.gl.glUseProgram(renderingProgramID);

		OrthographicProjection3D(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight(), -1, 1);
/*
		float[] mm = new float[16];

		mm[0] = 1.0f; mm[4] = 0.0f; mm[8] = 0.0f; mm[12] = 0.0f;
		mm[1] = 0.0f; mm[5] = 1.0f; mm[9] = 0.0f; mm[13] = 0.0f;
		mm[2] = 0.0f; mm[6] = 0.0f; mm[10] = 1.0f; mm[14] = 0.0f;
		mm[3] = 0.0f; mm[7] = 0.0f; mm[11] = 0.0f; mm[15] = 1.0f;

		modelMatrixBuffer = BufferUtils.newFloatBuffer(16);
		modelMatrixBuffer.put(mm);
		modelMatrixBuffer.rewind();

		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrixBuffer);
*/
		//COLOR IS SET HERE
		Gdx.gl.glUniform4f(colorLoc, 0.7f, 0.2f, 0, 1);

		BoxGraphic.create(positionLoc, normalLoc);
		SphereGraphic.create(positionLoc, normalLoc);
		SincGraphic.create(positionLoc);
		CoordFrameGraphic.create(positionLoc);

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		ModelMatrix.main = new ModelMatrix();
		ModelMatrix.main.loadIdentityMatrix();
		ModelMatrix.main.setShaderMatrix(modelMatrixLoc);

		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

		//OrthographicProjection3D(-2, 2, -2, 2, 1, 100);
		PerspctiveProjection3D();
        //change the first point to change the starting view
		//Look3D(new Point3D(1.0f, 3.0f, 2.0f), new Point3D(0,0,0), new Vector3D(0,1,0));
        cam = new Camera(viewMatrixLoc);
        cam.look(new Point3D(4.0f, 0.0f, 2.0f), new Point3D(4.0f,0,0), new Vector3D(0,1,0));
	}

	private void input()
	{
		if(!cam.hitWall) {
			float deltaTime = Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				cam.pitch(90f * deltaTime);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				cam.pitch(-90f * deltaTime);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				cam.yaw(-90f * deltaTime);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				cam.yaw(90f * deltaTime);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.E)) {
				cam.slide(0, 3.0f * deltaTime, 0);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
				cam.slide(0, -3.0f * deltaTime, 0);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				cam.slide(-3.0f * deltaTime, 0, 0);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				cam.slide(3.0f * deltaTime, 0, 0);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.W)) {
				cam.slide(0, 0, -3.0f * deltaTime);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
				cam.slide(0, 0, 3.0f * deltaTime);
			}
		}
	}
	
	private void update()
	{
		float deltaTime = Gdx.graphics.getDeltaTime();

		//angle += 180.0f * deltaTime;

        cam.checkCollision();

		//do all updates to the game
	}
	
	private void display()
	{
		//do all actual drawing and rendering here
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		Gdx.gl.glUniform4f(colorLoc, 0.9f, 0.3f, 0.1f, 1.0f);

        cam.setShaderMatrix();
		ModelMatrix.main.loadIdentityMatrix();
        ModelMatrix.main.pushMatrix();

        int max = 10;

		for (int i = 0; i < max; i++){
            //ModelMatrix.main.addTranslation(1.0f,0,0);
            //ModelMatrix.main.pushMatrix();

            for(int j = 0; j < max; j++){

                //ModelMatrix.main.addTranslation(0,0,-1.0f);
                //ModelMatrix.main.pushMatrix();

                //TODO: WALL SHIT
				cells[i][j] = new Cell(randomSouth[10*i+j], randomWest[10*i+j]);
				if(cells[i][j].southwall){
					ModelMatrix.main.pushMatrix();
					ModelMatrix.main.addTranslation((float)i + 0.5f, 0, (float)j - 0.15f);
					ModelMatrix.main.addScale(1f,1f,0.3f);
					ModelMatrix.main.setShaderMatrix();
					BoxGraphic.drawSolidCube();
					ModelMatrix.main.popMatrix();
				}
				if(cells[i][j].westwall){
					ModelMatrix.main.pushMatrix();
					ModelMatrix.main.addTranslation((float)i - 0.15f, 0, (float)j + 0.5f);
					ModelMatrix.main.addScale(0.3f,1f,1f);
					ModelMatrix.main.setShaderMatrix();
					BoxGraphic.drawSolidCube();
					ModelMatrix.main.popMatrix();
				}

            }
        }
        ModelMatrix.main.popMatrix();


		//BoxGraphic.drawOutlineCube();
		//SphereGraphic.drawSolidSphere();
		//SphereGraphic.drawOutlineSphere();
		//ModelMatrix.main.popMatrix();
	}

	@Override
	public void render () {
		
		input();
		//put the code inside the update and display methods, depending on the nature of the code
		update();
		display();

	}

	private void OrthographicProjection3D(float left, float right, float bottom, float top, float near, float far) {
		float[] pm = new float[16];

		pm[0] = 2.0f / (right - left); pm[4] = 0.0f; pm[8] = 0.0f; pm[12] = -(right + left) / (right - left);
		pm[1] = 0.0f; pm[5] = 2.0f / (top - bottom); pm[9] = 0.0f; pm[13] = -(top + bottom) / (top - bottom);
		pm[2] = 0.0f; pm[6] = 0.0f; pm[10] = 2.0f / (near - far); pm[14] = (near + far) / (near - far);
		pm[3] = 0.0f; pm[7] = 0.0f; pm[11] = 0.0f; pm[15] = 1.0f;

		matrixBuffer = BufferUtils.newFloatBuffer(16);
		matrixBuffer.put(pm);
		matrixBuffer.rewind();
		Gdx.gl.glUniformMatrix4fv(projectionMatrixLoc, 1, false, matrixBuffer);

		pm[0] = 1.0f; pm[4] = 0.0f; pm[8] = 0.0f; pm[12] = 0.0f;
        pm[1] = 0.0f; pm[5] = 1.0f; pm[9] = 0.0f; pm[13] = 0.0f;
        pm[2] = 0.0f; pm[6] = 0.0f; pm[10] = 1.0f; pm[14] = 0.0f;
        pm[3] = 0.0f; pm[7] = 0.0f; pm[11] = 0.0f; pm[15] = 1.0f;

		matrixBuffer = BufferUtils.newFloatBuffer(16);
		matrixBuffer.put(pm);
		matrixBuffer.rewind();
		Gdx.gl.glUniformMatrix4fv(viewMatrixLoc, 1, false, matrixBuffer);
	}

	//deciding the lens of the camera, this is 90 degrees wide in any direction.
	//NearPlane = 1, FarPlane = 100
	private void PerspctiveProjection3D() {
		float[] pm = new float[16];

		pm[0] = 1.0f; pm[4] = 0.0f; pm[8] = 0.0f; pm[12] = 0.0f;
		pm[1] = 0.0f; pm[5] = 1.0f; pm[9] = 0.0f; pm[13] = 0.0f;
		pm[2] = 0.0f; pm[6] = 0.0f; pm[10] = -1.02f; pm[14] = -2.02f;
		pm[3] = 0.0f; pm[7] = 0.0f; pm[11] = -1.0f; pm[15] = 0.0f;

		matrixBuffer = BufferUtils.newFloatBuffer(16);
		matrixBuffer.put(pm);
		matrixBuffer.rewind();
		Gdx.gl.glUniformMatrix4fv(projectionMatrixLoc, 1, false, matrixBuffer);

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}


}