package com.model2.mvc.view.product;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action{
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int productNO = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("GetProductAction  :: " + productNO);
		
		ProductService service = new ProductServiceImpl();
		Product prodVo = service.getProduct(productNO);
		String menu = request.getParameter("menu");
		
		request.setAttribute("prodVo", prodVo);
		
		request.setAttribute("menu", menu);
			
		if(menu != null && !menu.isEmpty()) {
		if (menu.equals("search")) {
		    String history = "";
		    Cookie[] cookies = request.getCookies();

		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if (cookie.getName().equals("history")) {
		                history = cookie.getValue();
		                break;
		            }
		        }
		    }

		    if (history.isEmpty()) {
		        history = String.valueOf(productNO);
		    } else if (!Arrays.asList(history).contains(String.valueOf(productNO))) {
		        history += ":"+productNO;
		    }
		    
		    
		    
		    Cookie historyCookie = new Cookie("history", history);
		    historyCookie.setMaxAge(60 * 60);
		    response.addCookie(historyCookie);

		    return "forward:/product/getProduct.jsp";
		}else {
						
		return "forward:/product/updateProduct.jsp";
		}

	}else {
		return "forward:/product/updateProduct.jsp";
	}
	}
}
