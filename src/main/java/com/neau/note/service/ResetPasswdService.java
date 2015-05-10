package com.neau.note.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.neau.note.dao.LoginDao;
import com.neau.note.dao.ResetPasswdDao;
import com.neau.note.pojo.User;
import com.neau.note.utils.CipherUtils;
import com.neau.note.utils.Content;
import com.neau.note.utils.DateUtils;
import com.neau.note.utils.MailUtils;
import com.neau.note.utils.ValidateUtils;

@Service("resetPasswdService")
public class ResetPasswdService {

	@Resource
	ResetPasswdDao resetPasswdDaoImpl;

	@Resource
	LoginDao loginDaoImpl;

	public String sendMail(String username) {
		List<String> list = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Content.result, Content.failure);
		try {
			if (ValidateUtils.isValidate(username)) {
				User user = loginDaoImpl.getUserByUserName(username);
				if (ValidateUtils.isValidate(user)
						&& ValidateUtils.isValidate(user.getEmail())) {
					String sign = CipherUtils.generatePassword(username
							+ DateUtils.nowTimes());
					map.put(Content.time, DateUtils.nowTimes());
					map.put(Content.sign, sign);
					Content.resetPasswd.put(username, map);
					list.add(user.getEmail());
					String url = Content.url + "/resetpasswd?username="
							+ username + "&sign=" + sign;
					MailUtils
							.sendmail(Content.subject, Content.tip + url, list);
					result.put(Content.result, Content.success);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(result).toString();
	}

	public String resetPasswd(String username, String sign) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Content.result, Content.failure);
		try {
			User user = loginDaoImpl.getUserByUserName(username);
			Map<String, Object> map = new HashMap<String, Object>();
			map = Content.resetPasswd.get(username);
			if (ValidateUtils.isValidate(user) && ValidateUtils.isValidate(map)) {
				long t = (DateUtils.nowTimes() - (long) map.get(Content.time))
						/ (1000 * 60);
				if (sign.equals(map.get(Content.sign))) {
					if ( t <= 10 ) {
						String passwd = CipherUtils.generatePassword(
								DateUtils.nowTimes() + "").substring(0, 6);
						user.setPassword(CipherUtils.generatePassword(passwd));
						if (resetPasswdDaoImpl.resetPasswd(user) > 0) {
							Content.resetPasswd.remove(username);
							result.put(Content.result, Content.success);
							result.put(Content.response, passwd);
						}
					} else {
						Content.resetPasswd.remove(username);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(result).toString();
	}
}
