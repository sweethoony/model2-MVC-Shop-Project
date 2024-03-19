package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserVO userVO = (UserVO) request.getSession().getAttribute("user");
        String productStr = request.getParameter("prod_no");
        System.out.println("1 AddPurchaseAction : " +productStr );
        int prodNo = 0;

        
        prodNo = Integer.parseInt(productStr.toString());
        System.out.println("2 AddPurchaseAction : " +prodNo );
        
        ProductVO productVO = new ProductVO();
        productVO.setProdNo(prodNo);
        
        System.out.println("3 AddPurchaseAction : " +prodNo );
        
        PurchaseVO purchaseVO = new PurchaseVO();
        purchaseVO.setPurchaseProd(productVO);
        
        System.out.println("4 AddPurchaseAction : " +productVO );
        
        purchaseVO.setBuyer(userVO);
        
        System.out.println("5 AddPurchaseAction : " + userVO);
        purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
        purchaseVO.setReceiverName(request.getParameter("receiverName"));
        purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
        purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
        purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
        purchaseVO.setDivyDate(request.getParameter("receiverDate"));

        
        PurchaseService service = new PurchaseServiceImpl();
        service.addPurchase(purchaseVO);

        request.setAttribute("purchaseVO", purchaseVO);

        return "forward:/purchase/getPurchase.jsp";
    }
}
