package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class PurchaseDAO {
	
	public Purchase findPurhase(int productNO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from TRANSACTION where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, productNO);
		
		ResultSet rs = stmt.executeQuery();
		
		Purchase purchase = null;
		int prodNo = 0;
		String userId = null;
		
		while(rs.next()) {
			purchase = new Purchase();
			
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyAddr(rs.getString("DEMAILADDR"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			String date1 = rs.getString("DLVY_DATE");
			String[] date = date1.split(" ");
			purchase.setDivyDate(date[0]);
			System.out.println(purchase.getDivyDate());
			purchase.setOrderDate(rs.getDate("ORDER_DATA"));
			userId  = rs.getString("BUYER_ID");
			prodNo = rs.getInt("PROD_NO");
		}
		
		User userVO = new User();
		UserService userService = new UserServiceImpl();
		
		userVO= userService.getUser(userId);
		
		Product product = new Product();
		ProductService productService = new ProductServiceImpl();
		
		product = productService.getProduct(prodNo);
		
		System.out.println("1 purchasedao findpurchase :: " + userVO);
		System.out.println("2 purchasedao findpurchase :: " + product);
		purchase.setBuyer(userVO);
		purchase.setPurchaseProd(product);
		
		con.close();
		
		return purchase;
		 
	}
	
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception{
		
		Connection con = DBUtil.getConnection();
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    String originalSql = "select * from TRANSACTION WHERE BUYER_ID = ?";
	    
	    PreparedStatement pStmt = con.prepareStatement(originalSql);
	    pStmt.setString(1, buyerId);
	    
	    ResultSet rs = pStmt.executeQuery();
	    
	    String sql = makeCurrentPageSql(originalSql, search);
	    System.out.println("search, buyerId :: " + search + buyerId);
	    System.out.println("PurchaseDAO::Original SQL :: " + sql);
	    
	    int totalCount = this.getTotalCount(sql,buyerId);
	    map.put("totalCount", totalCount);
	    System.out.println("PurchaseDAO :: totalCount  :: " + totalCount);
		
		
	
		System.out.println(search);

		List<Purchase> list = new ArrayList<Purchase>();
		
		int prodNo = 0;
		String userId = null;
		
		while(rs.next()){
				
				Purchase vo = new Purchase();
				vo.setDivyAddr(rs.getString("DEMAILADDR"));
				vo.setDivyRequest(rs.getString("DLVY_REQUEST"));
				vo.setOrderDate(rs.getDate("ORDER_DATA"));
				vo.setPaymentOption(rs.getString("PAYMENT_OPTION"));
				vo.setReceiverName(rs.getString("RECEIVER_NAME"));
				vo.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				String tranStatusCodeStr = rs.getString("TRAN_STATUS_CODE");
				int tranStatusCode = (tranStatusCodeStr != null) ? Integer.parseInt(tranStatusCodeStr.trim()) : 0;
				vo.setTranNo(rs.getInt("TRAN_NO"));
				
				userId  = rs.getString("BUYER_ID");
				User user = new User();
				UserService userService = new UserServiceImpl();
				user = userService.getUser(userId);
				
				prodNo = rs.getInt("PROD_NO");
				Product product = new Product();
				ProductService productService = new ProductServiceImpl();
				
				product = productService.getProduct(prodNo);
				
				product.setProTranCode(String.valueOf(tranStatusCode));
				
				System.out.println("1 purchasedao findpurchase :: " + user);
				System.out.println("2 purchasedao findpurchase :: " + product);
				vo.setBuyer(user);
				vo.setPurchaseProd(product);
						
				list.add(vo);
				
			
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		
		return map;
	}
		
	
	public HashMap<String, Object> getSaleList(Search search) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		return map;
	}
	
	public void insertPurchase(Purchase purchase) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO TRANSACTION VALUES (SEQ_TRANSACTION_TRAN_NO.NEXTVAL,?,?,?,?,?,?,?,1,SYSDATE,?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"TRAN_NO", "ORDER_DATA"});
		
		pstmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		System.out.println("1 PurchaseDAO : " + purchase.getPurchaseProd().getProdNo());
		pstmt.setString(2, purchase.getBuyer().getUserId());
		System.out.println("2 PurchaseDAO : " + purchase.getBuyer().getUserId());
		pstmt.setString(3, purchase.getPaymentOption());
		pstmt.setString(4, purchase.getReceiverName());
		pstmt.setString(5, purchase.getReceiverPhone());
		pstmt.setString(6, purchase.getDivyAddr());
		pstmt.setString(7, purchase.getDivyRequest());
		pstmt.setString(8, purchase.getDivyDate());
		
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		
		while(rs.next()) {
			purchase.setTranNo(rs.getInt(1));
			purchase.setOrderDate(rs.getDate(2));
		}
		
		con.close();
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update TRANSACTION set PAYMENT_OPTION=?, RECEIVER_NAME=?, RECEIVER_PHONE=?, DEMAILADDR=?, DLVY_REQUEST=?, DLVY_DATE=? where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchase.getPaymentOption());
		stmt.setString(2, purchase.getReceiverName());
		stmt.setString(3, purchase.getReceiverPhone());
		stmt.setString(4, purchase.getDivyAddr());
		stmt.setString(5, purchase.getDivyRequest());
		stmt.setString(6, purchase.getDivyDate());
		stmt.setInt(7, purchase.getTranNo());
		
		stmt.executeUpdate();
		
		con.close();
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update TRANSACTION set TRAN_STATUS_CODE =? where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchase.getTranCode());		
		stmt.setInt(2, purchase.getTranNo());
		System.out.println("1 PurchaseDAO : " + purchase.getTranNo());
		
		
		stmt.executeUpdate();
		
		con.close();
		
	}
	public void UpdateTranCodeByProdAction(Purchase purchase) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update TRANSACTION set TRAN_STATUS_CODE =? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
			
		stmt.setString(1, purchase.getTranCode());
		stmt.setInt(2, purchase.getPurchaseProd().getProdNo());
		System.out.println("1 PurchaseDAO : " + purchase.getPurchaseProd().getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
	
			
		}
	
	private int getTotalCount(String sql, String buyerId) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		 pStmt.setString(1, buyerId);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("PurchaseDAO :: make SQL :: "+ sql);	
		
		return sql;
	}

}
