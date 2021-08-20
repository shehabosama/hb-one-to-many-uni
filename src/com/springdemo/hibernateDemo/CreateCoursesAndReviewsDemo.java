package com.springdemo.hibernateDemo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springdemo.hibernate.entity.Course;
import com.springdemo.hibernate.entity.Instructor;
import com.springdemo.hibernate.entity.InstructorDetail;
import com.springdemo.hibernate.entity.Review;
import com.springdemo.hibernate.entity.Student;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
				
	
		    //start trans action
			session.beginTransaction();
	
			// create course
			Course tempCourse = new Course("PHP");
			//add some reviews to the course
			tempCourse.add(new Review("Greate"));
			tempCourse.add(new Review("bad"));
			tempCourse.add(new Review("good"));
			tempCourse.add(new Review("excelint"));
			
			// save the course and leverage the cascade all
			session.save(tempCourse);
		
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			}finally {
				session.close();
				factory.close();
			
			}
		}

		
	
}
