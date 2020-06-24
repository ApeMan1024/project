package com.apeman1024.entity;

import java.io.Serializable;

public class Com implements Serializable{
	String id="";
	String name="";
	String src="";
	Double qian;
	Integer count;
	String title;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Double getQian() {
		return qian;
	}
	public void setQian(Double qian) {
		this.qian = qian;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Com() {
		super();
	}
	public Com(String id, String name, String src, Double qian, Integer count, String title) {
		super();
		this.id = id;
		this.name = name;
		this.src = src;
		this.qian = qian;
		this.count = count;
		this.title = title;
	}
	@Override
	public String toString() {
		return "Com [id=" + id + ", name=" + name + ", src=" + src + ", qian=" + qian + ", count=" + count + ", title="
				+ title + "]";
	}
	
	
	
}
