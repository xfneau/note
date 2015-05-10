package com.neau.note.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.pojo.User;
import com.neau.note.service.LoginService;
import com.neau.note.utils.Content;
import com.neau.note.utils.ValidateUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Controller("securityInterceptorNoLogin")
@Scope("prototype")
public class SecurityInterceptorNoLogin extends AbstractInterceptor {

	
	private static final long serialVersionUID = 1L;
	
	@Resource
	LoginService loginService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Logger logger = Logger.getLogger(SecurityInterceptorNoLogin.class);

		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		HttpSession session = request.getSession();
		Map<String, Object> sessions = actionContext.getSession();
		if (session != null && session.getAttribute(Content.SessionKey) != null ) {
			logger.info("user login...ok");
			User user = (User) sessions.get(Content.SessionKey);
			if( user == null ){
				return Content.Login;
			}
			return invocation.invoke();
		}else{
			Cookie[] cookies = request.getCookies();
			if( cookies != null ){
				User user = null;
				for( Cookie cookie:cookies ){
					if( Content.username.equals(cookie.getName()) ){
						String value = cookie.getValue();
						if( ValidateUtils.isValidate(value) ){
							String[] values = value.split(Content.splitStr);
							user = loginService.getUserByUsernamePassword(values[0], values[1]);
							if( user != null ){
								request.getSession().setAttribute(Content.SessionKey, user);
							} else{
								logger.info("user didn't exist");
								return Content.Login;
							}
						} else{
							logger.info("unavailable user cookie");
							return Content.Login;
						}
					} else{
						
					}
				}
				if (user == null) {
					if (request.getSession().getAttribute(Content.SessionKey) != null)
					{
						request.getSession().removeAttribute(Content.SessionKey);
					}
				}
				
			} else{
				logger.info("cookie is null");
				if (request.getSession().getAttribute(Content.SessionKey) != null)
				{
					request.getSession().removeAttribute(Content.SessionKey);
				}
			}
		}
		return Content.Login;
	}

}
