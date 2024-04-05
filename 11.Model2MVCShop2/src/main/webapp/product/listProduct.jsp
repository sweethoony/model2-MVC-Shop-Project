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
<title>��ǰ �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">


	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="../javascript/common/fncGetList.js">
</script>
<script type="text/javascript">

						
		
		
		 $(function() {
			 
			 var searchText = $('#searchInput').val();
			 
		       $.ajax({
		           url: "/product/json/listProduct/search", 
		           method: "POST",
		           dataType: "application/json",
		           data: JSON.stringify({
		                "currentPage": 1,
		                "searchCondition": 1,
		                "searchKeyword": searchText,
		                "pageSize": 0,
		                "endRowNum": 0,
		                "startRowNum": 0
		            }),
		           success: function(data, status) {
		             alert(data);
		             alert(status);
		               $.each(data.list, function(index, product) {
		                   var displayValue = "<div class='product'>" +
		                       "<h6>" +
		                       "��ǰ�� : " + product.prodName + "<br>" +
		                       "��ǰ������ : " + product.prodDetail + "<br>" +
		                       "�������� : " + product.manuDate + "<br>" +
		                       "���� : " + product.price + "<br>" +
		                       "���� :" + product.category + "<br>" +
		                       "</h6>" +
		                       "</div>";
		                   
		                       $('#mainContainer').empty();
		                  
		                       $('#mainContainer').html(displayValue);
		               });
		           }
		       });
		   });
	   

</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">


<tbody>

</tbody>



<!--<form name="detailFormProduct" action="/product/listProduct?menu=${requestScope.menu}" method="post">  --> 
<%-- 
 <form name="detailFormProduct" >
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0" data-search = "${search }">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							��ǰ ����
					
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
        <td id = "page" colspan="11">��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
    </tr>
    <tr>
        <td class="ct_list_b" width="100">No</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b" width="150">�����ϰ� ����</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b" width="150">��ǰ��</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b" width="150">����</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b">�����</td>
        <td class="ct_line02"></td>
        <td class="ct_list_b">�������</td>
        
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
			<td align="left" data-prodNo="${vo.prodNo}">��������</td>
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
                            <td align="left">�Ǹ���</td>
                        </c:when>
                        <c:when test="${vo.proTranCode == '1'}">
                            <td align="left">
                                �����Ϸ�
                                <a href="/purchase/updateTranCodeByProd?prodNo=${vo.prodNo}&tranCode=2">����ϱ�</a>
                            </td>
                        </c:when>
                        <c:when test="${vo.proTranCode == '2'}">
                            <td align="left">�����</td>
                        </c:when>
                        <c:when test="${vo.proTranCode == '3'}">
                            <td align="left">��ۿϷ�</td>
                        </c:when>
                    </c:choose>
                </c:if>
                <c:if test="${'search' == menu.trim()}">
                    <c:choose>
                        <c:when test="${vo.proTranCode == '0'}">
                            <td align="left">�Ǹ���</td>
                        </c:when>
                        <c:otherwise>
                            <td align="left">������</td>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </td>
        </tr>
        <tr>
            <td id="${vo.prodNo}" colspan="11" bgcolor="D6D7D6" height="1"></td>
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
	--%>
</div>
</body>
</html>