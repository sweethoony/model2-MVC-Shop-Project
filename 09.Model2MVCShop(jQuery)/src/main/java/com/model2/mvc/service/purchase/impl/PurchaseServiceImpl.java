package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;


@Service("PurchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	@Qualifier("PurchaseDaoImpl")
	PurchaseDao purchaseDao;
	
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}
	
	public Purchase addPurchase(Purchase purchase) throws Exception{
		purchaseDao.insertPurchase(purchase);
		
		return purchase;
		
	}
	
	public Purchase getPurchase(int productNO) throws Exception{
			
		return purchaseDao.findPurhase(productNO);
		
	}
	
	public Map<String, Object> getPurchaseList(Search search, String buyer) throws Exception{
		
		Map<String, Object> map = new HashMap<>();

        List<Purchase> list = purchaseDao.getPurchaseList(search, buyer);
        
        int totalCount = purchaseDao.getTotalCount(buyer);
        
        System.out.println("purchaseDao getPurchaseList list ::" + list);
        System.out.println("purchaseDao getPurchaseList totalCount ::" + totalCount);
        map.put("list", list);
        map.put("totalCount", new Integer(totalCount));

        return map;
    }
		
	
	public Map<String, Object> getSaleList(Search search) throws Exception{
		
		return null;
		
	}
	
	public Purchase updatePurchase(Purchase purchase) throws Exception{  //회원정보 수정으로 연결
		
		purchaseDao.updatePurchase(purchase);
		
		return purchase;
		
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		purchaseDao.updateTranCode(purchase);
	}
	
	public void UpdateTranCodeByProdAction(Purchase purchase) throws Exception{
		purchaseDao.UpdateTranCodeByProdAction(purchase);
	}

}
