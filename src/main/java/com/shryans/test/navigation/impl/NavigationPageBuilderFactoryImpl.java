package com.shryans.test.navigation.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.Student;
import com.shryans.test.data.entity.User;
import com.shryans.test.navigation.NavigationPageBuilder;
import com.shryans.test.navigation.NavigationPageBuilderFactory;
import com.shryans.test.navigation.impl.builder.IndexMessagePageBuilder;

@Service
public abstract class NavigationPageBuilderFactoryImpl implements NavigationPageBuilderFactory{
	
	protected User user;
	protected Student student;
	protected Registration registration;
	protected Course course;

	protected Model model;

	@Resource(type=IndexMessagePageBuilder.class)
	private NavigationPageBuilderImpl errorPageBuilder;

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	@Override
	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public NavigationPageBuilder getPageBuilder(String errMsg) {
		NavigationPageBuilder pageBuilder = errorPageBuilder;
		pageBuilder.setModel(model);
		if(user != null) pageBuilder.setUser(user);
		if(course != null) pageBuilder.setCourse(course);
		if(registration != null) pageBuilder.setRegistration(registration);
		pageBuilder.setErrMsg(errMsg);
		return pageBuilder;
	}

}
