package com.model2.mvc.web.like;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Like;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.like.LikeService;
import com.model2.mvc.service.product.ProductService;



@Controller
@RequestMapping("/like/*")
public class LikeController {
	
	///Field
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("LikeServiceImpl")
	private LikeService likeService;
	//setter Method 구현 않음
		
	public LikeController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
//	@RequestMapping("/wishListLike.do")
	@RequestMapping(value = "wishListLike")
	public String wishListLike(@ModelAttribute("search") Search search, Model model, HttpSession session) throws Exception {

		System.out.println("/like/wishListLike : GET/POST");
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
	
//	@RequestMapping("/addLike.do")
	@RequestMapping(value = "addLike", method = RequestMethod.GET)
	public String addLike( @RequestParam("pushLikeNo") String pushLike, @RequestParam("prodNo") String prodNob, @RequestParam("countLikeProd") int countLikeProd, Model model
							,@RequestParam("menu") String menu, HttpSession session) throws Exception {
		
		User user = (User) session.getAttribute("user");	
		
		int prodNo =Integer.parseInt(prodNob);
		
		System.out.println("/like/addLike : POST");
		
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
		
		return "forward:/product/getProduct";
	}
	
	
//	@RequestMapping("/deleteLike.do")
	@RequestMapping(value = "deleteLike",method = RequestMethod.GET)
	public String deleteLike( @RequestParam("prodNo") String prodNob,@RequestParam("countLikeProd") int countLikeProd, Model model
			,@RequestParam("menu") String menu, HttpSession session ) throws Exception{
		System.out.println("/like/deleteLike");
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
		return "forward:/product/getProduct";
	}
	
//	@RequestMapping("/listLike.do")
	@RequestMapping(value = "listLike")
	public String listLike(@ModelAttribute("search") Search search, Model model) throws Exception {
	    System.out.println("/like/listLike : GET/POST");

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