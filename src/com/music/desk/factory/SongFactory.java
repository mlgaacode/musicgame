package com.music.desk.factory;

import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.music.and.proxy.DataProxy;
import com.music.desk.model.Song;


public class SongFactory implements IFactory {

	private static SongFactory instance=null;
	private Vector<Song> list;
	private ArrayList<String> songNames;
	private String type="internal";
	
	public SongFactory(){
		//loadList();
	}
	
	public static synchronized SongFactory getInstance(){
		if(instance==null)
			instance=new SongFactory();
		return instance;
	}
	
	public Vector<Song> getSongs(){
		return list.size()>0?list:null;
	}
	public ArrayList<String> getSongNames(){
		return songNames;
	}

	public Song getSong(){		
		Song song=getSong(DataProxy.getInstance().getSongInfo().get(0),DataProxy.getInstance().getSongInfo().get(3));
		//Song song=getSong("test_song","tests_song.mp3");
		return song;
	}
	private Song getSong(String name,String file){
		Music music;
		if(this.type=="internal")
			music=Gdx.audio.newMusic(Gdx.files.internal(file));
		else
			music=Gdx.audio.newMusic(Gdx.files.absolute(file));
		music.setVolume(0.5f);
		Song song=new Song();
		song.setName(name);
		song.setMusic(music);
		return song;
	} 
	public void setType(String type){
		this.type=type;
	}
	
	@Override
	public void dispose(Object type) {
		// TODO Auto-generated method stub

	}

}
