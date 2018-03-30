package com.netease.koala.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.netease.koala.model.ShopCar;
import com.netease.koala.model.ShopCarExtend;

public interface ShopCarDao {
	
	int deleteByPrimaryKey(Integer id);

	int insert(ShopCar record);

	int insertSelective(ShopCar record);

	ShopCar selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ShopCar record);

	int updateByPrimaryKey(ShopCar record);

	/******************** 以上是自动生成的代码，以下是业务逻辑所需要 *****************************/

	// 查询所有的购物车
	List<ShopCar> selectAllShopCar();

	// 某用户所有的购物车的商品
	List<ShopCarExtend> selectShopCarByUserId(Integer userid);

	// 删除某一个购物车信息
	int deleteShopCarByShopCarId(Integer id);
	
	//查询该物品是否存在某用户的购物车中
	ShopCar selectByUseridAndItemid(@Param("userid")Integer userId,@Param("itemid") Integer itemId);

}