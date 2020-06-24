package com.apeman1024.Ichange;

import java.util.List;
import java.util.Map;

import com.apeman1024.entity.Admin;
import com.apeman1024.entity.Card;
import com.apeman1024.entity.Com;
import com.apeman1024.entity.Ping;

public interface IAminChange {
	Admin pd(Admin admin);
	Boolean cong(Card card);
	Boolean getID(String guid);
	List<Map<String, Object>> getCom(int start, int len);
	List<Ping> getPing(int start, int len);
	boolean delPing(Ping ping);
	boolean addCom(Map<String, Object>map);
	boolean delCom(Com com);
	boolean addMiao(String data, String time, List<Map<String, Object>> zhuan);
}
