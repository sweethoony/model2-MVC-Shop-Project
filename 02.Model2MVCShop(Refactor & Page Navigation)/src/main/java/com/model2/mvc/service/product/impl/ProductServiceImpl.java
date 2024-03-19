package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;

public class ProductServiceImpl implements ProductService{

	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		
		this.productDAO= new ProductDAO();
	}
	
	public Product addProduct(Product product) throws Exception{
		productDAO.insertProduct(product);
		
		return product;
	}
	
	public Product getProduct(int proNO) throws Exception {
		
		return productDAO.findProduct(proNO);
	}
	
	public Map<String, Object> getProductList(Search search) throws Exception{
		
		return productDAO.getProductList(search);
	}
	
	public void updateProduct(Product product) throws Exception{
		productDAO.updateProduct(product);
	}
}
