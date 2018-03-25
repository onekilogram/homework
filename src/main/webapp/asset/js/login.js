/*初始化载入
 */
$(function($) {
	var loginForm = $("#loginForm");
	if (!loginForm) {
		return;
	}
	$("#loginButton").click(function() {
		var userName = $("input[name='userName']").val();
		var passWord = $("input[name='passWord']").val();
		//var value2 = md5(password.value);
		var loading = new Loading();
		/* 先检验消息 */
		if (!check()) {
			// 防止消失
			$("#loginButton").css("display", "block");
			return;
		}
		loading.show();
		// ajax
		var value2 = md5(passWord);
		$.ajax({
			type : "POST",
			data:{userName:userName,passWord:value2},
			dataType: 'json',
			url:homeworkWebBaseURL+'api/login',
			success : function(msg) {
				//loading.hide();
				//alert("Data Saved: " + msg);
				if(msg.success){
					loading.result('登录成功');
					window.location.href =homeworkWebBaseURL+'/';
				}else{
					loading.result('登录失败');
				}
			},
			error : function(msg) {
				loading.hide();
				loading.result('登录失败');
			}
		});

		$("#loginButton").css("display", "block");
	});

	// 点击用户名和密码红框消失
	$("input[name='userName']").click(function() {
		$("input[name='userName']").removeClass("z-err");
	});
	$("input[name='passWord']").click(function() {
		$("input[name='passWord']").removeClass("z-err");
	});
	// 检查用户名和密码是否输入
	function check() {
		var result = true;
		var userName = $("input[name='userName']").val();
		var passWord = $("input[name='passWord']").val();
		if (userName == null || userName == '') {
			$("input[name='userName']").addClass('z-err');
			result = false;
		}
		if (passWord == null || passWord == '') {
			$("input[name='passWord']").addClass('z-err');
			result = false;
		}
		return result;
	}
});
