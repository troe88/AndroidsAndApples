package actors;

import game.GameAssetManager;
import game.Q;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Bullet extends AbstractActor {

	private MoveToAction _fly = null;
	Random _random;

	public MoveToAction fly() {
		if (_fly == null) {
			_fly = new MoveToAction();
			_fly.setDuration(2f);
		}
		return _fly;
	}

	public Bullet(final Actor shooter) {
		super(Q.BULLET);
		_random = new Random();

		Sound s;
		if (_random.nextInt(10) > 5) {
			s = GameAssetManager.getInstance().get(Q.SHOOT_SOUND);
		} else {
			s = GameAssetManager.getInstance().get(Q.SHOOT_SOUND2);
		}
		long id = s.play();
		s.setVolume(id, 0.8F);

		float x = shooter.getX() + shooter.getWidth() / 2F - getWidth() / 2;
		setPosition(x, shooter.getY() + shooter.getHeight());
		fly().setPosition(x, Gdx.graphics.getHeight());
		addAction(fly());
	}
}
