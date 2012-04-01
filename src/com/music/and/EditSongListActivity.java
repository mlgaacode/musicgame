package com.music.and;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.music.and.R;
import com.music.and.utils.Util;

import android.app.Activity;
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
				Log.i("title", target.get("title").toString());
			}
		});
	}
	
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> mListData = new ArrayList<Map<String, Object>>();		
		mListData.addAll(Setting.getInstance().getDemoSong());
		SQLiteDatabase database=Util.openDatabase(this);
		Cursor mCursor=database.rawQuery("select musicId from songs",null);
		database.close();
		mCursor.moveToFirst();		
		Cursor mAudioCursor = this.getContentResolver().query(	MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,	null,null, null, MediaStore.Audio.AudioColumns.TITLE);
		int indexTitle = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
		int indexARTIST = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
		int indexALBUM = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
		int indexId=mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID);
		boolean exist=false;
		for (int i = 0; i < mAudioCursor.getCount(); i++) {
			mAudioCursor.moveToNext();
			String strTitle = mAudioCursor.getString(indexTitle);
			String strARTIST = mAudioCursor.getString(indexARTIST);
			String strALBUM = mAudioCursor.getString(indexALBUM);
			String strId=mAudioCursor.getString(indexId);				
			for(int j=0;j<mCursor.getColumnCount();j++){
				if(strId.equals(mCursor.getString(0))){
					exist=true;
					mCursor.moveToFirst();
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
		}
		return mListData;
	}
}
