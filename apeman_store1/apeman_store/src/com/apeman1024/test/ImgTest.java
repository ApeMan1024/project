package com.apeman1024.test;

import java.time.LocalDate;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import com.apeman1024.entity.Car;
import com.apeman1024.mapper.IImg;
import com.apeman1024.util.Sql_util1;

public class ImgTest {
	
	@Test
	public void getComTest() {
		SqlSession session = Sql_util1.getSession();
		LocalDate now = LocalDate.now();
		System.out.println(now);
		Map<String, Object> com = session.getMapper(IImg.class).getCom("1G94V547l29065wu", now);
		System.out.println(com);
	}
	
	@Test
	public void updateCarTest() {
		SqlSession session = Sql_util1.getSession();
		Car car=new Car();
		car.setId("1");
		car.setUsername("1");
		car.setNum(1);
		Integer updateCar = session.getMapper(IImg.class).updateCar(car);
		session.commit();
		session.close();
		System.out.println(updateCar);
	}
	
	@Test
	public void addCar() {
		SqlSession session = Sql_util1.getSession();
		Car car=new Car();
		car.setBool(false);
		car.setId("1");
		car.setName("1");
		car.setName("1");
		car.setQian(1.);
		car.setSrc("1");
		car.setTitle("1");
		car.setUsername("1");
		car.setXian(1.);
		car.setNum(1);
		Integer addCar = session.getMapper(IImg.class).addCar(car);
		session.commit();
		session.close();
		System.out.println(addCar);
	}
}
