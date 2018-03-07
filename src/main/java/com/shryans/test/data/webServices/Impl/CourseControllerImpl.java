package com.shryans.test.data.webServices.Impl;

import java.sql.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.RegistrationId;
import com.shryans.test.data.entity.Role;
import com.shryans.test.data.entity.Student;
import com.shryans.test.data.entity.User;
import com.shryans.test.data.repository.CourseRepository;
import com.shryans.test.data.repository.RegistrationRepository;
import com.shryans.test.data.repository.StudentRepository;
import com.shryans.test.data.repository.UserRepository;
import com.shryans.test.data.webServices.CourseController;
import com.shryans.test.navigation.NavigationPage;
import com.shryans.test.navigation.NavigationPageBuilder;
import com.shryans.test.navigation.NavigationPageBuilderFactory;
import com.shryans.test.navigation.Page;
import com.shryans.test.navigation.impl.builder.factory.CourseNavigationPageBuilderFactory;

@Controller
public class CourseControllerImpl implements CourseController {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RegistrationRepository registrationRepository;

	@Resource(type=CourseNavigationPageBuilderFactory.class)
	private NavigationPageBuilderFactory navigationFactory;

	@Override
	@RequestMapping(value="/{userId}/courses", method=RequestMethod.GET)
	public String getAllCourses(@PathVariable(value="userId") int userId, Model model) {
		try {
			User user = userRepository.findOne(userId);
			navigationFactory.setModel(model);
			navigationFactory.setUser(user);

			NavigationPageBuilder builder = (user == null) 
					? navigationFactory.getPageBuilder("Login Required") 
							: navigationFactory.getPageBuilder(Page.AllCourses);
					NavigationPage navigationPage = builder.build();

					model = navigationPage.getModel();
					return navigationPage.getPageName();
		}
		catch(Exception e) {
			e.printStackTrace();
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("An internal error occurred\n" + e.getMessage()).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
	}


	@Override
	@RequestMapping(value="/{userId}/getAddCourse", method=RequestMethod.GET)
	public String getAddCoursePage(@PathVariable(value="userId") int userId, Model model) {
		try {
			User user = userRepository.findOne(userId);
			navigationFactory.setModel(model);
			navigationFactory.setUser(user);

			NavigationPageBuilder builder = (user == null) 
					? navigationFactory.getPageBuilder("Login Required") 
							: navigationFactory.getPageBuilder(Page.AddCourse);
					NavigationPage navigationPage = builder.build();

					model = navigationPage.getModel();
					return navigationPage.getPageName();
		}
		catch(Exception e) {
			e.printStackTrace();
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("An internal error occurred\n" + e.getMessage()).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
	}


	@Override
	@RequestMapping(value="/{userId}/addCourse", method=RequestMethod.POST)
	public String getAddCoursePage(@ModelAttribute Course course, @PathVariable(value="userId") int userId, Model model) {
		try {
			User user = userRepository.findOne(userId);
			course.setCreator(user);
			long today = new java.util.Date().getTime();
			course.setCreatedOn(new Date(today));
			course.setModifiedOn(new Date(today));
			course = courseRepository.save(course);

			navigationFactory.setCourse(course);
			navigationFactory.setModel(model);
			navigationFactory.setUser(user);
			NavigationPageBuilder builder = (user == null) 
					? navigationFactory.getPageBuilder("Login Required") 
							: navigationFactory.getPageBuilder(Page.CourseDetails);
					NavigationPage navigationPage = builder.build();

					model = navigationPage.getModel();
					return navigationPage.getPageName();
		}

		catch(Exception e) {
			e.printStackTrace();
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("An internal error occurred\n" + e.getMessage()).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
	}

	@Override
	@RequestMapping(value="/{userId}/course/{courseId}", method=RequestMethod.GET)
	public String getCourseDetails(@PathVariable(value="courseId") int courseId, @PathVariable(value="userId") int userId, Model model) {
		try {
			User user = userRepository.findOne(userId);
			Course course = courseRepository.findOne(courseId);

			navigationFactory.setModel(model);
			navigationFactory.setUser(user);
			navigationFactory.setCourse(course);
			if(user == null || user.getRole() == Role.STUDENT) {
				Student student = studentRepository.findOne(userId);
				navigationFactory.setStudent(student);
				Registration registration = registrationRepository.findOne(new RegistrationId(student, course));
				navigationFactory.setRegistration(registration);
			}
			NavigationPageBuilder builder = (user == null) 
					? navigationFactory.getPageBuilder("Login Required")
							: (course == null)
							? navigationFactory.getPageBuilder("Page Not Found")
									: navigationFactory.getPageBuilder(Page.CourseDetails);
							NavigationPage navigationPage = builder.build();					
							model = navigationPage.getModel();
							return navigationPage.getPageName();
		}
		catch(Exception e) {
			e.printStackTrace();
			NavigationPage navigationPage = navigationFactory
					.getPageBuilder("An internal error occurred\n" + e.getMessage()).build();
			model = navigationPage.getModel();
			return navigationPage.getPageName();
		}
	}

}
