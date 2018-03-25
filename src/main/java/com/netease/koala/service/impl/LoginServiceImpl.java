package com.netease.koala.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.koala.common.ResultDTO;
import com.netease.koala.dao.UserDao;
import com.netease.koala.model.User;
import com.netease.koala.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private Logger log = Logger.getLogger(this.getClass());

	private final UserDao userDao;

	@Autowired
	public LoginServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	public ResultDTO<User> selectByName(String userName) {
		ResultDTO<User> result = new ResultDTO<User>();
		try {
			User user = userDao.selectByUserName(userName);
			result.setSuccess(true);
			result.setModule(user);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("按用户名查询失败！");
			log.error("按用户名查询失败！");
		}
		return result;
	}
}
