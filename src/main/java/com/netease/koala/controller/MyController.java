package com.netease.koala.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("MyController")
public class MyController {
	private Logger log = LoggerFactory.getLogger(getClass());

	public String getJson() {

		return "";
	}
}
