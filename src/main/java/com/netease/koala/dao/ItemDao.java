package com.netease.koala.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.netease.koala.model.Item;

public interface ItemDao {
	
	int deleteByPrimaryKey(Integer id);

	int insert(Item record);

	int insertSelective(Item record);

	Item selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Item record);

	int updateByPrimaryKey(Item record);

	/******************** 以上是自动生成的代码，以下是业务逻辑所需要 *****************************/
	// 查询所有的商品
	List<Item> selectAllItem();
	
	// 某用户购买的商品
	List<Item> selectHaveItemByUserId(Integer userId);

	// 某用户未购买的商品
	List<Item> selectNoHaveItemByUserId(Integer userId);
	
	// 某用户未购买的商品
	int updateItemCount(@Param("itemid")Integer itemId,@Param("count") Integer count,@Param("remain") Integer remain);
	
	
}