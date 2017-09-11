package com.todoList.springBootToDo.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todoList.springBootToDo.web.model.Todo;
import com.todoList.springBootToDo.web.servers.LoginService;
import com.todoList.springBootToDo.web.servers.TodoService;

@Controller
@SessionAttributes("username")
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(ModelMap model) {

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showLogin(ModelMap model, @RequestParam String username, @RequestParam String password) {
		boolean isValid = loginService.validateUser(username, password);
		if(!isValid){
			model.put("inValidUser", "InValidateUser");
			return "login";
		}
		model.put("username", username);
		model.put("password", password);

		return "welcome";
	}

	

}
