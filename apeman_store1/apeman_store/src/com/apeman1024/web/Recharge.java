package com.apeman1024.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apeman1024.Ichange.IAminChange;

import com.apeman1024.change.AdminChange;
import com.apeman1024.entity.Card;
import com.apeman1024.entity.Ping;

import com.apeman1024.util.Rand;


@WebServlet("/recharge")
public class Recharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String pa = request.getParameter("flag");
		response.setContentType("text/html;charset=utf-8");
		if(pa.equals("1")) {
			String username = request.getParameter("username");
			String card = request.getParameter("card");
			String qian = request.getParameter("qian");
			Card card1=new Card();
			card1.setBalance(qian);
			card1.setCard(card);
			card1.setUsername(username);
			IAminChange admin=new AdminChange();
			boolean cong2 = admin.cong(card1);
			response.getWriter().print(cong2);
		}
		else if(pa.equals("2")) {
			String guid = Rand.getGUID();
			IAminChange ia = new AdminChange();
			boolean bool=ia.getID(guid);
			while(bool) {
				guid = Rand.getGUID();
				bool=ia.getID(guid);
			}
			response.getWriter().print(guid);
		}
		else if(pa.equals("3")) {
			IAminChange ia = new AdminChange();
			int start=0,len=12;
			List<Map<String, Object>> list=ia.getCom(start,len);
			
			response.getWriter().print(new JSONArray(list).toString()); 
		}
		else if(pa.equals("4")) {
			IAminChange ia = new AdminChange();
			List<Map<String, Object>> list=ia.getCom(Integer.parseInt(request.getParameter("start")), Integer.parseInt(request.getParameter("len")));
			

			response.getWriter().print(new JSONArray(list).toString());
		}
		else if(pa.equals("5")) {
			IAminChange ia = new AdminChange();
			int start=0,len=10;
			List<Ping> list=ia.getPing(start,len);
			response.getWriter().print(new JSONArray(list).toString());
			
		}
		else if(pa.equals("6")) {
			IAminChange ia = new AdminChange();
			List<Ping> list=ia.getPing(Integer.parseInt(request.getParameter("start")),Integer.parseInt(request.getParameter("len")));
			response.getWriter().print(new JSONArray(list).toString());
		}
		else if(pa.equals("7")) {
			IAminChange ia = new AdminChange();
			Ping ping=new Ping();
			try {
				JSONObject obj=new JSONObject(request.getParameter("obj"));
				ping.setHead(obj.getString("head"));
				ping.setId(obj.getInt("id"));
				ping.setNei(obj.getString("nei"));
				ping.setTime(obj.getString("time"));
				ping.setUsername(obj.getString("username"));
				boolean bool=ia.delPing(ping);
				response.getWriter().print(bool);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
	}

}
