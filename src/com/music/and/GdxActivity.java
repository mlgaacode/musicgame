package com.music.and;

import android.app.Activity;
import android.content.Intent;

public class GdxActivity extends Activity {

	private Intent intent;
	public GdxActivity() {
		// TODO Auto-generated constructor stub
	}
	
	public void goList(){
		intent=new Intent();
		intent.setClass(GdxActivity.this, SongListActivity.class);
		startActivity(intent);
	}

}
