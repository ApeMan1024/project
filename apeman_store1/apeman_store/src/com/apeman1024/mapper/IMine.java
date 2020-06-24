package com.apeman1024.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.apeman1024.entity.Indent;

public interface IMine {

	/**
	 * 根据用户名获取订单列表	
	 * @param username
	 * @return
	 */
	List<Indent> getIndent(String username);

	
	/**
	 * 根据用户名以及商品编号，删除用户订单列表中的一个商品
	 * @param username
	 * @param number
	 * @return
	 */
	Integer delIndent(@Param("username") String username,@Param("number") String number);

	/**
	 * 根据用户名清空用户订单列表
	 * @param username
	 * @return
	 */
	Integer del(String username);

}
