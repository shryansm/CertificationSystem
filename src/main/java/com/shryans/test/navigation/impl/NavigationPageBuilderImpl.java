package com.shryans.test.navigation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.Student;
import com.shryans.test.data.entity.User;
import com.shryans.test.navigation.NavigationPage;
import com.shryans.test.navigation.NavigationPageBuilder;

public abstract class NavigationPageBuilderImpl implements NavigationPageBuilder{
	
	protected User user;
	protected Student student;
	protected Registration registration;
	protected Course course;

	protected Model model;
	protected String errMsg = null;
	
	@Autowired
	protected NavigationPage navigationPage;

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	@Override
	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	protected abstract String getPage();
	protected abstract Model getModel();

	@Override
	public NavigationPage build() {
		String pageName = this.getPage();
		this.model = this.getModel();
		if(this.user != null) this.model.addAttribute("user", user);
		if(this.course != null) this.model.addAttribute("course", course);
		if(this.registration != null) this.model.addAttribute("registration", registration);
		if(this.errMsg !=null)	this.model.addAttribute("error", errMsg);
		this.navigationPage.setModel(model);
		this.navigationPage.setPageName(pageName);
		return navigationPage;
	}
	
}
