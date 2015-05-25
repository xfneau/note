package com.neau.note.dao;

import java.util.List;

import com.neau.note.pojo.Note;
import com.neau.note.pojo.Sms;

public interface SynchronizationDataDao {

	public int backups(List<Note> list);
	
	public List<Note> recover(int userid);
	
	public void remove(int userid);
	
	public void _backUps(List<Sms> list);
	
	public List<Sms> _getTest(Sms s);
}
