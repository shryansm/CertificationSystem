package com.shryans.test.util.impl;

import org.springframework.stereotype.Service;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.Student;
import com.shryans.test.util.CertificateContentGenerator;

@Service
public class CertificateStandardContentGeneratorImpl implements CertificateContentGenerator {

	private Student student;
	private Course course;
	private Registration registration;

	@Override
	public String[] generate() {
		String[] content = {"This is to certify that ",
				student.getUsername(),			
				"has passed the course",
				course.getName(),
							" on " + registration.getAttemptedOn().toString()};
		
		return content;
	}

	@Override
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

}
