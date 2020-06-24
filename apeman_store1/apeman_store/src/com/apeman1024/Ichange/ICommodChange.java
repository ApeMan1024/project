package com.apeman1024.Ichange;

import java.util.List;
import java.util.Map;

import com.apeman1024.entity.Com;

public interface ICommodChange {
public List<String> getTitle();
	
	public List<Com> getComs(int num,int ye,String title);

	public List<Map<String, Object>> getDis(String parameter);

	public List<Map<String, Object>> getSales(int num);

	public List<Map<String, Object>> getSearch(String parameter);
}
