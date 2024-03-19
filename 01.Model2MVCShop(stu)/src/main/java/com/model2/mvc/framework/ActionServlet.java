package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	//.properties�� ������ key, value�� �˸´� ���� Ȯ���ϰ� �׿� �´� Action�̶� Bean�� �̾Ƴ���.
	
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
		//�ش� url �Ľ��Ͽ� actionmapping�� ������ /*.do�� ����� ���̴�.
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		System.out.println("\nActionServlet - service ����\npath"+ path);
		
		try{  // �˸´� Action�� �����Դٸ� �׿� �´� �ൿ�� ���ϵ��� �Ѵ�.
			Action action = mapper.getAction(path);
			
			action.setServletContext(getServletContext());//has a ���� �����ʿ� ����. -> listUserAction.java��
			
			
			String resultPage=action.execute(request, response);
			String result=resultPage.substring(resultPage.indexOf(":")+1);
			
			System.out.println("resultPage : " + resultPage);
			System.out.println("result : " + result + "\nActionServlet - service ��\n");
			
			//���� ����� client�� ���� ���� HttpUtil�� �ִ� ������ forward, redirect�� �Ǵ��Ͽ� �ൿ�ϵ��� ����
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