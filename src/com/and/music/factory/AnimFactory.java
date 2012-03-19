package com.and.music.factory;


import java.util.ArrayList;
import java.util.List;
import com.and.music.assets.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimFactory implements IFactory {
	private static AnimFactory instance;
	private Texture player=null;
	public AnimFactory(){
		player=Assets.getPlayer();
	}
	
	public static synchronized AnimFactory getInstance(){
		if(instance==null)
			instance=new AnimFactory();
		return instance;
	}
	
	
	public Animation getStand(){
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		list.add(new TextureRegion(player,305,0,38,83));
		Animation a=new Animation(0.15f, list);
		return a;
	}
	
	public Animation getLeft(){
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		list.add(new TextureRegion(player,0,0,39,98));
		list.add(new TextureRegion(player,40,0,43,98));
		list.add(new TextureRegion(player,83,0,44,98));
		Animation a=new Animation(.15f, list);
		return a;
	}
	
	public Animation getRight(){
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		TextureRegion t;
		t=new TextureRegion(player,0,0,39,98);
		t.flip(true, false);
		list.add(t);
		t=new TextureRegion(player,40,0,43,98);
		t.flip(true, false);
		list.add(t);
		t=new TextureRegion(player,83,0,44,98);
		t.flip(true, false);
		list.add(t);
		Animation a=new Animation(.15f, list);
		return a;
	}
	
	public Animation geSkate(){
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		list.add(new TextureRegion(player,0,98,53,91));
		list.add(new TextureRegion(player,54,98,67,91));
		list.add(new TextureRegion(player,120,98,57,91));
		list.add(new TextureRegion(player,177,98,53,91));
		list.add(new TextureRegion(player,230,98,40,91));
		list.add(new TextureRegion(player,270,98,53,91));
		list.add(new TextureRegion(player,323,98,67,91));
		list.add(new TextureRegion(player,390,98,59,91));
		list.add(new TextureRegion(player,450,98,51,91));
		Animation a=new Animation(.1f, list);
		return a;
	}
	
	public Animation getJump(){
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		list.add(new TextureRegion(player,123,0,50,99));
		list.add(new TextureRegion(player,175,0,50,99));
		list.add(new TextureRegion(player,225,0,39,99));
		list.add(new TextureRegion(player,264,0,40,99));
		Animation a=new Animation(.1f, list);
		return a;
	}
	
	public Animation getWhite(){
		Texture texture=Assets.getButtions();
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		list.add(new TextureRegion(texture,38,0,10,200));
		list.add(new TextureRegion(texture,48,0,10,200));
		Animation a=new Animation(.15f, list);
		return a;
	}
	
	@Override
	public void dispose(Object type) {
		// TODO Auto-generated method stub
		
	}

}
