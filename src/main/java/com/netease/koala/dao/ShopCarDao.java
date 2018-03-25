package com.netease.koala.dao;

import com.netease.koala.model.ShopCar;

public interface ShopCarDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);
    
    /********************以上是自动生成的代码，以下是业务逻辑所需要*****************************/
}