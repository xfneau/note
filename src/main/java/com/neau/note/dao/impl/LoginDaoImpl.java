package com.neau.note.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neau.note.dao.LoginDao;
import com.neau.note.pojo.User;

@Repository("loginDaoImpl")
public class LoginDaoImpl implements LoginDao {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public User getUserByUserName(String username) {
		User user = null;
		try {
			user = sqlSessionTemplate.selectOne("User.getUserByUserName",
					username);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int signUp(User user) {
		int id = 0;
		try {
			sqlSessionTemplate.insert("User.insertUser", user);
			id = user.getId();
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public User getUserByUserid(int userid) {
		User user = null;
		try {
			user = sqlSessionTemplate.selectOne("User.getUserByUserId", userid);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int updateUserByUserid(User user) {
		int id = 0;
		try {
			id = sqlSessionTemplate.update("User.updateUserByUserid", user);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int getSumNote(int userid) {
		int id = 0;
		try {
			id = sqlSessionTemplate.selectOne("User.getSumNote", userid);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public User getUserByUsernamePassword(Map map) {
		User user = null;
		try {
			sqlSessionTemplate.selectOne("User.getUserByUsernamePassword", map);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return user;
	}
}
