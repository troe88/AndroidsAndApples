package game;

import screens.CustomScreen;
import screens.ScreenManager;
import com.badlogic.gdx.Game;

public class AndroidsAndApplesGame extends Game {
	
	@Override
	public void create () {
		ScreenManager.getInstance().init(this);
        ScreenManager.getInstance().show(CustomScreen.LOAD_SCREEN);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
}
