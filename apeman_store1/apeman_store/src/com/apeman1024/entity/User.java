package com.apeman1024.entity;

import java.io.Serializable;

public class User implements Serializable{
	private String username="";//用户名是主键
	private String pass1="";//密码
	private String email="";//邮箱
	private String phone="";//手机号码
	private String address="";//配送地址
	private String card="";//猿人卡号
	private String pass2="";//猿人卡密码
	private String head="";//头像
	
	public User(String username, String pass1, String email, String phone, String address, String card, String pass2,
			String head) {
		super();
		this.username = username;
		this.pass1 = pass1;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.card = card;
		this.pass2 = pass2;
		this.head = head;
	}
	public User() {
		super();
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass1() {
		return pass1;
	}
	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", pass1=" + pass1 + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", card=" + card + ", pass2=" + pass2 + ", head=" + head + "]";
	}
	
	
}
