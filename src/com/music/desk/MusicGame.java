package com.music.desk;

import android.app.Activity;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.music.desk.manager.ScreenMgr;
import com.music.desk.screen.SkateScreen;
import com.music.desk.untils.Debugger;
import com.music.desk.untils.TimeUtil;
import com.badlogic.gdx.graphics.GL10;

public class MusicGame implements ApplicationListener {
	boolean first=true;	
	boolean debug=true;
	BitmapFont font;
	public static Activity act;
	public MusicGame(Activity act){
		this.act=act;
	}
	public MusicGame(){

	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
		TimeUtil.getInstance();
		ScreenMgr.getInstance().setScreen(new SkateScreen());
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		ScreenMgr.getInstance().getScreen().render(Gdx.graphics.getDeltaTime());
		TimeUtil.getInstance().render();
		if(debug) renderDebug();
	}
	private void renderDebug(){		
		Debugger.getInstance().setInfo("fps:"+Gdx.graphics.getFramesPerSecond());
		Debugger.getInstance().draw();
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}