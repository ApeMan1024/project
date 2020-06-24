package com.apeman1024.entity;

import java.io.Serializable;

public class Car implements Serializable{
	private String id;
	private String username="";
	private String title;
	private String name="";
	private Double qian;
	private Double xian;
	private boolean bool;
	private Integer num;
	private String src="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getQian() {
		return qian;
	}
	public void setQian(Double qian) {
		this.qian = qian;
	}
	public Double getXian() {
		return xian;
	}
	public void setXian(Double xian) {
		this.xian = xian;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", username=" + username + ", title=" + title + ", name=" + name + ", qian=" + qian
				+ ", xian=" + xian + ", bool=" + bool + ", num=" + num + ", src=" + src + "]";
	}
	public Car() {
		super();
	}
	public Car(String id, String username, String title, String name, Double qian, Double xian, boolean bool,
			Integer num, String src) {
		super();
		this.id = id;
		this.username = username;
		this.title = title;
		this.name = name;
		this.qian = qian;
		this.xian = xian;
		this.bool = bool;
		this.num = num;
		this.src = src;
	}
	
}
