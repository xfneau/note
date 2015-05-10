package com.neau.note.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletResponseAware,
		ServletRequestAware, SessionAware {

	private static final long serialVersionUID = 7400684686763828144L;

	protected Logger logger = Logger.getLogger(this.getClass());

	protected HttpServletResponse response;

	protected HttpServletRequest request;

	protected Map<String, Object> session;

	public String execute() throws Exception{
		try{
			return doAction();
		}catch(Throwable e){
			logger.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}
	protected String doAction() throws Exception {
		return super.execute();
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}
	
	public Map<String, Object> getSession() {
		return session;
	}

	public HttpServletResponse getServletResponse() {
		return response;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
