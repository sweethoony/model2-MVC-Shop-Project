package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ProductDAO {
	
	public ProductDAO(){
		
	}
	
	public Product findProduct(int proNo) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT DISTINCT p.MANUFACTURE_DAY, p.PROD_NAME, p.PRICE, p.IMAGE_FILE, p.PROD_DETAIL, p.PROD_NO, p.REG_DATE, NVL(t.TRAN_STATUS_CODE, 0) AS TRAN_STATUS_CODE "+
				"FROM PRODUCT p "+
				"LEFT JOIN TRANSACTION t ON p.PROD_NO = t.PROD_NO "+
				"WHERE p.PROD_NO = ?";


		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, proNo);
		
		ResultSet rs = stmt.executeQuery();
		
		Product product = null;
		int prodNo = 0;
		
		while(rs.next()) {
			product = new Product();
			
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setRegDate(rs.getDate("REG_DATE"));
			product.setProTranCode(String.valueOf(rs.getInt("TRAN_STATUS_CODE")));
			System.out.println("productDao :: "+product);
		}
		con.close();
		
		return product;
	}
	
	public Map<String,Object> getProductList(Search search)throws Exception{
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT DISTINCT p.MANUFACTURE_DAY, p.PROD_NAME, p.PRICE, p.IMAGE_FILE, p.PROD_DETAIL, p.PROD_NO, p.REG_DATE, NVL(t.TRAN_STATUS_CODE, 0) AS TRAN_STATUS_CODE"+
				" FROM PRODUCT p"+
				" LEFT JOIN TRANSACTION t ON p.PROD_NO = t.PROD_NO";



		if (search.getSearchCondition() != null) {         
			System.out.println("search.getSearchCondition() :: "+search.getSearchCondition());
			if (search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += " where p.PROD_NO='" + search.getSearchKeyword()
						+ "'";
			} else if (search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " where p.PROD_NAME='" + search.getSearchKeyword()
						+ "'";
			} else if(search.getSearchCondition().equals("2")&& !search.getSearchCondition().equals("")){
				sql += " where p.PRICE='" + search.getSearchKeyword()
				+ "'";
			}
		}
		sql += " order by p.PROD_NO";

		System.out.println("ProductDAO::Original SQL :: " + sql);
		
		int totalCount = this.getTotalCount(sql);
		System.out.println("ProductDAO :: totalCount  :: " + totalCount);
		
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();

		System.out.println(search);

		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
				Product vo = new Product();
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
			}
		map.put("totalCount", new Integer(totalCount));
		System.out.println("totalCount : " + totalCount);

		map.put("list", list);
		System.out.println("list : "+ list);
		
		rs.close();
		pStmt.close();
		con.close();
			
		return map;
	}

	
	public void insertProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
	    String sql = "insert into PRODUCT values (SEQ_PRODUCT_PROD_NO.NEXTVAL,?,?,?,?,?,SYSDATE)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, product.getProdName());
		pstmt.setString(2, product.getProdDetail());
		pstmt.setString(3, product.getManuDate());
		pstmt.setInt(4, product.getPrice());
		pstmt.setString(5, product.getFileName());
		
		pstmt.executeUpdate();
		
		con.close();
	}
	
	public void updateProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update PRODUCT set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?, IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.setInt(6, product.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}
		private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
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
			
			System.out.println("ProductDAO :: make SQL :: "+ sql);	
			
			return sql;
		}
}
