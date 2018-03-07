package com.shryans.test.navigation.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shryans.test.navigation.NavigationPage;

@Service
public class NavigationPageImpl implements NavigationPage {

	private String  pageName;
	private Model model;

	@Override
	public String getPageName() {
		return pageName;
	}

	@Override
	public Model getModel() {
		return model;
	}

	@Override
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Override
	public void setModel(Model model) {
		this.model = model;
	}

}
