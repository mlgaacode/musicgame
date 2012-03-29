package com.music.desk.model;

public class Player implements IPlayer {
	
	private int score=0;
	public float width=0f;
	public float height=0f;
	public String name="";	
	
	@Override
	public void changeClothes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScore(int score) {
		// TODO Auto-generated method stub
		this.score=score;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return this.score;
	}

}
