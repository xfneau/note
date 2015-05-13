package com.neau.note.dao;

public class Quotes {

	private int id;
	
	private String content;
	
	private String author;
	
	private long time;
	
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
