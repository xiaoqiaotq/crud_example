package org.xiaoqiaotq.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.service.UserService;
import org.xiaoqiaotq.util.Servlets;

/**
 * @author xiaoqiaotq@gmail.com	
 * @date   2014年12月11日
 */
@Controller
@RequestMapping("/user")
public class UserController {
  //每页显示记录数
  private static final String PAGE_SIZE = "5";
  //分页插件显示数
  private static final int paginationSize = 4;
  
  @Autowired	
  private UserService userService;
  
  
  @RequestMapping("/home")
  public String home(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request){
	  Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
	  Page<User> page=userService.queryUser( searchParams, pageNumber, pageSize, sortType);
	  model.addAttribute("page", page);
	  model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
	  //pagination
	  int current =  page.getNumber() + 1;
	  int begin = Math.max(1, current - paginationSize/2);
	  int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

	  request.setAttribute("current", current);
	  request.setAttribute("begin", begin);
	  request.setAttribute("end", end);
	  
	  return "user/user_list";
  }
  @RequestMapping(value="/createOrUpdate",method=RequestMethod.POST)
  public String createOrUpdate(@Valid@ModelAttribute("user") User user,BindingResult result,Model model,RedirectAttributes redirectAttributes){
	if(result.hasErrors()){
		return "/user/user_form";
	}
	String message="";
	if(user.isNew()){
		user.setRegisterDate(new Date());
		message="创建成功";
	}else{
		message="修改成功";
	}
	redirectAttributes.addFlashAttribute("message", message);
	userService.save(user);
	return "redirect:/user/home";
  }
  @RequestMapping(value="/create",method=RequestMethod.GET)
  public String createForm(User user){
	  return "user/user_form";
  }
  @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
  public String updateForm(@PathVariable long id,Model model){
	  User user=userService.find(id);
	  model.addAttribute("user", user);
	  return "user/user_form";
  }
  @RequestMapping(value="/del/{id}",method=RequestMethod.GET)
  public String del(@PathVariable long id,RedirectAttributes redirectAttributes){
	  userService.remove(id);
	  redirectAttributes.addFlashAttribute("message", "删除成功");
	  return "redirect:/user/home"; 
  }
  @RequestMapping(value="/exists")
  @ResponseBody
  public String exists(@RequestParam String username){
	  return String.valueOf(!userService.isExist(username));
  }
//  @RequestMapping("/showRole/{id}")
//  public String showRole(@PathVariable int id,Map map){
//	  User user=userService.find(id);
//	  map.put("user", user);
//	  
//	  List<Role> roles=roleService.findAll();
//	  for (Role role : roles) {
//		 for (Role r : user.getRoles()) {
//			if(r.getId().equals(role.getId())){
//				role.setChecked(true);
//				break;
//			}
//		}
//	  }
//	  map.put("roles", roles);
//	  return "user/user_role";
//  }
//  @RequestMapping("/saveRole/{userId}")
//  public String saveRole(@PathVariable int userId, Integer[] roleIds){
//	  User user=userService.find(userId);
//	  if (roleIds!=null) {
//		  Set<Role> roles=roleService.find(roleIds);
//		  user.setRoles(roles);
//	  }else{
//		  user.setRoles(null);
//	  }
//	  userService.save(user);
//	  return "redirect:/user/home	";
//  }
	@ModelAttribute
	public void getTask(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("user", userService.find(id));
		}
	}
}
