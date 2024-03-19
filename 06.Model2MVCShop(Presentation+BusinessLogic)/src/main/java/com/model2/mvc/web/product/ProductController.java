	package com.model2.mvc.web.product;

import java.util.Arrays;
import java.util.List;
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

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.like.LikeService;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 Controller
@Controller
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("LikeServiceImpl")
	private LikeService likeService;
	
	//setter Method 구현 않음
		
	public ProductController(){
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
	
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("PROD_NO")int prodNo,Model model) throws Exception {

		System.out.println("/updateProductView.do");
		
		Product productNo = productService.getProduct(prodNo);
		
		model.addAttribute("productNo",productNo);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct( @ModelAttribute("product") Product product, Model model) throws Exception {

		System.out.println("/addProduct.do");
		//Business Logic
		product.setManuDate(product.getManuDate().replace("-", ""));
		productService.addProduct(product);
		
		model.addAttribute("productVO", product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu,
	                          HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception {

	    System.out.println("/getProduct.do");
	    // Business Logic
	    Product prodVo = productService.getProduct(prodNo);
	    System.out.println("/getProduct.Product" + prodVo);
	    String userId = ((User) session.getAttribute("user")).getUserId();

	    int pushLikeNo = likeService.getLike(userId, prodNo);

	    int countLikeProd = likeService.countLikeProd(prodNo);
	    System.out.println("/getProduct.countLikeProd" + pushLikeNo);
	    System.out.println("/getProduct.like" + countLikeProd);
	    // Model 과 View 연결
	    model.addAttribute("countLikeProd", countLikeProd);
	    model.addAttribute("pushLikeNo1", pushLikeNo);
	    model.addAttribute("prodVo", prodVo);
	    model.addAttribute("menu", menu);

	    if (menu != null && !menu.isEmpty()) {
	        if (menu.equals("search")) {
	            String history = "";
	            Cookie[] cookies = request.getCookies();

	            if (cookies != null) {
	                for (Cookie cookie : cookies) {
	                    if (cookie.getName().equals("history")) {
	                        history = cookie.getValue();
	                        break;
	                    }
	                }
	            }

	            if (history.isEmpty()) {
	                history = String.valueOf(prodNo); 
	            } else if (!Arrays.asList(history.split(":")).contains(String.valueOf(prodNo))) {
	                history += ":" + prodNo;  
	            }
	            Cookie historyCookie = new Cookie("history", history);
	            historyCookie.setMaxAge(60 * 60);
	            response.addCookie(historyCookie);

	            return "forward:/product/getProduct.jsp";
	        } else {

	            return "forward:/product/updateProduct.jsp";
	        }

	    } else {
	        return "forward:/product/updateProduct.jsp";
	    }
	}

	
	@RequestMapping("/updateProduct.do")
	public String updateProduct( @RequestParam("prodNo") int prodNo ,@ModelAttribute("product") Product product , Model model, HttpSession session ) throws Exception{

		System.out.println("/updateProduct.do");
		//Business Logic
		Product product1 = productService.getProduct(prodNo);
				
		productService.updateProduct(product1);
		
		session.setAttribute("product", product1);
		
		return "redirect:/getProduct.do?prodNo="+ prodNo;
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct(@ModelAttribute("search") Search search, Model model, @RequestParam("menu") String menu) throws Exception {

	    System.out.println("/listProduct.do");

	    if (search.getCurrentPage() == 0) {
	        search.setCurrentPage(1);
	    }
	    System.out.println("search" + search);
	    search.setPageSize(pageSize);
	    search.setSearchCondition(search.getSearchCondition());
	    search.setSearchKeyword("-" + search.getSearchKeyword() + "-");
	    search.setSearchKeyword(search.getSearchKeyword().replace("-", ""));
	    Map<String, Object> map = productService.getProductList(search);

	    Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);

	    System.out.println(resultPage);
	    System.out.println("listProduct  map" + map);

	    List<Product> productList = (List<Product>) map.get("list");
	    for (Product product : productList) {
	        String proTranCode = product.getProTranCode();
	        if (proTranCode != null) {
	            product.setProTranCode(proTranCode.trim());
	        }
	    }

	    model.addAttribute("menu", menu);
	    model.addAttribute("list", productList); 
	    model.addAttribute("resultPage", resultPage);
	    model.addAttribute("search", search);

	    return "forward:/product/listProduct.jsp";
	}

}