package com.music.and;

import com.music.and.proxy.DataProxy;
import com.music.desk.Config;
import com.music.desk.MusicGame;
import com.music.desk.manager.NoteMgr;
import com.music.desk.manager.ScreenMgr;
import com.music.desk.manager.XMLMgr;
import com.music.desk.screen.MainScreen;
import com.music.desk.screen.SkateScreen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.utils.XmlReader.Element;
public class StartGameActivity extends AndroidApplication implements IGdxActivity {
	private DataProxy proxy;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		initGame();		
		initialize(new MusicGame(this), false);
	}
	
	protected void initGame() {
		Bundle bundle=getIntent().getExtras();
		int id=Integer.valueOf(bundle.getString("id"));
		String mode=bundle.getString("mode");
		Config.
		if(mode==NoteMgr.MODE_GAME){
			ScreenMgr.getInstance().setScreen(new SkateScreen());
			proxy=new DataProxy();
			proxy.setSongId(this,id);
			XMLMgr.getInstance().setProxy(proxy);
		}
		else if(mode==NoteMgr.MODE_PLAY){
			ScreenMgr.getInstance().setScreen(new MainScreen());
		}
	}	
	@Override
	public void goList(){
		intent=new Intent();
		intent.setClass(this, SongListActivity.class);
		startActivity(intent);
	}

	@Override
	public void goEditList() {
		// TODO Auto-generated method stub
		intent=new Intent();
		intent.setClass(StartGameActivity.this, EditSongListActivity.class);
		startActivity(intent);
	}
	@Override
	public Context getContext(){
		return this;
	}
}
