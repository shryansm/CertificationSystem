package com.shryans.test.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.CourseStatus;
import com.shryans.test.data.entity.Registration;
import com.shryans.test.data.entity.RegistrationId;
import com.shryans.test.data.entity.Student;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, RegistrationId> {

	List<Registration> findByIdUser(Student user);
	
	List<Registration> findByIdCourse(Course course);
	
	List<Registration> findByStatus(CourseStatus status);
}
