package com.shryans.test.navigation.impl.builder;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shryans.test.navigation.impl.NavigationPageBuilderImpl;

@Service
public class AddCoursePageBuilder extends NavigationPageBuilderImpl{
	
	@Override
	protected String getPage() {
		String pageName= "addCourse";
		return pageName;
	}

	@Override
	protected Model getModel() {
		return model;
	}
}
