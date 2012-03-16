package com.and.music.screen;

import com.and.music.Config;
import com.and.music.factory.ButtonFactory;
import com.and.music.manager.ScreenMgr;
import com.and.music.manager.XMLMgr;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

public class StartScreen implements Screen {	
	private Button btn_start;
	private Stage stage;
	public StartScreen(){
		stage=new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		this.btn_start=ButtonFactory.getInstance().startButton();
		btn_start.x=200f;
		btn_start.y=20f;
		btn_start.setClickListener(startListener());
		stage.addActor(btn_start);
		Gdx.input.setInputProcessor(stage);
	}
	
	ClickListener startListener(){
		ClickListener listener=new ClickListener() {			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				ScreenMgr.getInstance().setScreen(new MainScreen());
			}
		};
		return listener;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
