package com.neau.note.dao;

import com.neau.note.pojo.User;

public interface ResetPasswdDao {

	public User getUserByEmail(String email);
	
	public int resetPasswd(User user);
}
