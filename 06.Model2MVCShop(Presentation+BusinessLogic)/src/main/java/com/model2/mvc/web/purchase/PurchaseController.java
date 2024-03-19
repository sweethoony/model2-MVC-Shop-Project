package com.model2.mvc.web.purchase;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 Controller
@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("PurchaseServiceImpl")
	private PurchaseService purchaseService;
	//setter Method 구현 않음
		
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productService;
	
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	
	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addPurchaseView(@RequestParam("prod_no") String prodNoString)throws Exception {	    
	    
		int productNO = Integer.parseInt(prodNoString);
		
		System.out.println("productNO : " + productNO);
		
		Product prodVo = productService.getProduct(productNO);
		
		System.out.println("prodVo : " + prodVo);
		
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("/purchase/addPurchaseView.jsp");
	    mv.addObject("prod_no", prodVo);
	    
	    return mv;
	}
	
	@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase(@ModelAttribute("purchase")Purchase purchase ,@RequestParam("prod_no") String prodNo,
								     HttpSession session)throws Exception {
		
		 System.out.println("addPurchase purchase"+ purchase);
		
	    User user = (User) session.getAttribute("user");	    
	    
	    int prodNoInt = Integer.parseInt(prodNo);
	    Product product = new Product();
	    product.setProdNo(prodNoInt);
	    
	    purchase.setBuyer(user);
	    purchase.setPurchaseProd(product);
	    purchaseService.addPurchase(purchase);
	    
	    // Purchase 객체 설정
	    

	    // ModelAndView 생성
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("/purchase/getPurchase.jsp");
	    mv.addObject("purchaseVO", purchase);
	    
	    return mv;
	}

	
	@RequestMapping("/getPurchase.do")
	public ModelAndView getPurchase(@RequestParam("tranNo") String tranNO)throws Exception {
	    
	    Purchase purchase = purchaseService.getPurchase(Integer.parseInt(tranNO));
	    System.out.println("getPurchase purchase"+ purchase);
	    // ModelAndView 생성
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("/purchase/getPurchase.jsp");
	    mv.addObject("purchaseVO", purchase);
	    
	    return mv;
	}
	@RequestMapping("/updatePurchaseView.do")
	public ModelAndView updatePurchaseView(@RequestParam("tranNo") String tranNO)throws Exception {
	    
		int tranNo = Integer.parseInt(tranNO);
		
		System.out.println("updatePurchaseView tranNo"+ tranNo);
		
	    Purchase purchase = purchaseService.getPurchase(tranNo);
	    
	    System.out.println("updatePurchaseView purchase"+ purchase);
	    
	    // ModelAndView 생성
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("/purchase/updatePurchase.jsp");
	    mv.addObject("purchaseVO", purchase);
	    
	    return mv;
	}
	
	@RequestMapping("/updatePurchase.do")
	public ModelAndView updatePurchase(@ModelAttribute("purchase") Purchase purchase,@RequestParam("tranNo") String tranNO)throws Exception {
	    
		purchase.setTranNo(Integer.parseInt(tranNO));
	    purchaseService.updatePurchase(purchase);
	    Purchase purchase1= purchaseService.getPurchase(Integer.parseInt(tranNO));
	    // ModelAndView 생성
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("/purchase/getPurchase.jsp");
	    mv.addObject("purchaseVO", purchase1);
	    
	    return mv;
	}
	
	@RequestMapping("/updateTranCode.do")
	public ModelAndView updateTranCode(@RequestParam("tranCode") String tranCode,@RequestParam("tranNo") String tranNO)throws Exception {
	    
		tranCode = tranCode.substring(0, 10);

		Purchase purchase = new Purchase();
		purchase.setTranNo(Integer.parseInt(tranNO));
		purchase.setTranCode(tranCode);
	    purchaseService.updateTranCode(purchase);
	    // ModelAndView 생성
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("forward:/listPurchase.do");
	    
	    return mv;
	}
	
	@RequestMapping("/updateTranCodeByProd.do")
	public ModelAndView updateTranCodeByProd(@RequestParam("tranCode") String tranCode,@RequestParam("prodNo") String prodNo)throws Exception {
	    
		System.out.println("updateTranCodeByProd tranCode,prodNo  :: "+ tranCode+", "+prodNo);
		
		Product product = new Product();
		Purchase purchase = new Purchase();
		
		
		product.setProdNo(Integer.parseInt(prodNo));
		
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
		
		System.out.println("updateTranCodeByProd purchase"+ purchase);
		
	    purchaseService.UpdateTranCodeByProdAction(purchase);
	    // ModelAndView 생성
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("/listProduct.do?menu=manage");

	    
	    return mv;
	}
	
	@RequestMapping("/listPurchase.do")
	public ModelAndView listPurchase(@ModelAttribute("search") Search search, HttpSession session)throws Exception {
	    
		System.out.println("/listProduct.do");
		
		String buyerId = ((User)session.getAttribute("user")).getUserId();
		
		if(search.getCurrentPage() == 0){
			search.setCurrentPage(1);
		}
		System.out.println("search"+search);
		search.setPageSize(pageSize);
		search.setSearchCondition(search.getSearchCondition());
		search.setSearchKeyword("-"+search.getSearchKeyword()+"-");
		search.setSearchKeyword(search.getSearchKeyword().replace("-", ""));
		Map<String,Object> map= purchaseService.getPurchaseList(search, buyerId);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
				
		System.out.println(resultPage);
		System.out.println("listProduct  map"+map);
		
		// Model 과 View 연결
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("/purchase/listPurchase.jsp");
	    mv.addObject("list", map.get("list"));
	    mv.addObject("resultPage", resultPage);
	    mv.addObject("search", search);
	    
	    return mv;
	}
}