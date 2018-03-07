package com.shryans.test.data.webServices;

import org.springframework.ui.Model;

import com.shryans.test.data.entity.Course;

public interface CourseController {

	String getCourseDetails(int courseId, int userId, Model model);
	String getAllCourses(int user, Model model);
	String getAddCoursePage(int userId, Model model);
	String getAddCoursePage(Course course, int userId, Model model);
}
