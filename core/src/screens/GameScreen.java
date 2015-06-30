package screens;

import game.GameAssetManager;
import game.GameLevelSettings;
import game.Q;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import actors.Bullet;
import actors.BulletEnemy;
import actors.Enemy;
import actors.Player;
import actors.TextActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen {
	Random _random;
	private Stage _stage;
	private OrthographicCamera _camera;

	private int _score = 0;
	private long lastFire_p = 0;
	private long lastFire_e = 0;

	private List<Bullet> _listBullets;
	private List<BulletEnemy> _listBulletsEnemy;
	private List<Enemy> _listEnemys;
	private TextActor _scoreText;
	private TextActor _levelText;
	private TextActor _totalScoreText;
	private Player _player;
	private Music _music;

	SpriteBatch _batch = new SpriteBatch();
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		_batch.begin();
		_batch.draw(GameAssetManager.getInstance().get(Q.GAME_BACKGROUND, Texture.class), 0, 0, 800, 480);
		_batch.end();

		
		if (_listEnemys.isEmpty()) {
			GameLevelSettings.increase(_score);
			ScreenManager.getInstance().show(CustomScreen.LEVEL_SWITCHER);
		}

		genBullet();
		collisionDetection();
		_stage.act(delta);
		_stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		_camera.setToOrtho(false, width, height);
		_camera.update();
	}

	@Override
	public void show() {
		_music = GameAssetManager.getInstance().get(Q.GAME_MUSIC);
		_music.play();

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
				case Keys.ESCAPE:
					ScreenManager.getInstance().show(CustomScreen.MAIN_MENU);
					break;
				default:
					break;
				}
				return super.keyDown(keycode);
			}
		});

		_score = 0;
		
		_levelText = new TextActor("null", 20, 20);
		_scoreText = new TextActor("null", 300, 20);
		_totalScoreText = new TextActor("null", 580, 20);
		
		_random = new Random();
		_listBullets = new LinkedList<Bullet>();
		_listBulletsEnemy = new LinkedList<BulletEnemy>();
		_camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		_stage = new Stage();
		_player = new Player();

		genEnemys();

		_stage.getViewport().setCamera(_camera);
		_stage.addActor(_levelText);
		_stage.addActor(_totalScoreText);
		_stage.addActor(_scoreText);
		_stage.addActor(_player);
	}

	private void genEnemys() {
		_listEnemys = new LinkedList<Enemy>();
		int x_step = 75;
		int y_step = 75;
		for (int i = 0, y_start = 410; i < GameLevelSettings.get_enemRow(); i++, y_start -= y_step) {
			for (int j = 0, x_start = 220; j < 8; j++, x_start += x_step) {
				Enemy temp = new Enemy(x_start, y_start);
				_listEnemys.add(temp);
				_stage.addActor(temp);
			}
		}
	}

	private void collisionDetection() {
		Iterator<Bullet> bullet = _listBullets.iterator();
		while (bullet.hasNext()) {
			Bullet b = (Bullet) bullet.next();
			Iterator<Enemy> enemy = _listEnemys.iterator();
			while (enemy.hasNext()) {
				Enemy e = (Enemy) enemy.next();
				if (b.overlaps(e) && b.isVisible() && e.isVisible()) {
					b.setVisible(false);
					b.remove();
					b.clear();
					e.exp();
					e._isHit = true;
					bullet.remove();
					enemy.remove();
					_score += GameLevelSettings.get_scoreForEnemy();
					updateText();
				}
			}
		}

		Iterator<BulletEnemy> bulletEnemy = _listBulletsEnemy.iterator();
		while (bulletEnemy.hasNext()) {
			BulletEnemy b = (BulletEnemy) bulletEnemy.next();
			if (b.overlaps(_player)) {
				b.setVisible(false);
				b.remove();
				bulletEnemy.remove();
				ScreenManager.getInstance().show(CustomScreen.LOSE_SCREEN);
			}
		}
	}

	private void genBullet() {
		E: if (System.currentTimeMillis() - lastFire_e >= GameLevelSettings
				.get_enemyShootSpeed()) {
			BulletEnemy bullet = _listEnemys.size() == 0 ? null
					: new BulletEnemy(_listEnemys.get(_random
							.nextInt(_listEnemys.size())));
			if (bullet == null) {
				break E;
			}
			_listBulletsEnemy.add(bullet);
			_stage.addActor(bullet);
			lastFire_e = System.currentTimeMillis();
		}

		if (System.currentTimeMillis() - lastFire_p >= GameLevelSettings
				.get_playerShootSpeed()) {
			_score += GameLevelSettings.get_scoreForShoot();
			updateText();
			Bullet bullet = new Bullet(_player);
			_stage.addActor(bullet);
			_listBullets.add(bullet);
			lastFire_p = System.currentTimeMillis();
		}
	}

	private void updateText() {
//		_scoreText.setText(
//		"Level: "
//		+ GameLevelSettings.get_levelNumber()
//		+ "\tScore: "
//		+ new Integer(_score += GameLevelSettings
//				.get_scoreForShoot()).toString() + 
//		"\tTotal score: " + GameLevelSettings.get_score());

		_scoreText.setText("Level score: " + _score);
		_totalScoreText.setText("Total score: " + GameLevelSettings.get_score());
		_levelText.setText("Level: " + GameLevelSettings.get_levelNumber());
	}

	@Override
	public void dispose() {
		_stage.dispose();
		_music.dispose();
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
}
