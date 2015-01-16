package org.xiaoqiaotq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.service.UserService;

/**
 * @author xiaoqiaotq@gmail.com	
 * @date   2014年12月18日
 */
@Controller
@RequestMapping("/test")
public class HelloController {
	@Autowired
	UserService userService;
	@RequestMapping("hello")
	public String hello(){
		return "hello";
	}
	@RequestMapping("/response")
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String response(){
		return "hello";
	}
	@RequestMapping("/addUser")
	public String add(User u){
		u.setAge(9);
		userService.save(u);
		return "hello";
	}
}
