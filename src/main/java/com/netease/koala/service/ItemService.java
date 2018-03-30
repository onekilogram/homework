package com.netease.koala.service;
/**
 * 商品服务类
 */

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.Item;

public interface ItemService {

	// 查询所有的商品
	BatchResultDTO<Item> selectAllItem();

	// 某用户购买的商品
	BatchResultDTO<Item> selectHaveItemByUserId(Integer userId);

	// 某用户未购买的商品
	BatchResultDTO<Item> selectNoHaveItemByUserId(Integer userId);

	// 删除某一个商品
	ResultDTO<Integer> deleteItemByItemId(Integer itemId);

	// 查询某一个商品
	ResultDTO<Item> selectOneItem(Integer itemId);

	// 修改售出数量和剩余数量
	ResultDTO<Integer> updateItem(Integer itemId, Integer count, Integer remain);

	ResultDTO<Integer> insertItem(Item record);

	// 修改售出数量和剩余数量
	ResultDTO<Integer> updateItem(Item item);

}
