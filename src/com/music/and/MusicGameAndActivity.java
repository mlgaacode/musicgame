package com.music.and;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.musicgame.and.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MusicGameAndActivity extends Activity {
    /** Called when the activity is first created. */
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);
        createDatabaseToSD();
        intent=new Intent();
        SharedPreferences settings=getSharedPreferences("settings", MODE_PRIVATE);
        String userName=settings.getString("name", "");
        if(userName==""){
        	intent.setClass(MusicGameAndActivity.this, LoginActivity.class); 
        }else{
        	Setting.getInstance().setName(userName);
        	intent.setClass(MusicGameAndActivity.this, SongListActivity.class); 
        }       
        startActivity(intent);
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