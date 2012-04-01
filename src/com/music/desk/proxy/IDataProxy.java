package com.music.desk.proxy;

import java.util.List;

public interface IDataProxy {
	String getNotesInfo();
	void setNotesInfo(String root);
	List<String> getSongInfo();
	String getName();
	int getScore();
	int getHiScore();
	void saveScores(int score,int hiscore);
}
