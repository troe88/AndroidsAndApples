package actors;

import game.GameAssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AbstractActor extends Actor {
	protected AtlasRegion _texture;
	protected String _name;
	protected ShapeRenderer shapeRenderer;
	
	public AbstractActor(String name) {
		shapeRenderer = new ShapeRenderer();
		_name = name;
		_texture = GameAssetManager.getTexture(_name);
		setBounds(getX(), getY(), _texture.getRegionWidth(), _texture.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
//		batch.end();
//		shapeRenderer.begin(ShapeType.Line);
//		shapeRenderer.setColor(Color.GREEN);
//		shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
//		shapeRenderer.end();
//		batch.begin();
		batch.draw(_texture, getX(), getY());
	}
	
	public boolean overlaps(AbstractActor a){
		if(getBounds().overlaps(a.getBounds())){
			return true;
		}
		return false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
}
