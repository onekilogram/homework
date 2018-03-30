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
			$("#form").attr('action', homeworkWebBaseURL + "api/updateItem"); // 通过jquery为action属性赋值
			$("#form").attr("onsubmit", "return true;");
			$("#form").submit(); // 提交ID为myform的表单
		}

		// form.submit();

	});

	function check() {

		var list = [];
		list.push($("input[name='title']"));
		list.push($("input[name='summary']"));
		var type = $("input[name='picType']").val();
		if (type == "url") {
			list.push($("input[name='image']"));
		} else {
			f = document.getElementById('fileUp').files[0];
			if (typeof (f) == "undefined") {
				alert("请选择图片！");
				return false;
			}
		}
		list.push($("textarea[name='detail']"));
		list.push($("input[name='price']"));

		var title = $("input[name='title']").val();
		var summary = $("input[name='summary']").val();
		var detail = $("textarea[name='detail']").val();
		var price = $("input[name='price']");
		var result = true;
		//alert(list[0].val());
		$.each(list,function(i, item) {
			var value = item.val();
			
			if (value == null || value == "") {
				//alert(value);
				item.addClass('z-err');
				result = false;
			}
		});
		if (isNaN($("input[name='price']").val())) {
			$("input[name='price']").addClass('z-err');
			result = false;
		}
		return result;
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
	
	//初始化所有信息
	
	var Request = new Object();
	Request = GetRequest();
	var itemId = Request['itemId'];
	$.getJSON(homeworkWebBaseURL + 'api/selectOneItem',{itemId:itemId}, function(data) {
		// oData = {"username":"nimojs","userid":1}
		//$('#info').html('用户名：' + oData.username + '<br>用户ID：' + oData.userid);
		if (!data.success) {
			alert("selectOneItem 数据查询有误");
			window.location.href=homeworkWebBaseURL; 
			return;
		}
		var item = data.dataObject.item;
		var count = item.count;//销量
		var datatime = item.datatime;//时间
		var icon = item.icon;//图片
		var itemname = item.itemname;//商品名
		var pirce = item.pirce;//价格
		var remain = item.remain;//剩余量
		var status = item.status;//图片的来源
		var title = item.title;//摘要
		var decp=item.description;//描述
		var itemId=item.id;
		
		var role=data.dataObject.role;
		var overPrice=data.dataObject.overPrice;//以前的价格
		
		
		//初始化
		$("input[name='itemId']").val(item.id)
		$("input[name='title']").val(itemname);
		$("input[name='summary']").val(title);
		$("textarea[name='detail']").val(decp);
		$("input[name='price']").val(pirce);
		if(status==1){
			$(".imgpre img").attr({
				src : icon,
				alt : "网络图片"
			});
		}else{
			$(".imgpre img").attr({
				src : "/images/" + icon,
				alt : "本地图片"
			});
		}
	});
	
	function GetRequest() {
		var url = location.search; // 获取url中"?"符后的字串
		var theRequest = new Object();
		if (url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split("&");
			for (var i = 0; i < strs.length; i++) {
				theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			}
		}
		return theRequest;
	}
});
