package com.music.desk.proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.music.desk.Config;

public class DataProxy implements IDataProxy {

	@Override
	public String getNotesInfo() {
		// TODO Auto-generated method stub
		XmlReader reader=new XmlReader();
		Element ele=null;
		try {
			ele=reader.parse(Gdx.files.internal(Config.songPath+"/"+"test.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ele.toString();
	}

	@Override
	public void setNotesInfo(String root) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getSongInfo() {
		// TODO Auto-generated method stub
		ArrayList<String> l=new ArrayList<String>();
		l.add("TestSong");
		l.add("SingerTest");
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
