package com.shryans.test.util;

import javax.servlet.http.HttpServletResponse;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.Student;

public interface CertificateGeneratorFacade {
	void setStudent(Student student);
	void setCourse(Course course);
	void setRegistration(Registration registration);
	void setServlet(HttpServletResponse response);
	void generate();
	
}
