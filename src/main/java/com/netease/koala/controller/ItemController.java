package com.netease.koala.controller;

import java.awt.List;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.netease.koala.common.BatchResultDTO;
import com.netease.koala.common.ResultDTO;
import com.netease.koala.model.Item;
import com.netease.koala.model.Record;
import com.netease.koala.model.User;
import com.netease.koala.service.ItemService;
import com.netease.koala.service.RecordService;
import com.netease.koala.utils.FileUpload;

@Controller
@RequestMapping("api")
public class ItemController extends BaseController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ItemService itemService;

	@Autowired
	private RecordService recordService;

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
	@RequestMapping(value = "deleteItem", method = RequestMethod.POST)
	public String deleteItemById(Integer itemId,HttpServletRequest request) {
		User user = getLoginUser(request);

		if (user == null || user.getRole() != 2) {
			return responseControllerResultError("NoLogin");
		}

		ResultDTO<Integer> result = itemService.deleteItemByItemId(itemId);
		if (result.isSuccess()) {
			return responseControllerResultSuccess(result);
		} else {
			log.error("删除数据异常！");
			return responseControllerResultError("删除数据异常！");
		}
	}

	@ResponseBody
	@RequestMapping(value = "selectOneItem")
	public String selectOneItem(Integer itemId, HttpServletRequest request) {
		ResultDTO<Item> result = itemService.selectOneItem(itemId);
		if (result.isSuccess() && result.getModule() != null) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("role", 0);
			map.put("overPrice", -1);
			User user = getLoginUser(request);
			if (user != null) {
				map.put("role", user.getRole());
				BatchResultDTO<Record> resultDTO = recordService.selectOneRecord(user.getId(),
						itemId);
				if (resultDTO.isSuccess()) {
					java.util.List<Record> list = resultDTO.getModule();
					if (list.size() > 0)
						map.put("overPrice", list.get(list.size() - 1).getPrice());

				}
			}
			map.put("item", result.getModule());
			return responseControllerResultSuccess(map);
		} else {
			log.error("selectOneItem 数据异常！");
			return responseControllerResultError("selectOneItem 数据异常！");
		}
	}

	@RequestMapping(value = "addItem", method = RequestMethod.POST)
	public String addItem(String title, String summary, String picType, String image, String detail,
			String price, MultipartFile file, HttpServletRequest request) throws IOException {

		User user = getLoginUser(request);

		if (user == null || user.getRole() != 2) {
			return responseControllerResultError("NoLogin");
		}
		Integer type = 1;
		String url = image;
		if (picType.equals("url")) {
			type = 1;
		} else {
			type = 0;// 本地
			url = FileUpload.uploadFile(file, request);
			if (url == null) {
				return responseControllerResultError("上传图片错误！");
			}
		}
		Item item = new Item();
		item.setItemname(title);
		item.setDatatime(new Date());
		item.setDescription(detail);
		item.setIcon(url);
		item.setStatus(type);
		item.setPirce(Float.valueOf(price));
		item.setTitle(summary);

		try {
			ResultDTO<Integer> result = itemService.insertItem(item);
			if(result.isSuccess()){
				 return "redirect:../asset/html/publicSubmit.html";
			}
			return "redirect:../asset/html/public.html";
		} catch (Exception e) {
			log.error("addItem 数据异常！");
			return "redirect:../asset/html/public.html";

		}
	}
	
	@RequestMapping(value = "updateItem", method = RequestMethod.POST)
	public String updateItem(String title, String summary, String picType, String image, String detail,
			String price, MultipartFile file, String itemId,HttpServletRequest request) throws IOException {

		User user = getLoginUser(request);

		if (user == null || user.getRole() != 2) {
			return responseControllerResultError("NoLogin");
		}
		Integer type = 1;
		String url = image;
		if (picType.equals("url")) {
			type = 1;
		} else {
			type = 0;// 本地
			url = FileUpload.uploadFile(file, request);
			if (url == null) {
				return responseControllerResultError("上传图片错误！");
			}
		}
		Item item = new Item();
		item.setId(Integer.valueOf(itemId));
		item.setItemname(title);
		item.setDatatime(new Date());
		item.setDescription(detail);
		item.setIcon(url);
		item.setStatus(type);
		item.setPirce(Float.valueOf(price));
		item.setTitle(summary);

		try {
			ResultDTO<Integer> result = itemService.updateItem(item);
			if(result.isSuccess()){
				 return "redirect:../asset/html/publicSubmit.html?itemId="+itemId;
			}
			return "redirect:../asset/html/edit.html?itemId="+itemId;
		} catch (Exception e) {
			log.error("updateItem 数据异常！");
			return "redirect:../asset/html/edit.html?itemId="+itemId;

		}
	}
	
}
