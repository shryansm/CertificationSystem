package com.shryans.test.data.webServices;

import org.springframework.ui.Model;

import com.shryans.test.data.entity.Student;
import com.shryans.test.data.entity.User;

public interface UserController {

	String getUser(String username, String password, Model model);
	
	String createUser(User user, int userId, Model model);
	
	String register(Student user, Model model);

	String index();

	String getAddUserPage(int userId, Model model);
}
