package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class UpdateProductAction extends Action{
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ProductService service = new ProductServiceImpl();
		
		int prodNO = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductVO productVO = service.getProduct(prodNO);
		System.out.println("updateProductAction : " + prodNO);
		System.out.println("updateProductAction : " + productVO);
		productVO.setProdNo(prodNO);
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price").trim()));
		productVO.setFileName(request.getParameter("fileName"));
		
		
		service.updateProduct(productVO);
		
		HttpSession session = request.getSession();
		String sessionId = String.valueOf(productVO.getProdNo());
		
		if(sessionId.equals(String.valueOf(prodNO))) {
			session.setAttribute("prodVo", productVO);
		}
		
		return "redirect:/getProduct.do?prodNo="+prodNO;
	}


}
