package springbootproject.courses.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootproject.courses.dao.CourseDao;
import springbootproject.courses.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {
	List<Course> courses;
	
	@Autowired
	private CourseDao courseDao;

	public CourseServiceImpl() {
		courses = new ArrayList<>();
	    courses.add(new Course(145, "Java Core Courses", "this contains all courses"));
		courses.add(new Course(146, "Spring Boot Course", "This is spring boot course"));
	}

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseDao.findAll();
	}

	@Override
	public Course getCourse(long courseId) {
		/*
		 * Course c = null; for(Course course : courses) { if (course.getId() ==
		 * courseId) return course; } return c;
		 */
		return courseDao.findById(courseId).get();
	}

	@Override
	public Course addCourse(Course course) {
		return courseDao.save(course);
	}

	@Override
	public Course updateCourse(Course course) {
		/*
		 * courses.forEach(c1 -> { if (c1.getId() == course.getId()) {
		 * c1.setDescription(course.getDescription()); c1.setTitle(course.getTitle()); }
		 * });
		 */
		
			return courseDao.save(course);
	}

	@Override
	public void deleteCourse(long courseId) {
		courses.stream().filter(e -> e.getId() != courseId).collect(Collectors.toList());		
	}

}
