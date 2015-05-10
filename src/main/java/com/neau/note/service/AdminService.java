package com.neau.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.neau.note.dao.AdminDao;
import com.neau.note.dao.SynchronizationDataDao;
import com.neau.note.pojo.Admin;
import com.neau.note.pojo.Note;
import com.neau.note.pojo.User;
import com.neau.note.utils.CipherUtils;
import com.neau.note.utils.Content;

@Service("adminService")
public class AdminService {

	@Resource
	AdminDao adminDaoImpl;

	@Resource
	SynchronizationDataDao synchronizationDao;
	
	public int getLogin(String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.username, username);
		map.put(Content.password, CipherUtils.generatePassword(password));
		Admin admin = adminDaoImpl.getLogin(map);
		if (admin == null)
			return -1;
		if (admin.getFlag() == 0)
			return 0;
		return admin.getFlag();
	}

	public String deleteAdmin(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		int raw = adminDaoImpl.deleteAdmin(id);
		if (raw > 0) {
			map.put(Content.result, Content.success);
		}
		return JSONObject.fromObject(map).toString();
	}

	public boolean isOrigin(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public String addAdmin(String realName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		Admin admin = new Admin();
		admin.setRealName(realName);
		long millis = System.currentTimeMillis();
		admin.setUsername("Y" + millis);
		admin.setFlag(0);
		int pwd = (int) (millis % 1000000);
		admin.setPassword(CipherUtils.generatePassword(pwd + ""));
		int id = adminDaoImpl.addAdmin(admin);
		if (id > 0) {
			map.put(Content.result, Content.success);
			map.put(Content.password, pwd);
			map.put(Content.username, "Y" + millis);
		}
		return JSONObject.fromObject(map).toString();
	}

	public List<Admin> getAllAdmin() {

		return adminDaoImpl.getAllAdmin();
	}

	public String alterPasswd(String oldpwd, String newpwd, String username) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put(Content.username, username);
		_map.put(Content.password, CipherUtils.generatePassword(oldpwd));
		map.put(Content.result, Content.failure);
		map.put(Content.response, Content.pwdError);
		Admin admin = adminDaoImpl.getLogin(_map);
		if (admin != null) {
			admin.setPassword(CipherUtils.generatePassword(newpwd));
			int row = adminDaoImpl.alterPasswd(admin);
			if (row > 0) {
				map.put(Content.result, Content.success);
				map.put(Content.response, Content.alterRight);
			}
		}
		return JSONObject.fromObject(map).toString();
	}

	public String getAllUser(int id) {

		List<User> list = adminDaoImpl.getAllUser(id);
		return JSONArray.fromObject(list).toString();
	}
	
	public String delUser(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		map.put(Content.response, Content.pwdError);
		int raw = adminDaoImpl.delUser(id);
		if( raw > 0 ){
			synchronizationDao.remove(id);
			map.put(Content.result, Content.success);
			map.put(Content.response, Content.alterRight);
		}
		return JSONObject.fromObject(map).toString();
	}

	public String getAllNote(int id) {
		
		List<Note> list = adminDaoImpl.getAllNote(id);
		return JSONArray.fromObject(list).toString();
	}
	
	public String getUserLength() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		map.put(Content.response, Content.pwdError);
		int sum = adminDaoImpl.getUserLength();
		sum = (int) Math.ceil((double)sum/15);
		if( sum > 0 ){
			map.put(Content.result, Content.success);
			map.put(Content.response, sum);
		}
		return JSONObject.fromObject(map).toString();
	}
	
	public String getNoteLength() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		map.put(Content.response, Content.pwdError);
		int sum = adminDaoImpl.getNoteLength();
		if( sum > 0 ){
			map.put(Content.result, Content.success);
			map.put(Content.response, sum);
		}
		return JSONObject.fromObject(map).toString();
	}

}
