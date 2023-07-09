package com.schoolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolapp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
