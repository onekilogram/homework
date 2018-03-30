package com.netease.koala.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.User;
import com.netease.koala.service.LoginService;
/**
 * @ClassName LoginController 
 * @Description 登录与注销
 * @author kg 
 */
@Controller
@RequestMapping("api")
public class LoginController extends BaseController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private LoginService loginService;

	@ResponseBody
	@RequestMapping("login")
	public String Login(String userName, String passWord, HttpServletRequest request) {
		ResultDTO<User> resultDTO = loginService.selectByName(userName);
		if (resultDTO.isSuccess()) {
			User user = resultDTO.getModule();
			// 用户不存在或者密码错误，返回
			if (user == null || !user.getPassword().equals(passWord)) {
				log.error("用户名或密码错误！");
				return responseControllerResultError("用户名或密码错误！");
			} else {
				// 登录成功
				/* 重要的session处理 */
				// 登陆成功
				// 创建session对象
				HttpSession session = request.getSession();
				// 把用户数据保存在session域对象中
				session.setAttribute("user", user);
				// 跳转到用户主页
				return responseControllerResultSuccess("登录成功！");
			}
		} else {
			log.error("查询数据异常！");
			return responseControllerResultError("查询数据异常！");
		}
	}

	@RequestMapping("logout")
	public String Logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/"+"asset/html/login.html";
	}
	
	@ResponseBody
	@RequestMapping("getuerinfo")
	public String initIndexHtml(HttpServletRequest request) {
		try {
			// 先确定角色
			// 创建session对象
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			HashMap<String, Object> map = new HashMap<>();
			if (user == null) { // 没有登录
				map.put("role", 0);
			} else  { // 买家
				map.put("role", user.getRole());
				map.put("userId", user.getId());
				map.put("userName",user.getUsername());
			} 
			return responseControllerResultSuccess(map);
		} catch (Exception e) {
			// TODO: handle exception
			return responseControllerResultError("初始化index head失败！");
		}
	}
	
}
