package com.music.and;

import com.music.and.proxy.DataProxy;
import com.music.desk.MusicGame;
import com.music.desk.factory.SongFactory;
import com.music.desk.manager.NoteMgr;
import com.music.desk.manager.PlayerMgr;
import com.music.desk.manager.SongMgr;
import com.music.desk.manager.XMLMgr;
import com.music.desk.model.Song;
import com.music.desk.untils.TimeUtil;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.utils.XmlReader.Element;
public class StartGameActivity extends AndroidApplication {
	private DataProxy proxy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initGame();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		initialize(new MusicGame(new GdxActivity()), false);
	}
	
	protected void initGame() {
		Bundle bundle=getIntent().getExtras();
		int id=bundle.getInt("id");
		proxy=new DataProxy();
		proxy.setSongId(this,id);
		 initMgr();
	}	
	private void initMgr(){
		XMLMgr.getInstance().setProxy(proxy);
		Element root=XMLMgr.getInstance().readXML();
		PlayerMgr.getInstance().setProxy(proxy);
		NoteMgr.getInstance().setRoot(root);
		NoteMgr.getInstance().mode=NoteMgr.MODE_GAME;
		Song song=SongFactory.getInstance().getSong();		
		SongMgr.getInstance().setSong(song);
		SongMgr.getInstance().setProxy(proxy);
		SongMgr.getInstance().pause();
		TimeUtil.getInstance().markPoint();
	}	
}
