package actors;

import game.Q;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TextActor extends Actor {
	static BitmapFont _font;
	private String _text;

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Q.FONT));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 22;
		_font = generator.generateFont(parameter);		
	}
	
	public TextActor() {
		this("no msg", 0, 0);
	}

	public TextActor(String text, float x, float y) {
		_text = text;
		setPosition(x, y);
		setBounds(getX(), getY(), 10, 10);
	}

	@Override
	public void draw(final Batch batch, final float parentAlpha) {
		_font.draw(batch, getText(), getX(), getY());
	}

	public String getText() {
		return _text;
	}

	public void setText(final String text) {
		_text = text;
	}

}
