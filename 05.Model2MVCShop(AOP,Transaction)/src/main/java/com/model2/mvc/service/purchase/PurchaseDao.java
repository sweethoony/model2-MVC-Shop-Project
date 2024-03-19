package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public Purchase findPurhase(int productNO) throws Exception;
	
	public List<Purchase> getPurchaseList(Search search,String buyer) throws Exception;
	
	public List<Object> getSaleList(Search search) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	
	public void UpdateTranCodeByProdAction(Purchase purchase) throws Exception;

	public int getTotalCount(String buyer) throws Exception ;
}
