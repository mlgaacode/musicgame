package com.musicgame.and;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SongListActivity extends Activity {
	private ListView lv_songs;
	private Button btn_editsong;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.songlist);
		initView();
	}
	private void initView(){
		lv_songs=(ListView)findViewById(R.id.lv_songs);
		SimpleAdapter adapter=new SimpleAdapter(this,getData(),R.layout.songs,new String[]{"title","info"},new int[]{R.id.title,R.id.info});		
		lv_songs.setAdapter(adapter);
		lv_songs.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
				//进入游戏主界面
				Map<String, Object> target=(Map<String, Object> )arg0.getAdapter().getItem(position);
				Log.i("title", target.get("title").toString());
			}
		});
		btn_editsong=(Button)findViewById(R.id.btn_editsong);
		btn_editsong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent=new Intent();
				intent.setClass(SongListActivity.this, EditSongListActivity.class);
				startActivity(intent);
			}
		});
		
	}
	
	
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> mListData = new ArrayList<Map<String, Object>>();		
		mListData.addAll(Config.getInstance().getDemoSong());
		Cursor mAudioCursor = this.getContentResolver().query(	MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,	null,null, null, MediaStore.Audio.AudioColumns.TITLE);
		for (int i = 0; i < mAudioCursor.getCount(); i++) {
			mAudioCursor.moveToNext();
			int indexTitle = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
			int indexARTIST = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
			int indexALBUM = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
			int indexPATH=mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
			String strTitle = mAudioCursor.getString(indexTitle);
			String strARTIST = mAudioCursor.getString(indexARTIST);
			String strALBUM = mAudioCursor.getString(indexALBUM);
			String strPATH=mAudioCursor.getString(indexPATH);			
			HashMap<String, Object> nowMap = new HashMap<String, Object>();
			nowMap.put("title", strTitle);
			nowMap.put("info", strARTIST+ "---" + strALBUM);
			nowMap.put("path", strPATH);
			mListData.add(nowMap);
		}
		return mListData;
	}
}
