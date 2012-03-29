package com.music.desk.untils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.music.desk.Config;
import com.music.desk.assets.Assets;

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
		LabelStyle style=new LabelStyle(Assets.getFont1(), Color.WHITE);
		label=new Label("", style);
		label.x=5f;
		label.y=Gdx.graphics.getHeight()-10f;
		batch=new SpriteBatch();
		
	}
	public void setInfo(String info){
		this.label.setText(info);
	}
	public String getInfo(){
		return (String) this.label.getText();
	}
	public void draw(){
		batch.begin();
		label.draw(batch, 1);
		batch.end();
	}
}
