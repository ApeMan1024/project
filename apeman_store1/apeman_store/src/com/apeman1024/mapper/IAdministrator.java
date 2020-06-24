package com.apeman1024.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.apeman1024.entity.Admin;
import com.apeman1024.entity.Card;
import com.apeman1024.entity.Com;
import com.apeman1024.entity.Ping;

public interface IAdministrator {

	Admin pd(Admin admin);

	Integer cong(Card card);

	Boolean getID(String guid);

	Integer addCom(Com com);
	
	Integer updateTitle(String title);
	
	Integer inserClassify(Map<String, Object>map);

	Integer inserImg(Map<String, String>map);
	
	Integer insertDiscount(Map<String, Object>map);
	
	List<Map<String, Object>> getCom(LocalDate data,RowBounds ro);

	List<Ping> getPing(RowBounds ro);

	Integer delPing(Ping ping);

	Integer delCom(Com com);
	
	Integer updateCom(Com com);
	
	Integer delClass(Com com);

	Boolean pdMiao(@Param("data") String data,@Param("time") String time);
	
	Integer tianMiao(@Param("data") String data,@Param("time") String time);
	
	Integer setDiscount(Map<String, Object>map);
	
	Integer addMiao(Map<String, Object>map);

	

}
