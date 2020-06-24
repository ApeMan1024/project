package com.apeman1024.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.apeman1024.entity.Car;

public interface IShopping {
	/**
	 * 根据用户名获取购物车信息
	 * @param parameter
	 * @return
	 */
	public List<Car> getCar(String parameter);

	/**
	 * 设置用户购物车中的商品是否已经被选中
	 * @param car
	 * @return
	 */
	public Integer setCar(Car car);
	/**
	 * 根据用户名全选用户购物车列表，或不全选
	 * @param parseBoolean
	 * @param username
	 * @return
	 */
	public Integer setBool(@Param("bool") boolean parseBoolean,@Param("username") String username);

	/**
	 * 设置购物车中某个商品的数量
	 * @param car
	 * @return
	 */
	public Integer setCar1(Car car);
	
	/**
	 * 删除购物车信息
	 * @param username
	 * @return
	 */
	public Integer delCar(@Param("username") String username,@Param("id") String id);
	
	/**
	 * 统计用户购物车中商品的数量
	 * @param username
	 * @return
	 */
	public Integer getNum(String username);

}
