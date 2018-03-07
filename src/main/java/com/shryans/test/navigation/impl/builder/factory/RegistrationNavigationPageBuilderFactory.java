package com.shryans.test.navigation.impl.builder.factory;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.shryans.test.navigation.NavigationPageBuilder;
import com.shryans.test.navigation.Page;
import com.shryans.test.navigation.impl.NavigationPageBuilderFactoryImpl;
import com.shryans.test.navigation.impl.builder.CourseDetailsPageBuilder;
import com.shryans.test.navigation.impl.builder.CourseRegistrationsPageBuilder;

@Service
public class RegistrationNavigationPageBuilderFactory extends NavigationPageBuilderFactoryImpl {

	@Resource(type = CourseDetailsPageBuilder.class)
	private NavigationPageBuilder courseDetailsStudentPageBuilder;

	@Resource(type = CourseRegistrationsPageBuilder.class)
	private NavigationPageBuilder courseRegistrationsPageBuilder;

	@Override
	public NavigationPageBuilder getPageBuilder(Page page) {
		NavigationPageBuilder pageBuilder = null;
		switch(page) {
		case CourseRegistrations:
			pageBuilder = courseRegistrationsPageBuilder;
			break;
		default:
			pageBuilder = courseDetailsStudentPageBuilder;
		}
		pageBuilder.setModel(model);
		pageBuilder.setUser(user);
		if(student!= null)pageBuilder.setStudent(student);
		pageBuilder.setCourse(course);
		pageBuilder.setRegistration(registration);
		return pageBuilder;
	}
}