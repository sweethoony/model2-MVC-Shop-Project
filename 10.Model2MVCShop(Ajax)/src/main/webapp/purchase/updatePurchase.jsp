<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>

<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<title>�������� ����</title>

<script type="text/javascript" src="../javascript/calendar.js">
</script>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	function fncAddPurchase(){
		var paymentOption = $("select[name='paymentOption']").val();

		var receiverName = $("input[name = 'receiverName']").val();
		var receiverPhone = $("input[name = 'receiverPhone']").val();
		var divyAddr = $("input[name = 'divyAddr']").val();
		var divyRequest = $("input[name = 'divyRequest']").val();
		var divyDate = $("input[name='divyDate']").val();
		
		if(paymentOption == null || paymentOption.length<1){
			alert("���Ź���� �ݵ�� �Է��Ͽ��� �մϴ�.");
			return;
		}
		if(receiverName == null || receiverName.length<1){
			alert("������ �̸��� �ݵ�� �Է��Ͽ��� �մϴ�.");
			return;
		}
		if(receiverPhone == null || receiverPhone.length<1){
			alert("������ ����ó�� �ݵ�� �Է��ϼž� �մϴ�.");
			return;
		}
		if(divyAddr == null || divyAddr.length<1){
			alert("������ �ּ��� �ݵ�� �Է��ϼž� �մϴ�.");
			return;
		}
		if(divyRequest == null || divyRequest.length<1){
			alert("������ �ּҴ� �ݵ�� ������ּž� �մϴ�.")
			return;
		}
		if(divyDate == null || divyDate.length<1){
			alert("��� ��� ���ڴ� �ݵ�� ������ּž� �մϴ�.")
			return;
		}
		
		$("form").attr("method" , "POST").attr("action" , "/purchase/updatePurchase").submit();
	}
	
	$(function() {
		 $( "td.ct_btn01:contains('����')" ).on("click" , function() {
			 fncAddPurchase();
		 });
	});
 $(function(){	 
		 $( "td.ct_btn01:contains('���')" ).on("click" , function() {
			 self.location = "/product/listProduct?menu=search"
		 });
	});
	</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<!-- <form name="updatePurchase" method="post"	action="/purchase/updatePurchase"> -->
<form name = "updatePurchase">
<input type="hidden" name = "tranNo" value= "${purchaseVO.tranNo}">
<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif"  width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">������������</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="600" border="0" cellspacing="0" cellpadding="0"	align="center" style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�����ھ��̵�</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${ purchaseVO.getBuyer().getUserId()}</td>
		<input type="hidden" name="buyerId" value="${purchaseVO.getBuyer().getUserId()}"/>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">���Ź��</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="paymentOption" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20">
					
				<option value="1" ${purchaseVO.getPaymentOption().equals("1") ? "selected":"" }>���ݱ���</option>
				<option value="2" ${purchaseVO.getPaymentOption().equals("2") ? "selected":"" }>�ſ뱸��</option>
				
			</select>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�������̸�</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverName" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="${purchaseVO.getReceiverName()}">
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">������ ����ó</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="receiverPhone" class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="${purchaseVO.getReceiverPhone()}" />
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�������ּ�</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="divyAddr" class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="${purchaseVO.getDivyAddr()}" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">���ſ�û����</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="divyRequest" 	class="ct_input_g" style="width: 100px; height: 19px" 
							maxLength="20" value="${purchaseVO.getDivyRequest()} "/>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">����������</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td width="200" class="ct_write01">
			<input type="text" readonly="readonly" name="divyDate" class="ct_input_g" 
						style="width: 100px; height: 19px" maxLength="20"value="${purchaseVO.getDivyDate()} "/>
				<img 	src="../images/ct_icon_date.gif" width="15" height="15"	
							onclick="show_calendar('document.updatePurchase.divyDate', document.updatePurchase.divyDate.value)"/>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
					����
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
				<td width="30"></td>
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<!--  <a href="/product/listProduct?menu=search">���</a>-->
					���
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>

</body>
</html>