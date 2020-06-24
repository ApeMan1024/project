package com.apeman1024.entity;

import java.io.Serializable;

public class Indent implements Serializable{
	private String id="";
	private String src="";
	private String name="";
	private String title="";
	private Integer num;
	private String number="";
	private Double price;
	private String address;
	private String username="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Indent [id=" + id + ", src=" + src + ", name=" + name + ", title=" + title + ", num=" + num
				+ ", number=" + number + ", price=" + price + ", address=" + address + ", username=" + username + "]";
	}
	public Indent(String id, String src, String name, String title, Integer num, String number, Double price,
			String address, String username) {
		super();
		this.id = id;
		this.src = src;
		this.name = name;
		this.title = title;
		this.num = num;
		this.number = number;
		this.price = price;
		this.address = address;
		this.username = username;
	}
	public Indent() {
		super();
	}
	
}
