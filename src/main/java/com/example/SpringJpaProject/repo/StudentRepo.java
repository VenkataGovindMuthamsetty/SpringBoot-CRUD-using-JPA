package com.example.SpringJpaProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringJpaProject.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
	
}
