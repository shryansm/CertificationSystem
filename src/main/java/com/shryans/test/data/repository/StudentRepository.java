package com.shryans.test.data.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shryans.test.data.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

	Student findByUsername(String username);
}
