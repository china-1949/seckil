package com.dayup.seckil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dayup.seckil.model.User;


@Controller
public class HomeController {
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String login(Model model,User user){  //User对象全局可以拿到的
		System.err.println("-----------"+user.getUsername());
		model.addAttribute("user",user);
		return "home";
		
	}
}
