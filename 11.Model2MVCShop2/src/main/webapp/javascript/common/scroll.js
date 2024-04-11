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
                            "<img class='product-image' src='" + imagePath + "' width='200' height='auto'>" +
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

                // 이미지 클릭 이벤트 처리
                $('.product-image').on('click', function() {
                    var prodNo = $(this).data('prodno');
                    if (prodNo) {
                        window.location.href = "/product/getProduct?prodNo=" + prodNo + "&menu=search";
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error("Error:", error);
                isLoading = false;
            }
        });
    }
}
