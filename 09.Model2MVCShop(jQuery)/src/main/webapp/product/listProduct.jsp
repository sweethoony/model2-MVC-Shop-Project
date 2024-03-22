<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.List"%>

<%@ page import="com.model2.mvc.common.Search"%>
<%@ page import="com.model2.mvc.common.Page"%>
<%@ page import="com.model2.mvc.service.domain.User"%>
<%@ page import="com.model2.mvc.service.domain.Product"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<%--<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncGetProductList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
	</script> --%>
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="../javascript/common/fncGetList.js">
</script>
<script type="text/javascript">
		var menu = ${requestScope.menu}
		var search
		var manage
		
		 $(function() {
			 $( "td.ct_btn01:contains('검색')" ).on("click" , function() {
				 fncGetList(1);
			 });			 
			
		 });
		
		<%--$(document).ready(function() {
		    $(".prodName").on("click", function() {
		        var prodNo = $(this).attr("data-prodNo"); 
		        var menu = $(this).attr("data-menu");
		        self.location = "/product/getProduct?prodNo=" + prodNo + "&menu=" + menu;
		    });--%>

		    $(".updateTranCodeByProd").on("click", function() {
		        var prodNo1 = $(this).attr("data-prodNo"); 
		        self.location = "/purchase/updateTranCodeByProd?prodNo="+prodNo1+"&tranCode=2"
		    });
		});

</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<!-- <form name="detailFormProduct" action="/product/listProduct?menu=${requestScope.menu}" method="post"> -->

<form name="detailFormProduct" >
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							상품 관리
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
<c:if test="${not empty search.searchCondition}">
    <td align="right">
        <select name="searchCondition" class="ct_input_g" style="width:80px">
            <c:choose>
                <c:when test="${search.searchCondition == '0'}">
                    <option value="0" selected>상품번호</option>
                    <option value="1">상품명</option>
                    <option value="2">상품가격</option>
                </c:when>
                <c:when test="${search.searchCondition == '1'}">
                    <option value="0">상품번호</option>
                    <option value="1" selected>상품명</option>
                    <option value="2">상품가격</option>
                </c:when>
                <c:otherwise>
                    <option value="0">상품번호</option>
                    <option value="1">상품명</option>
                    <option value="2" selected>상품가격</option>
                </c:otherwise>
            </c:choose>
        </select>
        <input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" />
    </td>
</c:if>
<c:if test="${empty search.searchCondition}">
    <td align="right">
        <select name="searchCondition" class="ct_input_g" style="width:80px">
            <option value="0" selected>상품번호</option>
            <option value="1">상품명</option>
            <option value="2">상품가격</option>
        </select>
        <input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" />
    </td>
</c:if>

<td align="right" width="70">
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="17" height="23">
                <img src="/images/ct_btnbg01.gif" width="17" height="23">
            </td>
            <td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
               <!--  <a href="javascript:fncGetList(1);">검색</a>--> 
               검색
            </td>
            <td width="14" height="23">
                <img src="/images/ct_btnbg03.gif" width="14" height="23">
            </td>
        </tr>
    </table>
</td>
</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
    <tr>
        <td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
    </tr>
    <tr>
        <td class="ct_list_b" width="100">No</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b" width="150">상품명</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b" width="150">가격</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b">등록일</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b">현재상태</td>
    </tr>
    <tr>
        <td colspan="11" bgcolor="808285" height="1"></td>
    </tr>
    <c:set var="i" value="0" />
    <c:forEach var="vo" items="${list}">
    	<c:set var="i" value="${ i+1 }" />
        <tr class="ct_list_pop">
            <td align="center">${i}</td>
            <td></td>
            <c:choose>
                <c:when test="${vo.proTranCode == '0'}">
                    <td align="left">
                        <a href="/product/getProduct?prodNo=${vo.prodNo}&menu=${(menu != null && menu =='manage') ? 'manage': 'search'}">
                            ${vo.prodName}
                        </a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td align="left">${vo.prodName}</td>
                </c:otherwise>
            </c:choose>
            <td></td>
            <td align="left">${vo.price}</td>
            <td></td>
            <td align="left">${vo.regDate}</td>
            <td></td>
            <td align="left">
                <c:if test="${user != null && (user.role == 'admin' || user.role == 'manager') && 'manage' == menu.trim()}">
                    <c:choose>
                        <c:when test="${vo.proTranCode == '0'}">
                            <td align="left">판매중</td>
                        </c:when>
                        <c:when test="${vo.proTranCode == '1'}">
                            <td align="left">
                                결제완료
                                <a href="/purchase/updateTranCodeByProd?prodNo=${vo.prodNo}&tranCode=2">배송하기</a>
                            </td>
                        </c:when>
                        <c:when test="${vo.proTranCode == '2'}">
                            <td align="left">배송중</td>
                        </c:when>
                        <c:when test="${vo.proTranCode == '3'}">
                            <td align="left">배송완료</td>
                        </c:when>
                    </c:choose>
                </c:if>
                <c:if test="${'search' == menu.trim()}">
                    <c:choose>
                        <c:when test="${vo.proTranCode == '0'}">
                            <td align="left">판매중</td>
                        </c:when>
                        <c:otherwise>
                            <td align="left">재고없음</td>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="11" bgcolor="D6D7D6" height="1"></td>
        </tr>
    </c:forEach>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
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