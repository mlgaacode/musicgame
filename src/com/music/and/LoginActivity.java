package com.music.and;
import com.musicgame.and.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private EditText txt_userName;
	private Button btn_login;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initView();
	}
	private void initView(){
		txt_userName=(EditText)findViewById(R.id.txt_user_name);
		btn_login=(Button)findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(txt_userName.getText().toString()!=""){
					SharedPreferences settings=getSharedPreferences("settings", MODE_PRIVATE);
					Editor editor=settings.edit();
					editor.putString("name", txt_userName.getText().toString());
					editor.commit();
				}
				intent=new Intent();
				intent.setClass(LoginActivity.this, SongListActivity.class);
				startActivity(intent);
			}		
		});
	}

}
