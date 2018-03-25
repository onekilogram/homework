package com.netease.koala.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.dao.ItemDao;
import com.netease.koala.model.Item;
import com.netease.koala.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public BatchResultDTO<Item> selectAllItem() {
		BatchResultDTO<Item> result = new BatchResultDTO<Item>();
		try {
			List<Item> list = itemDao.selectAllItem();
			result.setSuccess(true);
			result.setModule(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectAllItem 查询失败！");
			log.error("selectAllItem 查询失败！");
		}
		return result;
	}

	@Override
	public BatchResultDTO<Item> selectHaveItemByUserId(Integer userId) {
		BatchResultDTO<Item> result = new BatchResultDTO<Item>();
		try {
			List<Item> list = itemDao.selectHaveItemByUserId(userId);
			result.setSuccess(true);
			result.setModule(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectHaveItemByUserId 查询失败！");
			log.error("selectHaveItemByUserId 查询失败！");
		}
		return result;
	}

	@Override
	public BatchResultDTO<Item> selectNoHaveItemByUserId(Integer userId) {
		BatchResultDTO<Item> result = new BatchResultDTO<Item>();
		try {
			List<Item> list = itemDao.selectNoHaveItemByUserId(userId);
			result.setSuccess(true);
			result.setModule(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectNoHaveItemByUserId 查询失败！");
			log.error("selectNoHaveItemByUserId 查询失败！");
		}
		return result;
	}

	@Override
	public ResultDTO<Integer> deleteItemByItemId(Integer itemId) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			Integer rs = itemDao.deleteByPrimaryKey(itemId);
			result.setSuccess(true);
			result.setModule(rs);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("deleteItemByItemId 删除失败！");
			log.error("deleteItemByItemId 删除失败！");
		}
		return result;
	}

	@Override
	public ResultDTO<Item> selectOneItem(Integer itemId) {
		ResultDTO<Item> result = new ResultDTO<Item>();
		try {
			Item item = itemDao.selectByPrimaryKey(itemId);
			result.setSuccess(true);
			result.setModule(item);
			if(item==null){
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectOneItem 失败！");
			log.error("selectOneItem 失败！");
		}
		return result;
	}
}
