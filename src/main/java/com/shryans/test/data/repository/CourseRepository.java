package com.shryans.test.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shryans.test.data.entity.Course;
import com.shryans.test.data.entity.User;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

	Course findByName(String Name);
	
	Iterable<Course> findByCreator(User user);
}
