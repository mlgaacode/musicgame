package com.and.music.factory;

import com.and.music.assets.Assets;
import com.and.music.ui.TouchDownListener;
import com.and.music.ui.TouchUpListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;

public class ButtonFactory implements IFactory {
	private static ButtonFactory instance=null;
	private Texture texture;
	public ButtonFactory(){
		texture=Assets.getButtions();
	}
	public  static synchronized ButtonFactory getInstance(){
		if(instance==null)
			instance=new ButtonFactory();
		return instance;
	}
	
	public Button ctrlButton(int type,final TouchDownListener listener,final TouchUpListener listener2){
		//type 0 1 2 3  left right up down
		NinePatch n1;
		if(type==0)
			n1=new NinePatch(new TextureRegion(texture, 89, 0, 41, 40));
		else if(type==1)
			n1=new NinePatch(new TextureRegion(texture, 170, 0, 41, 40));
		else if(type==2)
			n1=new NinePatch(new TextureRegion(texture, 129,0, 41, 40));
		else 
			n1=new NinePatch(new TextureRegion(texture, 88, 0, 41, 40));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style){
			@Override
			public boolean touchDown(float x, float y, int pointer) {
				// TODO Auto-generated method stub
				super.touchDown(x, y, pointer);
				listener.touchDown();
				return false;
			}
			@Override
			public void touchUp(float x, float y, int pointer) {
				// TODO Auto-generated method stub
				super.touchUp(x, y, pointer);
				if(listener2!=null)listener2.touchUp();
			}
		};
		return btn;
	}
	
	public Button startButton(){
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 40, 64, 17));
		NinePatch n2=new NinePatch(new TextureRegion(texture, 88, 57, 64, 17));
		ButtonStyle style=new ButtonStyle(n1,n2,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	
	public Button playButton() {
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 74, 64,17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public Button pauseButton() {
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 91, 64, 17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public Button stopButton() {
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 108, 64, 17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public NinePatch listPatch() {
		NinePatch n=new NinePatch(new TextureRegion(texture,88,125,64,17));
		return n;
	}
	public Button demoButton(){
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 119, 64, 17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	@Override
	public void dispose(Object type) {
		// TODO Auto-generated method stub
		texture.dispose();
	}
}
