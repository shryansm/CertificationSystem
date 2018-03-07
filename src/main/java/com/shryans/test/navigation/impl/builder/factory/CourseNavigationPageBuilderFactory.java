package com.shryans.test.navigation.impl.builder.factory;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.shryans.test.navigation.NavigationPageBuilder;
import com.shryans.test.navigation.Page;
import com.shryans.test.navigation.impl.NavigationPageBuilderFactoryImpl;
import com.shryans.test.navigation.impl.builder.AddCoursePageBuilder;
import com.shryans.test.navigation.impl.builder.AllCoursesPageBuilder;
import com.shryans.test.navigation.impl.builder.CourseDetailsPageBuilder;

@Service
public class CourseNavigationPageBuilderFactory extends NavigationPageBuilderFactoryImpl {

	@Resource(type = CourseDetailsPageBuilder.class)
	private NavigationPageBuilder courseDetailsPageBuilder;

	@Resource(type = AllCoursesPageBuilder.class)
	private NavigationPageBuilder allCoursesPageBuilder;

	@Resource(type = AddCoursePageBuilder.class)
	private NavigationPageBuilder addCoursePageBuilder;

	@Override
	public NavigationPageBuilder getPageBuilder(Page page) {
		
		NavigationPageBuilder pageBuilder = null;
		
		switch(page) {
		case AllCourses:
			pageBuilder = allCoursesPageBuilder;
			break;
		case AddCourse:
			pageBuilder = addCoursePageBuilder;
			break;
		default:
			pageBuilder = courseDetailsPageBuilder;
		}
		pageBuilder.setModel(model);
		pageBuilder.setUser(user);
		pageBuilder.setCourse(course);
		if(student != null)pageBuilder.setStudent(student);
		if(registration != null)pageBuilder.setRegistration(registration);
		return pageBuilder;
	}
}