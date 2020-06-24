package com.apeman1024.entity;

import java.io.Serializable;

public class Card implements Serializable{
	private String username;
	private String card;
	private String pass;
	private String balance;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Card [username=" + username + ", card=" + card + ", pass=" + pass + ", balance=" + balance + "]";
	}
	public Card(String username, String card, String pass, String balance) {
		super();
		this.username = username;
		this.card = card;
		this.pass = pass;
		this.balance = balance;
	}
	public Card() {
		super();
	}
	
}
