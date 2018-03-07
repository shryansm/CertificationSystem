package com.shryans.test.navigation.impl.builder;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shryans.test.navigation.impl.NavigationPageBuilderImpl;

@Service
public class IndexMessagePageBuilder extends NavigationPageBuilderImpl {
	
	@Override
	protected String getPage() {
		String pageName= "index";
		return pageName;
	}

	@Override
	protected Model getModel() {
		model.addAttribute("error","Invalid username or password");
		return model;
	}
}
