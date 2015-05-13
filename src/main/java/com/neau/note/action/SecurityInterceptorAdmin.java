package com.neau.note.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.pojo.User;
import com.neau.note.service.AdminService;
import com.neau.note.utils.Content;
import com.neau.note.utils.ValidateUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Controller("securityInterceptorAdmin")
@Scope("prototype")
public class SecurityInterceptorAdmin  extends AbstractInterceptor{

	@Resource
	AdminService adminService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例  
        ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute(Content.SessionKey) != null ) {
			String user = (String) session.getAttribute(Content.SessionKey);
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (Content.SessionKey.equals(cookie.getName())) {
						String value = cookie.getValue();
						if( value.equals(user) ){
							session.setMaxInactiveInterval(120);
							return invocation.invoke();
						}
					}
				}
				return invocation.invoke();
			}
		} 
		return Content.adminLogin;
	}

	
}
