package com.apeman1024.web;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apeman1024.Ichange.IShoppingChange;

import com.apeman1024.change.ShoppingChange;
import com.apeman1024.entity.Car;



@WebServlet("/cle")
public class Cle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Cle() {
        super();
        
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
			IShoppingChange  shop=new ShoppingChange();
			List<Car> list=shop.getCar(request.getParameter("username"));
			PrintWriter writer = response.getWriter();
			writer.print(new JSONArray(list).toString());
		}
		else if(pa.equals("2")) {
			IShoppingChange  shop=new ShoppingChange();
			boolean bool=shop.setCar(request.getParameter("username"),request.getParameter("id"),Boolean.parseBoolean(request.getParameter("bool")));
			PrintWriter writer = response.getWriter();
			writer.print(bool);
		}
		else if(pa.equals("3")) {
			IShoppingChange  shop=new ShoppingChange();
			try {
				JSONObject jsonObject = new JSONObject(request.getParameter("user"));
				boolean bool=shop.setBool(Boolean.parseBoolean(request.getParameter("bool")),jsonObject.getString("username"));
				PrintWriter writer = response.getWriter();
				writer.print(bool);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(pa.equals("4")) {
			IShoppingChange  shop=new ShoppingChange();
			boolean bool=shop.setCar1(request.getParameter("username"),request.getParameter("id"),Integer.parseInt(request.getParameter("num")));
			PrintWriter writer = response.getWriter();
			writer.print(bool);
		}
		else if(pa.equals("5")) {
			IShoppingChange  shop=new ShoppingChange();
			boolean bool=shop.delCar(request.getParameter("username"));
			response.getWriter().print(bool);
		}
		else if(pa.equals("6")) {
			IShoppingChange  shop=new ShoppingChange();
			boolean bool=shop.delCar(request.getParameter("id"),request.getParameter("username"));
			response.getWriter().print(bool);
		}
		else if(pa.equals("num")) {
			String username = request.getParameter("username");
			IShoppingChange  shop=new ShoppingChange();
			Integer num = shop.getNum(username);
			response.getWriter().print(num);
		}
	}

}
