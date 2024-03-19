package com.model2.mvc.common;


public class SearchVO {
	
	private int page;    //현재 페이지
	String searchCondition;   //페이지 번호
	String searchKeyword;   //검색 키워드
	int pageUnit;  //한페이지에 보이는 항목 개수
	
	public SearchVO(){
	}
	
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
}