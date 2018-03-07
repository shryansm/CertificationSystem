package com.shryans.test.data.webServices.Impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shryans.test.data.entity.Role;
import com.shryans.test.data.entity.Student;
import com.shryans.test.data.entity.User;
import com.shryans.test.data.repository.UserRepository;
import com.shryans.test.data.webServices.UserController;
import com.shryans.test.navigation.NavigationPage;
import com.shryans.test.navigation.NavigationPageBuilder;
import com.shryans.test.navigation.NavigationPageBuilderFactory;
import com.shryans.test.navigation.Page;
import com.shryans.test.navigation.impl.builder.factory.UserNavigationPageBuilderFactory;

@Controller
public class UserControllerImpl implements UserController{

	@Autowired
	private UserRepository userRepository;

	@Resource(type=UserNavigationPageBuilderFactory.class)
	private NavigationPageBuilderFactory navigationFactory;

	@Override
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	@Override
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String getUser(@RequestParam String username, @RequestParam String password, Model model) {
		try {
			User user = userRepository.findByUsername(username);
			navigationFactory.setUser(user);
			navigationFactory.setModel(model);

			NavigationPage navigationPage = (user != null && user.getPassword().equals(password)) 
					? navigationFactory.getPageBuilder(Page.LoginSucces).build()
							: navigationFactory.getPageBuilder("Invalid Username or Password").build();
					model = navigationPage.getModel();
					return navigationPage.getPageName();
		}
		catch(Exception e) {
			e.printStackTrace();
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("An internal error occurred\n" + e.getMessage()).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
	}

	@Override
	@RequestMapping(value="/{userId}/getAddUser", method=RequestMethod.GET)
	public String getAddUserPage(@PathVariable(value="userId") int userId, Model model) {
		try {
			User user = userRepository.findOne(userId);
			navigationFactory.setModel(model);
			navigationFactory.setUser(user);

			NavigationPageBuilder builder = (user == null) 
					? navigationFactory.getPageBuilder("Login Required") 
							: navigationFactory.getPageBuilder(Page.AddAdminUser);
					NavigationPage navigationPage = builder.build();

					model = navigationPage.getModel();
					return navigationPage.getPageName();
		}
		catch(Exception e) {
			e.printStackTrace();
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("An internal error occurred\n" + e.getMessage()).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
	}


	@Override
	@RequestMapping(value="{userId}/create", method=RequestMethod.POST)
	public String createUser(@ModelAttribute User user, @PathVariable(value="userId") int userId, Model model) {
		try {
			User admin = userRepository.findOne(userId);
			if(admin == null)
				return navigationFactory.getPageBuilder("Login Required").build().getPageName();
			user.setRole(Role.ADMIN);
			user = userRepository.save(user);
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("Student account created successfully").build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
		catch(Exception e) {
			e.printStackTrace();
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("An internal error occurred\n" + e.getMessage()).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
	}

	@Override
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String register(@ModelAttribute Student user, Model model) {
		try {
			user.setRole(Role.STUDENT);
			user = userRepository.save(user);
			navigationFactory.setUser(user);
			navigationFactory.setModel(model);
			NavigationPage navigationPage = navigationFactory.getPageBuilder(Page.SignupSuccess).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
		catch(Exception e) {
			e.printStackTrace();
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("An internal error occurred\n" + e.getMessage()).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
	}

}
