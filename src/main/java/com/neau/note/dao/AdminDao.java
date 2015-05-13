package com.neau.note.dao;

import java.util.List;
import java.util.Map;

import com.neau.note.pojo.Admin;
import com.neau.note.pojo.Advise;
import com.neau.note.pojo.Note;
import com.neau.note.pojo.User;

public interface AdminDao {

	public Admin getLogin(Map<String,Object> map);
	
	public int deleteAdmin( int id);
	
	public boolean isOrigin(String username);
	
	public int addAdmin(Admin admin);
	
	public int addAdvise(Advise map);
	
	public int addQuote(Quotes map);
	
	public List<Admin> getAllAdmin(int id);
	
	public int alterPasswd(Admin admin);
	
	public List<User> getAllUser(int id);
	
	public List<Quotes> getAllQuotes(int id);
	
	public List<Advise> getAllAdvi(int id);
	
	public int delUser(int id);
	
	public List<Note> getAllNote(int id);
	
	public int getUserLength();
	
	public int getNoteLength();

	public int getQuoteLength();
	
	public int deleteQuotes(int id);
	
	public List<User> searchUser(String context);
}
