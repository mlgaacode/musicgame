package com.music.desk.model;

import com.music.desk.proxy.IDataProxy;

public class Player implements IPlayer {
	
	public float width=0f;
	public float height=0f;
	private String name="";
	private IDataProxy proxy;
	
	public void setProxy(IDataProxy proxy){
		this.proxy=proxy;
	}
	
	public String getName(){
		if(this.name=="")
			this.name=proxy.getName();
		return name;
	}
	@Override
	public void changeClothes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScore(int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return proxy.getScore();
	}
	public void saveScore(int score,int hiscore){
		proxy.saveScores(score, hiscore);
	}
	
	public int getHiScore(){
		return proxy.getHiScore();
	}

}
