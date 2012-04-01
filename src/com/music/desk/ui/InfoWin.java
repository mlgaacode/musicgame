package com.music.desk.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.music.desk.assets.Assets;
import com.music.desk.factory.ButtonFactory;
import com.music.desk.factory.GUIFactory;

public class InfoWin extends Table {
	private List<Label> lbl_infos;
	public Button btn_confirm;
	public InfoWin(){
		super();
		lbl_infos=new ArrayList<Label>();
		initView();
	}
	private void initView(){
		this.width=267;
		this.height=187;
		this.addActor(GUIFactory.getInstance().getInfobk());
		LabelStyle style=new LabelStyle(Assets.getFont1(), Color.WHITE);	
		for(int i=0;i<8;i++){
			lbl_infos.add(new Label("", style));
			lbl_infos.get(i).x=40;
			lbl_infos.get(i).y=this.height-20-20*i;
			this.addActor(lbl_infos.get(i));
		}	
		btn_confirm=ButtonFactory.getInstance().confirmButton();
		btn_confirm.x=180;
		btn_confirm.y=10;
		this.addActor(btn_confirm);
	}
	
	public void setInfos(String nickname,String songName,int[] scores,int score,int hiscore){
		lbl_infos.get(0).setText("nickname:    "+nickname);
		lbl_infos.get(1).setText("songName:    "+songName);
		lbl_infos.get(2).setText("perfect:    "+scores[0]);
		lbl_infos.get(3).setText("good:    "+scores[1]);
		lbl_infos.get(4).setText("bad:    "+scores[2]);
		lbl_infos.get(5).setText("miss:    "+scores[3]);
		lbl_infos.get(6).setText("score:     "+score);
		lbl_infos.get(7).setText("hiscore:    "+hiscore);
	}
}
