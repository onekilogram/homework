package com.netease.koala.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.dao.ShopCarDao;
import com.netease.koala.model.Item;
import com.netease.koala.model.ShopCar;
import com.netease.koala.service.ShopCarService;

@Service("shopCarService")
public class ShopCarServiceImpl implements ShopCarService {

	@Autowired
	private ShopCarDao shopCarDao;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public BatchResultDTO<ShopCar> selectAllShopCar() {
		// TODO Auto-generated method stub
		
		BatchResultDTO<ShopCar> result = new BatchResultDTO<ShopCar>();
		try {
			List<ShopCar> list = shopCarDao.selectAllShopCar();
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
	public BatchResultDTO<ShopCar> selectShopCarByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultDTO<Integer> deleteShopCarByShopCarId(Integer shopCarId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultDTO<Integer> insertOneShopCar(Integer userId, Integer itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultDTO<Integer> editOneShopCar(Integer userId, Integer itemId, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

}
