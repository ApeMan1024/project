package com.apeman1024.mapper;

import com.apeman1024.entity.Card;
import com.apeman1024.entity.User;

public interface IHome {

	public Boolean pdUser(User user);
	
	public Integer register(User user);

	public User login(User user);

	public Integer perfect(User user);
	
	public Integer insertCard(Card card);

}
