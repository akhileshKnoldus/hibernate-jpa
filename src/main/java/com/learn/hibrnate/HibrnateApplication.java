package com.learn.hibrnate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learn.hibrnate.entity.Course;
import com.learn.hibrnate.entity.FullTimeEmployee;
import com.learn.hibrnate.entity.PartTimeEmployee;
import com.learn.hibrnate.entity.Review;
import com.learn.hibrnate.entity.Student;
import com.learn.hibrnate.repository.CourseRepository;
import com.learn.hibrnate.repository.EmployeeRepository;
import com.learn.hibrnate.repository.StudentRepository;

@SpringBootApplication
public class HibrnateApplication implements CommandLineRunner {

	@Autowired
	CourseRepository repository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(HibrnateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// repository.playWithEntityManager();
		// studentRepository.saveStudentWithPassport();
		// repository.addReviewsForCourse();

		List<Review> reviews = new ArrayList<>();

		reviews.add(new Review("5", "Great Hands-on Stuff."));
		reviews.add(new Review("5", "Hatsoff."));

		//repository.addReviewsForCourse(10003L, reviews);
		//studentRepository.insertHardcodedStudentAndCourse();
		//studentRepository.insertStudentAndCourse(new Student("Jack"), 
			//	new Course("Microservices in 100 Steps"));
		
		employeeRepository.insert(new PartTimeEmployee("Akhilesh", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Akhilesh Gupta", new BigDecimal("500")));
		
		logger.info("Employee details ->{}",employeeRepository.retriveAllEmployees());

	}

}
