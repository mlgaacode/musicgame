package com.and.music.factory;

import java.util.ArrayList;
import java.util.Vector;

import com.and.music.Config;
import com.and.music.assets.Assets;
import com.and.music.manager.XMLMgr;
import com.and.music.model.Song;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.XmlReader.Element;


public class SongFactory implements IFactory {

	private static SongFactory instance=null;
	private Vector<Song> list;
	private ArrayList<String> songNames;
	
	public SongFactory(){
		loadList();
	}
	
	public static synchronized SongFactory getInstance(){
		if(instance==null)
			instance=new SongFactory();
		return instance;
	}
	private void loadList(){
		XMLMgr.getInstance().setFile(Assets.ASSETS_SONG_LIST);
		Element root=XMLMgr.getInstance().readXML();
		list=new Vector<Song>();
		songNames=new ArrayList<String>();
		for (int i = 0; i < root.getChildCount(); i++) {
			songNames.add(root.getChild(i).getAttribute("title"));
			Song song=getSong(root.getChild(i).getAttribute("title"),root.getChild(i).getAttribute("file"));
			song.setNoteFile(root.getChild(i).getAttribute("note"));
			list.add(song);
		}
	}
	public Vector<Song> getSongs(){
		return list.size()>0?list:null;
	}
	public ArrayList<String> getSongNames(){
		return songNames;
	}
	public Song getSong(int index){
		return list.get(index);
	}
	private Song getSong(String name,String file){
		Music music=Gdx.audio.newMusic(Gdx.files.internal(Config.songPath+file));
		music.setVolume(0.5f);
		Song song=new Song();
		song.setName(name);
		song.setMusic(music);
		return song;
	} 
	
	@Override
	public void dispose(Object type) {
		// TODO Auto-generated method stub

	}

}
