package com.apeman1024.web;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.apeman1024.Ichange.IOpinionChange;

import com.apeman1024.change.OpinionChange;
import com.apeman1024.entity.Ping;



@WebServlet("/remark")
public class Remark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Remark() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String me = request.getParameter("flag");
		if(me.equals("1")) {
			int pa = Integer.parseInt(request.getParameter("num"));//获取已经当前显示的条数
			IOpinionChange opinion=new OpinionChange();
			int num=5;
			List<Ping> list=opinion.select(pa,num);
			Integer count = opinion.count();
			JSONArray jsonArray = new JSONArray(list);
			Map<String, String>map=new HashMap<String, String>();
			map.put("list",jsonArray.toString());
			map.put("count", count.toString());
			JSONObject obj = new JSONObject(map);
			PrintWriter writer = response.getWriter();
			writer.print(obj.toString());
		}else if(me.equals("2")) {
			Ping ping=new Ping();
			ping.setId(Integer.parseInt(request.getParameter("id")));
			ping.setHead(request.getParameter("head"));
			ping.setUsername(request.getParameter("username"));
			ping.setNei(request.getParameter("nei"));
			ping.setTime(request.getParameter("time"));
			IOpinionChange opinion=new OpinionChange();
			Ping p=opinion.addPing(ping);
			PrintWriter writer = response.getWriter();
			if(p==null) {
				writer.print(false);
			}else {
				writer.print(true);
			}
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
