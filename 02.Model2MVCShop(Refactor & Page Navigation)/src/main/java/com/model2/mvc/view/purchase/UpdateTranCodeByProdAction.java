package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeByProdAction extends Action{  //구매상태코드수정요청

	public UpdateTranCodeByProdAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String prodNo = request.getParameter("prodNo");
	    String tranCode = request.getParameter("tranCode");

	    PurchaseService service = new PurchaseServiceImpl();

	    Purchase purchase = new Purchase();
	    
//	    ProductVO productVO = new ProductVO();
//		ProductService productService = new ProductServiceImpl();
//		productVO = productService.getProduct(Integer.parseInt(prodNo));
		
	    Product product = new Product();

	    product.setProdNo(Integer.parseInt(prodNo));

	    purchase.setPurchaseProd(product);

	    purchase.setTranCode(tranCode);

	    System.out.println("UpdateTranCodeByProdAction  prodNo : " + prodNo);
	    System.out.println("UpdateTranCodeByProdAction  tranCode : " + tranCode);
	    System.out.println("UpdateTranCodeByProdAction  purchaseVO : " + purchase);

		service.UpdateTranCodeByProdAction(purchase);

	    return "forward:/listProduct.do?menu=manage";
	}

}
