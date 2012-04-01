package com.music.desk.screen;


import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.music.and.StartGameActivity;
import com.music.desk.MusicGame;
import com.music.desk.assets.Assets;
import com.music.desk.factory.AnimFactory;
import com.music.desk.factory.ButtonFactory;
import com.music.desk.factory.GUIFactory;
import com.music.desk.factory.SongFactory;
import com.music.desk.manager.LightMgr;
import com.music.desk.manager.NoteMgr;
import com.music.desk.manager.PlayerMgr;
import com.music.desk.manager.SongMgr;
import com.music.desk.manager.XMLMgr;
import com.music.desk.model.Note;
import com.music.desk.model.Song;
import com.music.desk.proxy.DataProxy;
import com.music.desk.ui.InfoWin;
import com.music.desk.ui.TouchDownListener;
import com.music.desk.ui.TouchUpListener;
import com.music.desk.untils.TimeUtil;

public class SkateScreen implements Screen {
	private Stage stage;
	private Stage noteStage;
	private Animation anim_white;
	private SpriteBatch batch;
	private float[] p0={240,220},p1={240,40};
	private Button leftButton,rightButton,upButton;
	private Image light;
	private Group noteGroup;
	private Note pNote;
	private boolean start=false;
	private Label lbl_name,lbl_songInfo,lbl_time,lbl_score;
	private InfoWin win_info;
	private boolean over=false;
	
	public SkateScreen(){	
		stage=new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		noteStage=new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		initMgr();
		initStage();
		Gdx.input.setInputProcessor(stage);
		batch=stage.getSpriteBatch();
	}
	
	private void initMgr(){
		DataProxy proxy=new DataProxy();
		XMLMgr.getInstance().setProxy(proxy);
		Element root=XMLMgr.getInstance().readXML();
		PlayerMgr.getInstance().setProxy(proxy);
		NoteMgr.getInstance().setRoot(root);
		NoteMgr.getInstance().mode=NoteMgr.MODE_GAME;
		NoteMgr.getInstance().setMove(p0, p1);
		Song song=SongFactory.getInstance().getSong();		
		SongMgr.getInstance().setSong(song);
		SongMgr.getInstance().setProxy(proxy);
		SongMgr.getInstance().pause();
	}
	
	private void initStage(){	
		Image bkImage=GUIFactory.getInstance().getGameBkImg();
		stage.addActor(bkImage);
		leftButton=ButtonFactory.getInstance().ctrlButton(0,touchDownLeft(),null);
		leftButton.x=10; leftButton.y=43;		
		stage.addActor(leftButton);
		rightButton=ButtonFactory.getInstance().ctrlButton(1,touchDownRight(),null);
		rightButton.x=430; rightButton.y=30;
		stage.addActor(rightButton);
		upButton=ButtonFactory.getInstance().ctrlButton(2,touchDownUp(),touchUpUp());
		upButton.x=430; upButton.y=87;
		stage.addActor(upButton);
		anim_white=AnimFactory.getInstance().getWhite();
		light=LightMgr.getInstance().getLight();
		light.x=230; light.y=220;
		stage.addActor(light);		
		LabelStyle style=new LabelStyle(Assets.getFont1(), Color.WHITE);		
		lbl_name=new Label("", style);
		lbl_name.width=90;
		lbl_name.x=10;		
		lbl_name.setText(PlayerMgr.getInstance().getName());
		lbl_name.y=Gdx.graphics.getHeight()-10;
		stage.addActor(lbl_name);
		lbl_songInfo=new Label("",style);
		lbl_songInfo.x=100;
		lbl_songInfo.y=Gdx.graphics.getHeight()-10;
		lbl_songInfo.width=250;
		lbl_songInfo.setText(SongMgr.getInstance().getName()+"--"+SongMgr.getInstance().getSinger());
		stage.addActor(lbl_songInfo);
		lbl_time=new Label("", style);
		lbl_time.x=360;
		lbl_time.y=Gdx.graphics.getHeight()-10;
		lbl_time.width=40;
		lbl_time.setText(TimeUtil.getInstance().timeFormat(SongMgr.getInstance().getTime()));
		stage.addActor(lbl_time);
		lbl_score=new Label("Score", style);
		lbl_score.x=Gdx.graphics.getWidth()-lbl_score.width-50;
		lbl_score.y=Gdx.graphics.getHeight()-20;
		stage.addActor(lbl_score);
		win_info=new InfoWin();
		win_info.x=(Gdx.graphics.getWidth()-win_info.width)/2;
		win_info.y=(Gdx.graphics.getHeight()-win_info.height)/2;
		win_info.btn_confirm.setClickListener(onConfirm());
		win_info.setInfos(PlayerMgr.getInstance().getName(), SongMgr.getInstance().getName(), PlayerMgr.getInstance().getSocres(), 
					PlayerMgr.getInstance().getScore(), PlayerMgr.getInstance().getHiScore());
		noteGroup=new Group();
		noteStage.addActor(noteGroup);
	}
	private ClickListener onConfirm(){
		ClickListener listener=new ClickListener() {
			
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				// TODO Auto-generated method stub
				((StartGameActivity)MusicGame.act).goList();
				stage.removeActor(win_info);
				
			}
		};		
		return listener;
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();				
		if(start) {
			batch.begin();
			batch.draw(anim_white.getKeyFrame(TimeUtil.getInstance().getGameNow(), true),235,-15);
			batch.end();	
			Note note=NoteMgr.getInstance().getCurrentNote();
			if(note!=null) noteGroup.addActorAt(0, note);		
			pNote=NoteMgr.getInstance().firstNote();	
			NoteMgr.getInstance().updateNotes();
			if(pNote!=null && pNote.statetime>(pNote.time-.2f))
				LightMgr.getInstance().turnGreen();
			else
				LightMgr.getInstance().turnRed();	
			noteStage.draw();
		}
		if(!over){
			batch.begin();
			batch.draw(PlayerMgr.getInstance().getKeyFrame(), 217,10);
			batch.end();	
		}
		if(SongMgr.getInstance().isStop() && !over){
			over=true;
			gameOver();
		}
	}
	
	private void gameOver(){
		start=false;
		SongMgr.getInstance().stop();
		PlayerMgr.getInstance().state=PlayerMgr.STATE_STAND;
		stage.addActor(win_info);
		win_info.setInfos(PlayerMgr.getInstance().getName(), SongMgr.getInstance().getName(), PlayerMgr.getInstance().getSocres(), 
				PlayerMgr.getInstance().getScore(), PlayerMgr.getInstance().getHiScore());		
		leftButton.touchable=rightButton.touchable=upButton.touchable=false;
	}	
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	private final TouchDownListener touchDownLeft() {
		TouchDownListener listener=new TouchDownListener() {			
			@Override
			public void touchDown() {
				// TODO Auto-generated method stub
				PlayerMgr.getInstance().stateTime=0f;
				PlayerMgr.getInstance().state=PlayerMgr.STATE_LEFT;				
				if(pNote!=null){
					TestTouch(pNote);
				}
			}
		};
		return listener;
	}
	
	private void TestTouch(Note p){
		float tTime= p.time-TimeUtil.getInstance().getTimePoint();
		if(tTime<.8f){
			NoteMgr.getInstance().removeHandler(p);
			if(tTime>.2 && tTime<.5){
				Gdx.app.log("hit", "good");
				PlayerMgr.getInstance().addScore(120);
			}else	if(tTime>0 && tTime<.2){
				Gdx.app.log("hit", "perfect");
				PlayerMgr.getInstance().addScore(200);
			}else{
				Gdx.app.log("hit", "miss");
			}
			lbl_score.setText(PlayerMgr.getInstance().getScore()+"");
		}		
	}
	
	private final TouchDownListener touchDownRight() {
		TouchDownListener listener=new TouchDownListener() {	
			@Override
			public void touchDown() {
				// TODO Auto-generated method stub
				PlayerMgr.getInstance().stateTime=0f;
				PlayerMgr.getInstance().state=PlayerMgr.STATE_RIGHT;				
				if(pNote!=null){
					TestTouch(pNote);
				}
			}
		};
		return listener;
	}
	private final TouchDownListener touchDownUp() {
		TouchDownListener listener=new TouchDownListener() {			
			@Override
			public void touchDown() {
				// TODO Auto-generated method stub
				if(start){
					PlayerMgr.getInstance().stateTime=0f;
					PlayerMgr.getInstance().state=PlayerMgr.STATE_JUMP;
					if(pNote!=null && TimeUtil.getInstance().getGameNow()>pNote.time){
						PlayerMgr.getInstance().state=PlayerMgr.STATE_SKATE;		
					}
				}
				if(!start){
					start=true;
					SongMgr.getInstance().play();
					TimeUtil.getInstance().markPoint();
					PlayerMgr.getInstance().state=PlayerMgr.STATE_SKATE;
				}				
			}
		};
		return listener;
	}
	private final TouchUpListener touchUpUp(){
		TouchUpListener listener=new TouchUpListener() {
			
			@Override
			public void touchUp() {
				// TODO Auto-generated method stub
				if(pNote!=null)	TestTouch(pNote);				
				if(PlayerMgr.getInstance().state==PlayerMgr.STATE_JUMP)
					PlayerMgr.getInstance().state=PlayerMgr.STATE_SKATE;			
				Gdx.app.log("here", "up");
			}
		};
		return listener;
	}

}
