package com.musicgame.and;


public final class Config {
	private static Config _instance=null;
	private static String _name="";
	public static String SDPATH=android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
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
}
