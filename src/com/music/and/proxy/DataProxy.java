package com.music.and.proxy;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.music.and.utils.Util;
import com.music.desk.proxy.IDataProxy;

public class DataProxy implements IDataProxy {

	private String data;
	@Override
	public String getNotesInfo() {
		// TODO Auto-generated method stub			
		return data;
	}
	public void setSongId(Context context,int id){
		String args[]={String.valueOf(id)};
		try {
			SQLiteDatabase database=Util.openDatabase(context);
			Cursor cursor=database.rawQuery("select notesInfo from songs where musicId=?",args);
			if(cursor.getCount()!=0){
				cursor.moveToFirst();
				data= cursor.getString(cursor.getColumnIndex("notesInfo"));
				cursor.close();
				database.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}

	@Override
	public void setNotesInfo(String root) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getSongInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHiScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void saveScores(int score, int hiscore) {
		// TODO Auto-generated method stub
		
	}

}
