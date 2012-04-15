package com.music.desk;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.music.and.IGdxActivity;
import com.music.desk.manager.NoteMgr;
import com.music.desk.manager.ScreenMgr;
import com.music.desk.screen.MainScreen;
import com.music.desk.screen.SkateScreen;
import com.music.desk.untils.Debugger;
import com.music.desk.untils.TimeUtil;
import com.badlogic.gdx.graphics.GL10;

public class MusicGame implements ApplicationListener {
	boolean first=true;	
	boolean debug=true;
	BitmapFont font;
	public static IGdxActivity act;
	public MusicGame(IGdxActivity act){
		MusicGame.act=act;	
	}
	public MusicGame(){

	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
		TimeUtil.getInstance();
		if(Config.mode.equals(NoteMgr.MODE_GAME))
			ScreenMgr.getInstance().setScreen(new SkateScreen());
		else if(Config.mode.equals(NoteMgr.MODE_PLAY))
			ScreenMgr.getInstance().setScreen(new MainScreen());
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
