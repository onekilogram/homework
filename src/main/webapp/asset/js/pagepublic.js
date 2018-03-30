/*初始化载入
 */
$(function($) {
	// + 操作
	$("#radio1").click(function() {
		$("#urlUpload").css("display", "block");
		$("#fileUpload").css("display", "none");

		$("input[name='picType']").attr("value", "url");

	});
	$("#radio2").click(function() {
		$("#urlUpload").css("display", "none");
		$("#fileUpload").css("display", "block");
		$("input[name='picType']").attr("value", "file");

	});

	$("#urlUpload-input").on('input', function(e) {
		var url = $("#urlUpload-input").val();
		$(".imgpre").find("img").attr("src", url);
		$(".imgpre").find("img").attr("alt", "网络图片");

	});
	$("#urlUpload-input").on('input', function(e) {
		var url = $("#urlUpload-input").val();
		$(".imgpre").find("img").attr("src", url);
		$(".imgpre").find("img").attr("alt", "网络图片");

	});

	$("#fileUp").change(function(e) {
		showPic();
	});

	function showPic() {
		var r = new FileReader();
		f = document.getElementById('fileUp').files[0];
		if (checkPhoto(f.name)) {// 不是图片就提示
			 r.readAsDataURL(f);
			 r.onload = function(e) {
			 document.getElementById('imgpre').src = this.result;
			 };
			 return;
		}
		var _html = "<input class='kg-input' name='file' type='file' id='fileUp'>";
		var _div = $("#fileUpload");
		$('#fileUp').remove();
		_div.append(_html);
		$("#fileUp").change(function(e) {
			showPic();
		});
	}
	$(".u-btn-lg").click(function() {
		var form = $('#form');
		var title = $("input[name='title']").val();
		var summary = $("input[name='summary']").val();
		var image = $("input[name='image']").val();
		var image = $("input[name='file']").val();
		var detail = $("textarea[name='detail']").val();
		var price = $("input[name='price']").val();
		var isSubmiting = false;
		// var imgpre = util.get('imgpre');
		var loading = new Loading();

		// alert(title);

		var result = check();
		if (result) {
			// form.submit();
			// alert(result);
			// onsubmit="return false;"
			$("#form").attr('action', homeworkWebBaseURL + "api/addItem"); // 通过jquery为action属性赋值
			$("#form").attr("onsubmit", "return true;");
			$("#form").submit(); // 提交ID为myform的表单
		}

		// form.submit();

	});

	function check() {

		var list = [];
		list.push($("input[name='title']"));
		list.push($("input[name='summary']"));
		var type=$("input[name='picType']").val();
		if (type=="url") {
			list.push($("input[name='image']"));
		}else{
			f = document.getElementById('fileUp').files[0];
			if (typeof (f) == "undefined") {
				alert("请选择图片！");
				return false;
				}
		}
//		if ($("#radio2").attr("checked")) {
//			// list.push($('input[type="file"]'));
//			//var r = new FileReader();
//			f = document.getElementById('fileUp').files[0];
//			if (typeof (f) == "undefined") {
//				alert("请选择图片！");
//				return false;
//				}
//		}
		// list.push($("input[name='image']"));
		// list.push($("input[name='file']"));
		list.push($("textarea[name='detail']"));
		list.push($("input[name='price']"));

		var title = $("input[name='title']").val();
		var summary = $("input[name='summary']").val();
		var detail = $("textarea[name='detail']").val();
		var price = $("input[name='price']");
		var result = true;
		$.each(list, function(i, item) {
			var value = item.val();
			if (value == null || value == "") {
				item.addClass('z-err');
				result = false;
			}
		});
		if (isNaN($("input[name='price']").val())) {
			$("input[name='price']").addClass('z-err');
			result = false;
		}
		return result;
		// alert(title);
		// 验证信息
		/*
		 * var result = true; [ [title,function(value){return value.length<2 ||
		 * value.length>80}], [summary,function(value){return value.length<2 ||
		 * value.length>140}], [image,function(value){return value == '' ||
		 * !(/^(http|https):\/\//.test(value) &&
		 * /\.(jpg|gif|png)$/.test(value))}], [detail,function(value){return
		 * value.length<2 || value.length>1000}], [price,function(value){return
		 * value == '' || !Number(value)}] ].forEach(function(item){ var value =
		 * item[0].value.trim(); if(item[1](value)){
		 * item[0].classList.add('z-err'); result = false; } item[0].value =
		 * value; }); return result;
		 */
	}
	$("input").click(function() {
		$(this).removeClass("z-err");
	});
	$("textarea").click(function() {
		$(this).removeClass("z-err");
	});
	function checkPhoto(_name) {
		var type = "";
		if (_name != '') {
			type = _name.match(/^(.*)(\.)(.{1,8})$/)[3];
			type = type.toUpperCase();
		}

		if (type != "JPEG" && type != "PNG" && type != "JPG" && type != "GIF") {
			alert("上传图片类型错误");
			return false;
		}
		return true;
	}
});
