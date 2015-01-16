package org.xiaoqiaotq.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.service.UserService;

/**
 * @author xiaoqiaotq@gmail.com	
 * @date   2015年1月16日  
 */
@Controller
@RequestMapping("/")
public class AccountController {
	@Autowired
	UserService userService;
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginForm() {
		return "account/login";
	}
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String login(HttpSession session,User u,@RequestParam(defaultValue="user/home") String url,Model model) {
		if((u=userService.login(u))!=null){
			session.setAttribute("user", u);
			return "redirect:"+url;
		}
		model.addAttribute("error","登录失败");
		return "account/login";
	}
	@RequestMapping(value="logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("user")!=null){
			session.removeAttribute("user");
		}
		return "redirect:/login";
	}

}
