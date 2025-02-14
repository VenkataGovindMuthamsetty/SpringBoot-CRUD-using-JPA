package com.example.SpringJpaProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringJpaProject.entity.Student;
import com.example.SpringJpaProject.repo.StudentRepo;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepo studentRepo;
	
	@PostMapping("/api/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student userDetails) {
		return new ResponseEntity<>(studentRepo.save(userDetails),HttpStatus.CREATED);
	}
	
	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getStudents(){
		return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Long id,@RequestBody Student userDetails){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			student.get().setStudentName(userDetails.getStudentName());
			student.get().setStudentEmail(userDetails.getStudentEmail());
			student.get().setStudentAddress(userDetails.getStudentAddress());
			return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
