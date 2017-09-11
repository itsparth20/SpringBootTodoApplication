package com.todoList.springBootToDo.web.servers;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean validateUser(String userid, String password) {
		
		return userid.equalsIgnoreCase("parth")
				&& password.equalsIgnoreCase("password");
	}

}