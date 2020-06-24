package com.apeman1024.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.apeman1024.Ichange.IMineChange;
import com.apeman1024.change.MineChange;
import com.apeman1024.entity.Indent;


public class MineTest {
	@Test
	public void getIndentTest() {
		IMineChange mine=new MineChange();
		List<Indent> indent = mine.getIndent("root");
		System.out.println(indent);
	}
	
	@Test
	public void delIndentTest() {
		IMineChange mine=new MineChange();
		boolean delIndent = mine.delIndent("root","512KNRLk70Ks1j9s");
		System.out.println(delIndent);
	}
	
	@Test
	public void delTest() {
		IMineChange mine=new MineChange();
		boolean del = mine.del("root");
		System.out.println(del);
	}
}
