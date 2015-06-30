package screens;

import game.GameAssetManager;
import game.Q;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class LoadScreen implements Screen {

	OrthographicCamera _camera;
	static private int WIDTH = 800;
	static private int HEIGHT = 480;
	SpriteBatch _batch;
	BitmapFont _font;
	private String _msg;

	public LoadScreen() {
		_batch = new SpriteBatch();
		_camera = new OrthographicCamera();
		_camera.setToOrtho(false, WIDTH, HEIGHT);
	}

	private void loadAssets() {
		ParticleEffectLoader.ParticleEffectParameter loadParam = new ParticleEffectLoader.ParticleEffectParameter();
		ParticleEffectLoader loader = new ParticleEffectLoader(new InternalFileHandleResolver());
		GameAssetManager.getInstance().setLoader(ParticleEffect.class, loader);
		GameAssetManager.getInstance().load(Q.EXPLOSEVE_P, ParticleEffect.class, loadParam);
		GameAssetManager.getInstance().load(Q.TEXTURE_ATLAS, TextureAtlas.class);
		GameAssetManager.getInstance().load(Q.SHOOT_SOUND, Sound.class);
		GameAssetManager.getInstance().load(Q.SHOOT_SOUND2, Sound.class);
		GameAssetManager.getInstance().load(Q.EXPLOSIVE_SOUND, Sound.class);
		GameAssetManager.getInstance().load(Q.GAME_MUSIC, Music.class);
		GameAssetManager.getInstance().load(Q.WIN_SPEECH, Music.class);
		GameAssetManager.getInstance().load(Q.LOSE_SPEECH, Music.class);
		GameAssetManager.getInstance().load(Q.MENU_MUSIC, Music.class);
		GameAssetManager.getInstance().load(Q.GAME_BACKGROUND, Texture.class);
		GameAssetManager.getInstance().load(Q.WIN_BACKGROUND, Texture.class);
		
		_msg = "Loading";
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_camera.update();
		_batch.setProjectionMatrix(_camera.combined);
		_batch.begin();
		_font.draw(_batch, _msg, 100, 150);
		_batch.end();

		if (!GameAssetManager.getInstance().update()) {
			_msg += ".";
		} else {
			_msg = "Done, tap to start!";
			if (Gdx.input.isTouched()) {
				ScreenManager.getInstance().show(CustomScreen.MAIN_MENU);
				dispose();
			}
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal(Q.FONT));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 32;
		_font = generator.generateFont(parameter);

		GameAssetManager.getInstance().init();
		loadAssets();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
