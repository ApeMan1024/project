package com.apeman1024.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Holder;

import org.json.JSONObject;

import com.apeman1024.Ichange.IHomeChange;

import com.apeman1024.change.HomeChange;
import com.apeman1024.entity.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		response.setContentType("text/html;charset=utf-8");
		if(id.equals("1")) {
			User user=new User();
			user.setUsername(request.getParameter("user"));
			user.setPass1(request.getParameter("pass"));
			user.setEmail(request.getParameter("email"));
			IHomeChange home = new HomeChange();
			boolean register = home.register(user);
			PrintWriter writer = response.getWriter();
			writer.print(register);
		}
		else if(id.equals("2")) {
			User user=new User();
			user.setUsername(request.getParameter("username"));
			user.setPass1(request.getParameter("password"));
			IHomeChange home = new HomeChange();
			User login = home.login(user);
			PrintWriter writer = response.getWriter();
			if(login==null) {
				writer.print(false);
			}else {
				JSONObject obj=new JSONObject(login);
				writer.print(obj.toString());
			}
		}
	}

}
