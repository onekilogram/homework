package com.netease.koala.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.koala.bo.IntegerList;
import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.Record;
import com.netease.koala.model.ShopCar;
import com.netease.koala.model.ShopCarExtend;
import com.netease.koala.model.User;
import com.netease.koala.service.ItemService;
import com.netease.koala.service.RecordService;
import com.netease.koala.service.ShopCarService;

@Controller
@RequestMapping("api")
public class ShopCarController extends BaseController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ShopCarService shopCarService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private RecordService recordService;

	@ResponseBody
	@RequestMapping("insertShopCar")
	public String insertShopCar(Integer userId, Integer itemId) {

		try {
			ResultDTO<Integer> result = shopCarService.insertOneShopCar(userId, itemId);
			if (result.isSuccess()) {
				return responseControllerResultSuccess(result);
			} else {
				log.error("insertShopCar 数据异常！");
				return responseControllerResultError("insertShopCar 数据异常！");
			}

		} catch (Exception e) {
			log.error("insertShopCar 数据异常！");
			return responseControllerResultError("insertShopCar 数据异常！");
		}
	}

	@ResponseBody
	@RequestMapping("getShopCarByUserId")
	public String getShopCarByUserId(Integer userId) {

		try {
			BatchResultDTO<ShopCarExtend> result = shopCarService.selectShopCarByUserId(userId);
			if (result.isSuccess()) {
				return responseControllerResultSuccess(result);
			} else {
				log.error("getShopCarByUserId 数据异常！");
				return responseControllerResultError("getShopCarByUserId 数据异常！");
			}

		} catch (Exception e) {
			log.error("getShopCarByUserId 数据异常！");
			return responseControllerResultError("getShopCarByUserId 数据异常！");
		}
	}

	@ResponseBody
	@RequestMapping("changeCount")
	public String changeCount(Integer count, Integer shopCarId) {

		try {
			ResultDTO<Integer> result = shopCarService.updateOneShopCar(shopCarId, count);
			if (result.isSuccess()) {
				return responseControllerResultSuccess(result);
			} else {
				log.error("getShopCarByUserId 数据异常！");
				return responseControllerResultError("getShopCarByUserId 数据异常！");
			}

		} catch (Exception e) {
			log.error("getShopCarByUserId 数据异常！");
			return responseControllerResultError("getShopCarByUserId 数据异常！");
		}
	}

	@ResponseBody
	@RequestMapping("deleteShopCarById")
	public String deleteShopCarById(Integer shopCarId) {

		try {
			ResultDTO<Integer> result = shopCarService.deleteShopCarByShopCarId(shopCarId);
			if (result.isSuccess()) {
				return responseControllerResultSuccess(result);
			} else {
				log.error("deleteShopCarById 数据异常！");
				return responseControllerResultError("deleteShopCarById 数据异常！");
			}

		} catch (Exception e) {
			log.error("deleteShopCarById 数据异常！");
			return responseControllerResultError("deleteShopCarById 数据异常！");
		}
	}

	@ResponseBody
	@RequestMapping("pay")
	public String pay( @RequestParam("shopCarIdlist[]") List<Integer> shopCarIdlist, HttpServletRequest request) {

		try {
			User user = getLoginUser(request);
			if (user == null || user.getRole() != 1) {
				log.error("pay 数据异常！");
				return responseControllerResultError("用户登录异常！");
			}
			for (Integer shopCarId : shopCarIdlist) {
				ResultDTO<ShopCar> resultDTO = shopCarService.selectShopCarById(shopCarId);
				ShopCar shopCar = resultDTO.getModule();
				Record record = new Record();
				record.setItemid(shopCar.getItemid());
				record.setDatatime(new Date());
				record.setCount(shopCar.getCount());
				record.setPrice(
						itemService.selectOneItem(shopCar.getItemid()).getModule().getPirce());
				record.setStatus(1);
				record.setUserid(user.getId());
				ResultDTO<Integer> insetResult = recordService.insertRecord(record);

				ResultDTO<Integer> result = shopCarService.deleteShopCarByShopCarId(shopCarId);
				if (insetResult.isSuccess() && result.isSuccess()) {
					return responseControllerResultSuccess("OK");
				} else {
					log.error("selectShopCarById or insertRecord or deleteShopCarById 数据异常！");
					return responseControllerResultError(
							"selectShopCarById or insertRecord or deleteShopCarById 数据异常！");
				}
			}
			return responseControllerResultSuccess("OK");
		} catch (Exception e) {
			log.error("deleteShopCarById 数据异常！");
			return responseControllerResultError("deleteShopCarById 数据异常！");
		}
	}

}
