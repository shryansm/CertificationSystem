package com.shryans.test.util;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Student;

public interface CertificateFileNameGenerator {
	void setStudent(Student student);
	void setCourse(Course course);
	
	String generate();
}
