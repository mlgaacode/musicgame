package com.music.and;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import com.music.and.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MusicGameActivity extends Activity {
    /** Called when the activity is first created. */
	private Intent intent;
	private Button btn_login;
	private Spinner sp_nickname;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDatabaseToSD();
        intent=new Intent();
        SharedPreferences settings=getSharedPreferences("settings", MODE_PRIVATE);
        String userName=settings.getString("name", "");
        if(userName==""){
        	 setContentView(R.layout.login);
        	 initView();
        }else{
        	Setting.getInstance().setName(userName);
        	intent.setClass(MusicGameActivity.this, SongListActivity.class); 
        	startActivity(intent);
        }         
    }
    
	private void initView(){
		sp_nickname=(Spinner)findViewById(R.id.sp_nickname);
		ArrayAdapter<CharSequence> ad=ArrayAdapter.createFromResource(this, R.array.nickname_sp, R.layout.login);
		ad.setDropDownViewResource(R.layout.login);
		sp_nickname.setAdapter(ad);
		sp_nickname.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btn_login=(Button)findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name=sp_nickname.getSelectedItem().toString();
				if(name!=""){
					SharedPreferences settings=getSharedPreferences("settings", MODE_PRIVATE);
					Editor editor=settings.edit();
					editor.putString("name", name);
					editor.commit();
				}
				intent=new Intent();
				intent.setClass(MusicGameActivity.this, SongListActivity.class);
				startActivity(intent);
			}		
		});
	}
    
    private void createDatabaseToSD(){
    	String fileName=Setting.DATAPATH+"/"+Setting.DATAFILE;
    	File dir=new File(Setting.DATAPATH);
    	if(!dir.exists())
    		dir.mkdirs();
    	File file=new File(fileName);
    	if(!file.exists()){
    		InputStream is=getResources().openRawResource(R.raw.mg);
    		try {
				file.createNewFile();
				FileOutputStream fos=new FileOutputStream(fileName);
				byte[] buffer=new byte[5000];
				int count=0;
				while((count=is.read(buffer))>0){
					fos.write(buffer, 0, count);
				}				
				fos.close();
				is.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				
			}
    	}
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 0:
			File file=new File(Setting.DATAFILE);
			if(file.exists()) {
				file.delete();
				file=new File(Setting.DATAPATH);
				file.delete();
			}
			System.exit(0);
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub		
		super.onCreateOptionsMenu(menu);
		menu.add(0,0,0, "退出");
		return true;
	}
}