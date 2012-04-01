package com.music.and;

import com.music.and.proxy.DataProxy;
import com.music.desk.MusicGame;
import com.music.desk.manager.XMLMgr;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
public class StartGameActivity extends AndroidApplication {
	private DataProxy proxy;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initGame();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		initialize(new MusicGame(this), false);
	}
	
	protected void initGame() {
		Bundle bundle=getIntent().getExtras();
		int id=bundle.getInt("id");
		//proxy=new DataProxy();
		//proxy.setdbData(this,id);
		//XMLMgr.getInstance().setProxy(proxy);
	}	
	public void goList(){
		intent=new Intent();
		intent.setClass(StartGameActivity.this, SongListActivity.class);
		startActivity(intent);
	}
}
