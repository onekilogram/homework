(function(w,d,u){
	var showContent = util.get('showContent');
	if(!showContent){
		return;
	}
	var loading = new Loading();
	var layer = new Layer();
	var page = {
		init:function(){
			//初始化页面
			initPage();
			showContent.addEventListener('click',function(e){
				var ele = e.target;
				var buy = ele && ele.dataset.buy;
				if(buy){
					layer.reset({
						content:'确认购买本内容吗？',
						onconfirm:function(){
							layer.hide();
							loading.show();
							ajax({
								data:{id:buy},
								url:'/api/buy',
								success:function(result){
									loading.result('购买成功',function(){location.href = './account.html';});
								},
								error:function(message){
									loading.result(message||'购买失败');
								}
							});
						}.bind(this)
					}).show();
					return;
				}
			}.bind(this),false);
		}
	};
	function initPage(){
		var Request = new Object();
		Request = GetRequest();
		var itemId = Request['itemId'];
		$.getJSON(homeworkWebBaseURL + 'api/selectOneItem', function(data) {
			// oData = {"username":"nimojs","userid":1}
			//$('#info').html('用户名：' + oData.username + '<br>用户ID：' + oData.userid);
			if (!data.success) {
				alert("selectOneItem 数据查询有误");
				return;
			}
			var item = dataObject.module;
			
		/*	count: 89,
			datatime: 1521464534000,
			icon: "http://nec.netease.com/img/s/1.jpg",
			id: 4,
			itemname: "java虚拟机2",
			pirce: 34,
			remain: 99,
			status: 1,
			title: "title"*/
			var count = item.count;//销量
			var datatime = item.datatime;//时间
			var icon = item.icon;//图片
			var itemname = item.itemname;//商品名
			var pirce = item.pirce;//价格
			var remain = item.remain;//剩余量
			var status = item.status;//图片的来源
			var title = item.title;//详细内容
			
			
			
			
			
			
			
			
			
			
			
		});
			
		
		
		
		
		
		
		
	}
	
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
	page.init();
})(window,document);