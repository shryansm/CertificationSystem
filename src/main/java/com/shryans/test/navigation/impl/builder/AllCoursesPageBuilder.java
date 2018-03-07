package com.shryans.test.navigation.impl.builder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.repository.CourseRepository;
import com.shryans.test.navigation.impl.NavigationPageBuilderImpl;

@Service
public class AllCoursesPageBuilder extends NavigationPageBuilderImpl {
	@Autowired
	private CourseRepository courseRepository;

	@Override
	protected String getPage() {
		String pageName= "courses";
		return pageName;
	}

	@Override
	protected Model getModel() {
		List<Course> courses = (List<Course>) courseRepository.findAll();
		model.addAttribute("courses", courses);
		return model;
	}
}
