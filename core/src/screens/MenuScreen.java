package screens;

import game.GameAssetManager;
import game.Q;
import actors.MenuActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuScreen implements Screen {

	private Stage _stage;
	private Music _music;
	SpriteBatch _batch;
	private Texture _region;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0.0f, 1);
		
		_batch.begin();
		_batch.draw(_region, 0, -30, _region.getWidth(), _region.getHeight());
		_batch.end();
		_stage.act(delta);
		_stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		_region = GameAssetManager.getTexture(Q.MENU_BACKGROUND).getTexture();
		_batch = new SpriteBatch();
		_music = GameAssetManager.getInstance().get(Q.MENU_MUSIC);
		_music.play();
		
		_stage = new Stage() {
			@Override
			public boolean keyDown(int keyCode) {
				switch (keyCode) {
				case Keys.ESCAPE:
					Gdx.app.exit();
					break;

				default:
					break;
				}
				return super.keyDown(keyCode);
			}
		};

		Gdx.input.setInputProcessor(_stage);
		int x = Gdx.graphics.getWidth();
		int y = Gdx.graphics.getHeight();

		final MenuActor startGame = new MenuActor(Q.START_GAME_MENU, x / 2 - 100, y - 150);

		startGame.addAction(new Action() {
			@Override
			public boolean act(float delta) {
				ScreenManager.getInstance().show(CustomScreen.GAME);
				return true;
			}
		});

		MenuActor exitGame = new MenuActor(Q.EXIT_GAME_MENU, (int) startGame.getX(),
				(int) startGame.getY() - 200);
		exitGame.addAction(new Action() {
			@Override
			public boolean act(float delta) {
				Gdx.app.exit();
				return true;
			}
		});

//		MenuActor playersTable = new MenuActor("Records",
//				(int) startGame.getX(), (int) startGame.getY() - 100);
//		playersTable.addAction(new Action() {
//			@Override
//			public boolean act(float delta) {
//				System.out.println("table");
//				ScreenManager.getInstance().show(CustomScreen.MAIN_MENU);
//				return true;
//			}
//		});

//		_stage.addActor(playersTable);
		_stage.addActor(exitGame);
		_stage.addActor(startGame);
	}

	@Override
	public void hide() {
		_music.stop();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		_music.dispose();
		_stage.dispose();
	}
}
