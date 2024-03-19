package com.model2.mvc.web.like;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Like;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.like.LikeService;
import com.model2.mvc.service.product.ProductService;





@Controller
public class LikeController {

	@Autowired
	@Qualifier("LikeServiceImpl")
	private LikeService likeService;
	
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public LikeController(){
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
	
	
	@RequestMapping("/wishListLike.do")
	public String wishListLike(@ModelAttribute("search") Search search, Model model, HttpSession session) throws Exception {

		System.out.println("/wishListLike.do");
		String buyerId = ((User) session.getAttribute("user")).getUserId();

		System.out.println("wishListLike  buyerId  :: " + buyerId);
		
	    if (search.getCurrentPage() == 0) {
	        search.setCurrentPage(1);
	    }
	    System.out.println("search" + search);
	    search.setPageSize(pageSize);
	    search.setSearchCondition(search.getSearchCondition());
	    search.setSearchKeyword("-" + search.getSearchKeyword() + "-");
	    search.setSearchKeyword(search.getSearchKeyword().replace("-", ""));
	    Map<String, Object> map = likeService.getWishLikeList(search,buyerId);

	    Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);

	    System.out.println(resultPage);
	    System.out.println("wishListLike  map" + map);

	    model.addAttribute("list", map.get("list")); 
	    model.addAttribute("resultPage", resultPage);
	    model.addAttribute("search", search);

		return "forward:/like/wishListLike.jsp";
	}
	
	@RequestMapping("/addLike.do")
	public String addLike( @RequestParam("pushLikeNo") String pushLike, @RequestParam("prodNo") String prodNob, @RequestParam("countLikeProd") int countLikeProd, Model model
							,@RequestParam("menu") String menu, HttpSession session) throws Exception {
		
		User user = (User) session.getAttribute("user");	
		
		int prodNo =Integer.parseInt(prodNob);
		
		System.out.println("/addLike.do");
		
		Product prodVo = productService.getProduct(prodNo);
		Like like = new Like();
		like.setPushLikeNo(pushLike);
		like.setLikeUserId(user);
		like.setLikeProdNo(prodVo);
		//Business Logic
		likeService.addLike(like);
		
		model.addAttribute("like", like);
		model.addAttribute("prodVo", prodVo);
		model.addAttribute("menu", menu);
		model.addAttribute("countLikeProd", countLikeProd);
		
		return "forward:/getProduct.do";
	}
	
	
	@RequestMapping("/deleteLike.do")
	public String deleteLike( @RequestParam("prodNo") String prodNob,@RequestParam("countLikeProd") int countLikeProd, Model model
			,@RequestParam("menu") String menu, HttpSession session ) throws Exception{
		System.out.println("/deleteLike.do");
		User user = (User) session.getAttribute("user");
		int prodNo =Integer.parseInt(prodNob);
		
		Like like = new Like();
		
		Product prodVo = productService.getProduct(prodNo);
		
		like.setLikeUserId(user);
		like.setLikeProdNo(prodVo);
		
		likeService.deleteLike(like);
		
		model.addAttribute("like", like);
		model.addAttribute("prodVo", prodVo);
		model.addAttribute("menu", menu);
		model.addAttribute("countLikeProd", countLikeProd);
		return "forward:/getProduct.do";
	}
	
	@RequestMapping("/listLike.do")
	public String listLike(@ModelAttribute("search") Search search, Model model) throws Exception {
	    System.out.println("/listLike.do");

	    if (search.getCurrentPage() == 0) {
	        search.setCurrentPage(1);
	    }
	    System.out.println("search" + search);
	    search.setPageSize(pageSize);
	    search.setSearchCondition(search.getSearchCondition());
	    search.setSearchKeyword("-" + search.getSearchKeyword() + "-");
	    search.setSearchKeyword(search.getSearchKeyword().replace("-", ""));
	    Map<String, Object> map = likeService.getLikeList(search);


	    Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);

	    System.out.println(resultPage);
	    System.out.println("listLike map " + map);

	    model.addAttribute("list", map.get("list"));
	    model.addAttribute("resultPage", resultPage);
	    model.addAttribute("search", search);

	    return "forward:/like/listLike.jsp";
	}

}
