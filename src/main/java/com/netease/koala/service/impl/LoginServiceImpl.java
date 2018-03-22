package com.netease.koala.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.koala.dao.UserDao;
import com.netease.koala.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private Logger log = Logger.getLogger(this.getClass());

	private final UserDao userDao;

	@Autowired
	public LoginServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
}
