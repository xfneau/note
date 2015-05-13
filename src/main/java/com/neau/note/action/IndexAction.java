package com.neau.note.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.dao.impl.LoginDaoImpl;
import com.neau.note.pojo.User;
import com.neau.note.service.LoginService;
import com.neau.note.utils.Content;
import com.neau.note.utils.ValidateUtils;

@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	LoginService loginService;
	@Resource
	LoginDaoImpl loginDaoImpl;

	private String username;

	private String password;

	private String keep;

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute(Content.SessionKey) == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (Content.SessionKey.equals(cookie.getName())) {
						String value = cookie.getValue();
						if (ValidateUtils.isValidate(value)) {
							String[] values = value.split(Content.splitStr);
							User user = loginService.getUserByUsernamePassword(
									values[0], values[1]);
							if (user != null) {
								request.getSession().setAttribute(
										Content.SessionKey, user);
							} else {
								if (request.getSession() != null
										&& request.getSession().getAttribute(
												Content.SessionKey) != null) {
									request.getSession().removeAttribute(
											Content.SessionKey);
									System.out
											.println("-------------------clear session------------------------------");
								}
								Cookie cookieA = new Cookie(Content.username,
										null);
								cookieA.setPath("/");
								cookieA.setMaxAge(0);
								this.getServletResponse().addCookie(cookieA);
								logger.info("user don't exist");
								return Content.Login;
							}
						} else {
							if (request.getSession() != null
									&& request.getSession().getAttribute(
											Content.SessionKey) != null) {
								request.getSession().removeAttribute(
										Content.SessionKey);
								System.out
										.println("-------------------clear session------------------------------");
							}
							Cookie cookieA = new Cookie(Content.username, null);
							cookieA.setPath("/");
							cookieA.setMaxAge(0);
							this.getServletResponse().addCookie(cookieA);
							logger.info("unavailable user cookie");
							return Content.Login;
						}
					}else {
						Map<String, Object> map = loginService.getLogin(username, password);
						if ((Boolean) map.get("result")) {
							Cookie cookieA = new Cookie(Content.username, null);
							cookieA.setPath("/");
							cookieA.setMaxAge(0);
							if (keep != null) {
								cookieA.setMaxAge(60*60*24*7);
							}
							this.getServletResponse().addCookie(cookieA);
							logger.info("add user cookie");
						}
					}
				}
			} else {
				Map<String, Object> map = loginService.getLogin(username, password);
				if ((Boolean) map.get("result")) {
					Cookie cookieA = new Cookie(Content.username, null);
					cookieA.setPath("/");
					cookieA.setMaxAge(0);
					if (keep != null) {
						cookieA.setMaxAge(60*60*24*7);
					}
					this.getServletResponse().addCookie(cookieA);
					logger.info("add user cookie");
				}
			}
		}
		return SUCCESS;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setKeep(String keep) {
		this.keep = keep;
	}

}
