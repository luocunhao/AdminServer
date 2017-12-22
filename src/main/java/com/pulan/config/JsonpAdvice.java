package com.pulan.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;
@ControllerAdvice(basePackages="com.pulan.jsonp.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice{
	public JsonpAdvice(){
		super("callback","jsonp");
	}
}
