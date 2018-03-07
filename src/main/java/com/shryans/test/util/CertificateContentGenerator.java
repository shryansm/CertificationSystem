package com.shryans.test.util;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.Student;

public interface CertificateContentGenerator {
	void setStudent(Student student);
	void setCourse(Course course);
	void setRegistration(Registration registration);
	
	String[] generate();
}
