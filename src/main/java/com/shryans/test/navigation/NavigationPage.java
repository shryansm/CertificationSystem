package com.shryans.test.navigation;

import org.springframework.ui.Model;

public interface NavigationPage {
	String getPageName();
	Model getModel();
	
	void setPageName(String pageName);
	void setModel(Model model);
}
