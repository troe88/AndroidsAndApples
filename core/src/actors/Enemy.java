package actors;

import game.GameAssetManager;
import game.Q;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Enemy extends AbstractActor {
	private static final int SHAKING = 220;
	boolean _isplay = false;
	boolean way = false;
	ParticleEffect ps;
	public boolean _isHit = false;
	private MoveByAction _moveRight;
	private MoveByAction _moveLeft;

	public Enemy(float x, float y) {
		super(Q.ENEMY);
		setPosition(x, y);
		ps = new ParticleEffect(GameAssetManager.getInstance().get(Q.EXPLOSEVE_P, ParticleEffect.class));
		ps.reset();

		_moveLeft = new MoveByAction();
		_moveLeft.setDuration(2F);
		_moveLeft.setAmount(-SHAKING, 0);

		_moveRight = new MoveByAction();
		_moveRight.setDuration(2F);
		_moveRight.setAmount(SHAKING, 0);
		
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (getActions().size == 0) {
			if (way) {
				addAction(_moveRight);
				_moveRight.restart();
				way = !way;
			} else {
				addAction(_moveLeft);
				_moveLeft.restart();
				way = !way;
			}
		}

		if (_isHit) {
			if (!_isplay) {
				_isplay = true;
				Sound s = GameAssetManager.getInstance().get(Q.EXPLOSIVE_SOUND);
				long id = s.play();
				s.setVolume(id, 0.6f);
				return;
			}
			ps.draw(batch);
			if (ps.isComplete()) {
				setVisible(false);
				remove();
			}
		} else {
			super.draw(batch, parentAlpha);
		}
	}

	public void exp() {
		ps.getEmitters().first()
				.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
	}

	@Override
	public void act(float delta) {
		if (_isHit) {
			ps.update(delta);
		}
		super.act(delta);
	}

}
