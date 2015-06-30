package screens;

import game.GameAssetManager;
import game.GameLevelSettings;
import game.Q;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class LevelSwithScreen implements Screen {

	SpriteBatch _batch;
	BitmapFont _font;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0.0f, 1);

		_batch.begin();
		_batch.draw(GameAssetManager.getInstance().get(Q.WIN_BACKGROUND, Texture.class), 0, 0, 800, 480);
		_font.draw(_batch, "Level complited, your total score is:  "
				+ GameLevelSettings.get_score(), 50, 400);
		_font.draw(_batch, "Wanna play next level ? ENTER/ESCAPE", 50, 380);
		_batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Q.FONT));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 22;
		_font = generator.generateFont(parameter); 
		_batch = new SpriteBatch();
		Gdx.input.setOnscreenKeyboardVisible(true);
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
				case Keys.ENTER:
					ScreenManager.getInstance().show(CustomScreen.GAME);
					break;
				case Keys.ESCAPE:
					GameLevelSettings.reset();
					ScreenManager.getInstance().show(CustomScreen.MAIN_MENU);
					break;
				default:
					break;
				}
				return super.keyDown(keycode);
			}
		});
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
