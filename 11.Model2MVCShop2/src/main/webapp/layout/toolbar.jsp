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
	         
	         	<!-- Tool Bar �� �پ��ϰ� ����ϸ�.... -->
	             <ul class="nav navbar-nav">
	             
	              <!--  ȸ������ DrowDown -->
	              <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >ȸ������</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#">����������ȸ</a></li>
	                         
	                         <c:if test="${sessionScope.user.role == 'admin'}">
	                         	<li><a href="#">ȸ��������ȸ</a></li>
	                         </c:if>
	                         
	                         <li class="divider"></li>
	                         <li><a href="#">etc..</a></li>
	                     </ul>
	                 </li>
	                 
	              <!-- �ǸŻ�ǰ���� DrowDown  -->
	               <c:if test="${sessionScope.user.role == 'admin'}">
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >�ǸŻ�ǰ����</span>
		                         <span class="caret"></span>
		                     </a>
		                     <ul class="dropdown-menu">
		                         <li><a href="#">�ǸŻ�ǰ���</a></li>
		                         <li><a href="#">�ǸŻ�ǰ����</a></li>
		                         <li class="divider"></li>
		                         <li><a href="#">etc..</a></li>
		                     </ul>
		                </li>
	                 </c:if>
	                 
	              <!-- ���Ű��� DrowDown -->
	              <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >��ǰ����</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#">�� ǰ �� ��</a></li>
	                         
	                         <c:if test="${sessionScope.user.role == 'user'}">
	                           <li><a href="#">�����̷���ȸ</a></li>
	                         </c:if>
	                         
	                         <li><a href="#">�ֱٺ���ǰ</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#">etc..</a></li>
	                     </ul>
	                 </li>
	                 
	                 <li><a href="#">�α� ��ǰ</a></li>
	                 
	                 <li><a href="#">�̺�Ʈ</a></li>
	             </ul>
	             
	             <c:if test="${empty user }">
	             <ul class="nav navbar-nav navbar-right">
                     <li><a href="#">ȸ������</a></li>
                     <li><a href="#">�� �� ��</a></li>
                    </c:if>
                     
	              <c:if test="${ ! empty user }">
	             <ul class="nav navbar-nav navbar-right">
	                <li><a href="#">�α׾ƿ�</a></li>
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
	    	 
	    	 $("a[href='#' ]:contains('�α� ��ǰ')").submit(function(event) {
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
	                            "��ǰ�� : " + product.prodName + "<br>" +
	                            "��ǰ������ : " + product.prodDetail + "<br>" +
	                            "�������� : " + product.manuDate + "<br>" +
	                            "���� : " + product.price + "<br>" +
	                            "���� : " + product.category + "<br>" +
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
	    	$("a[href='#' ]:contains('�α� ��ǰ')").on("click" , function() {
	    		$('#mainContainer').empty();
	    		getListLikeTool();
	    	});
	    });
   	
   	
   	//=====login
   	$( function() {
		//==> �߰��Ⱥκ� : "addUser"  Event ����
		$("a[href='#' ]:contains('�� �� ��')").on("click" , function() {
			self.location = "/user/login"
		});
	});
  //============= ȸ�������� ȭ���̵� =============
	$( function() {
		//==> �߰��Ⱥκ� : "addUser"  Event ����
		$("a[href='#' ]:contains('ȸ������')").on("click" , function() {
			self.location = "/user/addUser"
		});
	});
		//============= logout Event  ó�� =============	
		 $(function() {
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 	$("a:contains('�α׾ƿ�')").on("click" , function() {
				$(self.location).attr("href","/user/logout");
				//self.location = "/user/logout"
			}); 
		 });
		
		//============= ȸ��������ȸ Event  ó�� =============	
		 $(function() {
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 	$("a:contains('ȸ��������ȸ')").on("click" , function() {
				//$(self.location).attr("href","/user/logout");
				self.location = "/user/listUser"
			}); 
		 });
		
		//=============  ����������ȸȸ Event  ó�� =============	
	 	$( "a:contains('����������ȸ')" ).on("click" , function() {
	 		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			
			// ���ο� URL ����
			var newUrl = "/user/getUser?userId=${sessionScope.user.userId}";

			// ���ο� URL�� ������ �ε�
			$('#mainContainer').empty().load(newUrl);

	 	});
		
	</script>  