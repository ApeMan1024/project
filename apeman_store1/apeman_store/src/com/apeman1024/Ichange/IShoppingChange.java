package com.apeman1024.Ichange;

import java.util.List;

import com.apeman1024.entity.Car;

public interface IShoppingChange {
	
	public List<Car> getCar(String parameter);

	public boolean setCar(String username, String id,boolean bool);

	public boolean setBool(boolean parseBoolean,String username);

	public boolean setCar1(String username, String id, int num);

	public boolean delCar(String username);
	
	public boolean delCar(String id ,String username);
	
	public Integer getNum(String username);

}
