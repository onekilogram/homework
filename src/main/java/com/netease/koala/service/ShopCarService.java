package com.netease.koala.service;
/**
 * 商品服务类
 */

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.ShopCar;

public interface ShopCarService {

	// 查询所有的购物车
	BatchResultDTO<ShopCar> selectAllShopCar();

	// 某用户所有的购物车的商品
	BatchResultDTO<ShopCar> selectShopCarByUserId(Integer userId);

	// 删除某一个商品
	ResultDTO<Integer> deleteShopCarByShopCarId(Integer shopCarId);
	
	// insert某一个购物车信息
	ResultDTO<Integer> insertOneShopCar(Integer userId,Integer itemId);
	
	// edit某一个购物车信息
	ResultDTO<Integer> editOneShopCar(Integer userId,Integer itemId,Integer count);

}
