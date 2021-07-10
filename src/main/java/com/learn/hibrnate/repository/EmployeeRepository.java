package com.learn.hibrnate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learn.hibrnate.entity.Employee;

@Repository
public class EmployeeRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	public List<Employee> retriveAllEmployees(){
		return em.createQuery("select e from Employee e",Employee.class).getResultList();
	}

}
