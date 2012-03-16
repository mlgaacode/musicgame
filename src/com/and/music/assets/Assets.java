package com.and.music.assets;

import com.and.music.Config;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class Assets {
	
	public static String FONT_FONT1_FNT="font1.fnt";
	public static String FONT_FONT1_PNG="font1.png";
	public static String ASSETS_SONG_LIST="song_list.xml";
	public static String ASSETS_BUTTONS_PNG="assets_buttons.png";
	public static String ASSETS_PLAYER_PNG="assets_player.png";
	public static String ASSETS_GAMEBK_PNG="game_bk.png";
	
	private static Texture buttons=null;
	private static Texture player=null;
	private static Texture gamebk=null;
	private static BitmapFont font1=null;
	
	public static Texture getButtions(){
		return buttons==null?buttons=new Texture(Config.imgPath+"/"+ASSETS_BUTTONS_PNG):buttons;
	}
	
	public static Texture getPlayer(){
		return player==null?player=new Texture(Config.imgPath+"/"+ASSETS_PLAYER_PNG):player;
	}
	
	public static Texture getGamebk(){
		return gamebk==null?gamebk=new Texture(Config.imgPath+"/"+ASSETS_GAMEBK_PNG):gamebk;
	}
	
	public static BitmapFont getFont1(){
		return font1==null? font1=new BitmapFont(Gdx.files.internal(Config.fontPath+Assets.FONT_FONT1_FNT),	Gdx.files.internal(Config.fontPath+Assets.FONT_FONT1_PNG),false):font1;
	}
	
	public static void dispose(){
		buttons.dispose();
		player.dispose();
		gamebk.dispose();
	}
}
