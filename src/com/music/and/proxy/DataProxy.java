package com.music.and.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

import com.music.and.Setting;
import com.music.and.utils.Util;
import com.music.desk.MusicGame;
import com.music.desk.proxy.IDataProxy;

public class DataProxy implements IDataProxy {

	private String noteInfo;
	private List<String> songInfo;
	private static DataProxy instance=null;
	public String songId;
	public String userName;
	
	public synchronized static DataProxy getInstance(){
		if(instance==null)
			instance=new DataProxy();
		return instance;
	}
	public DataProxy(){
		songInfo=new ArrayList<String>();
	}
	@Override
	public String getNotesInfo() {
		// TODO Auto-generated method stub
		return noteInfo;
	}
	public void querySong(Context context){
		String args[]={songId};
		try {
			SQLiteDatabase database=Util.openDatabase(context);
			Cursor cursor=database.rawQuery("select notesInfo from songs where musicId=?",args);
			if(cursor.getCount()!=0){
				cursor.moveToFirst();
				noteInfo= cursor.getString(cursor.getColumnIndex("notesInfo"));
			}
			cursor.close();
			database.close();
			if(songId.startsWith("demo")){
				Map<String, Object> map=Setting.getInstance().getDemoSong().get(Integer.valueOf(songId.substring(4)));
				songInfo.add(map.get("title").toString());
				songInfo.add(map.get("info").toString());
				songInfo.add(map.get("time").toString());
				songInfo.add(map.get("file").toString());
			}
			else{
				cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Audio.AudioColumns._ID+"="+songId,null, MediaStore.Audio.AudioColumns.TITLE);
				int indexTitle = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
				int indexAritist = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
				int indexTime = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
				int indexFile=cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
				if (cursor.getCount()!=0) {
					cursor.moveToFirst();
					String strTitle = cursor.getString(indexTitle);
					String strArtist = cursor.getString(indexAritist);
					String strTime = cursor.getString(indexTime);
					String strFile=cursor.getString(indexFile);
					songInfo.add(strTitle);
					songInfo.add(strArtist);
					songInfo.add(strTime);
					songInfo.add(strFile);
				}
				cursor.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void setNotesInfo(String root) {
		// TODO Auto-generated method stub
		String sql="";
		try {
				SQLiteDatabase database=Util.openDatabase(MusicGame.act.getContext());
				sql="delete from songs where musicId="+songId;
				database.execSQL(sql);
				sql="insert into songs (musicId,notesInfo) values ("+songId+",'"+root+"')";
				database.execSQL(sql);	
				database.close();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public List<String> getSongInfo() {
		// TODO Auto-generated method stub
		return songInfo;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return userName;
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
