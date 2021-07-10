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
import com.learn.hibrnate.entity.Student;
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
	
	
	// SELECT * FROM COURSE WHERE COURSE.ID NOT IN (SELECT COURSE_ID FROM STUDENT_COURSE)
	@Test
	public void jpql_courses_without_student() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty",  Course.class);
		List<Course> courses = query.getResultList();
		logger.info("Course without student -> {}", courses);;
	}
	
	@Test
	public void jpql_courses_with_atleast_2_student() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2",  Course.class);
		List<Course> courses = query.getResultList();
		logger.info("Course with_atleast_2_student -> {}", courses);;
	}
	
	@Test
	public void jpql_courses_with_Order_by_student() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc",  Course.class);
		List<Course> courses = query.getResultList();
		logger.info("Course Order by -> {}", courses);;
	}
	
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("students_with_passports_in_a_certain_pattern -> {}", resultList);
	}
	
	//like
		//BETWEEN 100 and 1000
		//IS NULL
		//upper, lower, trim, length
		
		//JOIN => Select c, s from Course c JOIN c.students s
		//LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
		//CROSS JOIN => Select c, s from Course c, Student s
		//3 and 4 =>3 * 4 = 12 Rows
		@Test
		public void join(){
			Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
			List<Object[]> resultList = query.getResultList();
			logger.info("Results Size -> {}", resultList.size());
			for(Object[] result:resultList){
				logger.info("Course{} Student{}", result[0], result[1]);
			}
		}

		@Test
		public void left_join(){
			Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
			List<Object[]> resultList = query.getResultList();
			logger.info("Results Size -> {}", resultList.size());
			for(Object[] result:resultList){
				logger.info("Course{} Student{}", result[0], result[1]);
			}
		}

		@Test
		public void cross_join(){
			Query query = em.createQuery("Select c, s from Course c, Student s");
			List<Object[]> resultList = query.getResultList();
			logger.info("Results Size -> {}", resultList.size());
			for(Object[] result:resultList){
				logger.info("Course{} Student{}", result[0], result[1]);
			}
		}
  }
