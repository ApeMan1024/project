package com.apeman1024.entity;

import java.io.Serializable;

public class Ping implements Serializable{
	private Integer id;
	private String username="";
	private String head="";
	private String nei="";
	private String time="";
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getNei() {
		return nei;
	}
	public void setNei(String nei) {
		this.nei = nei;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public Ping(Integer id, String username, String head, String nei, String time) {
		super();
		this.id = id;
		this.username = username;
		this.head = head;
		this.nei = nei;
		this.time = time;
	}
	public Ping() {
		super();
	}
	@Override
	public String toString() {
		return "Ping [id=" + id + ", username=" + username + ", head=" + head + ", nei=" + nei + ", time=" + time + "]";
	}
	
	
}
