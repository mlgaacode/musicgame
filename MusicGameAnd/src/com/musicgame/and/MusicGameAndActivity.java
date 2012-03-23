package com.musicgame.and;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class MusicGameAndActivity extends Activity {
    /** Called when the activity is first created. */
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        intent=new Intent();
        SharedPreferences settings=getSharedPreferences("settings", MODE_PRIVATE);
        String userName=settings.getString("name", "");
        if(userName==""){
        	intent.setClass(MusicGameAndActivity.this, LoginActivity.class); 
        }else{
        	Config.getInstance().setName(userName);
        	intent.setClass(MusicGameAndActivity.this, SongListActivity.class); 
        }
        startActivity(intent);
    }
}