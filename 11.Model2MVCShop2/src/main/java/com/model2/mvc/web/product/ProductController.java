package com.model2.mvc.web.product;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.like.LikeService;
import com.model2.mvc.service.product.ProductService;



@Controller
@RequestMapping("/product/*")
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
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	//@RequestMapping("/updateProductView.do")
	@RequestMapping(value = "updateProduct", method = RequestMethod.GET)
	public String updateProduct(@RequestParam("PROD_NO")int prodNo,Model model) throws Exception {

		System.out.println("/product/updateProduct : GET");
		
		Product productNo = productService.getProduct(prodNo);
		
		model.addAttribute("productNo",productNo);
		
		return "forward:/product/updateProductView.jsp";
	}
	
//	@RequestMapping("/addProduct.do")
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct( Model model, MultipartHttpServletRequest request) throws Exception {

		System.out.println("/product/addProduct : GET");

		List<MultipartFile> fileList = request.getFiles("fileName");
		
        String path = "C:\\Users\\jhyng\\git\\model2-MVC-Shop-Project\\11.Model2MVCShop2\\src\\main\\webapp\\images\\uploadFiles\\";
		
        
        
		Product product = new Product();
		
		List<String> fileNames = new ArrayList<>();
		
		for(MultipartFile multiFile : fileList) {
			String orgFileName  = multiFile.getOriginalFilename();
			long filesize = multiFile.getSize();
			
			System.out.println("orgFileName :: "+orgFileName);
			System.out.println("filesize :: "+filesize);
			
			String deOrgFileName= URLDecoder.decode(orgFileName, "UTF-8");
			
			System.out.println("deOrgFileName :: "+deOrgFileName);
			
			fileNames.add(deOrgFileName);
			
			
			
			String safeFile = path + orgFileName;
			try {
				multiFile.transferTo(new File(safeFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("fileNames"+fileNames);
		
		StringBuilder sb = new StringBuilder();
		for (String name : fileNames) {
		    sb.append(name).append(",");
		}
		System.out.println("sb :: "+sb);
		if (sb.length() > 0) {
		    sb.deleteCharAt(sb.length() - 1);
		}
		
		String fileNamesCSV = sb.toString();	
		
	    product.setProdName(request.getParameter("prodName"));
	    product.setProdDetail(request.getParameter("prodDetail"));
	    product.setManuDate(request.getParameter("manuDate").replace("-",""));
	    product.setPrice(Integer.parseInt(request.getParameter("price")));
	    
	    product.setFileName(fileNamesCSV);
		
		productService.addProduct(product);
		
		model.addAttribute("productVO", product);
		
		return "forward:/product/addProduct.jsp";
	}
	
//	@RequestMapping("/getProduct.do")
	@RequestMapping(value = "getProduct",method = RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu,
	                          HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception {

	    System.out.println("/product/getProduct : GET");
	    // Business Logic
	    Product prodVo = productService.getProduct(prodNo);
	    System.out.println("/getProduct.Product" + prodVo);
	    System.out.println("/getProduct.session" + session);
	    
	    if(session != null && session.equals("user")) {
	    	System.out.println("/getProduct. session != null ");
	    String userId = ((User) session.getAttribute("user")).getUserId();

	    int pushLikeNo = likeService.getLike(userId, prodNo);

	    int countLikeProd = likeService.countLikeProd(prodNo);
	    System.out.println("/getProduct.countLikeProd" + pushLikeNo);
	    System.out.println("/getProduct.like 1 " + countLikeProd);
	    // Model 과 View 연결
	    model.addAttribute("countLikeProd", countLikeProd);
	    model.addAttribute("pushLikeNo1", pushLikeNo);
	    model.addAttribute("prodVo", prodVo);
	    model.addAttribute("menu", menu);
	  
	    System.out.println("/getProduct. menu  1"  + menu);
	    if (menu != null && !menu.isEmpty()) {
	    	 System.out.println("/getProduct. menu  2" + menu);
	        if (menu.equals("search")) {
	        	 System.out.println("/getProduct. menu  3" + menu);
	            String history = "";
	            Cookie[] cookies = request.getCookies();
	            System.out.println("/getProduct. cookies  1" + cookies);
	            if (cookies != null) {
	            	System.out.println("/getProduct. cookies  2" + cookies);
	                for (Cookie cookie : cookies) {
	                    if (cookie.getName().equals("history")) {
	                        history = cookie.getValue();
	                        break;
	                    }
	                }
	            }

	            if (history.isEmpty()) {
	                history = String.valueOf(prodNo); 
	                System.out.println("/getProduct. history  1" + history);
	            } else if (!Arrays.asList(history.split(":")).contains(String.valueOf(prodNo))) {
	                history += ":" + prodNo;  
	                System.out.println("/getProduct. history  2" + history);
	            }
	            Cookie historyCookie = new Cookie("history", history);
	            historyCookie.setPath("/");
	            System.out.println("/getProduct. historyCookie  1" + historyCookie);
	            historyCookie.setMaxAge(60 * 60);
	            response.addCookie(historyCookie);
	            System.out.println("/getProduct. historyCookie  2" + historyCookie);
	            return "forward:/product/getProduct.jsp";
	        } else {

	            return "forward:/product/updateProduct.jsp";
	        }

	    } else {
	        return "forward:/product/updateProduct.jsp";
	    }
	}
else {
	System.out.println("/getProduct.session = null");
	    int countLikeProd = likeService.countLikeProd(prodNo);
	    System.out.println("/getProduct.like 2" + countLikeProd);
	    
	    model.addAttribute("countLikeProd", countLikeProd);
	    model.addAttribute("prodVo", prodVo);
	    model.addAttribute("menu", menu);
	    
	    return "forward:/product/getProduct.jsp";
		}
	}
	
//	@RequestMapping("/updateProduct.do")
//	@RequestMapping(value = "updateProduct", method =  RequestMethod.POST)
//	public String updateProduct( @RequestParam("prodNo") int prodNo ,@ModelAttribute("product") Product product , Model model, HttpSession session ) throws Exception{
//
//		System.out.println("/product/updateProduct : POST");
//		//Business Logic
//		Product product1 = productService.getProduct(prodNo);
//				
//		productService.updateProduct(product1);
//		
//		session.setAttribute("product", product1);
//		
//		return "redirect:/product/getProduct?prodNo="+ prodNo;
//	}
	@RequestMapping(value = "updateProduct", method =  RequestMethod.POST)
	public String updateProduct( MultipartHttpServletRequest request , Model model, HttpSession session ) throws Exception{

		System.out.println("/product/updateProduct : POST");
		//Business Logic
		List<MultipartFile> fileList = request.getFiles("fileName");
		Product product = new Product();
        String path = "C:\\Users\\jhyng\\git\\model2-MVC-Shop-Project\\10.Model2MVCShop(Ajax)\\src\\main\\webapp\\images\\uploadFiles\\";
		List<String> fileNames = new ArrayList<>();
		
		for(MultipartFile multiFile : fileList) {
			String orgFileName  = multiFile.getOriginalFilename();
			long filesize = multiFile.getSize();
			
			System.out.println("orgFileName :: "+orgFileName);
			System.out.println("filesize :: "+filesize);
			
			fileNames.add(orgFileName);
			
			String safeFile = path + orgFileName;
			try {
				multiFile.transferTo(new File(safeFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("fileNames"+fileNames);
		
		StringBuilder sb = new StringBuilder();
		for (String name : fileNames) {
		    sb.append(name).append(",");
		}
		System.out.println("sb :: "+sb);
		if (sb.length() > 0) {
		    sb.deleteCharAt(sb.length() - 1);
		}
		
		String fileNamesCSV = sb.toString();
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Product product1 = productService.getProduct(prodNo);
		
		product1.setProdName(request.getParameter("prodName"));
		product1.setProdDetail(request.getParameter("prodDetail"));
		product1.setManuDate(request.getParameter("manuDate"));
		product1.setPrice(Integer.parseInt(request.getParameter("price")));
		product1.setFileName(fileNamesCSV);
		
		productService.updateProduct(product1);
		
		session.setAttribute("product", product1);
		
		return "redirect:/product/getProduct?prodNo="+ prodNo;
	}
	
//	@RequestMapping("/listProduct.do")
	@RequestMapping(value = "listProduct")
	public String listProduct(@ModelAttribute("search") Search search, Model model, @RequestParam("menu") String menu) throws Exception {

	    System.out.println("/product/listProduct : GET/POST");
	    
	    System.out.println("/product/listProduct  menu :: "+ menu);
	    

	    if (search.getCurrentPage() == 0) {
	        search.setCurrentPage(1);
	    }
	    System.out.println("search" + search);
	    search.setPageSize(pageSize);
	    search.setSearchCondition(search.getSearchCondition());
	    search.setSearchKeyword("-" + search.getSearchKeyword() + "-");
	    search.setSearchKeyword(search.getSearchKeyword().replace("-", ""));
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    if(menu.equals("search") || menu.equals("manager")) {
	    	map = productService.getProductList(search);
	    }
	    else if(menu.equals("newProd")){
	    	
	    	 map = productService.getNewProductList(search);
	    }
	    System.out.println("/product/listProduct  map ::  " + map);
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