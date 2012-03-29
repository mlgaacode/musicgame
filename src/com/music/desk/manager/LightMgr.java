package com.music.desk.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.music.desk.Config;
import com.music.desk.assets.Assets;

public class LightMgr {
	
	private static LightMgr instance=null;
	private Image light;
	private TextureRegion textureRegion1,textureRegion2;
	public LightMgr(){
		mkLight();
	}
	
	public static synchronized LightMgr getInstance(){
		if(instance==null)
			instance=new LightMgr();
		return instance;
	}
	public Image getLight(){
		return light;
	}
	private void mkLight(){
		Texture texture=Assets.getButtions();
		textureRegion1=new TextureRegion(texture,58,0,15,15);
		textureRegion2=new TextureRegion(texture,74,0,15,15);
		light=new Image(textureRegion1);
	}
	public void turnRed(){
		light.setRegion(textureRegion1);
	}
	public void turnGreen(){
		light.setRegion(textureRegion2);
	}
	
}
