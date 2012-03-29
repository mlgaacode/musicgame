package com.music.desk.manager;

import com.music.desk.model.Song;

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
	public void setSong(Song song) {
		this.song=song;
	}
	public Song getSong(){
		return this.song;
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
