package actors;

import game.Q;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class BulletEnemy extends AbstractActor {

	private MoveToAction _fly = null;

	public MoveToAction fly() {
		if (_fly == null) {
			_fly = new MoveToAction();
			_fly.setDuration(2f);
		}
		return _fly;
	}

	public BulletEnemy(final Actor shooter) {
		super(Q.BULLET_E);
		float x = shooter.getX() + shooter.getWidth() / 2F - getWidth() / 2;
		float y = shooter.getY() - getHeight();
		fly().setPosition(x, 0 - getHeight());
		setOrigin(getWidth() / 2, getHeight() / 2);
		setPosition(x, y);
		addAction(fly());
	}
}
