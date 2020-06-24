package com.apeman1024.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apeman1024.Ichange.IMyChange;
import com.apeman1024.Ichange.IShoppingChange;

import com.apeman1024.change.MyChange;
import com.apeman1024.change.ShoppingChange;
import com.apeman1024.entity.Indent;

import com.apeman1024.util.Rand;


@WebServlet("/kink")
public class Kink extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Kink() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String pa = request.getParameter("flag");
		response.setContentType("text/html;charset=utf-8");
		if(pa.equals("1")) {
			String user = request.getParameter("user");
			String list_2 = request.getParameter("list_2");
			try {
				JSONObject obj=new JSONObject(user);
				IMyChange my=new MyChange();
				boolean bool=my.pd( obj.getString("card"),obj.getString("pass2"),obj.getString("username"));
				List<Indent> indents = getIndents(list_2,obj);


				double balance1=0;
				for(Indent ind :indents) {
					balance1+=ind.getPrice();
				}
				if(bool) {
					double balance=my.getBalance( obj.getString("card"),obj.getString("pass2"),obj.getString("username"));
					if(balance<balance1) {
						//余额不足
						response.getWriter().print(2);
					}else {
						for(Indent ind :indents) {
							String guid = Rand.getGUID();
							boolean bool1=my.pd1(guid);
							while(bool1) {
								guid = Rand.getGUID();
								bool1=my.pd1(guid);
							}
							ind.setNumber(guid);
							boolean addIndent = my.addIndent(ind);
							if(addIndent) {
								IShoppingChange  shop=new ShoppingChange();
								shop.delCar(ind.getId(), ind.getUsername());
							}
						}
						my.expen(obj.getString("username"),obj.getString("card"),balance1);
						response.getWriter().print(3);
					}
				}else {
					//卡号或密码错误
					response.getWriter().print(1);
				}
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
	public List<Indent> getIndents(String json,JSONObject obj){
		try {
			JSONArray jsonArray = new JSONArray(json);
			List<Indent>list=new ArrayList<Indent>();
			Indent indent=null;
			for(int i=0;i<jsonArray.length();i++) {
				indent=new Indent();
				JSONObject jsonObject = new JSONObject(jsonArray.getString(i));
				indent.setId(jsonObject.getString("id"));
				indent.setName(jsonObject.getString("name"));
				indent.setNum(jsonObject.getInt("num"));
				indent.setSrc(jsonObject.getString("src"));
				indent.setTitle(jsonObject.getString("title"));
				indent.setUsername(jsonObject.getString("username"));
				indent.setAddress(obj.getString("address"));
				if(jsonObject.getDouble("xian")!=0) {
					indent.setPrice(jsonObject.getDouble("xian")*jsonObject.getInt("num"));
				}else {
					indent.setPrice(jsonObject.getDouble("qian")*jsonObject.getInt("num"));
				}
				list.add(indent);
			}
			if(!list.isEmpty())return list;
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
