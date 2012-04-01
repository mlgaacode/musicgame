package com.music.desk.factory;

import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.music.and.Setting;
import com.music.desk.Config;
import com.music.desk.assets.Assets;
import com.music.desk.manager.XMLMgr;
import com.music.desk.model.Song;


public class SongFactory implements IFactory {

	private static SongFactory instance=null;
	private Vector<Song> list;
	private ArrayList<String> songNames;
	private int index=0;
	
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
		Song song=getSong("testSong","test_song.mp3");
		return song;
	}
	private Song getSong(String name,String file){
		Music music=Gdx.audio.newMusic(Gdx.files.internal(Config.songPath+file));
		music.setVolume(0.5f);
		Song song=new Song();
		song.setName(name);
		song.setMusic(music);
		return song;
	} 
	public void setIndex(int id){
		this.index=id;
	}
	
	@Override
	public void dispose(Object type) {
		// TODO Auto-generated method stub

	}

}
