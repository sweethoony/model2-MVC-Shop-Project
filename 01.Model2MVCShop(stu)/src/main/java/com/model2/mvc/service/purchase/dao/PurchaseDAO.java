package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {
	
	public PurchaseVO findPurhase(int productNO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from TRANSACTION where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, productNO);
		
		ResultSet rs = stmt.executeQuery();
		
		PurchaseVO purchaseVO = null;
		int prodNo = 0;
		String userId = null;
		
		while(rs.next()) {
			purchaseVO = new PurchaseVO();
			
			purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
			purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));
			purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));
			String date1 = rs.getString("DLVY_DATE");
			String[] date = date1.split(" ");
			purchaseVO.setDivyDate(date[0]);
			System.out.println(purchaseVO.getDivyDate());
			purchaseVO.setOrderDate(rs.getDate("ORDER_DATA"));
			userId  = rs.getString("BUYER_ID");
			prodNo = rs.getInt("PROD_NO");
		}
		
		UserVO userVO = new UserVO();
		UserService userService = new UserServiceImpl();
		
		userVO= userService.getUser(userId);
		
		ProductVO productVO = new ProductVO();
		ProductService productService = new ProductServiceImpl();
		
		productVO = productService.getProduct(prodNo);
		
		System.out.println("1 purchasedao findpurchase :: " + userVO);
		System.out.println("2 purchasedao findpurchase :: " + productVO);
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPurchaseProd(productVO);
		
		con.close();
		
		return purchaseVO;
		 
	}
	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String purchaseId) throws Exception{
		

		Connection con = DBUtil.getConnection();
		
		String sql = "select * from TRANSACTION "
						+"WHERE BUYER_ID = ?";
//		if (searchVO.getSearchCondition() != null) {                //
//			if (searchVO.getSearchCondition().equals("0")) {
//				sql += " where PROD_NO='" + searchVO.getSearchKeyword()
//						+ "'";
//			} else if (searchVO.getSearchCondition().equals("1")) {
//				sql += " where PROD_NAME='" + searchVO.getSearchKeyword()
//						+ "'";
//			} else {
//				sql += " where PRICE='" + searchVO.getSearchKeyword()
//				+ "'";
//			}
//		}
//		sql += " order by PROD_NO";

		PreparedStatement stmt = 
			con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		stmt.setString(1, purchaseId);
		ResultSet rs = stmt.executeQuery();

		rs.last();    //rs의 데이터 결과의 합 마지막 행으로 이동
		int total = rs.getRow();  //현재 데이터 결과의 합의 행 번호를 가져와서 전체 행의 수를 변수 total에 저장
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		// ResultSet에서 특정 페이지의 첫 번째 행으로 커서(데이터 결과 집합)를 이동시키는 역할
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		int prodNo = 0;
		String userId = null;
		
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				
				PurchaseVO vo = new PurchaseVO();
				vo.setDivyAddr(rs.getString("DEMAILADDR"));
//				String date1 = rs.getString("DLVY_DATE");
//				String[] date = date1.split(" ");
//				vo.setDivyDate(date[0]);
				vo.setDivyRequest(rs.getString("DLVY_REQUEST"));
				vo.setOrderDate(rs.getDate("ORDER_DATA"));
				vo.setPaymentOption(rs.getString("PAYMENT_OPTION"));
				vo.setReceiverName(rs.getString("RECEIVER_NAME"));
				vo.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				String tranStatusCodeStr = rs.getString("TRAN_STATUS_CODE");
				int tranStatusCode = (tranStatusCodeStr != null) ? Integer.parseInt(tranStatusCodeStr.trim()) : 0;
				vo.setTranNo(rs.getInt("TRAN_NO"));
				
				userId  = rs.getString("BUYER_ID");
				UserVO userVO = new UserVO();
				UserService userService = new UserServiceImpl();
				userVO = userService.getUser(userId);
				
				prodNo = rs.getInt("PROD_NO");
				ProductVO productVO = new ProductVO();
				ProductService productService = new ProductServiceImpl();
				
				productVO = productService.getProduct(prodNo);
				
				productVO.setProTranCode(String.valueOf(tranStatusCode));
				
				System.out.println("1 purchasedao findpurchase :: " + userVO);
				System.out.println("2 purchasedao findpurchase :: " + productVO);
				vo.setBuyer(userVO);
				vo.setPurchaseProd(productVO);
				
				
				list.add(vo);
				if (!rs.next()) {
					break;
				}
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		
		return map;
	}
		
//		Connection con = DBUtil.getConnection();
//
//		   String sql = "SELECT "+
//		   			 "TS.TRAN_NO, U.USER_ID, NVL(TS.TRAN_STATUS_CODE, 0) TRAN_CODE, COUNT(*) OVER () AS TOTAL " +
//	                 "FROM USERS U, TRANSACTION TS " +
//	                 "WHERE U.USER_ID = TS.BUYER_ID " +
//	                 "AND U.USER_ID = ? " +
//	                 "AND TS.TRAN_NO BETWEEN ? AND ? " +
//	                 "ORDER BY TS.TRAN_NO";
//
//		   PreparedStatement stmt = 
//					con.prepareStatement(	sql,
//																ResultSet.TYPE_SCROLL_INSENSITIVE,
//																ResultSet.CONCUR_UPDATABLE);
//
//	int searchOne = searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1;
//	int searchTwo = searchOne + searchVO.getPageUnit() - 1;
//
//	System.out.println("1 purchaseDao getPurchaseList : " + searchOne);
//	System.out.println("2 purchaseDao getPurchaseList : " + searchTwo);
//	System.out.println("3 purchaseDao getPurchaseList : " + purchaseId);
//	
//	stmt.setString(1, purchaseId);
//	stmt.setInt(2, searchOne);
//	stmt.setInt(3, searchTwo);
//
//	ResultSet rs = stmt.executeQuery();
//
//	rs.last();   //rs의 데이터 결과의 합 마지막 행으로 이동
//	int total = rs.getRow();    //현재 데이터 결과의 합의 행 번호를 가져와서 전체 행의 수를 변수 total에 저장
//	System.out.println("로우의 수:" + total);
//	
//	HashMap<String, Object> map = new HashMap<String,Object>();
//	map.put("count", new Integer(total));
//
//	rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
//	// ResultSet에서 특정 페이지의 첫 번째 행으로 커서(데이터 결과 집합)를 이동시키는 역할
//	System.out.println("4 purchaseDao getPurchaseList : " + total);
//	
//	ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
//	int max = searchVO.getPageUnit();
//	
//	if (total > 0) {
//		for (int i = 0; i < max && rs.next(); i++) {
//		    PurchaseVO vo = new PurchaseVO();
//		    vo.setTranNo(rs.getInt("TS.TRAN_NO"));
//		    vo.setBuyer(new UserDAO().findUser(rs.getString("USER_ID")));
//		    vo.setTranCode(rs.getString("TRAN_CODE").trim());
//		    list.add(vo);
//		}
//	}
//	System.out.println("list.size() : "+ list.size());
//	map.put("list", list);
//	System.out.println("map().size() : "+ map.size());
//	con.close();
//
//	return map;
//	}

	
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		return map;
	}
	
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO TRANSACTION VALUES (SEQ_TRANSACTION_TRAN_NO.NEXTVAL,?,?,?,?,?,?,?,1,SYSDATE,?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"TRAN_NO", "ORDER_DATA"});
		
		pstmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		System.out.println("1 PurchaseDAO : " + purchaseVO.getPurchaseProd().getProdNo());
		pstmt.setString(2, purchaseVO.getBuyer().getUserId());
		System.out.println("2 PurchaseDAO : " + purchaseVO.getBuyer().getUserId());
		pstmt.setString(3, purchaseVO.getPaymentOption());
		pstmt.setString(4, purchaseVO.getReceiverName());
		pstmt.setString(5, purchaseVO.getReceiverPhone());
		pstmt.setString(6, purchaseVO.getDivyAddr());
		pstmt.setString(7, purchaseVO.getDivyRequest());
//		pstmt.setString(8, purchaseVO.getTranCode());
		pstmt.setString(8, purchaseVO.getDivyDate());
		
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		
		while(rs.next()) {
			purchaseVO.setTranNo(rs.getInt(1));
			purchaseVO.setOrderDate(rs.getDate(2));
		}
		
		con.close();
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update TRANSACTION set PAYMENT_OPTION=?, RECEIVER_NAME=?, RECEIVER_PHONE=?, DEMAILADDR=?, DLVY_REQUEST=?, DLVY_DATE=? where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchaseVO.getPaymentOption());
		stmt.setString(2, purchaseVO.getReceiverName());
		stmt.setString(3, purchaseVO.getReceiverPhone());
		stmt.setString(4, purchaseVO.getDivyAddr());
		stmt.setString(5, purchaseVO.getDivyRequest());
		stmt.setString(6, purchaseVO.getDivyDate());
		stmt.setInt(7, purchaseVO.getTranNo());
		
		stmt.executeUpdate();
		
		con.close();
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update TRANSACTION set TRAN_STATUS_CODE =? where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchaseVO.getTranCode());		
		stmt.setInt(2, purchaseVO.getTranNo());
		System.out.println("1 PurchaseDAO : " + purchaseVO.getTranNo());
		
		
		stmt.executeUpdate();
		
		con.close();
		
	}
	public void UpdateTranCodeByProdAction(PurchaseVO purchaseVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update TRANSACTION set TRAN_STATUS_CODE =? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
			
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		System.out.println("1 PurchaseDAO : " + purchaseVO.getPurchaseProd().getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
	
			
		}

}
