package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

public class PurchaseServiceImpl implements PurchaseService{
	
	private PurchaseDAO dao;
	
	private ProductDAO prodDAO;
	
	public PurchaseServiceImpl() {
		dao = new PurchaseDAO();
		prodDAO = new ProductDAO();
	}
	
	public Purchase addPurchase(Purchase purchase) throws Exception{
		dao.insertPurchase(purchase);
		
		return purchase;
		
	}
	
	public Purchase getPurchase(int productNO) throws Exception{
			
		return dao.findPurhase(productNO);
		
	}
	
	public Map<String, Object> getPurchaseList(Search search, String str) throws Exception{
		
		return dao.getPurchaseList(search, str);
		
	}
	
	public Map<String, Object> getSaleList(Search search) throws Exception{
		
		return dao.getSaleList(search);
		
	}
	
	public Purchase updatePurchase(Purchase purchase) throws Exception{  //회원정보 수정으로 연결
		
		dao.updatePurchase(purchase);
		
		return purchase;
		
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		dao.updateTranCode(purchase);
	}
	
	public void UpdateTranCodeByProdAction(Purchase purchase) throws Exception{
		dao.UpdateTranCodeByProdAction(purchase);
	}

}
