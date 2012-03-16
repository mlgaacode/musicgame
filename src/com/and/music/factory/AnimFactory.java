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
		list.add(new TextureRegion(player,0,0,20,52));
		list.add(new TextureRegion(player,20,0,23,52));
		list.add(new TextureRegion(player,43,0,23,52));
		Animation a=new Animation(.15f, list);
		return a;
	}
	
	public Animation getRight(){
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		TextureRegion t;
		t=new TextureRegion(player,0,0,20,52);
		t.flip(true, false);
		list.add(t);
		t=new TextureRegion(player,20,0,23,52);
		t.flip(true, false);
		list.add(t);
		t=new TextureRegion(player,43,0,23,52);
		t.flip(true, false);
		list.add(t);
		Animation a=new Animation(.15f, list);
		return a;
	}
	
	public Animation geSkate(){
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		list.add(new TextureRegion(player,0,53,20,47));
		list.add(new TextureRegion(player,20,53,28,47));
		list.add(new TextureRegion(player,48,53,35,47));
		list.add(new TextureRegion(player,83,53,30,47));
		list.add(new TextureRegion(player,113,53,28,47));
		list.add(new TextureRegion(player,141,53,21,47));
		list.add(new TextureRegion(player,162,53,28,47));
		list.add(new TextureRegion(player,190,53,34,47));
		list.add(new TextureRegion(player,224,53,32,47));
		Animation a=new Animation(.1f, list);
		return a;
	}
	
	public Animation getJump(){
		List<TextureRegion> list=new ArrayList<TextureRegion>();
		list.add(new TextureRegion(player,128,0,50,99));
		list.add(new TextureRegion(player,178,0,50,99));
		list.add(new TextureRegion(player,228,0,39,99));
		list.add(new TextureRegion(player,267,0,40,99));
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
