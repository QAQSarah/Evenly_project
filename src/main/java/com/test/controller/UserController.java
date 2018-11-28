package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.test.entity.User;
import com.test.service.UserService;

@Controller
@RequestMapping("/Get")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/users")//获取用户列表
	public ModelAndView getUsers() {
		ModelAndView mv=new ModelAndView();
		List<User> users=new ArrayList<>();
		users=userService.showUsers();
		mv.addObject("users", users);
		mv.setViewName("userlist");
		return mv;
	}
	
	
	@RequestMapping("/user")//获取单个用户信息详情
	public ModelAndView getUser(HttpServletRequest request,HttpServletResponse response) {
		String id=request.getParameter("id");
		System.out.println(id);
		User user=userService.showUser(Integer.parseInt(id));
		ModelAndView mv=new ModelAndView();
		mv.clear();
		mv.addObject("user", user);
		mv.setViewName("userdetail");
		return mv;
	}
	

	@RequestMapping("/user/{id}/edit")//到编辑页面
	public ModelAndView getUserEdit(@PathVariable("id") String id) {
		User user=userService.showUser(Integer.valueOf(id));
		ModelAndView mv=new ModelAndView();
		mv.clear();
		mv.addObject("mduser", user);
		mv.setViewName("usermodify");
		return mv;
	}
	
	@RequestMapping("/user/updt")//执行用户修改
	public String userUpdt(User user) {
		boolean val=false;
		val=userService.update(user);
		
		System.out.println(val);
		
		return "forward:/Get/users";
		
		
	}
	
	@RequestMapping("/user/{id}/confirmDelete")//执行用户删除
	@ResponseBody
	public void getUserDelete(int id,HttpServletRequest request,HttpServletResponse response){
		String i =String.valueOf(id);
		boolean val=false;
		val=userService.deleteUser(i);
		if (val) {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "用户删除成功");
			String str = JSON.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "有效用户不能删除");
			String str = JSON.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	@RequestMapping("/user/add")//到添加页面
	public String getUserNew() {
		return "useradd";
	}

	@RequestMapping("/user/new")//执行添加
	public String doUserNew(User user,HttpServletRequest request,HttpServletResponse response) {
		boolean val=userService.addUser(user);
		if (val) {
			return "forward:/Get/users";
		}else {
			request.setAttribute("add_msg", "添加失败");
			return "forward:/Get/user/new";
		}
		
	}
	
	
	@RequestMapping("/user/getUsersBy")			//多条件查询
	public ModelAndView getUsersBy(User user) {
		ModelAndView mv=new ModelAndView();

		List<User> users=new LinkedList<>();
		users=userService.search(user.getUsername());
		for(User u:users){
			System.out.println(u.toString());
		}
		mv.clear();
		mv.addObject("users", users);
		mv.setViewName("userlist");
		return mv;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String doLogin(String name,String password,HttpServletRequest request) {
		System.out.println(name);
		User user= new User(name,password);
		user.setName(name);
		user.setPassword(password);
		User user2=userService.login(user);
		if (user2!=null) {
			System.err.println("hehe---------------\n\n\n\n");
			request.getSession().setAttribute("userinfo", user2);
			return "forward:/Get/users";
		}else{
			request.getSession().setAttribute("lg_msg", "用户名或密码不正确");
			return "forward:/Get/tologin";
		}
		
	}
	
	@RequestMapping("/user/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("userinfo");
		return "forward:/Get/tologin";
	}
	
	@RequestMapping("/tologin")
	public String toLogin() {
		return "login";
	}
	
}

