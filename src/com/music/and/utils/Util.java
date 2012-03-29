package com.music.and.utils;

import com.music.and.Setting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Util {
	
	public static SQLiteDatabase openDatabase(Context context) {	    	
	    	SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(Setting.DATAPATH+"/"+Setting.DATAFILE, null);
	    	return database;
		}
}
