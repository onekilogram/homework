package com.netease.koala.service;

import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.User;

/**
 * @ClassName LoginService
 * @Description 用户登录的请求的Service处理
 * @author kg
 * @date 2018年3月21日 下午9:22:30
 */
public interface LoginService {
	//根据用户名查询
	public ResultDTO<User> selectByName(String userName);
	
}
