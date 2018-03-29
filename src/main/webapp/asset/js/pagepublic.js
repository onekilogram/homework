/*初始化载入
 */
$(function($) {
	// + 操作
	$("#radio1").click(function() {
		$("#urlUpload").css("display", "block");
		$("#fileUpload").css("display", "none");

		//		if ($("#radio1").attr("checked")) {
		//			alert("#radio1选中");
		//		} else {
		//			alert("#radio1未选中");
		//		}
		//		if ($("#radio2").attr("checked")) {
		//			alert("#radio2选中");
		//		} else {
		//			alert("#radio2未选中");
		//		}

	});
	$("#radio2").click(function() {
		$("#urlUpload").css("display", "none");
		$("#fileUpload").css("display", "block");

	});

	$("#urlUpload-input").on('input', function(e) {
		// alert('Changed!')  
		var url = $("#urlUpload-input").val();
		//alert(url);
		$(".imgpre").find("img").attr("src", url);
		$(".imgpre").find("img").attr("alt", "网络图片");

	});
	$("#urlUpload-input").on('input', function(e) {
		// alert('Changed!')  
		var url = $("#urlUpload-input").val();
		//alert(url);
		$(".imgpre").find("img").attr("src", url);
		$(".imgpre").find("img").attr("alt", "网络图片");

	});
	
	$("#fileUp").change(function(e) {
		showPic();
	});
	
	
	function showPic() {
		var r = new FileReader();
		f = document.getElementById('fileUp').files[0];

		r.readAsDataURL(f);
		r.onload = function(e) {
			document.getElementById('imgpre').src = this.result;
		};
	}
});
