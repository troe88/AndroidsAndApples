package game;

public class GameLevelSettings {

	static int _score;

	static int _enemRow;
	static long _enemyShootSpeed;
	static float _playerMoveSpeed;
	static long _playerShootSpeed;
	static float _enemyYSpeed;
	static int _levelNumber;
	static int _scoreForEnemy;
	static int _scoreForShoot;
	static {
		reset();
	}

	public static void set(int _enemRow, long _enemyShootSpeed,
			float _playerMoveSpeed, long _playerShootSpeed, float _enemyYSpeed,
			int _scoreForEnemy, int _scoreForShoot) {
		GameLevelSettings._enemRow = _enemRow;
		GameLevelSettings._enemyShootSpeed = _enemyShootSpeed;
		GameLevelSettings._playerMoveSpeed = _playerMoveSpeed;
		GameLevelSettings._playerShootSpeed = _playerShootSpeed;
		GameLevelSettings._enemyYSpeed = _enemyYSpeed;
		GameLevelSettings._scoreForEnemy = _scoreForEnemy;
		GameLevelSettings._scoreForShoot = -_scoreForShoot;
	}


	public static void increase(int score) {
		_score += score;
		_levelNumber++;
		switch (_levelNumber) {
		case 1:
			set(2, 1500, 1, 1200, 1, 12, 2);
			break;
		case 2:
			set(2, 1300, 1, 1000, 1, 14, 3);
			break;
		case 3:
			set(3, 1200, 1, 900, 1, 17, 6);
			break;
		case 4:
			set(3, 1100, 1, 800, 1, 19, 7);
			break;
		case 5:
			set(4, 1000, 1, 750, 1, 22, 9);
			break;
		case 6:
			set(4, 900, 1, 700, 1, 24, 10);
			break;
		case 7:
			set(4, 800, 1, 550, 1, 26, 12);
			break;
		case 8:
			set(4, 700, 1, 550, 1, 28, 14);
			break;
		default:
			set(4, (long) (600 - _levelNumber * 0.5), 1, 550 - _levelNumber, 1, 28 + _levelNumber, (int) (14 + _levelNumber * 0.5));
			break;
		}

	}

	public static int get_scoreForEnemy() {
		return _scoreForEnemy;
	}

	public static int get_scoreForShoot() {
		return _scoreForShoot;
	}

	public static int get_enemRow() {
		return _enemRow;
	}

	public static long get_enemyShootSpeed() {
		return _enemyShootSpeed;
	}

	public static float get_playerMoveSpeed() {
		return _playerMoveSpeed;
	}

	public static long get_playerShootSpeed() {
		return _playerShootSpeed;
	}

	public static float get_enemyYSpeed() {
		return _enemyYSpeed;
	}

	public static int get_levelNumber() {
		return _levelNumber;
	}

	public static int get_score() {
		return _score;
	}

	public static void reset() {
		set(1, 1700, 1, 1500, 1, 10, 1);
		_levelNumber = 0;
		_score = 0;
	}

}
