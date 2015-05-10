package com.neau.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neau.note.dao.LoginDao;
import com.neau.note.dao.SynchronizationDataDao;
import com.neau.note.pojo.Note;
import com.neau.note.pojo.User;
import com.neau.note.utils.CipherUtils;
import com.neau.note.utils.Content;
import com.neau.note.utils.ValidateUtils;

@Service("synchronizationService")
public class SynchronizationService {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Resource
	SynchronizationDataDao synchronizationDao;

	@Resource
	LoginDao loginDaoImpl;

	public String recover(Integer userid, String sign) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		try {
			User user = loginDaoImpl.getUserByUserid(userid);
			if (CipherUtils.hmacMd5Encode(user.getOpenkey(),
					Integer.toString(userid)).equals(sign)) {
				List<Note> list = synchronizationDao.recover(userid);
				map.put(Content.result, Content.success);
				map.put(Content.response, list);
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}

	public String backups(String list, String sign) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		try {
			Gson gson = new Gson();
			List<Note> note = gson.fromJson(list, new TypeToken<List<Note>>() {
			}.getType());
			int id = note.get(0).getUserid();
			User user = loginDaoImpl.getUserByUserid(id);
			if (ValidateUtils.isValidate(user) && CipherUtils.hmacMd5Encode(user.getOpenkey(), list).equals(sign)) {
				synchronizationDao.remove(id);
				int ant = synchronizationDao.backups(note);
				if (ant > 0) {
					map.put(Content.result, Content.success);
					map.put(Content.response, ant);
				}
			}
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}

}
