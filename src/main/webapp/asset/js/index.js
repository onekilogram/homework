/*初始化载入
 */
$(function($) {
	//logout绑定事件
	$("#logout").attr("href", homeworkWebBaseURL + "api/logout");
	$("#all_content").click(function() {
		$("#nobuy_list").css("display", "none");
		$("#all_list").css("display", "block");
		$("#all_li").addClass('z-sel');
		$("#nobuy_li").removeClass('z-sel');
	});

	$("#nobuy_content").click(function() {
		$("#all_list").css("display", "none");
		$("#nobuy_list").css("display", "block");
		$("#nobuy_li").addClass('z-sel');
		$("#all_li").removeClass('z-sel');
	});

	//三种状态的显示，1没有登录   2买家登录  3卖家登录

	$.getJSON(homeworkWebBaseURL + 'api/initIndex', function(data) {
		// oData = {"username":"nimojs","userid":1}
		//$('#info').html('用户名：' + oData.username + '<br>用户ID：' + oData.userid);
		if (!data.success) {
			return;
		}
		var dataObject = data.dataObject;
		var role = dataObject.role;
		if (role == 0) { //游客状态
			$.each(dataObject.list, function(i, item) {
				//向plist插入数据
				insertListItem_0(item);
			});
		} else if (role == 1) {
			$("#nobuy_li").css("display", "block");
			$.each(dataObject.havelist, function(i, item) {
				//向plist插入数据
				insertListItem_1_0(item);
			});
			$.each(dataObject.nohavelist, function(i, item) {
				//向plist插入数据
				insertListItem_1_1(item);
			});
		} else {
			$("#all_content").html("已售出");
			$("#nobuy_content").html("未售出");
			$("#nobuy_li").css("display", "block");
			//已售出 与未售出
			$.each(dataObject.list, function(i, item) {
				//向list插入数据
				insertListItem_2_1(item);
			});
			//添加删除事件
			$(".deleteButton").click(function() {
				//var itemId = $(this).next().html();
				var itemId = $(this).parent().find(".itemId").html();
				var layer = new Layer();
				var loading = new Loading();
				layer.reset({
					content : '确定要删除该内容吗？',
					onconfirm : function() {
						layer.hide();
						loading.show();
						$.ajax({
							type : "POST",
							data:{itemId:itemId},
							dataType: 'json',
							url:homeworkWebBaseURL+'api/deleteItem',
							success : function(msg) {
								//loading.hide();
								//alert("Data Saved: " + msg);
								if(msg.success){
									loading.result('删除成功!');
									delItemNode(itemId);
								}else{
									loading.result('删除失败!');
								}
							},
							error : function(msg) {
								loading.hide();
								loading.result('登录失败');
							}
						});
					}.bind(this)
				}).show();

			});
		}
	});
	
	function delItemNode(itemId){
		var item = $("#li-"+itemId);
		item.remove();
	}
	//
	function insertListItem_0(item) {
		var model = $(".limodel");
		var count = item.count;//销量
		var icon = item.icon;//图片
		var type = item.status;//1代表网络，0代表本地
		var itemname = item.itemname;//名字
		var price = item.pirce;
		var id = item.id;//id编号

		model.find(".link").attr("href", "./show.html?itemId=" + id);
		if (type == 1) {
			model.find(".img .classimg").attr({
				src : icon,
				alt : "在线图片"
			});
		} else {
			model.find(".img .classimg").attr({
				src : "picture" + icon,
				alt : "本地图片"
			});
		}
		model.find(".title").html(itemname);
		model.find(".v-value").html(price);
		model.find(".had").remove();
		$("#plist").append("<li>" + model.html() + "</li>");
	}

	function insertListItem_1_0(item) {
		var model = $(".limodel");
		var count = item.count;//销量
		var icon = item.icon;//图片
		var type = item.status;//1代表网络，0代表本地
		var itemname = item.itemname;//名字
		var price = item.pirce;
		var id = item.id;//id编号

		model.find(".link").attr("href", "./show.html?itemId=" + id);
		if (type == 1) {
			model.find(".img .classimg").attr({
				src : icon,
				alt : "在线图片"
			});
		} else {
			model.find(".img .classimg").attr({
				src : "picture" + icon,
				alt : "本地图片"
			});
		}
		model.find(".title").html(itemname);
		model.find(".v-value").html(price);
		model.find(".flag").html("已购买");
		$("#plist").append("<li>" + model.html() + "</li>");
	}
	function insertListItem_1_1(item) {
		var model = $(".limodel");
		var count = item.count;//销量
		var icon = item.icon;//图片
		var type = item.status;//1代表网络，0代表本地
		var itemname = item.itemname;//名字
		var price = item.pirce;
		var id = item.id;//id编号

		model.find(".link").attr("href", "./show.html?itemId=" + id);
		if (type == 1) {
			model.find(".img .classimg").attr({
				src : icon,
				alt : "在线图片"
			});
		} else {
			model.find(".img .classimg").attr({
				src : "picture" + icon,
				alt : "本地图片"
			});
		}
		model.find(".title").html(itemname);
		model.find(".v-value").html(price);
		model.find(".had").remove();
		$("#ulist").append("<li>" + model.html() + "</li>");
	}

	function insertListItem_2_1(item) {
		var model = $(".limodel");
		var count = item.count;//销量
		var icon = item.icon;//图片
		var type = item.status;//1代表网络，0代表本地
		var itemname = item.itemname;//名字
		var price = item.pirce;
		var id = item.id;//id编号

		model.find(".link").attr("href", "./show.html?itemId=" + id);
		//model.attr('id','li-'+id);
		if (type == 1) {
			model.find(".img .classimg").attr({
				src : icon,
				alt : "在线图片"
			});
		} else {
			model.find(".img .classimg").attr({
				src : "picture" + icon,
				alt : "本地图片"
			});
		}
		model.find(".title").html(itemname);
		model.find(".v-value").html(price);
		model.find(".itemId").html(id);
		if (count > 0) {
			model.find(".had").css("display", "block");
			model.find(".flag").html("已售出");
			model.find(".saleCount").html("已售" + count);
			model.find(".deleteButton").css("display", "none");
			$("#plist").append("<li id='li-"+id+"'>" + model.html() + "</li>");
		} else {
			model.find(".had").css("display", "none");
			model.find(".saleCount").html("");
			model.find(".deleteButton").css("display", "block");
			$("#ulist").append("<li id='li-"+id+"'>" + model.html() + "</li>");
		}
	}
});
