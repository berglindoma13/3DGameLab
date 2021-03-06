package com.ru.tgra.berglindoma13_Haukura14;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

public class LabFirst3DGame extends ApplicationAdapter implements InputProcessor {

    private static Shaders3D shader;

    private Camera cam;

    private static Maze maze;

	private static Obstacle obstacle;


	@Override
	public void create () {

        shader = new Shaders3D();


        maze = new Maze();

		obstacle = new Obstacle();

		Gdx.input.setInputProcessor(this);

		BoxGraphic.create(shader.getVertexPointer(), shader.getNormalPointer());
		SphereGraphic.create(shader.getVertexPointer(), shader.getNormalPointer());

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		ModelMatrix.main = new ModelMatrix();
		ModelMatrix.main.loadIdentityMatrix();
		ModelMatrix.main.setShaderMatrix(shader.getModelMatrixLoc());

		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        cam = new Camera(shader.getViewMatrixLoc(), shader.getProjectionMatrixLoc());

        cam.perspectiveProjection(100.0f,1.0f,0.01f,60.0f);
        cam.look(new Point3D(4.5f, -0.3f, 9.5f), new Point3D(5f,0,0), new Vector3D(0,1,0));
	}

	private void input()
	{
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
		/*if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			cam.slide(0, 3.0f * deltaTime, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			cam.slide(0, -3.0f * deltaTime, 0);
		}*/
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
	
	private void update()
	{
        input();
		obstacle.update();
	}
	
	private void display()
	{
		//do all actual drawing and rendering here
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        shader.setEyePosition(cam.eye.x, cam.eye.y, cam.eye.z, 1.0f);

        shader.setLightDiffuse(0.64f, 0.64f, 0.64f, 1.0f);

        //setting the position of the 3 different lights
        shader.setLightPosition(cam.eye.x,cam.eye.y,cam.eye.z,1.0f);
        shader.setLightPosition2(0.0f,0.0f,0.0f,1.0f);
		shader.setLightPosition3(10.0f,4.0f,10.0f,4.0f);

		ModelMatrix.main.loadIdentityMatrix();
        ModelMatrix.main.pushMatrix();

        //draw floor
        ModelMatrix.main.pushMatrix();
        ModelMatrix.main.addTranslation(5f,-0.5f,5);
        ModelMatrix.main.addScale(11.0f,0.1f,11.0f);
        shader.setMaterialDiffuse(1.5f, 0.0f, 0.0f, 1.0f);
        ModelMatrix.main.setShaderMatrix();
        BoxGraphic.drawSolidCube();
        ModelMatrix.main.popMatrix();

        int max = 11;

        shader.setShininess(0.1f);

		for (int i = 0; i < max ; i++){

            for(int j = 0; j < max; j++){

				if(maze.cells[i][j].northwall){
					ModelMatrix.main.pushMatrix();
					ModelMatrix.main.addTranslation((float)i + 0.5f, 0, (float)j);
					ModelMatrix.main.addScale(1.3f,1f,0.3f);
					ModelMatrix.main.setShaderMatrix();
					BoxGraphic.drawSolidCube();
					ModelMatrix.main.popMatrix();
				}
				if(maze.cells[i][j].westwall){
					ModelMatrix.main.pushMatrix();
					ModelMatrix.main.addTranslation((float)i, 0, (float)j + 0.5f);
					ModelMatrix.main.addScale(0.3f,1f,1f);
					ModelMatrix.main.setShaderMatrix();
					BoxGraphic.drawSolidCube();
					ModelMatrix.main.popMatrix();
				}
            }
        }
        ModelMatrix.main.popMatrix();
        cam.setShaderMatrix();
		obstacle.drawObstacle(shader);
        cam.checkCollision();
	}

	@Override
	public void render () {
		update();
		display();
	}

	public static Cell[][] getCells() {
		return maze.cells;
	}

	public static Obstacle getObstacle() {
		return obstacle;
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