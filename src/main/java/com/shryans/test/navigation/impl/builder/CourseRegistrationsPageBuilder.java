package com.shryans.test.navigation.impl.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.shryans.test.data.entity.CourseStatus;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.User;
import com.shryans.test.data.repository.RegistrationRepository;
import com.shryans.test.navigation.impl.NavigationPageBuilderImpl;

@Service
public class CourseRegistrationsPageBuilder extends NavigationPageBuilderImpl {

	@Autowired
	private RegistrationRepository registrationRepository;

	@Override
	protected String getPage() {
		String pageName= "courseRegistrations";
		return pageName;
	}

	@Override
	protected Model getModel() {
		List<Registration> registrations = registrationRepository.findByIdCourse(course);
		List<User> pending = new ArrayList<>();
		List<User> pass = new ArrayList<>();
		List<User> fail = new ArrayList<>();
		registrations.forEach(r ->{
			if(r.getStatus().equals(CourseStatus.PENDING))
				pending.add(r.getStudent());
			else if (r.getStatus().equals(CourseStatus.PASS))
				pass.add(r.getStudent());
			else fail.add(r.getStudent());
		});
		model.addAttribute("pending", pending);
		model.addAttribute("pass", pass);
		model.addAttribute("fail", fail);
		return model;
	}
}
