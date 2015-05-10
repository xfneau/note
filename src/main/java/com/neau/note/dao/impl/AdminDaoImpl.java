package com.neau.note.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.neau.note.dao.AdminDao;
import com.neau.note.pojo.Admin;
import com.neau.note.pojo.Note;
import com.neau.note.pojo.User;

@Repository("adminDaoImpl")
public class AdminDaoImpl implements AdminDao {

	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Admin getLogin(Map<String, Object> map) {
		Admin admin = null;
		try {
			admin = sqlSessionTemplate.selectOne("Admin.getLogin", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public int deleteAdmin(int id) {
		int raw = 0;
		try {
			raw = sqlSessionTemplate.delete("Admin.delAdmin", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return raw;
	}

	@Override
	public boolean isOrigin(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addAdmin(Admin admin) {
		int id = 0;
		try {
			sqlSessionTemplate.insert("Admin.addAdmin", admin);
			id = admin.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> list = new ArrayList<Admin>();
		try {
			list = sqlSessionTemplate.selectList("Admin.getAllAdmin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int alterPasswd(Admin admin) {
		int id = 0;
		try {
			id = sqlSessionTemplate.update("Admin.alterAdmin", admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<User> getAllUser(int id) {
		List<User> list = new ArrayList<User>();
		try {
			id = (id-1)*15;
			list = sqlSessionTemplate.selectList("Admin.getAllUser", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Note> getAllNote(int id) {
		List<Note> list = new ArrayList<Note>();
		try {
			list = sqlSessionTemplate.selectList("Admin.getAllNote", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int delUser(int id) {
		int raw = 0;
		try {
			raw = sqlSessionTemplate.delete("Admin.delUser", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return raw;
	}

	@Override
	public int getUserLength() {
		int raw = 0;
		try {
			raw = sqlSessionTemplate.selectOne("Admin.getUserLength");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return raw;
	}

	@Override
	public int getNoteLength() {
		int raw = 0;
		try {
			raw = sqlSessionTemplate.selectOne("Admin.getNoteLength");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return raw;
	}

}
