package com.apeman1024.change;

import org.apache.ibatis.session.SqlSession;

import com.apeman1024.Ichange.IHomeChange;
import com.apeman1024.entity.Card;
import com.apeman1024.entity.User;
import com.apeman1024.mapper.IHome;
import com.apeman1024.util.Md5;
import com.apeman1024.util.Sql_util1;

public class HomeChange implements IHomeChange {

	
	@Override
	public boolean register(User user) {
		SqlSession session = Sql_util1.getSession();
		try {
			IHome mapper = session.getMapper(IHome.class);
			user.setPass1(Md5.getMD5(user.getPass1()));
			Boolean pdUser = mapper.pdUser(user);
			if(pdUser==null) {
				Integer register = mapper.register(user);
				if(register>0) {
					session.commit();
					return true;
				}else {
					session.rollback();
				}
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
	public User login(User user) {
		SqlSession session = Sql_util1.getSession();
		try {
			IHome mapper = session.getMapper(IHome.class);
			user.setPass1(Md5.getMD5(user.getPass1()));
			User login = mapper.login(user);
			
			if (login!=null) {
				login.setPass1("");
				login.setPass2("");
			}
			return login;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return null;
	}

	@Override
	public User perfect(User user) {
		SqlSession session = Sql_util1.getSession();
		try {
			IHome mapper = session.getMapper(IHome.class);
			user.setPass2(Md5.getMD5(user.getPass2()));
			Integer perfect = mapper.perfect(user);
			if(perfect>0) {
				Card card=new Card();
				card.setUsername(user.getUsername());
				card.setPass(user.getPass2());
				card.setCard(user.getCard());
				Integer insertCard = mapper.insertCard(card);
				if(insertCard>0) {
					session.commit();
					user.setPass2("");
					
				}
				else {
					session.rollback();
				}
			}
			return user;
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return null;
	}

}
