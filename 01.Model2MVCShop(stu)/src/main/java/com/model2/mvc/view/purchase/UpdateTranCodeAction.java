package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

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
			PurchaseVO purchaseVO = new PurchaseVO();
			
			purchaseVO.setTranNo(Integer.parseInt(tranNo));
			purchaseVO.setTranCode(tranCode);
			
			System.out.println("UpdateTranCodeAction  tranNo : " + tranNo);
			System.out.println("UpdateTranCodeAction  tranCode : " + tranCode);
			System.out.println("UpdateTranCodeAction  purchaseVO : " + purchaseVO);
			
			service.updateTranCode(purchaseVO);
			
			return "forward:/listPurchase.do";
		
	}
}
