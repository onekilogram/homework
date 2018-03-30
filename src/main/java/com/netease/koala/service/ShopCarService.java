package com.netease.koala.service;
/**
 * 购物车服务类
 */

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.ShopCar;
import com.netease.koala.model.ShopCarExtend;

public interface ShopCarService {
	// 查询所有的购物车
	BatchResultDTO<ShopCar> selectAllShopCar();

	// 某用户所有的购物车的商品
	BatchResultDTO<ShopCarExtend> selectShopCarByUserId(Integer userId);

	// 删除某一个商品
	ResultDTO<Integer> deleteShopCarByShopCarId(Integer shopCarId);

	// insert某一个购物车信息
	ResultDTO<Integer> insertOneShopCar(Integer userId, Integer itemId);

	// edit某一个购物车信息
	ResultDTO<Integer> updateOneShopCar(Integer id, Integer count);

	// 主键查询购物车的商品
	ResultDTO<ShopCar> selectShopCarById(Integer id);

}
