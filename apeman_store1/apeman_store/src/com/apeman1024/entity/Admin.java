package com.apeman1024.entity;

import java.io.Serializable;

public class Admin implements Serializable{
	String  username="";
	String password="";
	String head="";
	String phone="";
	String occu="";
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
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOccu() {
		return occu;
	}
	public void setOccu(String occu) {
		this.occu = occu;
	}
	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", head=" + head + ", phone=" + phone
				+ ", occu=" + occu + "]";
	}
	public Admin(String username, String password, String head, String phone, String occu) {
		super();
		this.username = username;
		this.password = password;
		this.head = head;
		this.phone = phone;
		this.occu = occu;
	}
	public Admin() {
		super();
	}
	
	
}
