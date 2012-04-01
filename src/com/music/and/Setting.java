package com.music.and;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Setting {
	private static Setting _instance=null;
	private static String _name="";
	public static String SDPATH=android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"";
	public static String DATAPATH=SDPATH+"/com.music.game";
	public static String DATAFILE="mg.sqlite";
	private static List<Map<String, Object>> DemoSongs;
	public static Boolean isFirst=false;
	
	public Setting(){
		DemoSongs=new ArrayList<Map<String,Object>>();
		HashMap<String, Object> map1=new HashMap<String, Object>();
		map1.put("title", "test_song1");
		map1.put("info", "test_song1");
		map1.put("file", "test_song.mp3");
		map1.put("id", 0);
		HashMap<String, Object> map2=new HashMap<String, Object>();
		map2.put("title", "test_song2");
		map2.put("info", "test_song2");
		map1.put("file", "test_song.mp3");
		map2.put("id", 1);
		HashMap<String, Object> map3=new HashMap<String, Object>();
		map3.put("title", "test_song3");
		map3.put("info", "test_song3");
		map1.put("file", "test_song.mp3");
		map3.put("id",2);
		DemoSongs.add(map1);
		DemoSongs.add(map2);
		DemoSongs.add(map3);
	}
	public static synchronized Setting getInstance(){
		if(_instance==null)
			_instance=new Setting();
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
