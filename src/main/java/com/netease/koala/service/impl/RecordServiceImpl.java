package com.netease.koala.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.dao.RecordDao;
import com.netease.koala.model.Record;
import com.netease.koala.service.RecordService;

@Service("recordService")
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordDao recordDao;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public BatchResultDTO<Record> selectAllRecord(Integer userId) {
		BatchResultDTO<Record> result = new BatchResultDTO<Record>();
		try {
			List<Record> list = recordDao.selectAllRecord(userId);
			result.setSuccess(true);
			result.setModule(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectAllRecord 查询失败！");
			log.error("selectAllRecord 查询失败！");
		}
		return result;
	}

	@Override
	public BatchResultDTO<Record> selectOneRecord(Integer userId, Integer itemId) {
		BatchResultDTO<Record> result = new BatchResultDTO<Record>();
		try {
			List<Record> list = recordDao.selectOneRecord(userId, itemId);
			result.setSuccess(true);
			result.setModule(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("selectOneRecord 查询失败！");
			log.error("selectOneRecord 查询失败！");
		}
		return result;
	}

}
