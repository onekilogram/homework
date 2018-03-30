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
							//userId,itemId
							var userId = $("#userId").html();
							var itemId = $("#itemId").html();
							$.ajax({
								type : "POST",
								dataType: 'json',
								data:{itemId:itemId,userId:userId},
								url:homeworkWebBaseURL+'api/insertShopCar',
								success : function(msg) {
									//loading.hide();
									//alert("Data Saved: " + msg);
									if(msg.success){
										loading.result('加入购物车！');
										
									}else{
										loading.result('加入购物车异常',function(){location.href = './login.html';});
									}
								},
								error : function(msg) {
									loading.hide();
									loading.result('登录失败');
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
		$.getJSON(homeworkWebBaseURL + 'api/selectOneItem',{itemId:itemId}, function(data) {
			// oData = {"username":"nimojs","userid":1}
			//$('#info').html('用户名：' + oData.username + '<br>用户ID：' + oData.userid);
			if (!data.success) {
				alert("selectOneItem 数据查询有误");
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
			var role=data.dataObject.role;
			var overPrice=data.dataObject.overPrice;//以前的价格
			$(".g-doc #itemId").html(itemId);
			//游客
			var showContent=$("#showContent");
			if(status==1){//来自网络
				showContent.find(".img").find("img").attr("src",icon);
				showContent.find(".img").find("img").attr("alt","网络图片");
			}else{
				showContent.find(".img").find("img").attr("src","/images/"+icon);
				showContent.find(".img").find("img").attr("alt","本地图片");
			}
			showContent.find(".cnt .itemName").html(itemname);
			showContent.find(".cnt .title").html(title);
			showContent.find(".cnt .price .v-value").html(pirce);
			showContent.find(".cnt .saleCount").html(count);
			$(".g-doc .n-detail").find("h2").html(decp);
			if(role==1){//买家
				if(overPrice==-1){//已购
					$(".cnt .kg").html("<button class='u-btn u-btn-primary' data-buy='1'>加入购物车</button>");
					$(".cnt .kg").css("display","block");
				}else{//未购买
					$(".cnt .kg").html("<span class='u-btn u-btn-primary z-dis'>已购买</span>"
							+"<span class='buyprice'>当时购买价格：¥ "+overPrice+"</span>");
					$(".cnt .kg").css("display","block");
				}
			}else if(role==2){ //卖家
				$(".cnt .kg").html("<a href='./edit.html?itemId="+item.id+" 'class='u-btn u-btn-primary'>编 辑</a>");
				$(".cnt .kg").css("display","block");
			}
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