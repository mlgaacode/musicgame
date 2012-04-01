package com.music.desk.proxy;

import java.util.ArrayList;
import java.util.List;

public class DataProxy implements IDataProxy {

	@Override
	public String getNotesInfo() {
		// TODO Auto-generated method stub
		String data="<root title=\"song1\"><note time=\"1344\" type=\"A\" duration=\"125\"/></root>";
		return data;
	}

	@Override
	public void setNotesInfo(String root) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getSongInfo() {
		// TODO Auto-generated method stub
		ArrayList<String> l=new ArrayList<String>();
		l.add("SongName");
		l.add("Singer");
		l.add("2300000");
		return l;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Jacky";
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public int getHiScore() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public void saveScores(int score, int hiscore) {
		// TODO Auto-generated method stub
		
	}

}
