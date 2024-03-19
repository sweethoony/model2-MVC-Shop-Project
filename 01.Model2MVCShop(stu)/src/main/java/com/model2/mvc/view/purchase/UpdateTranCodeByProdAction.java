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

public class UpdateTranCodeByProdAction extends Action{  //구매상태코드수정요청

	public UpdateTranCodeByProdAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String prodNo = request.getParameter("prodNo");
	    String tranCode = request.getParameter("tranCode");

	    PurchaseService service = new PurchaseServiceImpl();

	    PurchaseVO purchaseVO = new PurchaseVO();
	    
//	    ProductVO productVO = new ProductVO();
//		ProductService productService = new ProductServiceImpl();
//		productVO = productService.getProduct(Integer.parseInt(prodNo));
		
	    ProductVO productVO = new ProductVO();

	    productVO.setProdNo(Integer.parseInt(prodNo));

	    purchaseVO.setPurchaseProd(productVO);

	    purchaseVO.setTranCode(tranCode);

	    System.out.println("UpdateTranCodeByProdAction  prodNo : " + prodNo);
	    System.out.println("UpdateTranCodeByProdAction  tranCode : " + tranCode);
	    System.out.println("UpdateTranCodeByProdAction  purchaseVO : " + purchaseVO);

		service.UpdateTranCodeByProdAction(purchaseVO);

	    return "forward:/listProduct.do";
	}

}
