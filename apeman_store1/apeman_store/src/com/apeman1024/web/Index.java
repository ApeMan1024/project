package com.apeman1024.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.apeman1024.Ichange.ICommodChange;

import com.apeman1024.change.CommodChange;
import com.apeman1024.entity.Com;




@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String p = request.getParameter("flag");
		response.setContentType("text/html;charset=utf-8");
		if(p.equals("1")) {
			ICommodChange com=new CommodChange();
			List<String> title = com.getTitle();
			List<Com> coms = com.getComs( 10,Integer.parseInt(request.getParameter("num")), title.get(0));
			List<Map<String, Object>> list1=com.getDis(request.getParameter("date"));
			List<Map<String, Object>> list2=com.getSales(Integer.parseInt(request.getParameter("lun")));
			Map<String, Object>map=new HashMap<String, Object>();
			map.put("title", title.get(0));
			map.put("obj", coms);
			List<Object> list=new ArrayList<Object>();
			list.add(title);
			list.add(map);
			list.add(list1);
			list.add(list2);
			if(!list1.isEmpty()) {
				Object time=list1.get(0).get("time");
				list.add(time);
			}
			PrintWriter writer = response.getWriter();
			writer.print(new JSONArray(list).toString());
		}
		else if(p.equals("2")){

			ICommodChange com=new CommodChange();
			List<Com> coms = com.getComs( 10,Integer.parseInt(request.getParameter("num")),request.getParameter("title"));
			Map<String, Object>map=new HashMap<String, Object>();
			map.put("title", request.getParameter("title"));
			map.put("obj", coms);
			PrintWriter writer = response.getWriter();
			writer.print(new JSONObject(map).toString());
		}
		else if(p.equals("3")) {
			ICommodChange com=new CommodChange();
			List<Map<String, Object>> list=com.getSearch(request.getParameter("sear"));
			PrintWriter writer = response.getWriter();
			writer.print(new JSONArray(list).toString());
		}
	}
	

}
