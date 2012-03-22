package com.and.music.screen;

import com.and.music.factory.ButtonFactory;
import com.and.music.manager.PlayerMgr;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class LoginScreen implements Screen {

	private Stage stage;
	private TextField txt_name;
	private Button btn_login;
	public LoginScreen(){
		stage=new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		Gdx.input.setInputProcessor(stage);
		initStage();
	}
	private void initStage(){
		txt_name=ButtonFactory.getInstance().inputText();
		stage.addActor(txt_name);
		btn_login=ButtonFactory.getInstance().startButton();
		btn_login.setClickListener(loginHandler());
		stage.addActor(btn_login);
	}
	private ClickListener loginHandler(){
		ClickListener listener=new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				PlayerMgr.getInstance().setName(txt_name.getText());
			}
		};
		return listener;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

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
