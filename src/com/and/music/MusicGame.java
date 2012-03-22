package com.and.music;

import javax.media.opengl.GL;
import com.and.music.manager.ScreenMgr;
import com.and.music.screen.LoginScreen;
import com.and.music.screen.SkateScreen;
import com.and.music.untils.Debugger;
import com.and.music.untils.TimeUtil;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MusicGame implements ApplicationListener {
	boolean first=true;	
	boolean debug=true;
	BitmapFont font;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		TimeUtil.getInstance();
		ScreenMgr.getInstance().setScreen(new LoginScreen());
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub		
		Gdx.gl.glClear(GL.GL_COLOR_BUFFER_BIT);
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
