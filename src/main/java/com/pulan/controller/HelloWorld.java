package com.pulan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pulan.Service.HuaShuService;
import com.pulan.Service.MessageService;
import com.pulan.Service.SemanticService;
import com.pulan.Service.SemanticSlotService;
import com.pulan.Service.UserService;
import com.pulan.model.Semantic;
import com.pulan.model.SemanticSlot;
import com.pulan.model.VerbalTrick;

@Controller 
@RequestMapping(value = "/message/app")
public class HelloWorld {
	@Autowired
	private SemanticService semanticServiceImpl;
	@Autowired
	private SemanticSlotService semanticSlotServiceImpl;
	@Autowired
	private HuaShuService huaShuServiceImpl;
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private MessageService messageServiceImpl;
	@ResponseBody
	@RequestMapping("/")
	public int helloworld(){
		System.out.println(messageServiceImpl.getAllMessage(0, 5));
		 return 0;
	}
}
