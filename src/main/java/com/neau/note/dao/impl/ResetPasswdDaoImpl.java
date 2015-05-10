package com.neau.note.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.neau.note.dao.ResetPasswdDao;
import com.neau.note.pojo.User;

@Repository("resetPasswdDaoImpl")
public class ResetPasswdDaoImpl implements ResetPasswdDao{

	@Resource
	SqlSessionTemplate sqlSessionTemplate;
	
	public User getUserByEmail(String email) {
		User user = null;
		try{
			user = sqlSessionTemplate.selectOne("Reset.getUserByEmail", email);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int resetPasswd(User user) {
		int id = 0;
		try{
			id = sqlSessionTemplate.update("Reset.updatePasswd", user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}

	
}
