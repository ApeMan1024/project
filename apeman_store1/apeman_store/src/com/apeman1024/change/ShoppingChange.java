package com.apeman1024.change;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.apeman1024.Ichange.IShoppingChange;
import com.apeman1024.entity.Car;
import com.apeman1024.mapper.IShopping;
import com.apeman1024.util.Sql_util1;

public class ShoppingChange implements IShoppingChange {

	@Override
	public List<Car> getCar(String parameter) {
		SqlSession session = Sql_util1.getSession();
		try {
			List<Car> car = session.getMapper(IShopping.class).getCar(parameter);
			return car;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean setCar(String username, String id, boolean bool) {
		SqlSession session = Sql_util1.getSession();
		try {
			Car car=new Car();
			car.setUsername(username);
			car.setId(id);
			car.setBool(bool);
			Integer setCar = session.getMapper(IShopping.class).setCar(car);
			if(setCar>0) {
				session.commit();
				return true;
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean setBool(boolean parseBoolean, String username) {
		SqlSession session = Sql_util1.getSession();
		try {
			Integer setBool = session.getMapper(IShopping.class).setBool(parseBoolean, username);
			if(setBool>0) {
				session.commit();
				return true;
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean setCar1(String username, String id, int num) {
		SqlSession session = Sql_util1.getSession();
		try {
			Car car=new Car();
			car.setId(id);
			car.setUsername(username);
			car.setNum(num);
			Integer setCar1 = session.getMapper(IShopping.class).setCar1(car);
			if(setCar1>0) {
				session.commit();
				return true;
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean delCar(String username) {
		SqlSession session = Sql_util1.getSession();
		System.out.println(username);
		try {
			Integer delCar = session.getMapper(IShopping.class).delCar(username,null);
			if(delCar>0) {
				session.commit();
				return true;
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean delCar(String id, String username) {
		SqlSession session = Sql_util1.getSession();
		try {
			Integer delCar = session.getMapper(IShopping.class).delCar(username,id);
			if(delCar>0) {
				session.commit();
				return true;
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public Integer getNum(String username) {
		SqlSession session = Sql_util1.getSession();
		try {
			Integer num = session.getMapper(IShopping.class).getNum(username);
			return num;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}
