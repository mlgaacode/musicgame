package com.and.music.untils;

import com.and.music.Config;
import com.and.music.assets.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Debugger {
	
	private static Debugger instance;
	private Label label;
	private SpriteBatch batch;
	public static Debugger getInstance(){
		if(instance==null)
			instance=new Debugger();
		return instance;
	}
	public Debugger(){
		LabelStyle style=new LabelStyle(new BitmapFont(Gdx.files.internal(Config.fontPath+Assets.FONT_FONT1_FNT),
								Gdx.files.internal(Config.fontPath+Assets.FONT_FONT1_PNG),false), Color.WHITE);
		label=new Label("", style);
		label.x=5f;
		label.y=Gdx.graphics.getHeight()-10f;
		batch=new SpriteBatch();
		
	}
	public void setInfo(String info){
		this.label.setText(info);
	}
	public String getInfo(){
		return this.label.getText();
	}
	public void draw(){
		batch.begin();
		label.draw(batch, 1);
		batch.end();
	}
}
