package com.netease.koala.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.dao.ShopCarDao;
import com.netease.koala.model.ShopCar;
import com.netease.koala.model.ShopCarExtend;
import com.netease.koala.service.ShopCarService;

@Service("shopCarService")
public class ShopCarServiceImpl implements ShopCarService {

	@Autowired
	private ShopCarDao shopCarDao;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public BatchResultDTO<ShopCar> selectAllShopCar() {

		BatchResultDTO<ShopCar> result = new BatchResultDTO<ShopCar>();
		try {
			List<ShopCar> list = shopCarDao.selectAllShopCar();
			result.setSuccess(true);
			result.setModule(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectAllShopCar 查询失败！");
			log.error("selectAllShopCar 查询失败！");
		}
		return result;
	}

	@Override
	public BatchResultDTO<ShopCarExtend> selectShopCarByUserId(Integer userId) {
		BatchResultDTO<ShopCarExtend> result = new BatchResultDTO<ShopCarExtend>();
		try {
			List<ShopCarExtend> list = shopCarDao.selectShopCarByUserId(userId);
			result.setSuccess(true);
			result.setModule(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectShopCarByUserId 查询失败！");
			log.error("selectShopCarByUserId 查询失败！");
		}
		return result;
	}

	@Override
	public ResultDTO<Integer> deleteShopCarByShopCarId(Integer shopCarId) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			Integer rs = shopCarDao.deleteByPrimaryKey(shopCarId);
			result.setSuccess(true);
			result.setModule(rs);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("deleteShopCarByShopCarId 失败！");
			log.error("deleteShopCarByShopCarId 失败！");
		}
		return result;
	}

	@Override
	public ResultDTO<Integer> insertOneShopCar(Integer userId, Integer itemId) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		ShopCar shopCar = new ShopCar();
		try {
			ShopCar temp = shopCarDao.selectByUseridAndItemid(userId, itemId);
			Integer rs = 1;
			if (temp == null) {
				shopCar.setCount(1);
				shopCar.setDatatime(new Date());
				shopCar.setUserid(userId);
				shopCar.setItemid(itemId);
				shopCar.setStatus(1);
				rs = shopCarDao.insert(shopCar);
			}
			result.setSuccess(true);
			result.setModule(rs);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("insertOneShopCar 失败！");
			log.error("insertOneShopCar 失败！");
		}
		return result;
	}

	@Override
	public ResultDTO<Integer> updateOneShopCar(Integer id, Integer count) {
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		try {
			ShopCar shopCar = new ShopCar();
			shopCar.setCount(count);
			shopCar.setId(id);
			Integer rs = shopCarDao.updateByPrimaryKeySelective(shopCar);
			result.setSuccess(true);
			result.setModule(rs);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("updateOneShopCar 失败！");
			log.error("updateOneShopCar 失败！");
		}
		return result;
	}

	@Override
	public ResultDTO<ShopCar> selectShopCarById(Integer id) {
		ResultDTO<ShopCar> result = new ResultDTO<ShopCar>();
		try {
			ShopCar shopCar = shopCarDao.selectByPrimaryKey(id);
			result.setSuccess(true);
			result.setModule(shopCar);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectShopCarById 查询失败！");
			log.error("selectShopCarById 查询失败！");
		}
		return result;
	}

}
