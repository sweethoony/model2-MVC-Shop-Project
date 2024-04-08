<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>


<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ///////////////////////////// 로그인시 Forward  /////////////////////////////////////// -->
 
 <!-- //////////////////////////////////////////////////////////////////////////////////////////////////// -->


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="../javascript/common/scroll.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	#target ul{
    height: 60px; 
}
.navbar-brand{
	font-size: 30px;
	line-height: 30px;
}
	
.header {
    display: flex;
    flex-direction: row; 
    align-items: center; 
}
#headerName {
	font-size : 40px;
    display: block;
    
    margin-left: 90px; 
}

#searchForm{
	display: block;
	margin-left: 30px;
}

#btn-search{
	margin-left: 10px;
}

input[type="text"] {
    width: 300px;
    height: 40px; 
    font-size: 16px;
}

#v-banner > ul {
  width: 2500px;
  padding: 0;
  margin: 0 auto;
  white-space: nowrap;
  overflow-x: hidden; 
}

#v-banner ul li {
  font-size: 24px;
  border: 1px solid #000;
  list-style: none;
  display: inline-block;
  padding: 8px 20px;
  margin: 10px;
  white-space: nowrap;
}

div#v-banner {
  border: 1px solid black;
  margin: 20px auto;
  margin-right: 10px;
  width: 800px;
  padding: 4px;
  overflow: hidden;
}

.col-md-3 {
  position: sticky;
  position: -webkit-sticky;
  top: -100px; 
}

aside{
	position: stic
}

#rcmd-prod{
	border: 0.5px solid black;
	text-align: center;
}

.product-list {
    list-style: none;
    padding: 0;
    margin: 0;
    overflow-x: auto; 
    white-space: nowrap;
}

.product-list li {
    display: inline-block;
    margin-right: 18px; 
}

.product-list img {
    width: auto;
    height: auto; 
}


#rcmd-list {
    position: relative;
    overflow: hidden;
    width: auto; 
    white-space: nowrap;
}

#rcmd-list ul{
	overflow: hidden;
}

.newProduct-list {
    list-style: none;
    padding: 0;
    margin: 0;
    overflow-x: auto; 
    white-space: nowrap;
    display: flex;
    flex-wrap: wrap; 
    justify-content: space-between;
}

.newProduct-list li {
    display: inline-block;
    margin-right: 18px; 
    width: calc(33.33% - 20px);
    margin-bottom: 20px;
}

.newProduct-list img {
    width: 100%;
    height: auto; 
}
#new-prod{
	border: 0.5px solid black;
	text-align: center;
}
#new-prod {
    width: 50%;
    float: left; 
    overflow: hidden; 
}

#news{
	border: 0.5px solid black;
	text-align: center;
}
#news {
    width: 50%;
    float: right; 
    overflow: hidden; 
}


	</style>
   	
   	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
		
		//============= 회원원가입 화면이동 =============
		$( function() {
			//==> 추가된부분 : "addUser"  Event 연결
			$("a[href='#' ]:contains('회원가입')").on("click" , function() {
				self.location = "/user/addUser"
			});
		});
		
		//============= 로그인 화면이동 =============
		$( function() {
			//==> 추가된부분 : "addUser"  Event 연결
			$("a[href='#' ]:contains('로 그 인')").on("click" , function() {
				self.location = "/user/login"
			});
		});
		



	//===> 베너 슬라이드 이벤트
		setInterval(fnSlide, 6000);
		
		function fnSlide() {
		  $("#v-banner ul").animate({ "margin-left": "-800px" }, 1200, function () {
		    $("#v-banner ul").css({ "margin-left": "-8px" });
		    $("#v-banner ul li:first-child").insertAfter("#v-banner ul li:last-child");
		  });
		};

		
		//===> 인기 상품 슬라이드 리스트
		setInterval(plSlide, 6000);
		
		function plSlide() {
			 $("#rcmd-list ul").animate({"margin-left": "-200px"}, 1200, function(){
			        $("#rcmd-list ul").css({"margin-left": "-3px"});
			        $("#rcmd-list ul li:first-child").insertAfter("#rcmd-list ul li:last-child"); 
			    });
			};
		
			
			
		//===> 인기 상품 ajax	
		$(document).ready(function() {
		    function getListLike() {
		        $.ajax({
		            url: '/like/json/listLike',
		            type: 'POST',
		            contentType: 'application/json',
		            data: JSON.stringify({
		                "currentPage": 1,
		                "searchCondition": null,
		                "searchKeyword": null,
		                "pageSize": 0,
		                "endRowNum": 0,
		                "startRowNum": 0
		            }),
		            success: function(response) {
		                var productList = $(".product-list");
		                productList.empty(); 

		                $.each(response, function(index, item) {
		                    var imagePaths = item.likeProdNo.fileName.split(',');
		                    var firstImagePath = imagePaths[0];
		                    
		                        var listItem = $("<li></li>");
		                        var figure = $("<figure></figure>");
		                        var href =$(' <a href="/product/getProduct?prodNo='+item.likeProdNo.prodNo+'&menu=search"></a>')
		                        var img = $("<img>").attr({
		                            src: "images/uploadFiles/" + firstImagePath.trim(),
		                            alt: item.likeProdNo.prodName
		                        }).css({
		                            width: "200px",
		                            height: "auto" 
		                        });
		                        var div = $("<div>", {class: "image-text", text: item.likeProdNo.prodName});
		                        var priceDiv = $("<div>",{class:"price-text",text:item.likeProdNo.price});
		                        href.append(img);
		                        href.append(div);
		                        href.append(priceDiv);
		                        figure.append(href);
		                        listItem.append(figure);
		                        productList.append(listItem);
		                        
		                });
		            }
		        });
		    }
		    getListLike();
		});
		
		
		//===> 최신 상품 ajax
		$(document).ready(function() {
		    function getNewList() {
		        $.ajax({
		            url: '/product/json/listProduct/newProd',
		            type: 'POST',
		            contentType: 'application/json',
		            data: JSON.stringify({
		                "currentPage": 1,
		                "searchCondition": null,
		                "searchKeyword": null,
		                "pageSize": 0,
		                "endRowNum": 0,
		                "startRowNum": 0
		            }),
		            success: function(response) {
		                var prodNewtList = $(".newProduct-list");
		                prodNewtList.empty(); 

		                $.each(response, function(index, item) {
		                    var imgPaths = item.fileName.split(',');
		                    var firstImgPath = imgPaths[0];
							
		                        var nListItem = $("<li></li>");
		                        var nFigure = $("<figure></figure>");
		                        var nHref =$(' <a href="/product/getProduct?prodNo='+item.prodNo+'&menu=search"></a>');
		                        var nImg = $("<img>").attr({
		                        	src: "images/uploadFiles/" + firstImgPath.trim(),
		                            alt: item.prodName
		                        }).css({
		                            width: "auto",
		                            height: "100px" 
		                        });
		                        var nDiv = $("<div>", {class: "image-text", text: item.prodName});
		                        var nPriceDiv = $("<div>",{class:"price-text",text:item.price});
		                        
		                        nHref.append(nImg);
		                        nHref.append(nDiv);
		                        nHref.append(nPriceDiv);
		                        nFigure.append(nHref);
		                        nListItem.append(nFigure);
		                        prodNewtList.append(nListItem);
		                        
		                });
		            }
		        });
		    }

		    getNewList();
		});
		
	

		$(document).ready(function() {
		    $('#searchForm').submit(function(event) {
		        event.preventDefault(); 
		        var searchText = $('#searchInput').val();
		        $('#mainContainer').empty();
		        fncSearch(searchText); 
		    });
		});


		 
	</script>	
	
</head>

<body>
    <header class="header" >
   		 <a id = "headerName" href="/index.jsp">후니네 반찬</a>
   		
   		   <form id="searchForm" method="post" action="/product/listProduct.jsp">
		        <input id="searchInput" type="text" name="productName" autocomplete="off" placeholder="원하는 상품명을 입력하세요.">
		        <button id="btn-search" title="검색하기" type="submit">
		            <img src="images/header-sch.png" alt="검색">
		        </button>
		    </form>

		
    </header>
    
    <!-- ToolBar Start /////////////////////////////////////-->
    	<jsp:include page="/layout/toolbar.jsp" />


  <div class="container">
    <div class="row">
     <aside class="col-md-3"style="position: sticky; top: 20px;">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-heart"></i> 회원관리
                </div>
                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="#">개인정보조회</a> <i class="glyphicon glyphicon-ban-circle"></i>
                    </li>
                    <li class="list-group-item">
                        <a href="#">회원정보조회</a> <i class="glyphicon glyphicon-ban-circle"></i>
                    </li>
                </ul>
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-briefcase"></i> 판매상품관리
                </div>
                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="#">판매상품등록</a> <i class="glyphicon glyphicon-ban-circle"></i>
                    </li>
                    <li class="list-group-item">
                        <a href="#">판매상품관리</a> <i class="glyphicon glyphicon-ban-circle"></i>
                    </li>
                </ul>
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-shopping-cart"></i> 상품구매
                </div>
                <ul class="list-group">
                    <li id="searchProduct" class="list-group-item">상품검색</a></li>
                    <li class="list-group-item">
                        <a href="#">구매이력조회</a> <i class="glyphicon glyphicon-ban-circle"></i>
                    </li>
                    <li class="list-group-item">
                        <a href="#">최근본상품</a> <i class="glyphicon glyphicon-ban-circle"></i>
                    </li>
                </ul>
            </div>
        </aside>
        
        <main id = "mainContainer" class="col-md-9">
            <div id="v-banner">
                <ul>
                    <li><img src="images/uploadFiles/AHlbAAAAtBqyWAAA.jpg" width="750" height="399" ></li>
                    <li><img src="images/uploadFiles/ak47Grand.jpg" width="750" height="399" ></li>
                    <li><img src="images/uploadFiles/AHlbAAAAve1WwgAC.jpg" width="750" height="399" ></li>
                </ul>
            </div>
            
            <div id = "rcmd-prod">
            		<h3>인기 상품</h3>
            		<div id = "rcmd-list">
            			<ul class = "product-list"></ul>
            		</div>
            </div>
            
            <div id = "new-prod">
            		<h3>최신 상품</h3>
            		<div id = "new-list">
            			<ul class = "newProduct-list"></ul>
            		</div>
            </div>
            
            <div id="news">
            	<h3>공지사항</h3>
            		<ul class = "news-list"></ul>
            </div>
        </main>
         <div id="searchMainContainer"></div>
    </div>
</div>

</body>


</html>