package com.shryans.test.util.impl;

import org.springframework.stereotype.Service;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Student;
import com.shryans.test.util.CertificateFileNameGenerator;

@Service
public class CertificateStandardNameGeneratorImpl implements CertificateFileNameGenerator {

	private Student student;
	private Course course;

	@Override
	public String generate() {
		String content = student.getUsername() + "_" + course.getName() + "_certificate";
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

}
