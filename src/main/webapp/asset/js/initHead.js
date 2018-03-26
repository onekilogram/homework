/*初始化载入
 */
$(function($) {
	//三种状态的显示，1没有登录   2买家登录  3卖家登录
	$.getJSON(homeworkWebBaseURL + 'api/getuerinfo', function(data) {
		if (!data.success) {
			return;
		}
		var dataObject = data.dataObject;
		var role = dataObject.role;
		var userName = dataObject.userName;
		var userId = dataObject.userId;
		var userDiv = $(".user");
		var navUl = $(".nav");
		
		if (role == 0) { //游客状态
			userDiv.append("请<a href='./login.html\'>[登录]</a>");
		} else if (role == 1) {
			//有账单和购物车
			userDiv.append('买家您好！'+userName+'<a href='+homeworkWebBaseURL+'api/logout>[退出]</a>');
			navUl.append("<li><a href='./account.html'>账务</a>");
			navUl.append("<li><a href='./account.html'>购物车</a>");
			$(".g-doc #userId").html(userId);
		} else {
			//有发布
			userDiv.append('卖家您好！'+userName+'<a href='+homeworkWebBaseURL+'api/logout>[退出]</a>');
			navUl.append("<li><a href='./public.html'>发布</a>");
			$(".g-doc #userId").html(userId);
		}
	});
});
