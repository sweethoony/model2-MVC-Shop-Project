package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class ProductDAO {
	
	public ProductDAO(){
		
	}
	
	public ProductVO findProduct(int proNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT DISTINCT p.MANUFACTURE_DAY, p.PROD_NAME, p.PRICE, p.IMAGE_FILE, p.PROD_DETAIL, p.PROD_NO, p.REG_DATE, NVL(t.TRAN_STATUS_CODE, 0) AS TRAN_STATUS_CODE "+
				"FROM PRODUCT p "+
				"LEFT JOIN TRANSACTION t ON p.PROD_NO = t.PROD_NO "+
				"WHERE p.PROD_NO = ?";


		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, proNo);
		
		ResultSet rs = stmt.executeQuery();
		
		ProductVO productVO = null;
		int prodNo = 0;
		
		while(rs.next()) {
			productVO = new ProductVO();
			
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
			productVO.setProTranCode(String.valueOf(rs.getInt("TRAN_STATUS_CODE")));
			System.out.println("productDao :: "+productVO);
		}
		con.close();
		
		return productVO;
	}
	
	public HashMap<String,Object> getProductList(SearchVO searchVO)throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT DISTINCT p.MANUFACTURE_DAY, p.PROD_NAME, p.PRICE, p.IMAGE_FILE, p.PROD_DETAIL, p.PROD_NO, p.REG_DATE, NVL(t.TRAN_STATUS_CODE, 0) AS TRAN_STATUS_CODE "+
				"FROM PRODUCT p "+
				"LEFT JOIN TRANSACTION t ON p.PROD_NO = t.PROD_NO";


		if (searchVO.getSearchCondition() != null) {                
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where p.PROD_NO='" + searchVO.getSearchKeyword()
						+ "'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " where p.PROD_NAME='" + searchVO.getSearchKeyword()
						+ "'";
			} else {
				sql += " where p.PRICE='" + searchVO.getSearchKeyword()
				+ "'";
			}
		}
		sql += " order by p.PROD_NO";

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		System.out.println("1111111 sql :: "+ sql);
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

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setPrice(rs.getInt("PRICE"));
				vo.setFileName(rs.getString("IMAGE_FILE"));
				vo.setProdDetail(rs.getString("PROD_DETAIL"));
				vo.setProdNo(rs.getInt("PROD_NO"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				// 기존 코드
				// int tranStatusCode = rs.getInt("TRAN_STATUS_CODE");
				// vo.setProTranCode(String.valueOf(tranStatusCode == 0 ? 0 : tranStatusCode));

				// 수정된 코드
				String tranStatusCode = rs.getString("TRAN_STATUS_CODE");
				vo.setProTranCode(String.valueOf(tranStatusCode == null ? 0 : Integer.parseInt(tranStatusCode.trim())));

				
				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		
		return map;
	}

	
	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
	    String sql = "insert into PRODUCT values (SEQ_PRODUCT_PROD_NO.NEXTVAL,?,?,?,?,?,SYSDATE)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, productVO.getProdName());
		pstmt.setString(2, productVO.getProdDetail());
		pstmt.setString(3, productVO.getManuDate());
		pstmt.setInt(4, productVO.getPrice());
		pstmt.setString(5, productVO.getFileName());
		
		pstmt.executeUpdate();
		
		con.close();
	}
	
	public void updateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update PRODUCT set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?, IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}

}
