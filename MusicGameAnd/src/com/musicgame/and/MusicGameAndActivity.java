package com.musicgame.and;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MusicGameAndActivity extends Activity {
    /** Called when the activity is first created. */
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        createDatabaseToSD();
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
    
    private void createDatabaseToSD(){
    	String fileName=Config.DATAPATH+Config.DATAFILE;
    	File dir=new File(Config.DATAPATH);
    	if(!dir.exists()){
    		dir.mkdir();
    		File file=new File(fileName);
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
}