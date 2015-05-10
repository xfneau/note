package com.neau.note.dao;

import java.util.List;

import com.neau.note.pojo.Note;

public interface SynchronizationDataDao {

	public int backups(List<Note> list);
	
	public List<Note> recover(int userid);
	
	public void remove(int userid);
}
