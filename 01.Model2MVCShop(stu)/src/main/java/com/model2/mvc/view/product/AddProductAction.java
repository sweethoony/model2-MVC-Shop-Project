package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class AddProductAction extends Action{
	
	@Override
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		ProductVO productVO = new ProductVO();
		productVO.setManuDate(request.getParameter("manuDate").replace("-", ""));
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		
		System.out.println(productVO);
		
		ProductService service = new ProductServiceImpl();
		service.addProduct(productVO);
		System.out.println("addProductAction.java");
		
		request.setAttribute("productVO", productVO);
		
		return "forward:/product/addProduct.jsp";

	}

}
