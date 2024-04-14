<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- ToolBar Start /////////////////////////////////////-->
<nav class="navbar  navbar-default">
	
	<div class="container">
		
		<!-- toolBar Button Start //////////////////////// -->
		<div class="navbar-header">
		    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#target">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		    </button>
		</div>
		<!-- toolBar Button End //////////////////////// -->
		
	    <!--  dropdown hover Start -->
		<div 	class="collapse navbar-collapse" id="target" 
	       			data-hover="dropdown" data-animations="fadeInDownNew fadeInRightNew fadeInUpNew fadeInLeftNew">
	         
	         	<!-- Tool Bar 를 다양하게 사용하면.... -->
	             <ul class="nav navbar-nav">
	             
	              <!--  회원관리 DrowDown -->
	              <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >회원관리</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#">개인정보조회</a></li>
	                         
	                         <c:if test="${sessionScope.user.role == 'admin'}">
	                         	<li><a href="#">회원정보조회</a></li>
	                         </c:if>
	                         
	                         <li class="divider"></li>
	                         <li><a href="#">etc..</a></li>
	                     </ul>
	                 </li>
	                 
	              <!-- 판매상품관리 DrowDown  -->
	               <c:if test="${sessionScope.user.role == 'admin'}">
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >판매상품관리</span>
		                         <span class="caret"></span>
		                     </a>
		                     <ul class="dropdown-menu">
		                         <li><a href="#">판매상품등록</a></li>
		                         <li><a href="#">판매상품관리</a></li>
		                         <li class="divider"></li>
		                         <li><a href="#">etc..</a></li>
		                     </ul>
		                </li>
	                 </c:if>
	                 
	              <!-- 구매관리 DrowDown -->
	              <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >상품구매</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#">상 품 검 색</a></li>
	                         
	                         <c:if test="${sessionScope.user.role == 'user'}">
	                           <li><a href="#">구매이력조회</a></li>
	                         </c:if>
	                         
	                         <li><a href="#">최근본상품</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#">etc..</a></li>
	                     </ul>
	                 </li>
	                 
	                 <li><a href="#">인기 상품</a></li>
	                 
	                 <li><a href="#">이벤트</a></li>
	             </ul>
	             
	             <c:if test="${empty user }">
	             <ul class="nav navbar-nav navbar-right">
                     <li><a href="#">회원가입</a></li>
                     <li><a href="#">로 그 인</a></li>
                    </c:if>
                     
	              <c:if test="${ ! empty user }">
	             <ul class="nav navbar-nav navbar-right">
	                <li><a href="#">로그아웃</a></li>
	            </ul>
	            </c:if>
	            
		</div>
		<!-- dropdown hover END -->	       
	    
	</div>
</nav>
		<!-- ToolBar End /////////////////////////////////////-->
 	
   	
   	
   	<script type="text/javascript">
   
	    function getListLikeTool() {
	    	var page = 1;
	    	 var isLoading = false;
	    	 
	    	 $("a[href='#' ]:contains('인기 상품')").submit(function(event) {
	 	        event.preventDefault(); 
	 	        var href = $(this).attr('href'); 

	 	        $('#mainContainer').empty().load(href);
	 	    });
	    	 $(document).ready(function() {
	    		    loadNextPage();
	    		});
	    	 $(window).on('scroll', function() {
			        if (!isLoading && $(window).scrollTop() + $(window).height() >= $(document).height() - 1) {
			            loadNextPage();
			        }
			    });
	    	 function loadNextPage(searchText) {
			        var productInfo = "";
			        isLoading = true;
	        $.ajax({
	            url: '/like/json/listLike',
	            type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify({
	                "currentPage": page,
	                "searchCondition": 1,
	                "searchKeyword": null,
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
	    
	
	
	    $(document).ready(function() {
	    	$("a[href='#' ]:contains('인기 상품')").on("click" , function() {
	    		$('#mainContainer').empty();
	    		getListLikeTool();
	    	});
	    });
   	
   	
   	//=====login
   	$( function() {
		//==> 추가된부분 : "addUser"  Event 연결
		$("a[href='#' ]:contains('로 그 인')").on("click" , function() {
			self.location = "/user/login"
		});
	});
  //============= 회원원가입 화면이동 =============
	$( function() {
		//==> 추가된부분 : "addUser"  Event 연결
		$("a[href='#' ]:contains('회원가입')").on("click" , function() {
			self.location = "/user/addUser"
		});
	});
		//============= logout Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 	$("a:contains('로그아웃')").on("click" , function() {
				$(self.location).attr("href","/user/logout");
				//self.location = "/user/logout"
			}); 
		 });
		
		//============= 회원정보조회 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 	$("a:contains('회원정보조회')").on("click" , function() {
				//$(self.location).attr("href","/user/logout");
				self.location = "/user/listUser"
			}); 
		 });
		
		//=============  개인정보조회회 Event  처리 =============	
	 	$( "a:contains('개인정보조회')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			
			// 새로운 URL 생성
			var newUrl = "/user/getUser?userId=${sessionScope.user.userId}";

			// 새로운 URL로 페이지 로드
			$('#mainContainer').empty().load(newUrl);

	 	});
		
	</script>  