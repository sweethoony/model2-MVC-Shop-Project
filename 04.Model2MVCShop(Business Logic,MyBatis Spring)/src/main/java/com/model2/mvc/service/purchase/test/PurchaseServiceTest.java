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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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
		purchase.setReceiverName("��");
		purchase.setReceiverPhone("010010010");
		purchase.setDivyAddr("��⵵");
		purchase.setDivyRequest("����");
		purchase.setDivyDate("2024/10/30");
		
		purchaseService.addPurchase(purchase);
		
		
		purchase = purchaseService.getPurchase(10058);

		//==> console Ȯ��
		System.out.println(purchase);
		

	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		

		//==> �ʿ��ϴٸ�...
//		product.setProdName("testProdName");
//		product.setProdDetail("testProdDetail");
//		product.setManuDate("000000");
//		product.setPrice(2000);
//		product.setFileName("testFileName");
	
		
		purchase = purchaseService.getPurchase(10058);

		//==> console Ȯ��
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
		purchase.setDivyRequest("�Ŀ�");
		purchase.setDivyDate("2024/10/12");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10057);
		Assert.assertNotNull(purchase);
		
		//==> console Ȯ��
		System.out.println(purchase);
			

	 }
	 

	 //==>  �ּ��� Ǯ�� �����ϸ�....
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

		    // ==> console Ȯ��
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

		    // ==> console Ȯ��
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
		
		//==> console Ȯ��
		System.out.println(purchase);
	 }
	 
}