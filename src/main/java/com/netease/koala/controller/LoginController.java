package com.netease.koala.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.koala.service.LoginService;

@Controller
@RequestMapping("api")
public class LoginController {

private Logger log = LoggerFactory.getLogger(getClass());
	
	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping("login")
	public String Login(String userName,String password){
		//判断角色
		return "";
	}
	@RequestMapping("logout")
	public String Logout(){
		//判断角色
		return "";
	}
}

