package com.apeman1024.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.apeman1024.Ichange.IOpinionChange;
import com.apeman1024.change.OpinionChange;
import com.apeman1024.entity.Ping;

public class OpinionTest {
	@Test
	public void selectTest() {
		int pa=0;
		int num=10;
		IOpinionChange iOpinionChange=new OpinionChange();
		List<Ping> select = iOpinionChange.select(pa, num);
		System.out.println(select);
	}
	
	@Test
	public void addPingTest() {
		IOpinionChange iOpinionChange=new OpinionChange();
		Ping ping =new Ping();
		ping.setHead("1");
		ping.setNei("11111");
		ping.setTime("2020-03-12 00:04");
		ping.setUsername("1");
		Ping addPing = iOpinionChange.addPing(ping);
		System.out.println(addPing);
	}
	
	@Test
	public void countTest() {

		IOpinionChange iOpinionChange=new OpinionChange();
		Integer count = iOpinionChange.count();
		System.out.println(count);
	}
}
