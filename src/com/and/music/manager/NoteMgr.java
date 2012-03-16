package com.and.music.manager;


import java.awt.Point;
import java.util.Vector;
import com.and.music.factory.NoteFactory;
import com.and.music.model.Note;
import com.and.music.untils.TimeUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.XmlReader.Element;

public class NoteMgr {
	public static String MODE_PLAY="play";
	public static String MODE_DEMO="demo";
	public static String MODE_GAME="game";
	private static NoteMgr instance=null;
	private Vector<Note> vector=new Vector<Note>();
	private Vector<Note> showVector=new Vector<Note>();
	private Element root;
	private int eIndex=0;
	private FadeOut fadeOut;
	private float from[],to[];
	public String mode="";
	public static NoteMgr getInstance(){
		if(instance==null)
			instance=new NoteMgr();
		return instance;
	}
	public void pushNote(Note note){
		fadeOut=FadeOut.$(.3f);
		fadeOut.setCompletionListener(completionListener());		
		vector.add(note);
	}
	public Note getCurrentNote(){
		if(mode==MODE_PLAY){
			return vector.size()>0?vector.lastElement():null;
		}else if(mode==MODE_DEMO){
			if(eIndex<root.getChildCount() && vector.size()<2){
				Note note=NoteFactory.getInstane().getNotesByXML(root.getChild(eIndex));
				note.setPosition(240, 150);				
				NoteMgr.getInstance().pushNote(note);
				eIndex++;
			}
			return vector.size()>0?vector.firstElement():null;
		}else if (mode==MODE_GAME) {
			Note note=null;	
			if(eIndex<root.getChildCount() && vector.size()<2){
				note=NoteFactory.getInstane().getNotesByXML(root.getChild(eIndex));
				NoteMgr.getInstance().pushNote(note);
				eIndex++;
			}
			note=vector.size()>0?vector.firstElement():null;			
			if(note!=null && TimeUtil.getInstance().getTimePoint()>note.time-2){
				note.setPosition(from[0], from[1]);
				note.state=Note.STATE_MOVE;
				note.scaleXY(0.01f);
				note.statetime=TimeUtil.getInstance().getTimePoint();
				showVector.add(note);
				vector.remove(note);
				return note;
			}
		}
		return null;
	}
	
	public void setRoot(Element element){
		root=element;
		eIndex=0;
	}
		
	private OnActionCompleted completionListener(){
		OnActionCompleted listener=new OnActionCompleted() {
			@Override
			public void completed(Action action) {
				// TODO Auto-generated method stub
				Note actor=(Note) action.getTarget();
				actor.getStage().removeActor(actor);
				actor.destory();
				vector.remove(actor);
			}
		};
			return listener;
	}
	
	public void setMove(float[] from,float[] to){
		this.to=to;
		this.from=from;
	}
	
	public void update(Note note){
		note.statetime+=Gdx.graphics.getDeltaTime();
		if(note.state==Note.STATE_SCALE){
			note.scaleX+=.04f;
		}else if(note.state==Note.STATE_FADEOUT){
			note.action(fadeOut);
			note.state="";
		}else if(note.state==Note.STATE_SCALE_FLIP){
			note.scaleX-=.04f;
			if(note.statetime>note.time+note.duration-.3f || note.scaleX<1f)
				note.state=Note.STATE_FADEOUT;
		}else{
			
		}
	}
	/*
	public void updateNotes(){
			for (int i=0;i<showVector.size();i++) {
				Note note=showVector.get(i);
				note.statetime+=Gdx.graphics.getDeltaTime();
				float s=to[1]-note.getPosition()[1];
				float t=note.time-note.statetime;
				float v=s/t;	
				float xx=note.type.equals("A")?(to[0]-10):(to[0]+10);
				float yy=note.getPosition()[1]+v*Gdx.graphics.getDeltaTime();
				if(t<=0){	
					//Gdx.app.log("pos", yy+":"+to[1]);
					//Gdx.app.log("time", note.statetime+":"+note.time);
					removeHandler(note);
					continue;
				}
				note.setPosition(xx, yy);
				note.scaleXY(note.scaleX<1?(note.scaleX+Gdx.graphics.getDeltaTime()*.7f):1);
			}
	}
	*/
	public void updateNotes(){
		for (int i=0;i<showVector.size();i++) {
			Note note=showVector.get(i);
			note.statetime+=Gdx.graphics.getDeltaTime();
			float s=to[1]-note.getPosition()[1];
			float t=note.time-note.statetime;
			float v=s/t;
			float xx=note.type.equals("A")?(to[0]-10):(to[0]+10);
			float yy=note.getPosition()[1]+v*Gdx.graphics.getDeltaTime();
			if(t>0){
				note.setPosition(xx, yy);
				note.scaleXY(note.scaleX<1?(note.scaleX+Gdx.graphics.getDeltaTime()*.7f):1);
			}else{
				yy=note.getPosition()[1]-2f;
				if(yy<-10){
					removeHandler(note);
					continue;
				}else
					note.setPosition(xx, yy);				
			}
		}
	}
	public void removeHandler(Note note){
		note.getStage().removeActor(note);		
		note.destory();
		showVector.remove(note);
	}
	public Note firstNote(){
		return showVector.size()>0?showVector.firstElement():null;
	}
}
