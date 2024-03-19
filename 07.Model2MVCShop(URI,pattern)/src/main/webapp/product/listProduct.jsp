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

<%--<script type="text/javascript">
	// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿�  
	function fncGetProductList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
	</script> --%>
	<script type="text/javascript" src="../javascript/common/fncGetList.js">
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/product/listProduct?menu=${requestScope.menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
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
	<%--<%
	if (search.getSearchCondition() != null) {
	
		    <td align="right">
		        <select name="searchCondition" class="ct_input_g" style="width:80px">
		            <%
		            if (search.getSearchCondition().equals("0")) {
		            %>
		                <option value="0" selected>��ǰ��ȣ</option>
		                <option value="1">��ǰ��</option>
		                <option value="2">��ǰ����</option>
		            <%
		            } else if (search.getSearchCondition().equals("1")) {
		            %>
		                <option value="0">��ǰ��ȣ</option>
		                <option value="1" selected>��ǰ��</option>
		                <option value="2">��ǰ����</option>
		            <%
		            } else {
		            %>
		                <option value="0">��ǰ��ȣ</option>
		                <option value="1">��ǰ��</option>
		                <option value="2" selected>��ǰ����</option>
		            <%
		            }
		            %>
        </select>
        <input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" />
    </td>
<%
}else{
%>				
				<td align="right">
					<select name="searchCondition" class="ct_input_g" style="width:80px">
		                <option value="0" selected>��ǰ��ȣ</option>
		                <option value="1">��ǰ��</option>
		                <option value="2">��ǰ����</option>
		            </select>
		           <input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" />
    			</td>
    			</select>
<%
}
%>
	
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">�˻�</a>
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
		<td colspan="11" >��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
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
	<%
			for(int i=0; i<list.size(); i++){
		Product vo = list.get(i);
	%>
	<tr class="ct_list_pop">
		<td align="center"><%=i + 1 %></td>
		<td></td>
				<%if(vo.getProTranCode().equals("0")) {%>
				<td align="left">
				    <a href="/getProduct.do?prodNo=<%=vo.getProdNo() %>&menu=<%= (request.getParameter("menu") != null && request.getParameter("menu").equals("manage")) ? "manage" : "search" %>">
				        <%= vo.getProdName() %>
				    </a>
				</td>
				<%} else { %>
				<td align="left"><%= vo.getProdName() %>
				</a>
				</td>
				<%} %>
		<td></td>
		<td align="left"><%= vo.getPrice() %></td>
		<td></td>
		<td align="left"><%= vo.getRegDate() %></td>
		<td></td>
		<td align="left">
		<% if(user != null && (user.getRole().equals("admin") || user.getRole().equals("manager")) && "manage".equals(menu.trim())) { %>
				 <% if(vo.getProTranCode().equals("0")) { %>
	               		<td align="left">�Ǹ���</td>
	               <% } else if(vo.getProTranCode().equals("1")) { %>
	               		<td align="left">
	               		�����Ϸ�
	               		<a href="updateTranCodeByProd.do?prodNo=<%= vo.getProdNo() %>&tranCode=2">����ϱ�</a>
	               		</td>
	               <% } else if(vo.getProTranCode().equals("2")) { %>
	               		<td align="left">�����</td>
	               <% } else if(vo.getProTranCode().equals("3")) { %>
	               		<td align="left">��ۿϷ�</td>
	               <% } %>
               <% } else if ("search".equals(menu.trim())){ %>
                   <% if(vo.getProTranCode().equals("0")) { %>
	               		<td align="left">�Ǹ���</td>
	               <% } else { %>
	               		<td align="left">�������</td>
	               <% } %>
               <% } %>
               </td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	<% }%>
</table>--%>

<c:if test="${not empty search.searchCondition}">
    <td align="right">
        <select name="searchCondition" class="ct_input_g" style="width:80px">
            <c:choose>
                <c:when test="${search.searchCondition == '0'}">
                    <option value="0" selected>��ǰ��ȣ</option>
                    <option value="1">��ǰ��</option>
                    <option value="2">��ǰ����</option>
                </c:when>
                <c:when test="${search.searchCondition == '1'}">
                    <option value="0">��ǰ��ȣ</option>
                    <option value="1" selected>��ǰ��</option>
                    <option value="2">��ǰ����</option>
                </c:when>
                <c:otherwise>
                    <option value="0">��ǰ��ȣ</option>
                    <option value="1">��ǰ��</option>
                    <option value="2" selected>��ǰ����</option>
                </c:otherwise>
            </c:choose>
        </select>
        <input type="text" name="searchKeyword" class="ct_input_g" style="width:200px; height:19px" />
    </td>
</c:if>
<c:if test="${empty search.searchCondition}">
    <td align="right">
        <select name="searchCondition" class="ct_input_g" style="width:80px">
            <option value="0" selected>��ǰ��ȣ</option>
            <option value="1">��ǰ��</option>
            <option value="2">��ǰ����</option>
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
                <a href="javascript:fncGetList(1);">�˻�</a>
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
        <td colspan="11">��ü ${resultPage.totalCount} �Ǽ�, ���� ${resultPage.currentPage} ������</td>
    </tr>
    <tr>
        <td class="ct_list_b" width="100">No</td>
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
                            <td align="left">�������</td>
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
			<%--
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					�� ����
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					���� ��
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
			<% } %> --%>
			<jsp:include page="../common/pageNavigator.jsp"/>	
			
    	</td>
    </tr>
</table>

<!--  ������ Navigator �� -->

</form>
	
</div>
</body>
</html>