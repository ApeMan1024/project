package com.apeman1024.Ichange;

import com.apeman1024.entity.Indent;

public interface IMyChange {
	public boolean pd(String  card, String  pass2,String username);

	public Double getBalance(String card, String pass2, String username);

	public boolean addIndent(Indent ind);

	public boolean expen(String username, String card, double balance1);

	public boolean pd1(String guid);
}
