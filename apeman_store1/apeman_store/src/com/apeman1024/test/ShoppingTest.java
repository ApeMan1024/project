package com.apeman1024.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.apeman1024.Ichange.IShoppingChange;
import com.apeman1024.change.ShoppingChange;
import com.apeman1024.entity.Car;

public class ShoppingTest {
	@Test
	public void getCarTest() {
		IShoppingChange iShoppingChange=new ShoppingChange();
		List<Car> car = iShoppingChange.getCar("root");
		System.out.println(car);
	}
	
	@Test
	public void setCarTest() {
		IShoppingChange iShoppingChange=new ShoppingChange();
		boolean setCar = iShoppingChange.setCar("root", "G4X5ficrLfCtW4Bb", true);
		System.out.println(setCar);
	}
	
	@Test
	public void setBoolTest() {
		IShoppingChange iShoppingChange=new ShoppingChange();
		boolean setBool = iShoppingChange.setBool(false, "root");
		System.out.println(setBool);
	}
	
	@Test
	public void setCar1Test() {
		IShoppingChange iShoppingChange=new ShoppingChange();
		boolean setCar1 = iShoppingChange.setCar1("root", "G4X5ficrLfCtW4Bb", 2);
		System.out.println(setCar1);
	}
	
	@Test
	public void delCarTest() {
		IShoppingChange iShoppingChange=new ShoppingChange();
		boolean delCar = iShoppingChange.delCar("1");
		System.out.println(delCar);
	}
	
	@Test
	public void delCar1Test() {
		IShoppingChange iShoppingChange=new ShoppingChange();
		boolean delCar = iShoppingChange.delCar("G4X5ficrLfCtW4Bb", "root");
		System.out.println(delCar);
	}
	
	@Test
	public void getNumTest() {
		IShoppingChange iShoppingChange=new ShoppingChange();
		Integer num = iShoppingChange.getNum("root");
		System.out.println(num);
	}
}
