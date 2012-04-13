package com.music.and.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.util.Log;

import com.music.and.Setting;
import com.music.and.utils.Util;
import com.music.desk.MusicGame;
import com.music.desk.proxy.IDataProxy;

public class DataProxy implements IDataProxy {

	private String data;
	private List<String> info;
	public DataProxy(){
		info=new ArrayList<String>();
	}
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
			if(id<3){
				Map<String, Object> map=Setting.getInstance().getDemoSong().get(id);
				info.add(map.get("title").toString());
				info.add(map.get("info").toString());
				info.add(map.get("time").toString());
			}
			else{
				cursor = context.getContentResolver().query(	MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,	null,null, null, MediaStore.Audio.AudioColumns.TITLE);
				int indexTitle = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
				int indexAritist = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
				int indexTime = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
				if (cursor.getCount()!=0) {
					cursor.moveToFirst();
					String strTitle = cursor.getString(indexTitle);
					String strArtist = cursor.getString(indexAritist);
					String strTime = cursor.getString(indexTime);
					info.add(strTitle);
					info.add(strArtist);
					info.add(strTime);
				}
				cursor.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}

	@Override
	public void setNotesInfo(String root) {
		// TODO Auto-generated method stub
		String sql="insert into songs (musicId,notesinfo) value ("+Setting.songId+","+root+")";
		try {
				SQLiteDatabase database=Util.openDatabase(MusicGame.act.getContext());
				database.execSQL(sql);
				database.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public List<String> getSongInfo() {
		// TODO Auto-generated method stub
		
		ArrayList<String> l=new ArrayList<String>();
		l.add("TestSong");
		l.add("SingerTest");
		l.add("2300000");
		return l;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Setting.getInstance().getName();
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
