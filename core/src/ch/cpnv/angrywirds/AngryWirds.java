package ch.cpnv.angrywirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import Models.Bird;
import Models.Bubble;
import Models.MovingObject;
import Models.PhysicalObject;
import Models.Pig;
import Models.Scenary;
import Models.Tnt;
import Models.Wasp;

public class AngryWirds extends ApplicationAdapter implements InputProcessor {
	public static final int WORLD_WIDTH = 1850;
	public static final int WORLD_HEIGHT = 1080;
	public static final int FLOOR_HEIGHT = 120;
	private static final int SLINGSHOT_WIDTH = 75;
	private static final int SLINGSHOT_HEIGHT = 225;
	private static final int SLINGSHOT_OFFSET = 100; // from left edge
	private static final int BOARD_WIDTH = 300;
	private static final int BOARD_HEIGHT = 200;
	private static final int BOARD_OFFSET = 50; // from left edge
	private static final float INITIAL_PUSH = 1f; // Multiplication factor on bird launch

	SpriteBatch batch;
	Texture bg;
	Texture slingshot;
	Texture board;

	Bird bird;
	Wasp wasp;
	Random alea;

	Scenary scenary;

	ShapeRenderer shapeRenderer;

	private OrthographicCamera camera;

	@Override
	public void create () {
		alea = new Random();

		batch = new SpriteBatch();
		bg = new Texture(Gdx.files.internal("background.jpg"));
		slingshot = new Texture(Gdx.files.internal("slingshot1.png"));
		board = new Texture(Gdx.files.internal("panel.png"));


		bird = new Bird(100,400,new Vector2(100,100));
		wasp = new Wasp(100,100,new Vector2(100,100));
		bird.setFreeze();
		wasp.setFreeze();

		scenary = new Scenary();

		for (int i = 0; i< 5; i++){
			try {
				scenary.addElement(new Tnt(alea.nextInt(WORLD_WIDTH),150,150));
			}catch (Exception e){
				Gdx.app.log("No TNT", "Already a TNT on it");
			}
		}
		for (int i = 0; i< 5; i++) {
			try {
				scenary.addElement(new Pig(alea.nextInt(WORLD_WIDTH), 150));
			} catch (Exception e) {
				Gdx.app.log("No Pig", "Already a TNT/pig on it");
			}
		}
		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		Gdx.input.setInputProcessor(this);
	}



	public void update(){
		float delta = Gdx.graphics.getDeltaTime();
		PhysicalObject hitObj = scenary.collidesWith(bird);

		if(hitObj != null){
			Gdx.app.log("Test", hitObj.getClass().getName());
			bird.setPosition(0,400);
			bird.setFreeze();
		}
		wasp.unFreeze();
		wasp.accelerate(delta);
		wasp.move(delta);
		bird.accelerate(delta);
		bird.move(delta);

		if(bird.getX() > WORLD_WIDTH){
			bird.setPosition(0,400);
			bird.setFreeze();
		}
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0, 0, camera.viewportWidth,camera.viewportHeight);
		batch.draw(slingshot, SLINGSHOT_OFFSET, FLOOR_HEIGHT, SLINGSHOT_WIDTH, SLINGSHOT_HEIGHT);
		batch.draw(board, BOARD_OFFSET, WORLD_HEIGHT - BOARD_HEIGHT, BOARD_WIDTH, BOARD_HEIGHT);
		bird.draw(batch);
		wasp.draw(batch);
		scenary.draw(batch);
		batch.end();
	}
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 pointTouched = camera.unproject(new Vector3(screenX, screenY, 0)); // Convert from screen coordinates to camera coordinates
		if (bird.isFrozen()) {
			if (bird.getX() == 0)
			{
				bird.setSpeed(new Vector2((pointTouched.x - bird.getX()) * INITIAL_PUSH, (pointTouched.y - bird.getY()) * INITIAL_PUSH));
				bird.unFreeze();
			} else
			{
				bird.setPosition(0, FLOOR_HEIGHT);
				wasp.unFreeze();
			}
		}
		return false;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
