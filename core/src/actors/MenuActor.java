package actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MenuActor extends AbstractActor {
	private boolean _is_start = false;

	public MenuActor(String msg) {
		super(msg);
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				_is_start = true;
				return true;
			}
		});
	}

	public MenuActor(String msg, int x, int y) {
		this(msg);
		setPosition(x, y);
	}

	@Override
	public void act(float delta) {
		if (_is_start) {
			super.act(delta);
		}
	}
}
