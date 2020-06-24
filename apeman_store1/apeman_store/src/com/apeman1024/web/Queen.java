package com.apeman1024.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.apeman1024.Ichange.IAminChange;

import com.apeman1024.change.AdminChange;
import com.apeman1024.entity.Admin;



@WebServlet("/queen")
public class Queen extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password=request.getParameter("password");
		Admin admin=new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		IAminChange adminChange=new AdminChange();
		Admin pd = adminChange.pd(admin);
		if(pd==null) {
			response.getWriter().print(false);
		}
		else {
			response.getWriter().print(new JSONObject(pd).toString());
		}
	}

}
