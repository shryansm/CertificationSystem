package com.shryans.test.navigation;

import org.springframework.ui.Model;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.Student;
import com.shryans.test.data.entity.User;

public interface NavigationPageBuilder {

	NavigationPage build();

	void setUser(User user);
	void setModel(Model model);
	void setErrMsg(String errMsg);

	void setCourse(Course course);

	void setRegistration(Registration registration);

	void setStudent(Student student);
	
}
