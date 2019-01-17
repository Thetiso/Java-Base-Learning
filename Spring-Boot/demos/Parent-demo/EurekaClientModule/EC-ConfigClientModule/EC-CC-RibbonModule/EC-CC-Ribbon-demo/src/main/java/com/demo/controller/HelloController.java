package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.HelloService;

@RestController
public class HelloController {

	@Autowired 
	HelloService helloService;
	
	@Value("${server.port}")
    String port;
	
	@PostMapping("/hi")
	public String hello(@RequestParam(value = "name", defaultValue = "forezp") String name,
    		HttpServletRequest request) {
		return "hi " + name + " ,i am ribbon at port:" + port;
	}
	
	@PostMapping("/hello")
	public String hello2(@RequestParam(value = "name", defaultValue = "forezp") String name,
    		HttpServletRequest request) {
		String result = helloService.hiService(name);
		return "hello " + name + " ,i am ribbon at port" + port + ",and get result from service:" + result;
	}
	
}
