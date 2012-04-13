package com.music.and;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.music.and.R;
import com.music.and.utils.Util;
import com.music.desk.MusicGame;
import com.music.desk.manager.NoteMgr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class EditSongListActivity extends Activity {
	
	private ListView lv_songs;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editlist);
		initView();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		lv_songs=(ListView)findViewById(R.id.lv_editSongs);
		SimpleAdapter adapter=new SimpleAdapter(this,getData(),R.layout.songs,new String[]{"title","info"},new int[]{R.id.title,R.id.info});
		lv_songs.setAdapter(adapter);
		lv_songs.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
				//进入编辑主界面
				Map<String, Object> target=(Map<String, Object> )arg0.getAdapter().getItem(position);
				Log.i("id", target.get("id").toString());			
				Setting.songId=target.get("id").toString();
				Bundle bundle=new Bundle();
				bundle.putString("id", target.get("id").toString());
				bundle.putString("mode", NoteMgr.MODE_PLAY);
				intent=new Intent();
				intent.putExtras(bundle);
				intent.setClass(EditSongListActivity.this, StartGameActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> mListData = new ArrayList<Map<String, Object>>();	
		SQLiteDatabase database=Util.openDatabase(this);
		Cursor mCursor=database.rawQuery("select musicId from songs",null);
		mCursor.moveToFirst();	
		List<String> musicIds=new ArrayList<String>();
		for(int i=0;i<mCursor.getCount();i++){
			musicIds.add(mCursor.getString(0));
			mCursor.moveToNext();
		}
		mCursor.close();
		database.close();
		Cursor mAudioCursor = this.getContentResolver().query(	MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,	null,null, null, MediaStore.Audio.AudioColumns.TITLE);
		mAudioCursor.moveToFirst();
		int indexTitle = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
		int indexARTIST = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
		int indexALBUM = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
		int indexId=mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID);
		boolean exist=false;
		for (int i = 0; i < mAudioCursor.getCount(); i++) {
			String strTitle = mAudioCursor.getString(indexTitle);
			String strARTIST = mAudioCursor.getString(indexARTIST);
			String strALBUM = mAudioCursor.getString(indexALBUM);
			String strId=mAudioCursor.getString(indexId);				
			for(int j=0;j<musicIds.size();j++){
				if(strId.equals(musicIds.get(j))){
					exist=true;
					break;
				}
			}	
			if(!exist){
				HashMap<String, Object> nowMap = new HashMap<String, Object>();
				nowMap.put("title", strTitle);
				nowMap.put("info", strARTIST+ "---" + strALBUM);
				nowMap.put("id", strId);
				mListData.add(nowMap);
				exist=false;
			}
			mAudioCursor.moveToNext();
		}
		return mListData;
	}
}
