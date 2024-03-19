package com.model2.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class RequestFilter implements Filter{   // 서블릿으로 가기전에 요청을 가로채어 해당 요청 문자 인코딩을 설정

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,	FilterChain arg2) 
																						throws IOException, ServletException {
		arg0.setCharacterEncoding("euc-kr");
		arg2.doFilter(arg0, arg1);
	}

	public void destroy() {
	}
}