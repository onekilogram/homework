package com.netease.koala.service;
/**
 * 购物记录服务类
 */

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.Record;
import com.netease.koala.model.ShopCarExtend;

public interface RecordService {
	// 查询所有的记录
	BatchResultDTO<ShopCarExtend> selectAllRecord(Integer userId);

	//查询是否存在 userId  itemId
	BatchResultDTO<Record> selectOneRecord(Integer userId,Integer itemId);
	
	//插入一条记录
	ResultDTO<Integer> insertRecord(Record record);
 
}
