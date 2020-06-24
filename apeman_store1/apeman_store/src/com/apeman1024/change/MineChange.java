package com.apeman1024.change;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.apeman1024.Ichange.IMineChange;
import com.apeman1024.entity.Indent;
import com.apeman1024.mapper.IMine;
import com.apeman1024.util.Sql_util1;

public class MineChange implements IMineChange {

	@Override
	public List<Indent> getIndent(String username) {
		SqlSession session = Sql_util1.getSession();
		try {
			List<Indent> indent = session.getMapper(IMine.class).getIndent(username);
			return indent;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean delIndent(String username, String number) {
		SqlSession session = Sql_util1.getSession();
		try {
			Integer delIndent = session.getMapper(IMine.class).delIndent(username, number);
			if(delIndent>0) {
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
	public boolean del(String username) {
		SqlSession session = Sql_util1.getSession();
		try {
			Integer del = session.getMapper(IMine.class).del(username);
			if(del>0) {
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

}
