package com.shryans.test.data.webServices.Impl;

import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.CourseStatus;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.RegistrationId;
import com.shryans.test.data.entity.Student;
import com.shryans.test.data.entity.User;
import com.shryans.test.data.repository.CourseRepository;
import com.shryans.test.data.repository.RegistrationRepository;
import com.shryans.test.data.repository.StudentRepository;
import com.shryans.test.data.repository.UserRepository;
import com.shryans.test.data.webServices.RegistrationController;
import com.shryans.test.navigation.NavigationPage;
import com.shryans.test.navigation.NavigationPageBuilder;
import com.shryans.test.navigation.NavigationPageBuilderFactory;
import com.shryans.test.navigation.Page;
import com.shryans.test.navigation.impl.builder.factory.RegistrationNavigationPageBuilderFactory;
import com.shryans.test.util.CertificateGeneratorFacade;

@Controller
public class RegistrationControllerImpl implements RegistrationController {

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CertificateGeneratorFacade certificateGenerator;

	@Resource(type=RegistrationNavigationPageBuilderFactory.class)
	private NavigationPageBuilderFactory navigationFactory;

	@Override
	@RequestMapping(value="/{userId}/course/{courseId}/apply", method=RequestMethod.GET)
	public String apply(@PathVariable(value="courseId") int courseId, @PathVariable(value="userId") int userId, Model model){
		try {
			Student student = studentRepository.findOne(userId);
			Course course = courseRepository.findOne(courseId);
			Registration registration = new Registration();
			RegistrationId id = new RegistrationId(student, course);
			registration.setRegistrationId(id);
			long today = new java.util.Date().getTime();
			registration.setRegisteredOn(new Date(today));
			registration.setStatus(CourseStatus.PENDING);
			registration = registrationRepository.save(registration);

			navigationFactory.setUser(student);
			navigationFactory.setCourse(course);
			navigationFactory.setModel(model);
			NavigationPage navigationPage = navigationFactory.getPageBuilder(Page.CourseDetails).build();
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
	@RequestMapping(value="/{userId}/course/{courseId}/certificate", method=RequestMethod.GET)
	public void getCertificate(@PathVariable(value="courseId") int courseId, @PathVariable(value="userId") int userId, HttpServletResponse response){
		try {
			Student student = studentRepository.findOne(userId);
			Course course = courseRepository.findOne(courseId);
			RegistrationId id = new RegistrationId(student, course);
			Registration registration = registrationRepository.findOne(id);
			certificateGenerator.setStudent(student);
			certificateGenerator.setCourse(course);
			certificateGenerator.setRegistration(registration);
			certificateGenerator.setServlet(response);
			certificateGenerator.generate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	@RequestMapping(value="/{userId}/course/{courseId}/update", method=RequestMethod.GET)
	public String getRegistrationsUpdateData(@PathVariable(value="courseId") int courseId, @PathVariable(value="userId") int userId, Model model){
		try {
			User user = userRepository.findOne(userId);
			Course course = courseRepository.findOne(courseId);
			navigationFactory.setUser(user);
			navigationFactory.setCourse(course);
			navigationFactory.setModel(model);
			NavigationPageBuilder pageBuilder = navigationFactory.getPageBuilder(Page.CourseRegistrations);
			NavigationPage navigationPage = pageBuilder.build();
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
	@RequestMapping(value="/{userId}/course/{courseId}/student/{studentId}/{status}", method=RequestMethod.GET)
	public String update(@PathVariable(value="userId") int userId, @PathVariable(value="courseId") int courseId, 
			@PathVariable(value="studentId") int studentId, @PathVariable(value="status") String status, Model model){
		try {
			User user = userRepository.findOne(userId);
			Course course = courseRepository.findOne(courseId);
			Student student = studentRepository.findOne(studentId);
			Registration registration = registrationRepository.findOne(new RegistrationId(student, course));
			registration.setStatus(CourseStatus.valueOf(status));
			long today = new java.util.Date().getTime();
			registration.setAttemptedOn(new Date(today));
			registration = registrationRepository.save(registration);
			navigationFactory.setUser(user);
			navigationFactory.setStudent(student);
			navigationFactory.setCourse(course);
			navigationFactory.setRegistration(registration);
			navigationFactory.setModel(model);
			NavigationPageBuilder pageBuilder = navigationFactory.getPageBuilder(Page.CourseRegistrations);
			NavigationPage navigationPage = pageBuilder.build();
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
