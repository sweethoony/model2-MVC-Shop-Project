package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        String productStr = request.getParameter("prod_no");
        System.out.println("1 AddPurchaseAction : " +productStr );
        int prodNo = 0;

        
        prodNo = Integer.parseInt(productStr.toString());
        System.out.println("2 AddPurchaseAction : " +prodNo );
        
        Product product = new Product();
        product.setProdNo(prodNo);
        
        System.out.println("3 AddPurchaseAction : " +prodNo );
        
        Purchase purchase = new Purchase();
        purchase.setPurchaseProd(product);
        
        System.out.println("4 AddPurchaseAction : " +product );
        
        purchase.setBuyer(user);
        
        System.out.println("5 AddPurchaseAction : " + user);
        purchase.setPaymentOption(request.getParameter("paymentOption"));
        purchase.setReceiverName(request.getParameter("receiverName"));
        purchase.setReceiverPhone(request.getParameter("receiverPhone"));
        purchase.setDivyAddr(request.getParameter("receiverAddr"));
        purchase.setDivyRequest(request.getParameter("receiverRequest"));
        purchase.setDivyDate(request.getParameter("receiverDate"));

        
        PurchaseService service = new PurchaseServiceImpl();
        service.addPurchase(purchase);

        request.setAttribute("purchaseVO", purchase);

        return "forward:/purchase/getPurchase.jsp";
    }
}
