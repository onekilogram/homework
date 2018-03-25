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

@Controller
@RequestMapping("api")
public class ItemController extends BaseController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ItemService itemService;

	@ResponseBody
	@RequestMapping("initIndex")
	public String initIndexHtml(HttpServletRequest request) {
		try {
			// 先确定角色
			// 创建session对象
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			HashMap<String, Object> map = new HashMap<>();
			if (user == null) { // 没有登录
				BatchResultDTO<Item> result = itemService.selectAllItem();
				if (result.isSuccess()) {
					map.put("list", result.getModule());
				} else {
					map.put("list", new List());// 返回一个空
				}
				map.put("role", 0);
			} else if (user.getRole() == 1) { // 买家
				BatchResultDTO<Item> result = itemService.selectHaveItemByUserId(user.getId());
				if (result.isSuccess()) {
					map.put("havelist", result.getModule());
				} else {
					map.put("havelist", new List());// 返回一个空
				}
				BatchResultDTO<Item> result2 = itemService.selectNoHaveItemByUserId(user.getId());
				if (result2.isSuccess()) {
					map.put("nohavelist", result2.getModule());
				} else {
					map.put("nohavelist", new List());// 返回一个空
				}
				map.put("role", user.getRole());
			} else {// 卖家
				BatchResultDTO<Item> result = itemService.selectAllItem();
				if (result.isSuccess()) {
					map.put("list", result.getModule());
				} else {
					map.put("list", new List());// 返回一个空
				}
				map.put("role", user.getRole());
			}
			return responseControllerResultSuccess(map);
		} catch (Exception e) {
			// TODO: handle exception
			return responseControllerResultError("初始化index界面失败！");
		}
	}

	@ResponseBody
	@RequestMapping("allitem")
	public String getAllItem() {
		BatchResultDTO<Item> result = itemService.selectAllItem();
		if (result.isSuccess()) {
			return responseControllerResultSuccess(result);
		} else {
			log.error("查询数据异常！");
			return responseControllerResultError("查询数据异常！");
		}
	}

	@ResponseBody
	@RequestMapping("hiveitem")
	public String getHaveItemByUserId(Integer userId) {
		BatchResultDTO<Item> result = itemService.selectHaveItemByUserId(userId);
		if (result.isSuccess()) {
			return responseControllerResultSuccess(result);
		} else {
			log.error("查询数据异常！");
			return responseControllerResultError("查询数据异常！");
		}
	}

	@ResponseBody
	@RequestMapping("nohaveitem")
	public String getNoHaveItemByUserId(Integer userId) {
		BatchResultDTO<Item> result = itemService.selectNoHaveItemByUserId(userId);
		if (result.isSuccess()) {
			return responseControllerResultSuccess(result);
		} else {
			log.error("查询数据异常！");
			return responseControllerResultError("查询数据异常！");
		}
	}
	@ResponseBody
	@RequestMapping("deleteItem")
	public String deleteItemById(Integer itemId) {
		ResultDTO<Integer> result= itemService.deleteItemByItemId(itemId);
		if (result.isSuccess()) {
			return responseControllerResultSuccess(result);
		} else {
			log.error("删除数据异常！");
			return responseControllerResultError("删除数据异常！");
		}
	}
	@ResponseBody
	@RequestMapping("selectOneItem")
	public String selectOneItem(Integer itemId,HttpServletRequest request) {
		ResultDTO<Item> result= itemService.selectOneItem(itemId);
		if (result.isSuccess()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("role", 0);
			map.put("overPrice", -1);
			User user=getLoginUser(request);
			if(user!=null){
				map.put("role", user.getRole());
			}
			
			
			
			return responseControllerResultSuccess(result);
		} else {
			log.error("selectOneItem 数据异常！");
			return responseControllerResultError("selectOneItem 数据异常！");
		}
	}
}
