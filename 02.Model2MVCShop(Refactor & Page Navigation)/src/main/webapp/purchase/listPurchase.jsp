<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="com.model2.mvc.common.Search"%>
<%@ page import="com.model2.mvc.common.Page"%>
<%@ page import="com.model2.mvc.common.util.CommonUtil"%>
<%@ page import= "com.model2.mvc.service.domain.*" %>


<%
	User user = (User)request.getSession().getAttribute("user");
	List<Purchase> list= (List<Purchase>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	String menu = (String) request.getAttribute("menu");
	System.out.println("menu :: " + menu);
	Search search = (Search)request.getAttribute("search");
	//==> null 을 ""(nullString)으로 변경
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
%>
%>

<!DOCTYPE html>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncGetPurchaseList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
	</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 <%=resultPage.getTotalCount()%> 건수, 현재 <%=resultPage.getCurrentPage()%> 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">주문한 고객</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<%
	if(resultPage.getTotalCount()==0){
	%>
	
	<tr class="ct_list_pop"> 
				<td align="center" colspan="100%">구매 내역이 없습니다.</td>
			</tr>
			<tr>
				<td colspan="11" bgcolor="D6D7D6" height="1"></td>
			</tr>
			<%
			} else {
			%>
			<%
			int no = list.size();
				System.out.println("no : " + no);
			            for(int i=0; i<list.size(); i++) {
			            	Purchase vo = (Purchase)request.getAttribute("purchaseVO");
			            	System.out.println("1   listPurchase.jsp   vo= "+vo);
			                vo = (Purchase)list.get(i);
			                System.out.println("2   listPurchase.jsp   vo= "+vo);
			%>	
	
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%= vo.getTranNo() %>"><%=no--%></a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=<%= vo.getBuyer().getUserId() %>"><%= vo.getBuyer().getUserId() %></a>
		</td>
		<td></td>
		<td align="left"><%= vo.getReceiverName() %></td>
		<td></td>
		<td align="left"><%= vo.getReceiverPhone() %></td>
		<td></td>
		<td align="left">
					<% if(vo.getPurchaseProd().getProTranCode().trim().equals("1")) { %>
					구매완료 배송을 준비중입니다.
					<% } else if(vo.getPurchaseProd().getProTranCode().trim().equals("2")) { %>
					구매하신 상품이 배송중입니다.
					<% } else if(vo.getPurchaseProd().getProTranCode().trim().equals("3")) { %>
					[상품 수령 완료!] 상품 거래가 끝났습니다.
					<% } %> 
					</td>
					<td></td>
					<td align="left">
					<% if(vo.getPurchaseProd().getProTranCode().trim().equals("2")) { %>
					<a href="updateTranCode.do?tranNo=<%= vo.getTranNo() %>&tranCode=3"/>물건 도착(수령 확인)</a>
					<% } %> 
					</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<%} %>
	<%} %>	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetPurchaseList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
					<a href="javascript:fncGetPurchaseList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
			<% } %>
		
    	</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>