package com.pulan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.Dao.HuaShuDao;
import com.pulan.Service.HuaShuService;
import com.pulan.model.VerbalTrick;
import com.pulan.utils.Pager;

@Controller // (直接返回数据而不是视图)
@RequestMapping(value = "/message/verbal")
public class HuaShuController {
	@Autowired
	private HuaShuService huaShuServiceImpl;
	@RequestMapping("/")
	public String verbal(ModelMap mm, HttpServletRequest request) {
		JSONObject ret = new JSONObject();
		// 当前默认为第一页
		int currentPage = 0;
		int totalRecord = huaShuServiceImpl.getDataSize();
		mm.put("totalRecord", totalRecord);
		String pageNum = request.getParameter("pageNum");
		int pageSize = Pager.DEFAULT_PAGESIZE;
		// 如果前台传入pageNum参数，则设置pageNum为当前第几页
		if (pageNum != null && !"".equals(pageNum.trim())) {
			currentPage = Integer.parseInt(pageNum);
		} else {
			currentPage = Pager.DEFAULT_PAGENUM;
		}
		Pager pager = new Pager(currentPage, pageSize, totalRecord);
		int fromIndex = pager.getPageSize() * (pager.getCurrentPage() - 1);
		mm.put("resp", huaShuServiceImpl.getTypes());
		mm.put("allData", huaShuServiceImpl.getVerbalByCategoryAndType("", "", fromIndex, pageSize));
		mm.put("page", pager);
		mm.put("pageNum", pageNum);
		return "pages/verbal";
	}

	@ResponseBody
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public JSONObject insertVerbal(VerbalTrick verbalTrick) {
		JSONObject result = new JSONObject();
		int ret = huaShuServiceImpl.insertVerbal(verbalTrick);
		if (ret == 0) {
			result.put("resp", "新增失败");
		} else {
			result.put("resp", "新增成功");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JSONObject updateVerbal(VerbalTrick verbalTrick) {
		JSONObject result = new JSONObject();
		int ret = huaShuServiceImpl.updateVerbal(verbalTrick);
		if (ret == 0) {
			result.put("resp", "修改失败");
		} else {
			result.put("resp", "修改成功");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JSONObject deleteVerbal(@RequestBody String msgData) {
		JSONObject result = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		int id = msgObj.getInteger("id");
		int ret = huaShuServiceImpl.deleteVerbal(id);
		if (ret == 0) {
			result.put("resp", "删除失败");
		} else {
			result.put("resp", "删除成功");
		}
		return result;
	}
    @ResponseBody
	@RequestMapping(value = "select", method = RequestMethod.GET)
	public JSONObject selectVerbal(HttpServletRequest request) {
    	JSONObject ret = new JSONObject();
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		// 当前默认为第一页
		int currentPage = 0;
		int totalRecord = huaShuServiceImpl.getDataSizepage(type, category);
		ret.put("totalRecord", totalRecord);
		String pageNum = request.getParameter("pageNum");
		int pageSize = Pager.DEFAULT_PAGESIZE;
		// 如果前台传入pageNum参数，则设置pageNum为当前第几页
		if (pageNum != null && !"".equals(pageNum.trim())) {
			currentPage = Integer.parseInt(pageNum);
		} else {
			currentPage = Pager.DEFAULT_PAGENUM;
		}
		Pager pager = new Pager(currentPage, pageSize, totalRecord);
		int fromIndex = pager.getPageSize() * (pager.getCurrentPage() - 1);
		ret.put("resp", huaShuServiceImpl.getVerbalByCategoryAndType(category, type, fromIndex, pageSize));
		ret.put("resp1", huaShuServiceImpl.getTypes());
		ret.put("page", pager);
		ret.put("pageNum", pageNum);
		ret.put("category", category);
		ret.put("type", type);
		return ret;
	}

	// 分页查询
    @ResponseBody
	@RequestMapping(value = "select_page", method = RequestMethod.GET)
	public JSONObject selectVerbalpage(HttpServletRequest request) {
    	JSONObject ret = new JSONObject();
		String category = request.getParameter("category");
		String type = request.getParameter("type");
		// 当前默认为第一页
		int currentPage = 0;
		int totalRecord = huaShuServiceImpl.getDataSizepage(type, category);
		ret.put("totalRecord", totalRecord);
		String pageNum = request.getParameter("pageNum");
		int pageSize = Pager.DEFAULT_PAGESIZE;
		// 如果前台传入pageNum参数，则设置pageNum为当前第几页
		if (pageNum != null && !"".equals(pageNum.trim())) {
			currentPage = Integer.parseInt(pageNum);
		} else {
			currentPage = Pager.DEFAULT_PAGENUM;
		}
		Pager pager = new Pager(currentPage, pageSize, totalRecord);
		int fromIndex = pager.getPageSize() * (pager.getCurrentPage() - 1);
		ret.put("resp", huaShuServiceImpl.getVerbalByCategoryAndType(category, type, fromIndex, pageSize));
		ret.put("resp1", huaShuServiceImpl.getTypes());
		ret.put("page", pager);
		ret.put("pageNum", pageNum);
		ret.put("category", category);
		ret.put("type", type);
		return ret;
	}

	// 获取所有type
	@ResponseBody
	@RequestMapping(value = "getAllTypes", method = RequestMethod.POST)
	public JSONObject getAllTypes() {
		JSONObject result = new JSONObject();
		List<String> li = huaShuServiceImpl.getTypes();
		result.put("resp", JSON.toJSONString(li));
		return result;
	}

	// 根据type获取小类
	@ResponseBody
	@RequestMapping(value = "getAllCategoryByType", method = RequestMethod.POST)
	public JSONObject getAllCategoryByType(@RequestBody String msgData) {
		JSONObject result = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String type = msgObj.getString("type");
		List<String> li = huaShuServiceImpl.getCategoryType(type);
		result.put("resp", JSON.toJSONString(li));
		return result;
	}

}
