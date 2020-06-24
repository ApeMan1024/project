package com.apeman1024.test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.apeman1024.Ichange.ICommodChange;
import com.apeman1024.change.CommodChange;
import com.apeman1024.entity.Com;

public class CommodTest {
	@Test
	public void getTitleTest() {
		ICommodChange commodChange = new CommodChange();
		List<String> title = commodChange.getTitle();
		System.out.println(title);
	}
	
	@Test
	public void getComsTest() {
		ICommodChange commodChange = new CommodChange();
		int ye=1;
		int num=10;
		List<Com> coms = commodChange.getComs(num, ye, "手机");
		System.out.println(coms.size());
	}
	
	@Test
	public void getDisTest() {
		ICommodChange commodChange = new CommodChange();
		String data="2020-03-28";
		List<Map<String, Object>> dis = commodChange.getDis(data);
		System.out.println(dis);
	}
	
	@Test
	public void getSalesTest() {
		ICommodChange commodChange = new CommodChange();
		int num=10;
		List<Map<String, Object>> sales = commodChange.getSales(num);
		System.out.println(sales);
	}
	
	@Test
	public void getSearchTest() {
		ICommodChange commodChange = new CommodChange();
		String s="零食";
		List<Map<String, Object>> search = commodChange.getSearch(s);
		System.out.println(search);
	}
}
