package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action{
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ProductService service = new ProductServiceImpl();
		
		int prodNO = Integer.parseInt(request.getParameter("prodNo"));
		
		Product product = service.getProduct(prodNO);
		System.out.println("updateProductAction : " + prodNO);
		System.out.println("updateProductAction : " + product);
		product.setProdNo(prodNO);
		product.setManuDate(request.getParameter("manuDate"));
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setPrice(Integer.parseInt(request.getParameter("price").trim()));
		product.setFileName(request.getParameter("fileName"));
		
		
		service.updateProduct(product);
		
		HttpSession session = request.getSession();
		String sessionId = String.valueOf(product.getProdNo());
		
		if(sessionId.equals(String.valueOf(prodNO))) {
			session.setAttribute("prodVo", product);
		}
		
		return "redirect:/getProduct.do?prodNo="+prodNO;
	}


}
