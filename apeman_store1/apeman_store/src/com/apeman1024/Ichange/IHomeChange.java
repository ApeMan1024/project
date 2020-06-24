package com.apeman1024.Ichange;

import com.apeman1024.entity.User;

public interface IHomeChange {
	public boolean register(User user);

	public User login(User user);

	public User perfect(User user);
}
