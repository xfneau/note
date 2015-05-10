package com.neau.note.pojo;

public class Note {
	
	private int id;
	
	private String title;
	
	private String content;
	
	private long time;
	
	private int isclock;
	
	private long clocktime;
	
	private int category;

	private int userid;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getIsclock() {
		return isclock;
	}

	public void setIsclock(int isclock) {
		this.isclock = isclock;
	}

	public long getClocktime() {
		return clocktime;
	}

	public void setClocktime(long clocktime) {
		this.clocktime = clocktime;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	

}
