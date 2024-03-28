<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="com.model2.mvc.common.Search"%>
<%@ page import="com.model2.mvc.common.Page"%>
<%@ page import= "com.model2.mvc.service.domain.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html>
<head>
<title>인기 랭킹</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../javascript/common/fncGetList.js">
</script>

<script type="text/javascript">
	function redirectUser(url) {
	if (${not empty user}) {
	window.location.href = url;
	} else {
	 window.location.href = '/user/loginView.jsp';
		 }
	}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<!-- <form name="detailForm" action="/like/listLike"method="post"> -->
<form name="detailFormLike">
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">인기 랭킹</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="200">랭킹</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">상품</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">상품 구경하러 가기</td>
		<td class="ct_line02"></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%-- 
	<%
	if(resultPage.getTotalCount()==0){
	%>--%>
	<c:if test="${empty resultPage.totalCount or resultPage.totalCount eq '0'}">
    <tr class="ct_list_pop">
        <td align="center" colspan="100%">랭킹 업데이트 중...</td>
    </tr>
    <tr>
        <td colspan="11" bgcolor="D6D7D6" height="1"></td>
    </tr>
</c:if>

<c:if test="${not empty resultPage.totalCount}">
	<c:set var="i" value="0" />
    <c:forEach var="vo" items="${list}" >
    <c:set var="i" value="${ i+1 }" />
        <tr class="ct_list_pop">
            <td align="center">${i}</td>
            <td></td>
            <td align="left">
                ${vo.likeProdNo.prodName}
            </td>
            <td></td>
            <td align="left">${vo.likeProdNo.price}</td>
            <td></td>
	           
				    <td align="left">
				        <a href="javascript:void(0);" onclick="redirectUser('/product/getProduct?prodNo=${vo.likeProdNo.prodNo}&menu=search')">구경하기</a>
				    </td>
				
        </tr>
        <tr>
            <td colspan="11" bgcolor="D6D7D6" height="1"></td>
        </tr>
    </c:forEach>
</c:if>

</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
				<jsp:include page="../common/pageNavigator.jsp"/>	
    	</td>
	</tr>
</table>

</form>

</div>

</body>
</html>