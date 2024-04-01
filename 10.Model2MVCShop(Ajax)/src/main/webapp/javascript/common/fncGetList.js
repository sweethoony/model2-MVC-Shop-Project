function fncGetList(currentPage) {
		
		
		
		$("#currentPage").val(currentPage)
		
		$('form[name="detailFormUser"]').attr("method" , "POST").attr("action" , "/user/listUser").submit();
		$('form[name="detailFormWhisLike"]').attr("method" , "POST").attr("action" , "/like/wishListLike").submit();
		$('form[name="detailFormPurchase"]').attr("method" , "POST").attr("action" , "/purchase/listPurchase" ).submit();
		$('form[name="detailFormLike"]').attr("method" , "POST").attr("action" , "/like/listLike").submit();
		$('form[name="detailFormProduct"]').attr("method" , "POST").attr("action" , "/product/listProduct?menu=search" ).submit();
}
