package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	public Purchase addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int productNO) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search, String buyer) throws Exception;
	
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	public Purchase updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;

	public void UpdateTranCodeByProdAction(Purchase purchase) throws Exception;
}
