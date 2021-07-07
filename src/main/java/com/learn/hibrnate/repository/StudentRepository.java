package com.learn.hibrnate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learn.hibrnate.entity.Passport;
import com.learn.hibrnate.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public void deleteById(Long id) {
		Student student= em.find(Student.class, id);
		 em.remove(student);
	}

	public void save(Student student) {
		if (student.getId() == null)
			em.persist(student);
		else
			em.merge(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);

		Student student = new Student("Akhilesh");

		student.setPassport(passport);
		em.persist(student);	
	}

}
