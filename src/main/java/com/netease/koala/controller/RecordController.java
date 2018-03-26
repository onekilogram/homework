package com.netease.koala.controller;

import java.awt.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.Item;
import com.netease.koala.model.User;
import com.netease.koala.service.ItemService;
import com.netease.koala.service.RecordService;

@Controller
@RequestMapping("api")
public class RecordController extends BaseController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private RecordService recordService;

	
}
