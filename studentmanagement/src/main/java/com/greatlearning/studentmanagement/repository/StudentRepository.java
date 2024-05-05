package com.greatlearning.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.studentmanagement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
