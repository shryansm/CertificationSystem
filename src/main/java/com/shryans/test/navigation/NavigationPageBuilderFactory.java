package com.shryans.test.navigation;

import org.springframework.ui.Model;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.Student;
import com.shryans.test.data.entity.User;

public interface NavigationPageBuilderFactory {
		
		void setUser(User user);
		void setCourse(Course course);
		void setRegistration(Registration registration);

		void setModel(Model model);
		
		NavigationPageBuilder getPageBuilder(Page page);
		NavigationPageBuilder getPageBuilder(String errMsg);
		void setStudent(Student student);
}
