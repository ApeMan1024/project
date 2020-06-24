package com.apeman1024.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Sql_util {
	private static  String url="jdbc:mysql://localhost:3306/store1?useUnicode=true&amp;characterEncoding=utf8";//数据库url
	//数据库账号
	private static String user="root";
	//数据库密码
	private static String password="root";
	
	private static Connection conn;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() {
		return conn;
	}
	
}
