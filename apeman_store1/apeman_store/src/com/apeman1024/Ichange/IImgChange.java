package com.apeman1024.Ichange;

import java.util.Map;

import com.apeman1024.entity.Car;

public interface IImgChange {
	Map<String, Object> getCom(String id);

	boolean addCar(Car car);
}
