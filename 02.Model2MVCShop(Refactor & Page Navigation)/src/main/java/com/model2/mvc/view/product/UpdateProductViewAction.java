package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductViewAction extends Action{
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			int productNO = Integer.parseInt(request.getParameter("PROD_NO"));
					
			ProductService service = new ProductServiceImpl();
			Product product = service.getProduct(productNO);
					
			request.setAttribute("prodVo", product);
			System.out.println("UpdateProductViewAction : "+product);		
			return "forward:/product/updateProduct.jsp";
		}


}
