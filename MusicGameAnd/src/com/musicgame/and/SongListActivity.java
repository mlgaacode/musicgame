package com.musicgame.and;

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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SongListActivity extends Activity {
	private ListView lv_songs;
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


			}
		});
	}
	
	private List<Map<String, Object>> getData(){
		Cursor mAudioCursor = this.getContentResolver().query(	MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,	null,null, null, MediaStore.Audio.AudioColumns.TITLE);
		List<Map<String, Object>> mListData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < mAudioCursor.getCount(); i++) {
			mAudioCursor.moveToNext();
			int indexTitle = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
			int indexARTIST = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
			int indexALBUM = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
			String strTitle = mAudioCursor.getString(indexTitle);
			String strARTIST = mAudioCursor.getString(indexARTIST);
			String strALBUM = mAudioCursor.getString(indexALBUM);
			HashMap<String, Object> nowMap = new HashMap<String, Object>();
			nowMap.put("title", strTitle);
			nowMap.put("info", strARTIST+ "---" + strALBUM);
			mListData.add(nowMap);
		}
		return mListData;
	}
}
