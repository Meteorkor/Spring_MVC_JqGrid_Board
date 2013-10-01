package com.meteor.model;

public class Board {

	private int id;
	private String subject;
	private String writer;
	private int t_count;
	private int valid;
	private String t_description;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int gett_count() {
		return t_count;
	}
	public void sett_count(int count) {
		this.t_count = count;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public String gett_description() {
		return t_description;
	}
	public void sett_description(String desc) {
		this.t_description = desc;
	}
	
	
	
	
}
