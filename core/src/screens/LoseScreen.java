package screens;

import game.GameAssetManager;
import game.GameLevelSettings;
import game.Q;
import actors.TextActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LoseScreen implements Screen{

	SpriteBatch _batch;
	private Stage _stage;
	private TextActor _text;
	private Texture _region;
	
	public LoseScreen() {
		_text = new TextActor("Total score: " + GameLevelSettings.get_score(), 180, 230);
		GameLevelSettings.reset();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		_batch.begin();
		_batch.draw(_region, 0, -30, _region.getWidth(), _region.getHeight());
		_batch.end();
		
		_stage.act(delta);
		_stage.draw();
	}

	@Override
	public void show() {
		_region = GameAssetManager.getTexture(Q.LOSE_BACKGROUND).getTexture();
		_batch = new SpriteBatch();
		_stage = new Stage();
		_stage.addActor(_text);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
