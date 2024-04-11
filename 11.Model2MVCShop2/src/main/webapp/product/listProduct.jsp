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

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>

<script type="text/javascript">

						
		
		//===> ajax로 리스트 불러오기
			function fncSearch(searchText) {
    var page = 1;
    var isLoading = false;

    $('#searchForm').submit(function(event) {
        event.preventDefault();
        loadNextPage(searchText);
    });

    $(window).on('scroll', function() {
        if (!isLoading && $(window).scrollTop() + $(window).height() >= $(document).height() - 1) {
            loadNextPage(searchText);
        }
    });

    function loadNextPage(searchText) {
        var productInfo = "";
        isLoading = true;
        $.ajax({
            url: "/product/json/listProduct/search",
            method: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "currentPage": page,
                "searchCondition": 1,
                "searchKeyword": searchText,
                "pageSize": 0,
                "endRowNum": 0,
                "startRowNum": 0
            }),
            success: function(data, status) {
                console.log(data);
                console.log(status);
                $.each(data, function(index, product) {
                    var fileNames = product.fileName;
                    if (fileNames) {
                        fileNames = fileNames.split(",");
                    } else {
                        fileNames = ["noImages.png"];
                    }

                    var imageHtml = "";
                    for (var i = 0; i < fileNames.length; i++) {
                        var imagePath = "../images/uploadFiles/" + fileNames[i].trim();
                        var productDetail = "<h6>" +
                            "<br>" +
                            "상품명 : " + product.prodName + "<br>" +
                            "상품상세정보 : " + product.prodDetail + "<br>" +
                            "제조일자 : " + product.manuDate + "<br>" +
                            "가격 : " + product.price + "<br>" +
                            "종류 : " + product.category + "<br>" +
                            "</h6>";

                        var productContent = "<div class='col-xs-12 col-sm-6 col-md-4'>" +
                            "<div class='thumbnail'>" +
                            "<a href='/product/getProduct?prodNo=" + product.prodNo + "&menu=search'><img src='" + imagePath + "' width='200' height='auto'></a>" +
                            "</div>" +
                            productDetail +
                            "</div>";

                        productInfo += productContent;
                    }

                    if ((index + 1) % 3 === 0) {
                        productInfo += "</div><div class='row'>";
                    }
                });

                $('#searchMainContainer').append(productInfo);
                page++;
                isLoading = false;
            },
            error: function(xhr, status, error) {
                console.error("Error:", error);
                isLoading = false;
            }
        });
    }
}

	   

</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<main id = "searchMainContainer" >
</main>

</div>
</body>
</html>