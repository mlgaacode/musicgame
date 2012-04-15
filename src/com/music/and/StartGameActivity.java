package com.music.and;

import com.music.and.proxy.DataProxy;
import com.music.desk.Config;
import com.music.desk.MusicGame;
import com.music.desk.factory.SongFactory;
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
		String mode=bundle.getString("mode");
		proxy=DataProxy.getInstance();
		proxy.querySong(this);
		if(!proxy.songId.startsWith("demo"))
			SongFactory.getInstance().setType("absolute");
		Config.mode=mode;	
		XMLMgr.getInstance().setProxy(proxy);
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
