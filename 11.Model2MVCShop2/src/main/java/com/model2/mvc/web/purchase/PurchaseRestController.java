package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


//==> ȸ������ RestController
@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
	
	///Field
	@Autowired
	@Qualifier("PurchaseServiceImpl")
	private PurchaseService purchaseService;
	//setter Method ���� ����
		
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productService;
	//setter Method ���� ����
		
	public PurchaseRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="/json/getPurchase/{tranNo}", method=RequestMethod.GET )
	public Purchase getPurchase( @PathVariable String tranNo ) throws Exception{
		System.out.println("tranNo" + tranNo);
		System.out.println("/purchase/json/getPurchase : GET");
		
		Purchase purchase = purchaseService.getPurchase(Integer.parseInt(tranNo));
		//Business Logic
		return purchase;
	}
	@RequestMapping( value="/json/getPurchase", method=RequestMethod.POST )
	public Purchase getPurchase( @RequestBody Purchase purchase ) throws Exception{
		System.out.println("purchase" + purchase);
		System.out.println("/purchase/json/getPurchase : POST");
		//Business Logic
		return purchase;
	}

	
}