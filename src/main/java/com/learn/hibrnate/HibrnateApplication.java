package com.learn.hibrnate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learn.hibrnate.entity.Course;
import com.learn.hibrnate.repository.CourseRepository;
import com.learn.hibrnate.repository.StudentRepository;

@SpringBootApplication
public class HibrnateApplication implements CommandLineRunner {

	@Autowired
	CourseRepository repository;
	
	@Autowired
	private StudentRepository studentRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(HibrnateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//repository.playWithEntityManager();
		//studentRepository.saveStudentWithPassport();

	}

}
