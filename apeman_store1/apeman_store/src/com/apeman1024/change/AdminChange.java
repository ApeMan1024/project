package com.apeman1024.change;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.apeman1024.Ichange.IAminChange;
import com.apeman1024.entity.Admin;
import com.apeman1024.entity.Card;
import com.apeman1024.entity.Com;
import com.apeman1024.entity.Ping;
import com.apeman1024.mapper.IAdministrator;
import com.apeman1024.util.Md5;
import com.apeman1024.util.Sql_util1;

public class AdminChange implements IAminChange{
	public Admin pd(Admin admin) {
		SqlSession session = Sql_util1.getSession();
		String md5 = Md5.getMD5(admin.getPassword());
		admin.setPassword(md5);
		Admin pd = session.getMapper(IAdministrator.class).pd(admin);
		session.close();
		return pd;
	}

	@Override
	public Boolean cong(Card card) {
		SqlSession session = Sql_util1.getSession();
		Integer cong = session.getMapper(IAdministrator.class).cong(card);
		session.commit();
		session.close();
		if(cong==null)return false;
		else return true;
	}

	@Override
	public Boolean getID(String guid) {
		SqlSession session = Sql_util1.getSession();
		Boolean id = session.getMapper(IAdministrator.class).getID(guid);
		if(id!=null&&id) {
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> getCom(int start, int len) {
		LocalDate date = LocalDate.now();
		RowBounds rowBounds=new RowBounds(start,len);
		List<Map<String, Object>> com = Sql_util1.getSession().getMapper(IAdministrator.class).getCom(date,rowBounds);
		com.forEach(item->{
			item.put("bool", false);
			if(item.get("xian")==null) {
				item.put("xian", 0);
			}
		});
		return com;
	}
	public List<Ping> getPing(int start, int len){
		RowBounds ro=new RowBounds(start,len);
		List<Ping> ping = Sql_util1.getSession().getMapper(IAdministrator.class).getPing(ro);
		return ping;
	}

	@Override
	public boolean delPing(Ping ping) {
		SqlSession session = Sql_util1.getSession();
		Integer delPing = session.getMapper(IAdministrator.class).delPing(ping);
		session.commit();
		session.close();
		if(delPing==0||delPing==null)return false;
		return true;
	}

	@Override
	public boolean addCom(Map<String, Object> map) {
		SqlSession session = Sql_util1.getSession();
		IAdministrator mapper = session.getMapper(IAdministrator.class);
		try {
			Integer updateTitle =mapper.updateTitle(String.valueOf(map.get("title")));

			if(updateTitle==0) {
				Map<String, Object>map1=new HashMap<String, Object>();
				map1.put("title",String.valueOf(map.get("title")));
				map1.put("num", 1);
				map1.put("count", map.get("count"));
				mapper.inserClassify(map1);

			}
			Com com=new Com();
			Map<String,String>map3=new HashMap<String, String>();
			com.setCount(0);
			com.setId(String.valueOf( map.get("id")));
			com.setName(String.valueOf(map.get("name")));
			com.setQian(Double.valueOf(String.valueOf(map.get("qian"))));
			com.setSrc(String.valueOf(map.get("src")));
			com.setTitle(String.valueOf(map.get("title")));
			Integer addCom = mapper.addCom(com);
			map3.put("id", String.valueOf(map.get("id")));
			map3.put("img", String.valueOf(map.get("img")));
			Integer inserImg = mapper.inserImg(map3);
			if(addCom!=0&&inserImg!=0) {
				if(!Double.valueOf(String.valueOf(map.get("xian"))).equals(0.)) {
					Map<String, Object>map2=new HashMap<>();
					map2.put("id", map.get("id"));
					map2.put("name", map.get("name"));
					map2.put("xian", map.get("xian"));
					map2.put("qian", map.get("qian"));
					map2.put("src", map.get("src"));
					Integer insertDiscount1 = mapper.insertDiscount(map2);
					if(insertDiscount1!=0)return true;
				}
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
	public boolean delCom(Com com) {
		SqlSession session = Sql_util1.getSession();
		try {
			IAdministrator mapper = session.getMapper(IAdministrator.class);
			Integer delCom = mapper.delCom(com);
			if(delCom>0) {
				Integer updateCom = mapper.updateCom(com);
				Integer delClass = mapper.delClass(com);
				session.commit();
				if(updateCom>0) return true;
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
	public boolean addMiao(String data, String time, List<Map<String, Object>> zhuan) {
		SqlSession session = Sql_util1.getSession();
		try {
			IAdministrator mapper = session.getMapper(IAdministrator.class);
			Boolean pdMiao = mapper.pdMiao(data, time);
			Boolean boolean1=true;
			if(pdMiao==null) {
				Integer tianMiao = mapper.tianMiao(data, time);
				if(tianMiao<=0)boolean1=false;
			}
			if(boolean1) {
				for (Map<String, Object> map : zhuan) {
					Integer setDiscount = mapper.setDiscount(map);
					if(setDiscount==0) {
						Integer addMiao = mapper.addMiao(map);
						if(addMiao<=0) {
							boolean1=false;
							break;
						}
					}
				}
			}
			session.commit();
			if(!boolean1) {
				session.rollback();
				return false;
			}
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
