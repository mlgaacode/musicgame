package com.and.music.factory;

import com.and.music.assets.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;



public class GUIFactory implements IFactory{
	private static GUIFactory instance;
	private Texture texture;
	public GUIFactory(){
		
	}
	public static synchronized GUIFactory getInstance(){
		if(instance==null)
				instance=new GUIFactory();
		return instance;
	}
	
	public Image getTouchImg(){
		texture=Assets.getPlayer();
		NinePatch n=new NinePatch(new TextureRegion(texture,0,180,480,300));
		return new Image(n);
	}
	
	public Image getGameBkImg(){
		texture=Assets.getGamebk();
		NinePatch n=new NinePatch(new TextureRegion(texture,0,0,480,320));
		return new Image(n);
	}
	@Override
	public void dispose(Object type) {
		// TODO Auto-generated method stub
		
	}
}
