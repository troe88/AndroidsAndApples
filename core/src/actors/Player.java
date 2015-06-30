package actors;

import game.Q;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player extends AbstractActor {

	float _speed = 0;

	public Player() {
		super(Q.PLAYER);
		setPosition(Gdx.graphics.getWidth() / 2 - getWidth() / 2, 50);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (Gdx.input.isKeyPressed(Keys.D)) {
			_speed += 10f;
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {
			_speed -= 10f;
		}

		float newx = getX() + 1 * _speed * delta;

		if (newx < 0) {
			_speed = 0;
			setX(0);
		} else if (newx > Gdx.graphics.getWidth() - getWidth()) {
			setX(Gdx.graphics.getWidth() - getWidth());
			_speed = 0;
		} else {
			setX(newx);
		}
	}

}
