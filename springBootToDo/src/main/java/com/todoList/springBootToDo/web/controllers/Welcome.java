package com.todoList.springBootToDo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Welcome {

	
	
	@RequestMapping("/welcomeDemo")
	public String showWelcome(@RequestParam String name, ModelMap model){
		model.put("name", name);
		return "login";
	}
	

	
}
