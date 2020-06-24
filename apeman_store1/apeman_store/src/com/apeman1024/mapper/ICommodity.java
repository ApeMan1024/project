package com.apeman1024.mapper;

import java.util.*;

import org.apache.ibatis.session.RowBounds;

import com.apeman1024.entity.Com;

public interface ICommodity {
	/**
	 * 获取商品的类型
	 * @return
	 */
	public List<String> getTitle();
	
	/**
	 * 	根据商品的类型获取商品
	 * @param title
	 * @param ro
	 * @return
	 */
	public List<Com> getComs(String title,RowBounds ro);
	/**
	 * 获取某个时间段的秒杀商品
	 * @param parameter
	 * @return
	 */
	public List<Map<String, Object>> getDis(String parameter);

	/**
	 * 获取销售量比较靠前面的商品
	 * @param ro
	 * @return
	 */
	public List<Map<String, Object>> getSales(RowBounds ro);

	/**
	 * 采用模糊查询的方式查找商品信息
	 * @param parameter
	 * @return
	 */
	public List<Map<String, Object>> getSearch(String parameter);

}
