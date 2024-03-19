package com.model2.mvc.service.purchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("PurchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testInsertPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		
		Product product = new Product();
		
		User user = new User();
		
		product.setProdNo(10003);
		purchase.setPurchaseProd(product);
		
		user.setUserId("user04");
		purchase.setBuyer(user);
		purchase.setPaymentOption("0");
		purchase.setReceiverName("하");
		purchase.setReceiverPhone("010010010");
		purchase.setDivyAddr("경기도");
		purchase.setDivyRequest("빨리");
		purchase.setDivyDate("2024/10/30");
		
		purchaseService.addPurchase(purchase);
		
		
		purchase = purchaseService.getPurchase(10058);

		//==> console 확인
		System.out.println(purchase);
		

	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		

		//==> 필요하다면...
//		product.setProdName("testProdName");
//		product.setProdDetail("testProdDetail");
//		product.setManuDate("000000");
//		product.setPrice(2000);
//		product.setFileName("testFileName");
	
		
		purchase = purchaseService.getPurchase(10058);

		//==> console 확인
		System.out.println(purchase);
		
	}
	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10057);
		Assert.assertNotNull(purchase);
			
		Product product = new Product();
		
		product.setProdNo(10003);
		purchase.setPurchaseProd(product);
		
		purchase.setPaymentOption("1");
		purchase.setReceiverName("dd");
		purchase.setReceiverPhone("000000000");
		purchase.setDivyAddr("ee");
		purchase.setDivyRequest("컴온");
		purchase.setDivyDate("2024/10/12");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10057);
		Assert.assertNotNull(purchase);
		
		//==> console 확인
		System.out.println(purchase);
			

	 }
	 

	 //==>  주석을 풀고 실행하면....
	 @Test
	 public void testGetPurchaseListAll() throws Exception{
		 
		 Search search = new Search();
		    search.setCurrentPage(1);
		    search.setPageSize(3);
		    
		    Purchase purchase = purchaseService.getPurchase(10060);
		    User user = new User();
		    
		    String buyer = purchase.getBuyer().getUserId();
		    
		    System.out.println("buyer :: " + buyer);
		    
		    Map<String, Object> map = purchaseService.getPurchaseList(search, buyer);

		    List<Purchase> list = (List<Purchase>) map.get("list");

		    // ==> console 확인
		    System.out.println("list :: " + list);

		    Integer totalCount = (Integer) map.get("totalCount");
		    System.out.println("totalCount ::"+totalCount);

		    System.out.println("=======================================");

		    search.setCurrentPage(1);
		    search.setPageSize(3);
		    search.setSearchCondition("0");
		    search.setSearchKeyword("");
		    map = purchaseService.getPurchaseList(search, buyer);

		    list = (List<Purchase>) map.get("list");

		    // ==> console 확인
		    System.out.println(list);

		    totalCount = (Integer) map.get("totalCount");
		    System.out.println(totalCount);
	 }
	 
	 @Test
	 public void testUpdateTranCode() throws Exception{
		  	
	 	Purchase purchase = purchaseService.getPurchase(10058);
	 	
	 	purchase.setTranCode("3");
	 	
	 	purchaseService.updateTranCode(purchase);
	 	
	 	purchase = purchaseService.getPurchase(10058);
		
		//==> console 확인
		System.out.println(purchase);
	 }
	 
}