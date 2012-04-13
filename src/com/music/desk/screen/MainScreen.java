package com.music.desk.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.music.desk.MusicGame;
import com.music.desk.factory.ButtonFactory;
import com.music.desk.factory.GUIFactory;
import com.music.desk.factory.NoteFactory;
import com.music.desk.factory.SongFactory;
import com.music.desk.manager.NoteMgr;
import com.music.desk.manager.PlayerMgr;
import com.music.desk.manager.SongMgr;
import com.music.desk.manager.XMLMgr;
import com.music.desk.model.Note;
import com.music.desk.model.Song;
import com.music.desk.proxy.DataProxy;
import com.music.desk.proxy.IDataProxy;
import com.music.desk.untils.TimeUtil;

public class MainScreen implements Screen,InputProcessor{
	
	private Button btn_play,btn_pause,btn_stop,btn_demo,btn_return;
	
	private Stage stage;
	private Element root;
	private Image image_touch;	
	public MainScreen(){
		initMgr();
		initStage();
		root=new Element("root", null);
		InputMultiplexer inputMultiplexer=new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);	
	}
	
	private void initStage(){
		stage=new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		Group btn_group=new Group();
		btn_play=ButtonFactory.getInstance().playButton();
		btn_play.x=70;
		btn_play.y=1;
		btn_play.setClickListener(playListener());
		btn_pause=ButtonFactory.getInstance().pauseButton();
		btn_pause.x=140;
		btn_pause.y=1;
		btn_pause.setClickListener(pauseListener());
		btn_stop=ButtonFactory.getInstance().stopButton();
		btn_stop.x=210;
		btn_stop.y=1;
		btn_stop.setClickListener(stopListener());
		btn_demo=ButtonFactory.getInstance().demoButton();
		btn_demo.x=280;
		btn_demo.y=1;
		btn_demo.setClickListener(demoListener());		
		btn_return=ButtonFactory.getInstance().returnButton();
		btn_return.x=350;
		btn_return.y=1;
		btn_return.setClickListener(returnListener());
		btn_group.addActor(btn_play);
		btn_group.addActor(btn_pause);
		btn_group.addActor(btn_stop);	
		btn_group.addActor(btn_demo);
		btn_group.addActor(btn_return);
		btn_group.x=0;
		btn_group.y=Gdx.graphics.getHeight()-20;
		btn_group.width=380;
		btn_group.height=20;
		stage.addActor(btn_group);
		image_touch=GUIFactory.getInstance().getTouchImg();
		image_touch.x=0;
		image_touch.y=0;		
		image_touch.visible=false;
		stage.addActor(image_touch);
	}
	
	private void initMgr(){
		IDataProxy proxy=XMLMgr.getInstance().getProxy();
		if(proxy==null)
		{
			proxy=new DataProxy();
			XMLMgr.getInstance().setProxy(proxy);
		}
		Song song=SongFactory.getInstance().getSong();
		SongMgr.getInstance().setSong(song);
		PlayerMgr.getInstance().setProxy(proxy);
	}
	private ClickListener returnListener(){
		ClickListener listener=new ClickListener() {
			
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				// TODO Auto-generated method stub
				XMLMgr.getInstance().saveXML(root);
				MusicGame.act.goEditList();
			}
		};
		return listener;
	}
	
	private ClickListener playListener() {
		ClickListener listener=new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				image_touch.visible=true;
				NoteMgr.getInstance().mode=NoteMgr.MODE_PLAY;
				SongMgr.getInstance().play();
				TimeUtil.getInstance().markPoint();
			}
		};
		return listener;
	}
	private ClickListener pauseListener() {
		ClickListener listener=new ClickListener() {			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				image_touch.visible=false;
				SongMgr.getInstance().pause();
				XMLMgr.getInstance().setXML(root);				
			}
		};
		return listener;
	}
	private ClickListener stopListener() {
		ClickListener listener=new ClickListener() {			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				SongMgr.getInstance().stop();
				XMLMgr.getInstance().setXML(root);
				image_touch.visible=false;
			}
		};
		return listener;
	}
	/*
	 * 演示模式
	 */
	private ClickListener demoListener() {
		ClickListener listener=new ClickListener() {			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				image_touch.visible=true;
				NoteMgr.getInstance().setRoot(XMLMgr.getInstance().readXML());
				SongMgr.getInstance().play();
				TimeUtil.getInstance().markPoint();
				NoteMgr.getInstance().mode=NoteMgr.MODE_DEMO;		
			}
		};
		return listener;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		image_touch.getRegion().getTexture().dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		stage.act(Gdx.graphics.getDeltaTime());	
		stage.draw();	
		Note note=NoteMgr.getInstance().getCurrentNote();
		if(note!=null && NoteMgr.getInstance().mode==NoteMgr.MODE_DEMO && TimeUtil.getInstance().getTimePoint()>note.time){
			stage.addActor(note);
		}
		if(note!=null)
			NoteMgr.getInstance().update(note);
	}
	
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
	}
  
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		if(arg1>20){
			Note note=NoteFactory.getInstane().getNote("block");
			note.setPosition(arg0,Gdx.graphics.getHeight()-arg1);			
			note.state=Note.STATE_SCALE;
			note.time=TimeUtil.getInstance().getTimePoint();
			stage.addActor(note);
			NoteMgr.getInstance().pushNote(note);
		}
		return false;
	}
	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		if(arg1>20){
			Note note=NoteMgr.getInstance().getCurrentNote();
			note.duration=TimeUtil.getInstance().getTimePoint()-note.time;
			note.state=Note.STATE_FADEOUT;
			Element element=new Element("note", root);
			element.setAttribute("time", String.valueOf(note.time));
			element.setAttribute("duration", String.valueOf(note.duration));
			element.setAttribute("type", "A");
			root.addChild(element);
		}
		return false;
	}
}

