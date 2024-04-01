<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>


<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ///////////////////////////// 로그인시 Forward  /////////////////////////////////////// -->
 <c:if test="${ ! empty user }">
 	<jsp:forward page="main.jsp"/>
 </c:if>
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
    text-align: center;
    font-size: 50px;
    line-height: 60px;
}
#insAfter > ul {
  width: 2500px;
  padding: 0;
  margin: 0 auto;
  white-space: nowrap;
  overflow-x: hidden; 
}

#insAfter ul li {
  font-size: 24px;
  border: 1px solid #000;
  list-style: none;
  display: inline-block;
  padding: 8px 20px;
  margin: 10px;
  white-space: nowrap;
}

div#insAfter {
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
		
		setInterval(fnSlide, 3000);
		function fnSlide() {
		  $("#insAfter ul").animate({ "margin-left": "-800px" }, 1200, function () {
		    $("#insAfter ul").css({ "margin-left": "-8px" });
		    $("#insAfter ul li:first-child").insertAfter("#insAfter ul li:last-child");
		  });
		};

	</script>	
	
</head>

<body>
    <div class="header">후니#</div>
    
    <!-- ToolBar Start /////////////////////////////////////-->
    <nav class="navbar  navbar-default">
        <div class="container">
            <a class="navbar-brand" href="#">후니#</a>
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
            <div class="collapse navbar-collapse"  id="target">
                 <ul class="nav navbar-nav navbar-right">
                     <li><a href="#">회원가입</a></li>
                     <li><a href="#">로 그 인</a></li>
                </ul>
           </div>
        </div>
    </nav>


  <div class="container">
    <div class="row">
        <main class="col-md-9">
            <div id="insAfter">
                <ul>
                    <li><img src="images/uploadFiles/AHlbAAAAtBqyWAAA.jpg" width="750" height="399" ></li>
                    <li><img src="images/uploadFiles/ak47Grand.jpg" width="750" height="399" ></li>
                    <li><img src="images/uploadFiles/AHlbAAAAve1WwgAC.jpg" width="750" height="399" ></li>
                </ul>
            </div>
        </main>
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
                    <li class="list-group-item"><a href="#">상품검색</a></li>
                    <li class="list-group-item">
                        <a href="#">구매이력조회</a> <i class="glyphicon glyphicon-ban-circle"></i>
                    </li>
                    <li class="list-group-item">
                        <a href="#">최근본상품</a> <i class="glyphicon glyphicon-ban-circle"></i>
                    </li>
                </ul>
            </div>
        </aside>
    </div>
</div>

</body>


</html>