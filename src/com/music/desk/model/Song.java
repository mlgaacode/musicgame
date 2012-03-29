package com.music.desk.model;

import com.badlogic.gdx.audio.Music;

public class Song implements ISong {
	
	private String name;
	private Music music;
	private String noteFile;
	public Song(){
		
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	@Override
	public void setMusic(Music music) {
		// TODO Auto-generated method stub
		this.music=music;
	}
	@Override
	public Music getMusic() {
		// TODO Auto-generated method stub
		return this.music;
	}
	@Override
	public void setNoteFile(String file) {
		// TODO Auto-generated method stub
		this.noteFile=file;
	}
	@Override
	public String getNoteFile() {
		// TODO Auto-generated method stub
		return this.noteFile;
	}
	
}
