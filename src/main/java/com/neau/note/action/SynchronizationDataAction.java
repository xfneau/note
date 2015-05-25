package com.neau.note.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.service.SynchronizationService;
import com.neau.note.utils.Content;

@Controller("synchronizationDateAction")
@Scope("prototype")
public class SynchronizationDataAction extends BaseAction{

	private static final long serialVersionUID = -853193883385659657L;

	private String result;
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	SynchronizationService synchronizationService;
	
	public String backups(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String json = request.getParameter(Content.json);
		String sign = request.getParameter(Content.sign);
		String userid = request.getParameter(Content.userid);
		result = synchronizationService.backups(json,sign,userid);
		return SUCCESS;
	}
	
	public String _backUps(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String json = request.getParameter(Content.json);
		Map map=request.getParameterMap();
		result = synchronizationService._backUps(json);
		return SUCCESS;
	}
	
	public String recover(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userid = request.getParameter(Content.userid);
		String sign = request.getParameter(Content.sign);
		result = synchronizationService.recover(Integer.valueOf(userid),sign);
		return SUCCESS;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
