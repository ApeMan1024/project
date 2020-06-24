package com.apeman1024.change;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.apeman1024.Ichange.ICommodChange;
import com.apeman1024.entity.Com;
import com.apeman1024.mapper.ICommodity;
import com.apeman1024.util.Sql_util1;

public class CommodChange implements ICommodChange{

	@Override
	public List<String> getTitle() {
		SqlSession session = Sql_util1.getSession();
		try {
			List<String> title = session.getMapper(ICommodity.class).getTitle();
			return title;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Com> getComs(int num, int ye, String title) {
		SqlSession session = Sql_util1.getSession();
		try {
			RowBounds ro=new RowBounds((ye-1)*num,num);
			List<Com> coms = session.getMapper(ICommodity.class).getComs(title, ro);
			return coms;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getDis(String parameter) {
		SqlSession session = Sql_util1.getSession();
		try {
			List<Map<String, Object>> dis = session.getMapper(ICommodity.class).getDis(parameter);
			return dis;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getSales(int num) {
		SqlSession session = Sql_util1.getSession();
		try {
			RowBounds ro=new RowBounds(0,num);
			List<Map<String, Object>> sales = session.getMapper(ICommodity.class).getSales(ro);
			return sales;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getSearch(String parameter) {
		SqlSession session = Sql_util1.getSession();
		try {
			List<Map<String, Object>> search = session.getMapper(ICommodity.class).getSearch(parameter);
			search.forEach(item->{
				if(item.get("xian")==null) {
					item.put("xian", 0);
				}
			});
			return search;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
}
