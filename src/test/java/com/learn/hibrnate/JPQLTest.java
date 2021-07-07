package com.learn.hibrnate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.hibrnate.entity.Course;
import com.learn.hibrnate.repository.CourseRepository;

@SpringBootTest(classes = HibrnateApplication.class)
public class JPQLTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		List list = em.createQuery("select c from Course c").getResultList();
		logger.info("select c from Course c -> {}", list);
	}

	@Test
	public void jpql_query() {
		Query query = em.createQuery("select c from Course c");
		logger.info("select c from Course c -> {}", query.getResultList());
	}

	@Test
	public void jpql_Typedquery() {
		TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
		List<Course> courses = query.getResultList();
		logger.info("select c from Course c -> {}", courses);
	}
	
	@Test
	public void jpql_NamedQuery() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_course",  Course.class);
		List<Course> courses = query.getResultList();
		logger.info("select c from Course c (named query)-> {}", courses);
	}
	
	@Test
	public void jpql_NativeQuery() {
		Query query = em.createNativeQuery("selct * from course");
		List courses = query.getResultList();
		logger.info("select c from Course c (native query)-> {}", courses);
	}
}
