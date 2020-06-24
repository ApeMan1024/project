package com.apeman1024.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/filter1")
public class Filter1 implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response1=(HttpServletResponse)response;
		HttpServletRequest request1=(HttpServletRequest)request;
		request1.setCharacterEncoding("utf-8");	
		response1.setHeader("Access-Control-Allow-Origin", "*");
	    response1.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response1.setHeader("Access-Control-Allow-Credentials", "true");
	    response1.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
		chain.doFilter(request1, response1);
	}
	public void init(FilterConfig fConfig) throws ServletException {}
}
