package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.User;
import com.test.service.UserService;

@Controller
public class UserServiceController {
	@Autowired
	private UserService userService;
	@RequestMapping("test/{id}")
	public ModelAndView test(@PathVariable Integer id) {
		User u=userService.selectByPrimaryKey(id);
		ModelAndView mv=new ModelAndView("index");
		mv.addObject("user",u);
		return mv;
	}
	

}