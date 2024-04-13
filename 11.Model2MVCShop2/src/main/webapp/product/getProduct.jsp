<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import= "com.model2.mvc.service.domain.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<title>상품상세보기</title>

<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	 
		$(function() {
			 $( "td.ct_btn01:contains('구매')" ).on("click" , function() {
				  event.preventDefault();
				  $('#mainContainer').empty();
				  $('#searchForm').empty();
				  $('#searchMainContainer').empty();
				  
				  $.ajax({
					    url: "/purchase/addPurchase",
					    data: { prod_no: ${prodVo.prodNo} },
					    type: "GET",
					    success: function(response) {
					        $('#mainContainer').html(response);
					    },
					    error: function(xhr, status, error) {
					        console.error(error);
					    }
					});


			 });
			 
			 $( "td.ct_btn01:contains('이전')" ).on("click" , function() {
				 self.location = "/index.jsp"


			 });
	 	});
		
		

		$(document).ready(function(){
			$("#emptyLikeButton").on("click",function(){
				alert("로그인 후 이용 가능합니다.")
				self.location = "/user/login"
			});
		});
		
		 $(document).ready(function() {
	         var csvImageNames = "${prodVo.fileName}"; 
	         var imageNamesArray = csvImageNames.split(","); 

	         var $imageTableBody = $("#imageTableBody");
	         
	         $.each(imageNamesArray, function(index, imageName) {
	        	    var imagePath = "/images/uploadFiles/" + imageName;

	        	    var $imageRow = $("<tr>");

	        	    var $imageCell = $("<td>");

	        	    var $imgElement = $("<img>").attr("src", imagePath).css({
	        	        "width": "200px",
	        	        "height": "auto"
	        	    });

	        	    $imageCell.append($imgElement);

	        	    $imageRow.append($imageCell);

	        	    $imageTableBody.append($imageRow);
	        	});


	     });
		 
		
		 
		
	</script>
</head> 	




<body bgcolor="#ffffff" text="#000000">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"	width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품상세조회</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif"  width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품번호 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105" id="prodNo">${prodVo.prodNo}</td>

				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품명 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${prodVo.prodName}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
	    <td width="104" class="ct_write">상품이미지</td>
	    <td bgcolor="D6D6D6" width="1"></td>
	    <td class="ct_write01">
	        <table border="0" cellspacing="0" cellpadding="0">
	            <tbody id="imageTableBody"></tbody>
	        </table>
	    </td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			상품상세정보 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${prodVo.prodDetail}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">제조일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${prodVo.manuDate}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">가격</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${prodVo.price}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">등록일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">${prodVo.regDate}</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
    <td width="104" class="ct_write">좋아요</td>
    <td bgcolor="D6D6D6" width="1"></td>
    <td class="ct_write01">
        <c:choose>
		    <c:when test="${user.role eq 'admin' or user.role eq 'manager'}">
		      
		        <span id="likeCount">좋아요 누른 고객의 수:${countLikeProd}</span>
		    </c:when>
		    <c:otherwise>
		       
		       <c:if test="${empty user }">
		       <button id="emptyLikeButton">
		     	  	<img src="/images/like_btn.png" width="20" height="20"/>
		     	  </button>
		       </c:if>
		        <c:if test="${ ! empty user }">
		      <button id="likeButton">
			    <c:choose>
			        <c:when test="${pushLikeNo1 == 0}">
			            <a href="/like/addLike?pushLikeNo=1&prodNo=${prodVo.prodNo}&menu=search&countLikeProd=${countLikeProd}">
			                <img src="/images/like_btn.png" width="20" height="20"/>
			            </a>
			        </c:when>
			        <c:otherwise>
			            <a href="/like/deleteLike?pushLikeNo=0&prodNo=${prodVo.prodNo}&menu=search&countLikeProd=${countLikeProd}">
			                <img src="/images/like_btn_red.png" width="20" height="20"/>
			            </a>
			        </c:otherwise>
			    </c:choose>
			</button>
			</c:if>
			좋아요 수 ${countLikeProd}
		    </c:otherwise>
		</c:choose>



        <input type="hidden" id="pushLikeNo" name="pushLikeNo" value="${pushLikeNo1} " />
    </td>
</tr> 
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">

		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
		
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
		 			<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<!-- <a href="/purchase/addPurchase?prod_no=${prodVo.prodNo}">구매</a> -->
					구매
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>
				<td width="30"></td>
		
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<!--  <a href="/product/listProduct?menu=search">이전</a>-->
					이전
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>
		</table>

		</td>
	</tr>
</table>

</body>
</html>