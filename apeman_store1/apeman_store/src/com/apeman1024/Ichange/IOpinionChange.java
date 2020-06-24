package com.apeman1024.Ichange;

import java.util.List;

import com.apeman1024.entity.Ping;

public interface IOpinionChange {

	public List<Ping> select(int pa, int num);

	public Ping addPing(Ping ping);
	
	public Integer count();

}
