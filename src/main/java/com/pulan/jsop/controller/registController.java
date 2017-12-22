package com.pulan.jsop.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.Service.UserService;
import com.pulan.Service.WkUserService;
import com.pulan.model.User;
import com.pulan.model.WkUser;
import com.pulan.utils.RedisClient;
@RestController
@RequestMapping(value = "/message")
public class registController {
	/*
	 * 来访宾客注册
	 * 
	 * */
	@Autowired
	private WkUserService wkUserServiceImpl;
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private UserService userServiceImpl;
	//外部访客注册
	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String registUser(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		String msgData = request.getParameter("msgData");
		String callback = request.getParameter("callback");
		WkUser wkUser = JSON.parseObject(msgData, WkUser.class);
		int ret = wkUserServiceImpl.insertUser(wkUser);
		if (ret == 0) {
			result.put("resp", "新增失败");
		} else {
			result.put("resp", "新增成功");
		}
		String results = callback + "(%s)";
		return String.format(results, result.toJSONString());
	}
	//内部员工注册
		@RequestMapping(value = "innerregist", method = RequestMethod.GET)
		public String innerregistUser(HttpServletRequest request) { 
			JSONObject result = new JSONObject();
			String msgData = request.getParameter("msgData");
			String callback = request.getParameter("callback");
			User user = JSON.parseObject(msgData, User.class);
			int ret = userServiceImpl.updateFaceId(user);
			if (ret == 0) {
				result.put("resp", "注册失败");
			} else {
				result.put("resp", "注册成功");
			}
			String results = callback + "(%s)";
			return String.format(results, result.toJSONString());
		}
		//根据faceid查询身份
		@RequestMapping(value="getInfoByFaceId",method=RequestMethod.GET)
		public String getBaseInfoByFaceId(@RequestParam String msgData,@RequestParam String callback){
			JSONObject jo = JSONObject.parseObject(msgData);
			JSONObject result = new JSONObject();
			String faceId = jo.getString("faceId");
			WkUser wkuser = wkUserServiceImpl.getUser(faceId);
			result.put("resp",wkuser);
			String results = callback + "(%s)";
			return String.format(results, result.toJSONString());
		}
	//获取所有内部员工的mailname渲染select
	@RequestMapping(value = "getAllMailName", method = RequestMethod.GET)
	public String getAllMailName(@RequestParam String callback) {
		JSONObject result = new JSONObject();
		result.put("resp",userServiceImpl.getAllMailName());
		String results = callback + "(%s)";
		return String.format(results, result.toJSONString());
	}
	//内部员工关怀
	@RequestMapping(value = "userPush", method = RequestMethod.GET)
	public String userPush(@RequestParam String faceid,@RequestParam String callback) {
		JSONObject ret = new JSONObject();
		User user = userServiceImpl.getUserByFaceid(faceid);
		if(user==null){
			ret.put("error", "您的头像未录入");
		}else{
		String mailname = userServiceImpl.getEmailByname(user.getMail_name());
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String today = df.format(new Date());
		String yesterday = df.format(new Date().getTime()-24*60*60*1000);
		String meettingkey = "meeting:my:"+mailname+":"+today+"*";
		String notifykey = "notify:my:"+mailname+":"+today+"*";
		String reviewkey = "review:my:"+mailname+":"+today+"*";
		String schedulekey = "schedule:my:"+mailname+":"+today+"*";
		String taskkey = "task:my:"+mailname+":"+today+"*";
		String attendancekey = "attendance:my:"+mailname+":"+yesterday+"*";
		String performancekey = "performance:my:"+mailname+":"+yesterday+"*";
		List<String> meettings = new ArrayList<String>();
		List<String> notifys = new ArrayList<String>();
		List<String> reviews = new ArrayList<String>();
		List<String> schedules = new ArrayList<String>();
		List<String> tasks = new ArrayList<String>();
		List<String> attendances = new ArrayList<String>();
		List<String> performances = new ArrayList<String>();
		List<String> births = new ArrayList<String>();
		try {
			Set<String> keys = redisClient.muhuKey(2,meettingkey);
			if(keys!=null){
				for(String k:keys){
					meettings.add(redisClient.get(2, k));
				}
			}
			
			Set<String> keys1 = redisClient.muhuKey(2,notifykey);
			if(keys1!=null){
				for(String k:keys1){
					notifys.add(redisClient.get(2, k));
				}
			}
			
			Set<String> keys2 = redisClient.muhuKey(2,reviewkey);
			if(keys2!=null){
				for(String k:keys2){
					reviews.add(redisClient.get(2, k));
				}
			}
			
			Set<String> keys3 = redisClient.muhuKey(2,schedulekey);
			if(keys3!=null){
				for(String k:keys3){
					schedules.add(redisClient.get(2, k));
				}
			}
			
			Set<String> keys4 = redisClient.muhuKey(2,taskkey);
			if(keys4!=null){
				for(String k:keys4){
					tasks.add(redisClient.get(2, k));
				}
				ret.put("tasks", tasks);
			}
			
			Set<String> keys5 = redisClient.muhuKey(2,attendancekey);
			if(keys5!=null){
				for(String k:keys5){
					attendances.add(redisClient.get(2, k));
				}
			}
			
			Set<String> keys6 = redisClient.muhuKey(2,performancekey);
			if(keys6!=null){
				for(String k:keys6){
					performances.add(redisClient.get(2, k));
				}
			}
			if(today.substring(4, 8).equals(user.getBirth().substring(4, 8))){
				births.add("今天是您的生日,花花祝您生日快乐");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ret.put("error", "");
		ret.put("meettings", meettings);
		ret.put("notifys", notifys);
		ret.put("reviews", reviews);
		ret.put("schedules", schedules);
		ret.put("attendances", attendances);
		ret.put("performances", performances);
		ret.put("births", births);
		}
		String results = callback + "(%s)";
		return String.format(results, ret.toJSONString());
	}
}
