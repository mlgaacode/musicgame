package com.music.desk.factory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.music.desk.assets.Assets;
import com.music.desk.model.Note;

public class NoteFactory implements IFactory {

	private static NoteFactory instance=null;
	private Texture texture;
	
	public NoteFactory(){
		texture=Assets.getButtions();
	}
	
	public static NoteFactory getInstane(){
		if(instance==null)
			instance=new NoteFactory();
		return instance;
	}
	
	public Note getNote(String type){
		Note note;
		if(type=="long")
			note=new Note(new TextureRegion(texture, 20, 0, 17, 88));
		else
			note=new Note(new TextureRegion(texture, 0, 0, 20, 20));
		return note;
	}
	
	public  Note getNotesByXML(Element element){
		Note note;
		if(Float.valueOf(element.getAttribute("duration"))<2f)
			note=getNote("");
		else 
			note=getNote("long");
		note.time=Float.valueOf(element.getAttribute("time"));
		note.duration=Float.valueOf(element.getAttribute("duration"));
		note.type=element.getAttribute("type").trim();
		note.state=Note.STATE_SCALE_FLIP;
		note.scaleX=Float.valueOf(element.getAttribute("duration"))*10+1;
		return note;
	}
	@Override
	public void dispose(Object type) {
		// TODO Auto-generated method stub

	}

}
