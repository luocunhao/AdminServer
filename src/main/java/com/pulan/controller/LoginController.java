package com.pulan.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pulan.Service.UserService;
import com.pulan.utils.RedisClient;

@Controller
@RequestMapping(value = "/message")
public class LoginController {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@ResponseBody
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public JSONObject loginUser(@RequestBody String msgData, HttpSession session) {
		JSONObject ret = new JSONObject();
		JSONObject msgObj = JSON.parseObject(msgData);
		String userName = msgObj.getString("userName");
		String passWord = msgObj.getString("passWord");
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, passWord);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(usernamePasswordToken); // 完成登录
			String mail_name = (String) subject.getPrincipal();
			//根据名字获取当前用户
			session.setAttribute("user", mail_name);
			ret.put("resp", "0");
			return ret;
		} catch (Exception e) {
			ret.put("resp", "-1");
			return ret;// 返回登录页面
		}

	}
    	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "login";
	}

}
