package com.and.music.manager;

import java.util.Vector;

import com.and.music.factory.AnimFactory;
import com.and.music.model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class PlayerMgr {
	public static String STATE_LEFT="state_left";
	public static String STATE_RIGHT="state_right";
	public static String STATE_SKATE="state_skate";
	public static String STATE_STAND="state_stand";
	public static String STATE_JUMP="state_jump";
	private static PlayerMgr instance=null;
	private Player player;
	private Vector<Animation> anims=new Vector<Animation>();
	public String state=STATE_STAND;
	public float stateTime;
	
	public PlayerMgr(){
		player=new Player();	
		player.width=25; player.height=55;
		anims.add(AnimFactory.getInstance().getStand());
		anims.add(AnimFactory.getInstance().geSkate());
		anims.add(AnimFactory.getInstance().getLeft());
		anims.add(AnimFactory.getInstance().getRight());
		anims.add(AnimFactory.getInstance().getJump());
	}
	public static PlayerMgr getInstance(){
		if(instance==null){
			instance=new PlayerMgr();
		}
		return instance;
	}
	
	public Rectangle getBundle(float x,float y){
		return new Rectangle(x, y, player.width, player.height);
	}
	
	public TextureRegion getKeyFrame(){
		this.stateTime+=Gdx.graphics.getDeltaTime();
		if(state==STATE_STAND)
			return anims.get(0).getKeyFrame(this.stateTime, true);
		if(state==STATE_SKATE)
			return anims.get(1).getKeyFrame(this.stateTime, true);
		if(state==STATE_JUMP)
			return anims.get(4).getKeyFrame(this.stateTime, false);
		if(state==STATE_LEFT)	{
			if(anims.get(2).isAnimationFinished(this.stateTime)){				
				state=STATE_SKATE;
			}
			return anims.get(2).getKeyFrame(this.stateTime, false);
		}
		if(state==STATE_RIGHT){
			if(anims.get(3).isAnimationFinished(this.stateTime)){
				state=STATE_SKATE;
			}
			return anims.get(3).getKeyFrame(this.stateTime, false);
		}
		//default
		return anims.get(0).getKeyFrame(stateTime, true);
	}
}
