package com.and.music.model;

import com.badlogic.gdx.audio.Music;

public interface ISong {
	void setName(String name);
	String getName();
	void setMusic(Music Music);
	Music getMusic();
	void setNoteFile(String file);
	String getNoteFile();
}
