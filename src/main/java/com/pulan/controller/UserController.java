package com.pulan.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.Service.UserService;
import com.pulan.model.User;
import com.pulan.utils.Pager;

@Controller // (直接返回数据而不是视图)
@RequestMapping(value = "/message/user")
public class UserController {
	@Autowired
	private UserService userServiceImpl;
	@RequestMapping(value = "/")
	public String user(ModelMap mm, HttpServletRequest request){
		// 当前默认为第一页
		int currentPage = 0;
		int totalRecord = userServiceImpl.getDataSize();
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
		mm.put("allData", userServiceImpl.getAllUser(fromIndex,pageSize));
		mm.put("page", pager);
		mm.put("pageNum", pageNum);
		return "pages/userManagement";
	}
	@ResponseBody
	@RequestMapping(value = "/select_page")
	public JSONObject userpage( HttpServletRequest request){
		JSONObject ret = new JSONObject();
		// 当前默认为第一页
		int currentPage = 0;
		int totalRecord = userServiceImpl.getDataSize();
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
		ret.put("allData", userServiceImpl.getAllUser(fromIndex,pageSize));
		ret.put("page", pager);
		ret.put("pageNum", pageNum);
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "/insertUser",method = RequestMethod.POST)
	public JSONObject insertUser(@RequestBody User user){
		JSONObject result = new JSONObject();
		int ret = userServiceImpl.insertUser(user);
		if (ret == 0) {
			result.put("resp", "新增失败");
		} else {
			result.put("resp", "新增成功");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public JSONObject deleteVerbal(@RequestBody String msgData) {
		JSONObject result = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String id = msgObj.getString("id");
		int ret = userServiceImpl.deleteUser(id);
		if (ret == 0) {
			result.put("resp", "删除失败");
		} else {
			result.put("resp", "删除成功");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JSONObject updateUser(@RequestBody User user) {
		JSONObject result = new JSONObject();
		int ret = userServiceImpl.update(user);
		if (ret == 0) {
			result.put("resp", "修改失败");
		} else {
			result.put("resp", "修改成功");
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "updateUhead", method = RequestMethod.POST)
	public JSONObject updateUhead(@RequestBody String msgData) {
		JSONObject ret = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String userName = msgObj.getString("username");
		String passWord = msgObj.getString("password");
		String pic = msgObj.getString("pic");
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, passWord);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(usernamePasswordToken); // 完成登录
			userServiceImpl.updateUhead(userName,pic);
			//根据名字获取当前用户
			ret.put("resp", "0");
			return ret;
		} catch (Exception e) {
			ret.put("resp", "-1");
			return ret;// 返回登录页面
		}

	}
	@ResponseBody
	@RequestMapping(value = "getUserById", method = RequestMethod.POST)
	public User updateUser(@RequestBody String msgData) {
		JSONObject msgObj = JSON.parseObject(msgData);
		String id = msgObj.getString("id");
		return userServiceImpl.getUserById(id);
	}
	@ResponseBody
	@RequestMapping(value = "getRolesAndUserRoles", method = RequestMethod.POST)
	public JSONObject getRolesAndUserRoles(@RequestBody String msgData) {
		JSONObject ret = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String id = msgObj.getString("id");
		ret.put("userRoles", userServiceImpl.getAllRoleByUserId(id));
		ret.put("roles", userServiceImpl.getAllRole());
		return ret;
	}
}
