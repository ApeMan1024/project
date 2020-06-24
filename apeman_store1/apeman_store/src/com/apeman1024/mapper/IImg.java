package com.apeman1024.mapper;

import java.time.LocalDate;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.apeman1024.entity.Car;

public interface IImg {

	Map<String, Object> getCom(@Param("id") String id,@Param("data") LocalDate data);

	Integer updateCar(Car car);
	
	Integer addCar(Car car);

}
