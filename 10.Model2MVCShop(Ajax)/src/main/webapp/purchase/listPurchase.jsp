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
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<!--  <form name="detailForm" action="/purchase/listPurchase" method="post">-->
<form name="detailFormPurchase" >
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
		<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
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

	<c:if test="${empty resultPage.totalCount}">
    <tr class="ct_list_pop">
        <td align="center" colspan="100%">구매 내역이 없습니다.</td>
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
                <a href="/user/getUser?userId=${vo.buyer.userId}">${vo.buyer.userId}</a>
            </td>
            <td></td>
            <td align="left">${vo.receiverName}</td>
            <td></td>
            <td align="left">${vo.receiverPhone}</td>
            <td></td>
            <td align="left">
                <c:choose>
                    <c:when test="${vo.purchaseProd.proTranCode.trim() == '1'}">
                        구매완료 배송을 준비중입니다.
                    </c:when>
                    <c:when test="${vo.purchaseProd.proTranCode.trim() == '2'}">
                        구매하신 상품이 배송중입니다.
                    </c:when>
                    <c:when test="${vo.purchaseProd.proTranCode.trim() == '3'}">
                        [상품 수령 완료!] 상품 거래가 끝났습니다.
                    </c:when>
                </c:choose>
            </td>
            <td></td>
            <td align="left">
                <c:if test="${vo.purchaseProd.proTranCode.trim() == '2'}">
                    <a href="/purchase/updateTranCode?tranNo=${vo.tranNo}&tranCode=3">물건 도착(수령 확인)</a>
                </c:if>
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

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>