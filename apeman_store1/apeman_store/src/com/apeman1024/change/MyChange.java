package com.apeman1024.change;

import org.apache.ibatis.session.SqlSession;

import com.apeman1024.Ichange.IMyChange;
import com.apeman1024.entity.Card;
import com.apeman1024.entity.Indent;
import com.apeman1024.mapper.IMy;
import com.apeman1024.util.Md5;
import com.apeman1024.util.Sql_util1;

public class MyChange implements IMyChange {

	@Override
	public boolean pd(String card, String pass2, String username) {
		
		SqlSession session = Sql_util1.getSession();
		try {
			Card card2=new Card();
			card2.setCard(card);
			card2.setPass(Md5.getMD5(pass2));
			card2.setUsername(username);
			Card pd = session.getMapper(IMy.class).pd(card2);
			if(pd!=null)return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public Double getBalance(String card, String pass2, String username) {
		SqlSession session=Sql_util1.getSession();
		try {
			Card card2=new Card();
			card2.setCard(card);
			card2.setPass(Md5.getMD5(pass2));
			card2.setUsername(username);
			Double balance = session.getMapper(IMy.class).getBalance(card2);
			return balance;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean addIndent(Indent ind) {
		SqlSession session = Sql_util1.getSession();
		try {
			IMy mapper = session.getMapper(IMy.class);
			Integer updateCom = mapper.updateCom(ind);
			if(updateCom>0) {
				Integer addIndent = mapper.addIndent(ind);
				if(addIndent>0) {
					session.commit();
					return true;
				}
				session.rollback();
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
	public boolean expen(String username, String card, double balance1) {
		SqlSession session = Sql_util1.getSession();
		try {
			IMy mapper = session.getMapper(IMy.class);
			Card card2=new Card();
			card2.setCard(card);
			card2.setUsername(username);
			card2.setBalance(String.valueOf( balance1));
			Integer expen = mapper.expen(card2);
			if(expen>0) {
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
	public boolean pd1(String guid) {
		SqlSession session = Sql_util1.getSession();
		try {
			Boolean pd1 = session.getMapper(IMy.class).pd1(guid);
			if(pd1!=null)return true;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}

}
