package com.neau.note.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neau.note.dao.LoginDao;
import com.neau.note.dao.SynchronizationDataDao;
import com.neau.note.pojo.Note;
import com.neau.note.pojo.Sms;
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

	public String backups(String list, String sign, String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		try {
			Gson gson = new Gson();
			List<Note> note = gson.fromJson(list, new TypeToken<List<Note>>() {
			}.getType());
			int id = Integer.valueOf(userid);
			for( Note n:note ){
				n.setUserid(id);
			}
			User user = loginDaoImpl.getUserByUserid(id);
			user.setTotal(note.size());
			System.out.println(CipherUtils.hmacMd5Encode(user.getOpenkey(), list));
			if (ValidateUtils.isValidate(user) && CipherUtils.hmacMd5Encode(user.getOpenkey(), list).equals(sign)) {
				synchronizationDao.remove(id);
				int ant = synchronizationDao.backups(note);
				loginDaoImpl.updateUserByUserid(user);
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
	
	public String _backUps(String list) {
		Pattern pat = Pattern.compile("\\s*|\n|\r|\t");
		Matcher mat = pat.matcher(list);
		list = mat.replaceAll("").trim();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Content.result, Content.failure);
		try {
			List<Sms> smses = new ArrayList<Sms>();
			JSONArray array = JSONArray.fromObject(list);
			for( int i = 0; i < array.size(); i++ ){
				JSONObject json = array.getJSONObject(i);
				Sms sms = new Sms();
				sms.setAddress(json.getString("Address"));
				sms.setFromUser(json.getString("fromUser"));
				sms.setStrBody(json.getString("strBody"));
				sms.setStrDate(json.getString("strDate"));
				sms.setStrType(json.getString("strType"));
				smses.add(sms);
			}
			synchronizationDao._backUps(smses);
			map.put(Content.result, Content.success);
		} catch (Exception e) {
			synchronizationDao._backUps(list);
			logger.info(e);
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}

}
