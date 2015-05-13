package com.neau.note.action;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neau.note.pojo.Admin;
import com.neau.note.service.AdminService;
import com.neau.note.utils.Content;
import com.opensymphony.xwork2.ActionContext;


@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String realName;
	
	private String password;
	
	private List<Admin> adminList;
	
	public String result;
	
	private String oldpwd;
	
	private String type;
	
	private String author;
	
	private String context;
	
	private String downloadFileName;
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	private String newpwd;
	
	private int id;
	
	@Resource
	AdminService adminService;
	
	public String getLogin(){
		id = adminService.getLogin(username, password);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if( id > 0 ){
			session.setAttribute(Content.SessionKey, username);
			Cookie cookieA = new Cookie(Content.SessionKey, username);
			cookieA.setPath("/");
			cookieA.setMaxAge(Integer.MAX_VALUE);
			this.getServletResponse().addCookie(cookieA);
			logger.info("add user cookie");
		}
		if( id == -1 ){
			return Content.adminLogin;
		} else if( id == 0 ){
			ActionContext.getContext().getSession().put(Content.SessionKey,username); 
			return "normal";
		}
		ActionContext.getContext().getSession().put(Content.SessionKey,username);
		return SUCCESS;
	}

	public String getAllAdmin(){
		adminList = adminService.getAllAdmin(id);
		result = JSONArray.fromObject(adminList).toString();
		return SUCCESS;
	}
	
	public String delAdmin(){
		result = adminService.deleteAdmin(id);
		return SUCCESS;
	}
	
	public String addAdmin() {
		result = adminService.addAdmin(realName);
		return SUCCESS;
	}
	
	public String alterPasswd() {
		result = adminService.alterPasswd(oldpwd, newpwd,username);
		return SUCCESS;
	}
	
	public String getAllUser(){
		result = adminService.getAllUser(id);
		return SUCCESS;
	}
	
	public String getAllQuotes(){
		result = adminService.getAllQuotes(id);
		return SUCCESS;
	}
	public String getAllAdvi(){
		result = adminService.getAllAdvi(id);
		return SUCCESS;
	}
	
	public String addAdvise(){
		result = adminService.addAdvise(username,context);
		return SUCCESS;
	}
	
	public String addQuote(){
		result = adminService.addQuote(type,context,author);
		return SUCCESS;
	}
	
	public String searchUser(){
		result = adminService.searchUser(context);
		return SUCCESS;
	}
	
	public String delUser(){
		result = adminService.delUser(id);
		return SUCCESS;
	}
	
	public String deleteQuotes(){
		result = adminService.deleteQuotes(id);
		return SUCCESS;
	}
	
	public String getAllNote(){
		result = adminService.getAllNote(id);
		return SUCCESS;
	}
	
	public String getUserLength(){
		result = adminService.getUserLength();
		return SUCCESS;
	}
	
	public String getNoteLength(){
		result = adminService.getNoteLength();
		return SUCCESS;
	}
	
	public String getQuoteLength(){
		result = adminService.getQuoteLength();
		return SUCCESS;
	}
	
	public String download(){
		return SUCCESS;
	}
	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String signUp(){
		
		return SUCCESS;
	}

	public String getSuccess(){
		return SUCCESS;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public List<Admin> getList() {
		return adminList;
	}

	public void setList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDownloadFileName() {
		return this.downloadFileName;
	}
	
	public InputStream getDownloadFile(){
		return ServletActionContext.getServletContext().getResourceAsStream("/app/" + downloadFileName); 
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	
	
}
