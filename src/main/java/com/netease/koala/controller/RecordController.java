package com.netease.koala.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.model.ShopCarExtend;
import com.netease.koala.service.RecordService;
/**
 * @ClassName RecordController 
 * @Description 消费记录查询
 * @author kg 
 */
@Controller
@RequestMapping("api")
public class RecordController extends BaseController {

	private Logger log = LoggerFactory.getLogger(getClass());

	
	@Autowired
	private RecordService recordService;

	@ResponseBody
	@RequestMapping("getRecordByUserId")
	public String getRecordByUserId(Integer userId){
		
		BatchResultDTO<ShopCarExtend> result = recordService.selectAllRecord(userId);
		if (result.isSuccess()) {
			return responseControllerResultSuccess(result);
		} else {
			log.error("getRecordByUserId 查询数据异常！");
			return responseControllerResultError("getRecordByUserId 查询数据异常！");
		}
	}
	
}
