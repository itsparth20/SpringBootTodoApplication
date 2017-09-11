package com.todoList.springBootToDo.web.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todoList.springBootToDo.web.model.Todo;
import com.todoList.springBootToDo.web.servers.TodoService;

@Controller
@SessionAttributes("username")
public class TodoController {
	@Autowired
	TodoService service;

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showToDos(ModelMap model) {
		String username = (String) model.get("username");

		List<Todo> list = service.retrieveTodos(username);
		model.put("todos", list);
		return "list-todos";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.GET)
	public String showAddToDoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String) model.get("username"), "Default Desc",
				new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)
	public String addToDo(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors()){
			return "todo";
		}
		service.addTodo((String) model.get("username"), todo.getDesc(), new Date(), false);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteToDo(ModelMap model, @RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:list-todos";
	}
}
