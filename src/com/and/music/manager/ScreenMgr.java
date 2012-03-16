package com.and.music.manager;

import com.badlogic.gdx.Screen;

public class ScreenMgr {
	private Screen screen=null;
	private static ScreenMgr instance=null;
	public ScreenMgr(){
		
	}
	public  static synchronized ScreenMgr getInstance(){
		if(instance==null)
			instance= new ScreenMgr();
		return instance;
	}
	public void setScreen(Screen screen){
		if(this.screen!=null){
			this.screen.pause();
			this.screen.dispose();
		}
		this.screen=screen;
	}
	public Screen getScreen(){
		return this.screen;
	}
}
