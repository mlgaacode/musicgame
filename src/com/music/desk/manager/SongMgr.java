package com.music.desk.manager;

import com.music.desk.model.Song;
import com.music.desk.proxy.IDataProxy;

public class SongMgr {
	
	private static SongMgr instance=null;
	private Song song;
	
	public  SongMgr() {
		
	}
	public static synchronized SongMgr getInstance(){
		if(instance==null)
			instance=new SongMgr();
		return instance;
	}	
	public void setProxy(IDataProxy proxy){
		song.setProxy(proxy);
	}
	public void setSong(Song song){
		this.song=song;
	}
	public boolean isStop(){
		return song.isStop();
	}
	public int getTime(){
		return song.getTime();
	}
	public String getName(){
		return song.getName();
	}
	public String getSinger(){
		return song.getSinger();
	}
	public void play() {
		this.song.getMusic().play();
	}
	public void pause() {
		this.song.getMusic().pause();
	}
	public void stop(){
		this.song.getMusic().stop();
	}
}
