package com.todoList.springBootToDo.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showToDos(ModelMap model) {
		String username = getLoggedInUserName(model);

		List<Todo> list = service.retrieveTodos(username);
		model.put("todos", list);
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		return (String) model.get("username");
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.GET)
	public String showAddToDoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "Default Desc", new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)
	public String addToDo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		service.addTodo(getLoggedInUserName(model), todo.getDesc(), new Date(), false);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteToDo(ModelMap model, @RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateToDo(ModelMap model, @RequestParam int id) {
		Todo todo = service.retrieveTodos(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String UpdateToDo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser((String) (model.get("username")));
		service.updateTodo(todo);
		return "redirect:list-todos";
	}
}
