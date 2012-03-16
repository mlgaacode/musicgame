package com.and.music.screen;


import javax.media.opengl.GL;
import com.and.music.Config;
import com.and.music.assets.Assets;
import com.and.music.factory.AnimFactory;
import com.and.music.factory.ButtonFactory;
import com.and.music.factory.GUIFactory;
import com.and.music.factory.SongFactory;
import com.and.music.manager.LightMgr;
import com.and.music.manager.NoteMgr;
import com.and.music.manager.PlayerMgr;
import com.and.music.manager.SongMgr;
import com.and.music.manager.XMLMgr;
import com.and.music.model.Note;
import com.and.music.model.Song;
import com.and.music.ui.TouchDownListener;
import com.and.music.untils.OverlapTester;
import com.and.music.untils.TimeUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.XmlReader.Element;

public class SkateScreen implements Screen {
	private Stage stage;
	private Stage noteStage;
	private Animation anim_white;
	private SpriteBatch batch;
	private float p0[]={240,220},p1[]={240,40};
	private Button leftButton,rightButton,upButton;
	private Image light;
	private Group noteGroup;
	private Label lbl_score;
	private Note pNote;
	private long score=0;
	
	public SkateScreen(){	
		stage=new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		noteStage=new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		initStage();
		Gdx.input.setInputProcessor(stage);
		batch=stage.getSpriteBatch();
	}
	
	private void initStage(){	
		Image bkImage=GUIFactory.getInstance().getGameBkImg();
		stage.addActor(bkImage);
		leftButton=ButtonFactory.getInstance().ctrlButton(0,touchDownLeft());
		leftButton.x=10; leftButton.y=43;		
		stage.addActor(leftButton);
		rightButton=ButtonFactory.getInstance().ctrlButton(1,touchDownRight());
		rightButton.x=430; rightButton.y=43;
		stage.addActor(rightButton);
		upButton=ButtonFactory.getInstance().ctrlButton(2,touchDownUp());
		upButton.x=430; upButton.y=87;
		stage.addActor(upButton);
		anim_white=AnimFactory.getInstance().getWhite();	
		light=LightMgr.getInstance().getLight();
		light.x=228; light.y=330;
		stage.addActor(light);		
		LabelStyle style=new LabelStyle(Assets.getFont1(), Color.WHITE);
		lbl_score=new Label("0", style);
		lbl_score.x=Gdx.graphics.getWidth()-lbl_score.width-50;
		lbl_score.y=Gdx.graphics.getHeight()-lbl_score.height;
		stage.addActor(lbl_score);
		
		XMLMgr.getInstance().setFile("song1.xml");
		Element root=XMLMgr.getInstance().readXML();
		NoteMgr.getInstance().setRoot(root);
		NoteMgr.getInstance().mode=NoteMgr.MODE_GAME;
		NoteMgr.getInstance().setMove(p0, p1);
		noteGroup=new Group();
		noteStage.addActor(noteGroup);
		Song song=SongFactory.getInstance().getSong(0);
		SongMgr.getInstance().setSong(song);
		SongMgr.getInstance().pause();
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub		
		Gdx.gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		stage.draw();
		batch.begin();
		batch.draw(anim_white.getKeyFrame(TimeUtil.getInstance().getGameNow(), true),235,-2);
		batch.draw(PlayerMgr.getInstance().getKeyFrame(), 225,10);
		batch.end();			
		noteStage.draw();
		
		Note note=NoteMgr.getInstance().getCurrentNote();
		if(note!=null) noteGroup.addActorAt(0, note);		
		pNote=NoteMgr.getInstance().firstNote();	
		NoteMgr.getInstance().updateNotes();
		if(pNote!=null && pNote.statetime>(pNote.time-.2f))
			LightMgr.getInstance().turnGreen();
		else
			LightMgr.getInstance().turnRed();		
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
					float tTime= pNote.time-TimeUtil.getInstance().getTimePoint();
						if(tTime<.5f){
						boolean hit=OverlapTester.overlapRectangles(PlayerMgr.getInstance().getBundle(230, 10), pNote.getBundle());
						if(hit){
							NoteMgr.getInstance().removeHandler(pNote);
							if(tTime>.3 && tTime<.5){
								Gdx.app.log("hit", "good");
								lbl_score.setText(String.valueOf(score+=120));
							}
							if(tTime>.1 && tTime<.3){
								Gdx.app.log("hit", "perfect");
								lbl_score.setText(String.valueOf(score+=200));
							}
						}else{
							Gdx.app.log("hit", "miss");
							lbl_score.setText(String.valueOf(score-=50));
						}
					}
				}
			}
		};
		return listener;
	}
	
	private final TouchDownListener touchDownRight() {
		TouchDownListener listener=new TouchDownListener() {	
			@Override
			public void touchDown() {
				// TODO Auto-generated method stub
				PlayerMgr.getInstance().stateTime=0f;
				PlayerMgr.getInstance().state=PlayerMgr.STATE_RIGHT;
			}
		};
		return listener;
	}
	private final TouchDownListener touchDownUp() {
		TouchDownListener listener=new TouchDownListener() {			
			@Override
			public void touchDown() {
				// TODO Auto-generated method stub								
				SongMgr.getInstance().play();
				TimeUtil.getInstance().markPoint();
				PlayerMgr.getInstance().state=PlayerMgr.STATE_SKATE;
			}
		};
		return listener;
	}
}