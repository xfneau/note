package com.neau.note.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.service.ResetPasswdService;


@Controller("resetPasswd")
@Scope("prototype")
public class ResetPasswdAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6784504993225164333L;

	private String result;

	@Resource
	ResetPasswdService resetPasswdService; 
	
	public String passwd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String sign = request.getParameter("sign");
		result = resetPasswdService.resetPasswd(username, sign);
		return SUCCESS;
	}
	
	public String sendMail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		result = resetPasswdService.sendMail(username);
		return SUCCESS;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
