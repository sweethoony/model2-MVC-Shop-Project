package com.model2.mvc.service.product.impl;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;


@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService{

	@Autowired
	@Qualifier("ProductDaoImpl")
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public ProductServiceImpl() {
		
		System.out.println(this.getClass());
	}
	
	public Product addProduct(Product product) throws Exception{
		String fileName = product.getFileName();
		String encodedFileName = URLEncoder.encode(fileName, "UTF-8"); // UTF-8로 인코딩
		product.setFileName(encodedFileName); 

		productDao.insertProduct(product);
		
		return product;
	}
	
	public Product getProduct(int prodNo) throws Exception {
		
		Product product = productDao.findProduct(prodNo);
		
		String encodedFileName = product.getFileName(); 
		String decodedFileName = URLDecoder.decode(encodedFileName, "UTF-8");

		product.setFileName(decodedFileName);
		
		return product;
	}
	
	public Map<String, Object> getProductList(Search search) throws Exception{
		
		List<Product> list = productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		System.out.println("getProductList list : " + list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		System.out.println("getProductList  map"+map);
		
		return map;
	}
	
	public void updateProduct(Product product) throws Exception{
		String fileName = product.getFileName();
		String encodedFileName = URLEncoder.encode(fileName, "UTF-8"); // UTF-8로 인코딩
		product.setFileName(encodedFileName); 

		productDao.updateProduct(product);
	}

	@Override
	public Map<String, Object> getNewProductList(Search search) throws Exception {
		List<Product> list = productDao.getNewProductList(search);
		int totalCount = productDao.getTotalCount(search);
		System.out.println("getProductList list : " + list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		System.out.println("getNewProductList  map"+map);	
		return map;
	}
}
