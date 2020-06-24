package com.apeman1024.test;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import com.apeman1024.Ichange.IAminChange;
import com.apeman1024.change.AdminChange;
import com.apeman1024.entity.Admin;
import com.apeman1024.entity.Card;
import com.apeman1024.entity.Com;
import com.apeman1024.entity.Ping;
import com.apeman1024.mapper.IAdministrator;
import com.apeman1024.util.Sql_util1;

public class AminTest {
	@Test
	public void pdtest() {
		SqlSession session = Sql_util1.getSession();
		Admin admin=new Admin();
		admin.setUsername("root");
		admin.setPassword("63a9f0ea7bb98050796b649e85481845");
		Admin pd = session.getMapper(IAdministrator.class).pd(admin);
		System.out.println(pd);
	}
	@Test
	public void congTest() {
		IAminChange admin = new AdminChange();
		Card card=new Card();
		card.setBalance("1");
		card.setCard("201709601266");
		card.setUsername("root");
		boolean cong = admin.cong(card);
		System.out.println(cong);
	}
	
	@Test
	public void getIDTest() {
		IAminChange admin = new AdminChange();
		Boolean cong = admin.getID("1G94V547l29065wu");
		System.out.println(cong);
	}
	
	
	@Test
	public void getComTest() {
		IAminChange admin = new AdminChange();
		List<Map<String, Object>> com = admin.getCom(0, 100);
		com.forEach((item)->{
			if(item.get("xian")==null) {
				item.put("xian", 0.);
			}
		});
		System.out.println(com);
	}
	
	@Test
	public void getPingTest() {
		IAminChange admin = new AdminChange();
		List<Ping> ping = admin.getPing(0, 100);
		System.out.println(ping);
	}
	
	@Test
	public void delPingTest() {
		IAminChange admin = new AdminChange();
		Ping ping = new Ping();
		ping.setId(1);
		boolean delPing = admin.delPing(ping);
		System.out.println(delPing);
	}
	
	@Test
	public void addComTest() {
		IAminChange admin = new AdminChange();
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", 1);
		map.put("src", 1);
		map.put("name", 1);
		map.put("qian", 1);
		map.put("count", 1);
		map.put("title", 1);
		map.put("img", 1);
		map.put("xian", 0);
		boolean addCom = admin.addCom(map);
		System.out.println(addCom);
	}
	
	
	@Test
	public void delComTest() {
		IAminChange admin = new AdminChange();
		Com com=new Com();
		com.setId("1");
		com.setTitle("1");
		boolean delCom = admin.delCom(com);
		System.out.println(delCom);
	}
	
	@Test
	public void addMiaoTets() {
		IAminChange admin = new AdminChange();
		String data="1";
		String time="1";
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("data", data);
		map.put("time", time);
		map.put("id", "1");
		map.put("name", "1");
		map.put("xian", "1");
		map.put("qian", "1");
		map.put("src", "1");
		List<Map<String, Object>>list=new ArrayList<Map<String,Object>>();
		list.add(map);
		boolean addMiao = admin.addMiao(data, time, list);
		System.out.println(addMiao);
	}
}
