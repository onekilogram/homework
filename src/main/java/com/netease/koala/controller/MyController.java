package com.netease.koala.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netease.koala.common.Model1;


@Controller
@RequestMapping("MyController")
public class MyController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@RequestMapping("getJson")
	public String getJson() {

		Model1 model = new Model1(21,"kg");
		
		String result = JSON.toJSONString(model);
		log.info("getSjon");
		return result;
	}
}
