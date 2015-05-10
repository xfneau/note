package com.neau.note.dao;

import java.util.Map;

import com.neau.note.pojo.User;

public interface LoginDao {
	
	public User getUserByUserName(String username);
	
	public User getUserByUserid(int userid);

	public int signUp(User user);
	
	public int updateUserByUserid(User user);
	
	public int getSumNote(int id);

	public User getUserByUsernamePassword(Map map);

}
