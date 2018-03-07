package com.shryans.test.navigation.impl.builder.factory;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.shryans.test.navigation.NavigationPageBuilder;
import com.shryans.test.navigation.Page;
import com.shryans.test.navigation.impl.NavigationPageBuilderFactoryImpl;
import com.shryans.test.navigation.impl.builder.AddUserPageBuilder;
import com.shryans.test.navigation.impl.builder.LoginSuccessPageBuilder;
import com.shryans.test.navigation.impl.builder.SignupSuccessStudentPageBuilder;

@Service
public class UserNavigationPageBuilderFactory extends NavigationPageBuilderFactoryImpl {

	@Resource(type=LoginSuccessPageBuilder.class)
	private NavigationPageBuilder loginSuccessPageBuilder;

	@Resource(type=SignupSuccessStudentPageBuilder.class)
	private NavigationPageBuilder signupSuccessStudentPageBuilder;

	@Resource(type=AddUserPageBuilder.class)
	private NavigationPageBuilder addUserPageBuilder;
	
	@Override
	public NavigationPageBuilder getPageBuilder(Page page) {
		NavigationPageBuilder pageBuilder = null;
		switch(page) {
		case SignupSuccess:
			pageBuilder = signupSuccessStudentPageBuilder;
			break;
		case AddAdminUser:
			pageBuilder = addUserPageBuilder;
			break;
		default:
			pageBuilder = loginSuccessPageBuilder;
			break;
		}
		pageBuilder.setModel(model);
		pageBuilder.setUser(user);
		return pageBuilder;
	}
}
