package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	//.properties에 지정된 key, value에 알맞는 것을 확인하고 그에 맞는 Action이란 Bean을 뽑아낸다.
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources");
		mapper=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		//해당 url 파싱하여 actionmapping에 기재한 /*.do로 변경될 것이다.
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		System.out.println("\nActionServlet - service 시작\npath"+ path);
		
		try{  // 알맞는 Action을 가져왔다면 그에 맞는 행동을 취하도록 한다.
			Action action = mapper.getAction(path);
			
			action.setServletContext(getServletContext());//has a 관계 가질필요 없다. -> listUserAction.java로
			
			
			String resultPage=action.execute(request, response);
			String result=resultPage.substring(resultPage.indexOf(":")+1);
			
			System.out.println("resultPage : " + resultPage);
			System.out.println("result : " + result + "\nActionServlet - service 끝\n");
			
			//응답 방식을 client에 따라 전달 HttpUtil에 있는 내용은 forward, redirect를 판단하여 행동하도록 구현
			if(resultPage.startsWith("forward:")) {
				HttpUtil.forward(request, response, result);}
			else {
				HttpUtil.redirect(response, result);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}