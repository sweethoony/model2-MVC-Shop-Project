package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class AddPurchaseViewAction extends Action{

	public AddPurchaseViewAction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String prodNoString = request.getParameter("prod_no").trim();
		int productNO = Integer.parseInt(prodNoString);
		System.out.println("1 AddPurchaseViewAction : " + productNO);
		
		ProductService service = new ProductServiceImpl();
		ProductVO prodVo = service.getProduct(productNO);
		
		System.out.println("2 AddPurchaseViewAction : " + prodVo);
		
		request.setAttribute("prod_no", prodVo);
		
		return "forward:/purchase/addPurchaseView.jsp";
		
	}

}
