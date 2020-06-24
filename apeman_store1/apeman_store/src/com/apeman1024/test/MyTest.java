package com.apeman1024.test;

import org.junit.jupiter.api.Test;

import com.apeman1024.Ichange.IMyChange;
import com.apeman1024.change.MyChange;
import com.apeman1024.entity.Indent;

public class MyTest {
	@Test
	public void pdTest() {
		IMyChange iMy=new MyChange();
		boolean pd = iMy.pd("1", "1", "1");
		System.out.println(pd);
		boolean pd1 = iMy.pd("3", "1", "1");
		System.out.println(pd1);
	}
	
	@Test
	public void getBalanceTest() {
		IMyChange iMy=new MyChange();
		Double balance = iMy.getBalance("1", "1", "1");
		System.out.println(balance);
		Double balance1 = iMy.getBalance("3", "1", "1");
		System.out.println(balance1);
	}
	
	@Test
	public void addIndentTest() {
		IMyChange iMy=new MyChange();
		Indent indent=new Indent();
		indent.setAddress("1");
		indent.setId("40qgdf8Rwol5XO5a");
		indent.setName("1");
		indent.setNum(1);
		indent.setNumber("1");
		indent.setPrice(1.);
		indent.setSrc("1");
		indent.setTitle("手机");
		indent.setUsername("1");
		boolean addIndent = iMy.addIndent(indent);
		System.out.println(addIndent);
		
	}
	
	@Test
	public void expenTest() {
		IMyChange iMy=new MyChange();
		boolean expen = iMy.expen("1", "1", 9.9);
		System.out.println(expen);
	}
	
	@Test
	public void pd1() {
		IMyChange iMy=new MyChange();
		boolean pd1 = iMy.pd1("1");
		System.out.println(pd1);
	}
}
