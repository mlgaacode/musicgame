package com.music.desk.untils;

import com.badlogic.gdx.Gdx;

public class TimeUtil implements ITimeUtil {

	private static TimeUtil instance;
	private float pointTime=0f;
	private float stateTime=0f;
	public TimeUtil(){

	}
	public static synchronized TimeUtil getInstance(){
		if(instance==null)
			instance=new TimeUtil();
		return instance;
	}
	@Override
	public void markPoint() {
		// TODO Auto-generated method stub
		this.pointTime=this.stateTime;
	}

	@Override
	public float getTimePoint() {
		// TODO Auto-generated method stub
		float result=this.stateTime-pointTime;
		return result;
	}
	
	public void resetPoints(){
		pointTime=0;
	}

	@Override
	public float getGameNow() {
		// TODO Auto-generated method stub
		return stateTime;
	}
	public String timeFormat(int sec){
		String str="";
		int m=(int)(sec/60);
		int s=sec%60;
		if(m<10)str="0"+m;
		if(s<10)str=str+"0"+s;
		return str;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		stateTime+=Gdx.graphics.getDeltaTime();
	}

}
