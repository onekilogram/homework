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
		$.getJSON(homeworkWebBaseURL + 'api/getRecordByUserId', {
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
				insertList(item);

			});
			setTotalPrice();

		});
	});
	function insertList(item) {
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
			img.attr("src", item.icon);
			img.attr("alt", "本地图片");
		}
		var a2 = tr.find("#a-itemname");
		a2.attr("href", "./show.html?itemId=" + item.itemid);
		a2.html(item.itemname);
		tr.find(".perPrice").html(item.pirce);
		tr.find(".kg-count").html(item.count);
		var datetime = tr.find(".v-datetime");
		datetime.html(formatDateTime(item.datatime));
		// alert(car.price);
		// alert(car.count);
		var totalPrice = parseFloat(item.pirce) * parseInt(item.count);
		tr.find(".totalPrice").html(new Number(totalPrice).toFixed(2));
		tbody.append("<tr id='tr-" + item.id + "'>" + tr.html() + "</tr>");
	}

	function setTotalPrice() {
		var table = $(".m-table");
		var trlist = table.find("tbody tr");
		var total = 0.0;
		$.each(trlist, function(i, it) {
			// alert("GG");
			var item = $(it)
			total = total + parseFloat(item.find(".totalPrice").html());
		});
		$("#totalValue").html(new Number(total).toFixed(2));
	}
	function formatDateTime(inputTime) {
		var date = new Date(inputTime);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		m = m < 10 ? ('0' + m) : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;
		var h = date.getHours();
		h = h < 10 ? ('0' + h) : h;
		var minute = date.getMinutes();
		var second = date.getSeconds();
		minute = minute < 10 ? ('0' + minute) : minute;
		second = second < 10 ? ('0' + second) : second;
		return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
	}
	
});
