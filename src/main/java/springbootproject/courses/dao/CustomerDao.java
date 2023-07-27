package springbootproject.courses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootproject.courses.entities.Course;
import springbootproject.courses.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long>{

}
