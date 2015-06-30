package screens;

import com.badlogic.gdx.Screen;

public enum CustomScreen {

	LOAD_SCREEN {
		@Override
		protected Screen getScreenInstance() {
			return new LoadScreen();
		}
	},

	MAIN_MENU {
		@Override
		protected Screen getScreenInstance() {
			return new MenuScreen();
		}
	},

	GAME {
		@Override
		protected Screen getScreenInstance() {
			return new GameScreen();
		}
	},

	BATTLE {
		@Override
		protected Screen getScreenInstance() {
			return new BattleScreen();
		}
	},
	LOSE_SCREEN {
		@Override
		protected Screen getScreenInstance() {
			return new LoseScreen();
		}
	},
	WIN_SCREEN {
		@Override
		protected Screen getScreenInstance() {
			return new WinScreen();
		}
	}, LEVEL_SWITCHER {
		@Override
		protected Screen getScreenInstance() {
			return new LevelSwithScreen();
		}
	};

	protected abstract com.badlogic.gdx.Screen getScreenInstance();

}