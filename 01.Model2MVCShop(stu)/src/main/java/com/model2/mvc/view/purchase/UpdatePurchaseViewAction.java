package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdatePurchaseViewAction extends Action {  //업데이트purchasejsp로

	public UpdatePurchaseViewAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));

		System.out.println("1 UpdatePurchaseViewAction : " + tranNo);

		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = service.getPurchase(tranNo);
		
		System.out.println("2 UpdatePurchaseViewAction : " + purchaseVO);

		request.setAttribute("purchaseVO", purchaseVO);

		return "forward:/purchase/updatePurchase.jsp";
	}
}
