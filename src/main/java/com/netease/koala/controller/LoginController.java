package com.netease.koala.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.koala.model.User;
import com.netease.koala.service.LoginService;

@Controller
@RequestMapping("api")
public class LoginController extends BaseController{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private LoginService loginService;

	// @Autowired
	// public LoginController(LoginService loginServicekg) {
	// this.loginServicekg = loginServicekg;
	// }
	@ResponseBody
	@RequestMapping("login")
	public String Login(String userName, String password) {

		User user = loginService.selectByName(userName);
		//用户不存在或者密码错误，返回
		if(user==null || !user.getPassword().equals(password)){
			return responseControllerResultError(password);
		}
		// 判断角色
		return responseControllerResultSuccess(user);
	}

	@RequestMapping("logout")
	public String Logout() {
		// 判断角色
		return "";
	}
}
