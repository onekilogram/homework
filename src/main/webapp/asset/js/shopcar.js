/*初始化载入
 */
$(function($) {
	// 三种状态的显示，1没有登录 2买家登录 3卖家登录
	$.getJSON(homeworkWebBaseURL + 'api/getuerinfo', function(data) {
		if (!data.success) {
			return;
		}
		var dataObject = data.dataObject;
		var userId = dataObject.userId;
		// alert(userId);
		$.getJSON(homeworkWebBaseURL + 'api/getShopCarByUserId', {
			userId : userId
		}, function(json) {
			if (!json.success) {
				return;
			}
			var dataOb = json.dataObject;
			var list = dataOb.module;
			// 初始化数据
			$.each(dataOb.module, function(i, item) {
				// 向plist插入数据
				insertListShopCar(item);

			});
			setTotalPrice();
			$(".deleteButton").click(function() {

				var tr = $(this).parent().parent();
				var shopCarId = getShopCarId(tr.attr("id"));
				// 请求删除
				var layer = new Layer();
				var loading = new Loading();
				layer.reset({
					content : '确定要删除吗？',
					onconfirm : function() {
						layer.hide();
						loading.show();
						$.ajax({
							type : "POST",
							data : {
								shopCarId : shopCarId
							},
							dataType : 'json',
							url : homeworkWebBaseURL + 'api/deleteShopCarById',
							success : function(msg) {
								// loading.hide();
								// alert("Data Saved: " + msg);
								if (msg.success) {
									loading.result('删除成功!');
									tr.remove();
									setTotalPrice();
								} else {
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

			// + 操作
			$(".moreNum").click(
					function() {
						var tr = $(this).parent().parent().parent();
						var count = $(this).parent().find(".count");
						count.html(parseInt(count.html()) + 1);
						// 需要有很多操作

						// 页面上的所有值
						var perPrice = parseFloat(tr.find(".perPrice").html());
						var count = parseInt(count.html());
						tr.find(".totalPrice").html(
								new Number((perPrice * count)).toFixed(2));
						// alert(perPrice.html());
						setTotalPrice();

						// 后台逻辑
						// 后台业务操作
						changeCount(count, getShopCarId(tr.attr("id")));

					});

			// - 操作
			$(".lessNum").click(
					function() {

						var tr = $(this).parent().parent().parent();
						var count = $(this).parent().find(".count");
						var cur = parseInt(count.html()) - 1;
						if (cur < 1) {
							count.html(1);
							tr.find(".deleteButton").click();
						} else {
							count.html(parseInt(count.html()) - 1);
						}
						// 需要有很多操作

						// 页面上的所有值
						var perPrice = parseFloat(tr.find(".perPrice").html());
						var count = parseInt(count.html());
						tr.find(".totalPrice").html(
								new Number((perPrice * count)).toFixed(2));
						// alert(perPrice.html());
						setTotalPrice();

						// 后台业务操作
						changeCount(count, getShopCarId(tr.attr("id")));

					});
			$('.i-checkbox').click(function() {
				setTotalPrice();
			});
		});
	});

	function insertListShopCar(item) {
		// alert(car);
		var tbody = $("#t-tbody");
		var tr = $("#tr-5");
		var type = item.itemstatus;
		var a1 = tr.find("#a-img");
		a1.attr("href", "./show.html?itemId=" + item.itemid);
		var img = a1.find("#i-img");
		// alert(item.icon);
		if (type == 1) {// 网络图片
			img.attr("src", item.icon);
			img.attr("alt", "网络图片");
		} else {
			img.attr("src", "/images/"+item.icon);
			img.attr("alt", "本地图片");
		}
		var a2 = tr.find("#a-itemname");
		a2.attr("href", "./show.html?itemId=" + item.itemid);
		a2.html(item.itemname);
		tr.find(".perPrice").html(item.pirce);
		tr.find(".kg-count").html(item.count);
		// alert(car.price);
		// alert(car.count);
		var totalPrice = parseFloat(item.pirce) * parseInt(item.count);
		tr.find(".totalPrice").html(new Number(totalPrice).toFixed(2));
		tbody.append("<tr id='tr-" + item.id + "'>" + tr.html() + "</tr>");
	}

	function getShopCarId(trId) {
		var arr = trId.split('-');
		return arr[1];
	}

	function setTotalPrice() {
		var table = $(".m-table");
		var trlist = table.find("tbody tr");
		var total = 0.0;
		$.each(trlist, function(i, it) {
			// alert("GG");
			var item = $(it)
			var check = item.find(".i-checkbox");
			if (check.is(':checked')) {
				total = total + parseFloat(item.find(".totalPrice").html());
			}
		});
		$("#totalValue").html(new Number(total).toFixed(2));
	}
	function changeCount(count, shopCarId) {
		var loading = new Loading();
		$.ajax({
			type : "POST",
			data : {
				count : count,
				shopCarId : shopCarId
			},
			dataType : 'json',
			url : homeworkWebBaseURL + 'api/changeCount',
			success : function(msg) {
				if (msg.success) {
				} else {
					loading.show();
					loading.result('修改失败!');
				}
			},
			error : function(msg) {
				loading.hide();
				loading.result('修改失败');
			}
		});
	}
	$(".u-btn-primary").click(function() {
		// 请求支付
		var money = $("#totalValue").html();
		var cont='确定要购买选中的商品吗？<br><br>共计' + money + '元';
		if(money==0){
			cont='请选择要购买的商品！！';
		}
		var layer = new Layer();
		var loading = new Loading();
		layer.reset({
			content : cont,
			onconfirm : function() {
				layer.hide();
				loading.show();
				var table = $(".m-table");
				var trlist = table.find("tbody tr");
				var shopCarIdlist = new Array();
				for(var i=0;i<trlist.size();i++){
					var item = $(trlist[i])
					var check = item.find(".i-checkbox");
					if (check.is(':checked')) {// 选中的东西
						shopCarIdlist.push(getShopCarId(item.attr("id")));
					}
					
				}
				$.ajax({
					type : "post",
					dataType : 'json',
					data : {
						shopCarIdlist : shopCarIdlist
					},
					url : homeworkWebBaseURL + 'api/pay',
					success : function(msg) {
						if (msg.success) {
							loading.show();
							loading.result('支付成功!');
							$.each(trlist, function(i, it) {
							var item = $(it)
							var check = item.find(".i-checkbox");
							if (check.is(':checked')) {// 选中的东西
								item.remove();
							}
							setTotalPrice();
 						});
						} else {
							loading.hide();
							loading.result('支付失败!');
						}
					},
					error : function(msg) {
						loading.hide();
						loading.result('支付失败');
					}
				});

				loading.hide();
			}.bind(this)
		}).show();
	});
});
