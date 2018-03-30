package com.netease.koala.dao;

import com.netease.koala.model.User;

public interface UserDao {
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /********************以上是自动生成的代码，以下是业务逻辑所需要*****************************/
    User selectByUserName(String userName);
}