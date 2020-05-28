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

	SpriteBatch batch;
	Texture bg;
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
		bird = new Bird(100,200,new Vector2(100,100));
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
			bird.setPosition(200,200);
			bird.setFreeze();
		}


		if(Gdx.input.isTouched()){
			wasp.unFreeze();
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			//Gdx.app.log(touchPos.toString(),"pos");
			bird.setSpeed(touchPos.x/6,touchPos.y/6);
			bird.unFreeze();
		}
		wasp.accelerate(delta);
		wasp.move(delta);
		bird.accelerate(delta);
		bird.move(delta);

		if(bird.getX() > WORLD_WIDTH){
			bird.setPosition(200,200);
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
		bird.draw(batch);
		wasp.draw(batch);
		scenary.draw(batch);
		batch.end();


		/*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.rect(bird.getBoundingRectangle().x,bird.getBoundingRectangle().y,bird.getBoundingRectangle().width,bird.getBoundingRectangle().height);
		shapeRenderer.end();*/
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
