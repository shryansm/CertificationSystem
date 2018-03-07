package com.shryans.test.navigation.impl.builder;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shryans.test.navigation.impl.NavigationPageBuilderImpl;

@Service
public class AddUserPageBuilder extends NavigationPageBuilderImpl{
	
	@Override
	protected String getPage() {
		String pageName= "addUser";
		return pageName;
	}

	@Override
	protected Model getModel() {
		return model;
	}
}
