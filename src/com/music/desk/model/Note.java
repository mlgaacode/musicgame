package com.music.desk.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Note extends Image implements INote {
	public static String STATE_SCALE="scale";
	public static String STATE_FADEOUT="fadeout";
	public static String STATE_SCALE_FLIP="scale_flip";
	public static String STATE_MOVE="move";
	public static String STATE_FLY="fly";
	
	public String state="";
	public float statetime=0f;
	public float duration=0f;
	public float time=0f;
	public String type="A";
	public float zz=0f;
	public float yy=0f;
	public float xx=0f;
	private int t=30;
	public Note(Texture texture){
		super(texture);
		init();
	}
	public Note(TextureRegion textureRegion){
		super(textureRegion);
		init();
	}
	private void init(){
		this.touchable=false;
	}
	public void setPosition(float x,float y,float z){
		xx=x;yy=y;zz=z;
		float scale=t/(t+z);
		this.scaleX=this.scaleY=scale;
		this.x=0+xx*scale-this.width/2;
		this.y=0+yy*scale-this.height/2;
		//this.x=x-this.width/2;
		//this.y=Gdx.graphics.getHeight()-y-this.height/2;
		
	}
	public void setPosition(float x,float y){
		this.x=x-(this.width*this.scaleX)/2;
		this.y=y-(this.height*this.scaleY)/2;
	}
	
	public float[] getPosition(){
		float[] pos = new float[2];
		pos[0]=this.x+(this.width*this.scaleX)/2;
		pos[1]=this.y+(this.height*this.scaleY)/2;
		return pos;
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}
	public Rectangle getBundle(){
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	public void scaleXY(float scale){
		this.scaleX=this.scaleY=scale;
	}
	
	@Override
	public void destory() {
		// TODO Auto-generated method stub
		this.getRegion().getTexture().dispose();
	}

}
