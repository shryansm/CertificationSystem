package com.shryans.test.data.webServices;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface RegistrationController {

	String apply(int courseId, int userId, Model model);
	String getRegistrationsUpdateData(int courseId, int userId, Model model);
	String update(int userId, int courseId, int studentId, String status, Model model);
	void getCertificate(int courseId, int userId, HttpServletResponse response);
}
