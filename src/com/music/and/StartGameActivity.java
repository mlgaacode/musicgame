package com.music.and;

import com.music.and.utils.Util;
import com.music.desk.MusicGame;
import com.music.desk.factory.SongFactory;
import com.music.desk.manager.XMLMgr;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
public class StartGameActivity extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initGame();
		initialize(new MusicGame(), false);
	}
	
	protected void initGame() {
		Bundle bundle=getIntent().getExtras();
		int id=bundle.getInt("id");
		String xml=getData(id);
		XMLMgr.getInstance().parseXML(xml);
		SongFactory.getInstance().setIndex(id);
	}
	
	protected String getData(int id){
		String data=null;
		if(id<3){
			String args[]={String.valueOf(1)};
			SQLiteDatabase database=Util.openDatabase(this);
			Cursor cursor=database.rawQuery("select notesInfo from songs where musicId=?",args);
			if(cursor.getCount()!=0){
				cursor.moveToFirst();
				data= cursor.getString(cursor.getColumnIndex("notesInfo"));
				cursor.close();
				database.close();
			}
		}else{
			
		}
		return data;		
	}
}
