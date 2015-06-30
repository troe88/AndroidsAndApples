package game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class GameAssetManager extends AssetManager{

    private static GameAssetManager instance;
    
    public static GameAssetManager getInstance(){
        if(null == instance){
            instance = new GameAssetManager();
        }
        return instance;
    }

    public static AtlasRegion getTexture(String name){
    	return GameAssetManager.getInstance().get(Q.TEXTURE_ATLAS, TextureAtlas.class).findRegion(name);
    }
    
    public void init(){}
}
