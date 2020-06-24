package com.apeman1024.change;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.apeman1024.Ichange.IImgChange;
import com.apeman1024.entity.Car;
import com.apeman1024.mapper.IImg;
import com.apeman1024.util.Sql_util1;

public class ImgChange implements IImgChange {

	

	@Override
	public Map<String, Object> getCom(String id) {
		SqlSession session = Sql_util1.getSession();
		LocalDate now = LocalDate.now();
		try {
			Map<String, Object> com = session.getMapper(IImg.class).getCom(id, now);
			if(com!=null) {
				Double dou=(Double)com.get("xian");
				com.put("num", 1);
				if(dou==null) {
					com.put("xian", 0);
				}
			}
			return com;
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean addCar(Car car) {
		SqlSession session = Sql_util1.getSession();
		try {
			IImg mapper = session.getMapper(IImg.class);
			Integer updateCar = mapper.updateCar(car);
			if(updateCar==0) {
				Integer addCar = mapper.addCar(car);
				if(addCar>0) {
					session.commit();
					return true;
				}
			}
			session.commit();
			return true;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return false;
	}

}
