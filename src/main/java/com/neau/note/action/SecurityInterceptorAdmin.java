package com.neau.note.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.service.AdminService;
import com.neau.note.utils.Content;
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
        ActionContext ctx = invocation.getInvocationContext();  
        Map session = ctx.getSession();  
        String user = (String) session.get(Content.SessionKey);  
        if (user!=null) {
            return invocation.invoke();
        } 
        return Content.adminLogin;  
	}

	
}
