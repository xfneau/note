package com.neau.note.action;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.service.LoginService;
import com.neau.note.utils.Content;

/**
 * 
 * @author Administrator
 *
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -307522586745144148L;
	protected Logger logger = Logger.getLogger(this.getClass());
	private String result;
	private String username;
	private String password;
	private String sign;
	private String email;
	private String sex;
	private String newPassword;
	private String jsonStr;

	@Resource
	LoginService loginService;

	/**
	 * 登陆接口
	 * 
	 * @return
	 */
	public String getLogin() {
		Map<String, Object> map = loginService.getLogin(username, password);
		 result = JSONObject.fromObject(map).toString();
		return SUCCESS;
	}

	/**
	 * 注册
	 * @throws IOException 
	 */
	public String signUp() {

		System.out.println(username);
		result = loginService.signUp(username, password, email, sex);
		System.out.println(result);
		return SUCCESS;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String alterUser() {

		result = loginService.alterUser(username, password, email, sex, newPassword,sign);
		return SUCCESS;
	}

	public String loginout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute(Content.SessionKey);
		Cookie cookieA = new Cookie(Content.SessionKey, null);
		cookieA.setPath("/");
		cookieA.setMaxAge(0);
		this.getServletResponse().addCookie(cookieA);
		return "adminLogin";
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	
}
