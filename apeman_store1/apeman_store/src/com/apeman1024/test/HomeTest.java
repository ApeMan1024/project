package com.apeman1024.test;

import org.junit.jupiter.api.Test;

import com.apeman1024.Ichange.IHomeChange;
import com.apeman1024.change.HomeChange;
import com.apeman1024.entity.User;

public class HomeTest {
	@Test
	public void registerTest() {
		IHomeChange iHome=new HomeChange();
		User user=new User();
		user.setAddress("1");
		user.setCard("1");
		user.setEmail("1");
		user.setHead("1");
		user.setPass1("1");
		user.setPass2("1");
		user.setPhone("1");
		user.setUsername("1");
		boolean register = iHome.register(user);
		System.out.println(register);
	}
	
	@Test
	public void loginTest() {
		IHomeChange iHome=new HomeChange();
		User user=new User();
		user.setPass1("1");
		user.setUsername("1");
		User login = iHome.login(user);
		System.out.println(login);
	}
	
	@Test
	public void perfectTest() {
		IHomeChange iHome=new HomeChange();
		User user=new User();
		user.setAddress("1");
		user.setCard("2");
		user.setEmail("1");
		user.setHead("1");
		user.setPass2("1");
		user.setPhone("1");
		user.setUsername("1");
		User perfect = iHome.perfect(user);
		System.out.println(perfect);
	}
}
