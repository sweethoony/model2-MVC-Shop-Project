package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductViewAction extends Action{
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			int productNO = Integer.parseInt(request.getParameter("PROD_NO"));
					
			ProductService service = new ProductServiceImpl();
			ProductVO productVO = service.getProduct(productNO);
					
			request.setAttribute("prodVo", productVO);
			System.out.println("UpdateProductViewAction : "+productVO);		
			return "forward:/product/updateProduct.jsp";
		}


}
