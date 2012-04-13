package com.music.desk.model;
import com.badlogic.gdx.audio.Music;
import com.music.desk.proxy.IDataProxy;

public class Song implements ISong {
	
	private String name="";
	private Music music;
	private String noteFile;
	private IDataProxy proxy;
	public Song(){
		
	}
	public void setProxy(IDataProxy proxy){
		this.proxy=proxy;
	}
	@Override
	public void setName(String name) {

	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(this.name=="")
			this.name=proxy.getSongInfo().get(0);
		return this.name;
	}
	public String getSinger(){
		return proxy.getSongInfo().get(1);
	}
	public int getTime(){
		int t=Integer.valueOf(proxy.getSongInfo().get(2));
		return (int)(t/1000);
	}
	@Override
	public void setMusic(Music music) {
		// TODO Auto-generated method stub
		music.setVolume(0);
		this.music=music;
	}
	public boolean isStop(){
		return !this.music.isPlaying();
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
