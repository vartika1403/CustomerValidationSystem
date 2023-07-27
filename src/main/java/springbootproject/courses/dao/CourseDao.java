package springbootproject.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootproject.courses.entities.Course;

public interface CourseDao extends JpaRepository<Course, Long>{

}
