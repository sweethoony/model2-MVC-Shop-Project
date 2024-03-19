package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction extends Action{  //구매상태코드수정요청

	public UpdateTranCodeAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			String tranNo = request.getParameter("tranNo");
			String tranCode = request.getParameter("tranCode");
			
			PurchaseService service = new PurchaseServiceImpl();
			Purchase purchase = new Purchase();
			
			purchase.setTranNo(Integer.parseInt(tranNo));
			purchase.setTranCode(tranCode);
			
			System.out.println("UpdateTranCodeAction  tranNo : " + tranNo);
			System.out.println("UpdateTranCodeAction  tranCode : " + tranCode);
			System.out.println("UpdateTranCodeAction  purchaseVO : " + purchase);
			
			service.updateTranCode(purchase);
			
			return "forward:/listPurchase.do";
		
	}
}
