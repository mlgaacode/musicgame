package com.music.desk.factory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.music.desk.assets.Assets;
import com.music.desk.ui.TouchDownListener;
import com.music.desk.ui.TouchUpListener;

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
				return true;
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
	
	public Button editButton(){
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 40, 64, 17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	
	public Button testButton() {
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 74, 64,17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public Button pauseButton() {
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 57, 64, 17));
		//
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public Button cancleButton() {
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 91, 64, 17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public Button saveButton() {
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 108, 64, 17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public NinePatch listPatch() {
		NinePatch n=new NinePatch(new TextureRegion(texture,88,125,64,17));
		return n;
	}
	public Button gameButton(){
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 141, 64, 17));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public Button confirmButton(){
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 160, 78, 25));
		ButtonStyle style=new ButtonStyle(n1,n1,n1,0f,0f,0f,0f);
		Button btn=new Button(style);
		return btn;
	}
	public TextField inputText(){
		NinePatch n1=new NinePatch(new TextureRegion(texture, 88, 119, 64, 17));
		TextFieldStyle style=new TextFieldStyle(Assets.getFont1(), Color.BLACK, Assets.getFont1(), Color.GRAY, null, null, n1);
		TextField textField=new TextField(style);
		return textField;
	}
	public Button returnButton(){
		NinePatch n1=new NinePatch(new TextureRegion(texture, 153, 40, 55, 17));
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
