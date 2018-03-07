package com.shryans.test.util.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.Student;
import com.shryans.test.util.CertificateContentGenerator;
import com.shryans.test.util.CertificateFileGenerator;
import com.shryans.test.util.CertificateFileNameGenerator;
import com.shryans.test.util.CertificateGeneratorFacade;

@Service
public class CertificateStandardPdfGeneratorFacadeImpl implements CertificateGeneratorFacade {

	@Autowired
	CertificateContentGenerator contentGenerator;
	
	@Autowired
	CertificateFileNameGenerator fileNameGenerator;
	
	@Autowired
	CertificateFileGenerator fileGenerator;

	private Student student;
	private Course course;
	private Registration registration;
	private HttpServletResponse response;
	
	@Override
	public void generate() {
		contentGenerator.setStudent(student);
		contentGenerator.setCourse(course);
		contentGenerator.setRegistration(registration);

		fileNameGenerator.setStudent(student);
		fileNameGenerator.setCourse(course);
		
		String[] content = contentGenerator.generate();
		String filename = fileNameGenerator.generate();
		
		try {
			ByteArrayOutputStream stream = fileGenerator.generate(content);
			filename = filename + fileGenerator.getFileTypeExtension();
			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			response.getOutputStream().write(stream.toByteArray());
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	@Override
	public void setServlet(HttpServletResponse response) {
		this.response = response;
	}

}
