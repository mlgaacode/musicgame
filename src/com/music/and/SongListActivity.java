package com.music.and;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.music.and.R;
import com.music.and.proxy.DataProxy;
import com.music.and.utils.Util;
import com.music.desk.manager.NoteMgr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
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
				DataProxy.getInstance().songId=target.get("id").toString();
				Bundle bundle=new Bundle();
				bundle.putString("mode", NoteMgr.MODE_GAME);
				intent=new Intent();
				intent.setClass(SongListActivity.this, StartGameActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
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
	/*
	 * 返回键处理
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
			Bundle bundle=new Bundle();
			bundle.putBoolean("back", true);
			intent=new Intent();
			intent.putExtras(bundle);
			intent.setClass(SongListActivity.this, MusicGameActivity.class);
			startActivity(intent);
		}
		return super.onKeyDown(keyCode, event);
	}
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> mListData = new ArrayList<Map<String, Object>>();		
		mListData.addAll(Setting.getInstance().getDemoSong());
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
		int indexTitle = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
		int indexARTIST = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
		int indexALBUM = mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
		int indexId=mAudioCursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID);	
		mAudioCursor.moveToFirst();
		for (int i = 0; i < mAudioCursor.getCount(); i++) {
			String strId=mAudioCursor.getString(indexId);	
			for(int j=0;j<musicIds.size();j++){	
				if(strId.equals(musicIds.get(j))){
					String strTitle = mAudioCursor.getString(indexTitle);
					String strARTIST = mAudioCursor.getString(indexARTIST);
					String strALBUM = mAudioCursor.getString(indexALBUM);
					HashMap<String, Object> nowMap = new HashMap<String, Object>();
					nowMap.put("title", strTitle);
					nowMap.put("info", strARTIST+ "---" + strALBUM);
					nowMap.put("id", strId);
					mListData.add(nowMap);
					break;
				}
			}			
			mAudioCursor.moveToNext();			
		}
		mAudioCursor.close();		
		return mListData;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
