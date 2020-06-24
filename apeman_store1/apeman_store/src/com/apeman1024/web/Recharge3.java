package com.apeman1024.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.nimbus.AbstractRegionPainter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apeman1024.Ichange.IAminChange;

import com.apeman1024.change.AdminChange;
import com.apeman1024.entity.Com;
import com.apeman1024.entity.Ping;

import com.apeman1024.util.Rand;


@WebServlet("/recharge3")
public class Recharge3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Recharge3() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String pa = request.getParameter("flag");
		if(pa.equals("1")) {
			try {
				JSONObject obj=new JSONObject(request.getParameter("obj"));
				IAminChange ia=new AdminChange();
				Com com=new Com();
				com.setId(obj.getString("id"));
				com.setTitle(obj.getString("title"));
				boolean bool=ia.delCom(com);
				response.getWriter().print(bool);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else if(pa.equals("2")) {
			IAminChange ia=new AdminChange();
			int start=0,len=10;
			List<Map<String, Object>> com = ia.getCom(start, len);
			response.getWriter().print(new JSONArray(com).toString());
		}
		else if(pa.equals("3")) {
			IAminChange ia=new AdminChange();
			int start=Integer.parseInt(request.getParameter("start")),len=Integer.parseInt(request.getParameter("len"));
			List<Map<String, Object>>list=ia.getCom(start,len);
			response.getWriter().print(new JSONArray(list).toString());
		}
		else if(pa.equals("4")) {
			try {
				JSONObject obj=new JSONObject(request.getParameter("json"));
				List<Map<String, Object>> zhuan = zhuan(obj.getString("data"), obj.getString("time"),new JSONArray(obj.getString("arr4")));
				IAminChange ia=new AdminChange();
				boolean bool=ia.addMiao(obj.getString("data"),obj.getString("time"),zhuan);
				response.getWriter().print(bool);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public List<Map<String, Object>> zhuan(String data,String time,JSONArray arr){
		List<Map<String , Object>>list=new ArrayList<Map<String,Object>>();
		Map<String , Object>map=null;
		for(int i=0;i<arr.length();i++) {
			map=new HashMap<String, Object>();
			try {
				JSONObject json = arr.getJSONObject(i);
				map.put("id",json.getString("id"));
				map.put("qian",json.getDouble("qian"));
				map.put("xian",json.getDouble("xian"));
				map.put("src",json.getString("src"));
				map.put("name",json.getString("name"));
				map.put("data",data);
				map.put("time",time);
				list.add(map);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
