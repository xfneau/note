package com.neau.note.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neau.note.dao.LoginDao;
import com.neau.note.pojo.User;
import com.neau.note.utils.CipherUtils;
import com.neau.note.utils.Content;
import com.neau.note.utils.DateUtils;
import com.neau.note.utils.ValidateUtils;

@Service("loginService")
public class LoginService {

	@Resource
	LoginDao loginDaoImpl;

	@Resource
	User user;

	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 每次登陆更新服务器端笔记数量、登陆时间、openkey
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Map<String, Object> getLogin(String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		try {
			if (ValidateUtils.isValidate(username)
					&& ValidateUtils.isValidate(password)) {
				User user = loginDaoImpl.getUserByUserName(username);
				if (ValidateUtils.isValidate(user)
						&& CipherUtils.validatePassword(user.getPassword(),
								password)) {
					user.setTotal(loginDaoImpl.getSumNote(user.getId()));
					user.setLasttime(DateUtils.nowTimes());
					user.setOpenkey(CipherUtils.generatePassword(user
							.getUsername() + DateUtils.nowTimes()));
					if (loginDaoImpl.updateUserByUserid(user) > 0) {
						map.put(Content.result, Content.success);
						map.put(Content.response, user.getOpenkey());
						map.put(Content.userid, user.getId());
						map.put(Content.username, user.getUsername());
					}
				}
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param sex
	 * @return
	 */
	public String signUp(String username, String password, String email,
			String sex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		try {
			if (ValidateUtils.isValidate(password)
					&& ValidateUtils.isValidate(username)
					&& !ValidateUtils.isValidate(loginDaoImpl
							.getUserByUserName(username))) {
				user.setUsername(username);
				user.setPassword(CipherUtils.generatePassword(password));
				user.setEmail(email);
				user.setSex(sex);
				user.setTotal(0);
				user.setOpenkey(CipherUtils.generatePassword(username
						+ DateUtils.nowTimes()));
				user.setLasttime(DateUtils.nowTimes());
				int id = loginDaoImpl.signUp(user);
				if (id > 0) {
					map.put(Content.result, Content.success);
					map.put(Content.response, user.getOpenkey());
					map.put(Content.userid, id);
				}
			} else{
				map.put(Content.message, Content.userError);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param sex
	 * @param newPassword
	 * @param sign
	 * @return
	 */
	public String alterUser(String username, String password, String email,
			String sex, String newPassword, String sign) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		try {
			if (ValidateUtils.isValidate(username)) {
				User user = loginDaoImpl.getUserByUserName(username);
				if (ValidateUtils.isValidate(user)
						&& CipherUtils.hmacMd5Encode(user.getOpenkey(),
								username).equals(sign)) {
					if (password != null && newPassword != null) {
						if (CipherUtils.validatePassword(user.getPassword(),
								password)) {
							user.setPassword(CipherUtils
									.generatePassword(newPassword));
						}else{
							map.put(Content.message, Content.pwdError);
							return JSONObject.fromObject(map).toString();
						}
					}
					if( ValidateUtils.isValidate(email) ){
						user.setEmail(email);
					}
					if( ValidateUtils.isValidate(sex) ){
						user.setSex(sex);
					}
					int id = loginDaoImpl.updateUserByUserid(user);
					if (id > 0) {
						map.put(Content.result, Content.success);
						map.put(Content.response, user.getOpenkey());
						map.put(Content.userid, user.getId());
					} else{
						map.put(Content.message, Content.alterError);
					}
				}
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}
	
	public User getUserByUsernamePassword(String username, String password){
		User user = null;
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", username);
		map.put("password", password);
		try{
			user = loginDaoImpl.getUserByUsernamePassword(map);
		}catch(Exception e){
			logger.info("getUserByUsernamePassword error",e);
		}
		return user;
	}

}
