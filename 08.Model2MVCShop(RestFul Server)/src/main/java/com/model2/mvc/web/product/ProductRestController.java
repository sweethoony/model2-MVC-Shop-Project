package com.model2.mvc.web.product;

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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.like.LikeService;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("LikeServiceImpl")
	private LikeService likeService;
	//setter Method 구현 않음
		
	public ProductRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="json/getProduct/{prodNo}", method=RequestMethod.GET )
	public Product getProduct( @PathVariable int prodNo ) throws Exception{
		
		System.out.println("/product/json/getProduct : GET");
		
		Product prodVo = productService.getProduct(prodNo);
		//Business Logic
		return prodVo;
	}

//	@RequestMapping(value = "json/updateProduct/{prodNo}", method = RequestMethod.GET)
//	public Product updateProductGet( @PathVariable int prodNo ) throws Exception{
//		
//		System.out.println("/product/json/updateProduct : GET");
//		
//		Product productNo = productService.getProduct(prodNo);
//		
//		return productNo;
//		
//	}
//	
	@RequestMapping(value = "json/updateProduct/{prodNo}", method = RequestMethod.POST)
	public Product updateProductPost( @PathVariable int prodNo, @RequestBody Product product ) throws Exception{
		
		System.out.println("/product/json/updateProduct : POST");
		
		 product = productService.getProduct(prodNo);
		
		productService.updateProduct(product);
		System.out.println("updateProductPost Json ::  " + product); 
		return product;
		
	}
}