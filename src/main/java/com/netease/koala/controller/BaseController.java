package com.netease.koala.controller;

import com.alibaba.fastjson.JSON;
import com.netease.koala.common.ControllerResult;
import com.netease.koala.model.User;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 适用SpringMVC的的一些数据传递方法
 */
public class BaseController {

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获得当前登录用户的id
	 *
	 * @param request
	 * @return
	 */
	protected Integer getCurrentUserId(HttpServletRequest request) {
		return (Integer) request.getSession().getAttribute("userId");
	}

	/**
	 * 从请求中获得Long类型的参数
	 * 
	 * @param request
	 * @param name
	 */
	protected Long getLongParameterFromRequest(HttpServletRequest request, String name,
			Long defVal) {
		String tmp = request.getParameter(name);
		if (StringUtils.isEmpty(tmp)) {
			return defVal;
		}
		return Long.parseLong(tmp);
	}

	/**
	 * 从请求中获得Integer类型的参数
	 * 
	 * @param request
	 * @param name
	 */
	protected Integer getIntegerParameterFromRequest(HttpServletRequest request, String name,
			Integer defVal) {
		String tmp = request.getParameter(name);
		if (StringUtils.isEmpty(tmp)) {
			return defVal;
		}
		return Integer.parseInt(tmp);
	}

	/**
	 * JSON输出
	 *
	 * @param obj
	 */
	public String responseJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * ActionResult错误返回
	 */
	protected String responseControllerResultError(String errorMessage) {
		ControllerResult actionResult = new ControllerResult();
		actionResult.setSuccess(false);
		actionResult.setMsg(errorMessage);
		return responseJson(actionResult);
	}

	/**
	 * ActionResult成功返回
	 */
	protected String responseControllerResultSuccess(Object obj) {
		ControllerResult actionResult = new ControllerResult();
		actionResult.setSuccess(true);
		actionResult.setDataObject(obj);
		return responseJson(actionResult);
	}
	
	protected User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return user;
	}
}
