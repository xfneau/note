package com.neau.note.pojo;

public class Sms {
	
	private int id;
	
	private String Address;
	
	private String fromUser;
	
	private String strBody;
	
	private String strDate;
	
	private String strType;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getStrBody() {
		return strBody;
	}

	public void setStrBody(String strBody) {
		this.strBody = strBody;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}
	
	

}
