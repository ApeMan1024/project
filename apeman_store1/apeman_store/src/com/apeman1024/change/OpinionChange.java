package com.apeman1024.change;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.apeman1024.Ichange.IOpinionChange;
import com.apeman1024.entity.Ping;
import com.apeman1024.mapper.IOpinion;
import com.apeman1024.util.Sql_util1;

public class OpinionChange implements IOpinionChange {

	@Override
	public List<Ping> select(int pa, int num) {
		SqlSession session = Sql_util1.getSession();
		try {
			RowBounds ro=new RowBounds(pa,num);
			List<Ping> select = session.getMapper(IOpinion.class).select(ro);
			return select;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public Ping addPing(Ping ping) {
		SqlSession session = Sql_util1.getSession();
		try {
			Integer addPing = session.getMapper(IOpinion.class).addPing(ping);
			if(addPing>0) {
				session.commit();
				return ping;
			}
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public Integer count() {
		SqlSession session = Sql_util1.getSession();
		try {
			Integer count = session.getMapper(IOpinion.class).count();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}
