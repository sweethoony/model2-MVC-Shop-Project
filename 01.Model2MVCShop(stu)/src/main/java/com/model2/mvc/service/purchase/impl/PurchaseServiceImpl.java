package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService{
	
	private PurchaseDAO dao;
	
	private ProductDAO prodDAO;
	
	public PurchaseServiceImpl() {
		dao = new PurchaseDAO();
		prodDAO = new ProductDAO();
	}
	
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception{
		dao.insertPurchase(purchaseVO);
		
		return purchaseVO;
		
	}
	
	public PurchaseVO getPurchase(int productNO) throws Exception{
			
		return dao.findPurhase(productNO);
		
	}
	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String str) throws Exception{
		
		return dao.getPurchaseList(searchVO, str);
		
	}
	
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception{
		
		return dao.getSaleList(searchVO);
		
	}
	
	public PurchaseVO updatePurchase(PurchaseVO purchaseVO) throws Exception{  //회원정보 수정으로 연결
		
		dao.updatePurchase(purchaseVO);
		
		return purchaseVO;
		
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception{
		dao.updateTranCode(purchaseVO);
	}
	
	public void UpdateTranCodeByProdAction(PurchaseVO purchaseVO) throws Exception{
		dao.UpdateTranCodeByProdAction(purchaseVO);
	}

}
