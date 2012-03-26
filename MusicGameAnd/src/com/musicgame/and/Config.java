package com.musicgame.and;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Config {
	private static Config _instance=null;
	private static String _name="";
	public static String SDPATH=android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
	public static String DATAPATH=SDPATH+"musicgame/";
	public static String DATAFILE="mg.sqlite";
	private static List<Map<String, Object>> DemoSongs;
	public static Boolean isFirst=false;
	
	public Config(){
		DemoSongs=new ArrayList<Map<String,Object>>();
		HashMap<String, Object> map1=new HashMap<String, Object>();
		map1.put("title", "test_song1.mp3");
		map1.put("info", "test_song1.mp3");
		map1.put("path", R.raw.song_test0);
		HashMap<String, Object> map2=new HashMap<String, Object>();
		map2.put("title", "test_song2.mp3");
		map2.put("info", "test_song2.mp3");
		map2.put("path", R.raw.song_test1);
		HashMap<String, Object> map3=new HashMap<String, Object>();
		map3.put("title", "test_song3.mp3");
		map3.put("info", "test_song3.mp3");
		map3.put("path", R.raw.song_test2);
		DemoSongs.add(map1);
		DemoSongs.add(map2);
		DemoSongs.add(map3);
	}
	public static synchronized Config getInstance(){
		if(_instance==null)
			_instance=new Config();
		return _instance;
	}
	
	public String getName(){
		return _name;
	}
	public void setName(String name){
		_name=name;
	}
	public List<Map<String, Object>> getDemoSong(){		
		return DemoSongs;
	}
}
