package springbootproject.courses.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springbootproject.courses.entities.Course;
import springbootproject.courses.entities.Customer;
import springbootproject.courses.services.CustomerService;
import springbootproject.courses.services.ValidationService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ValidationService validationService;
	
	@Autowired
    public LoggingController logger;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable String customerId) {
		return customerService.getCustomer(Long.parseLong(customerId));
	}
	
	@GetMapping("/customerValidationStatus/{customerId}")
	public String getCustomerValidationStatus(@PathVariable String customerId) {
		return validationService.getValidationStatus(Long.parseLong(customerId));
	}
	
	@PostMapping("/customer")
	public ResponseEntity<HttpStatus> addCustomer(@RequestBody Customer customer) {
		Long start = System.currentTimeMillis();

		logger.info("Thread name 1:- " + Thread.currentThread().getName());
		validationService.validateCustomer(customer);
		
		Long end = System.currentTimeMillis();
		logger.info("Completation Time : {}" + (end-start));
        
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/customer")
	public ResponseEntity<HttpStatus> updateCustomer(@RequestBody Customer customer) {
		Long start = System.currentTimeMillis();

		logger.info("Thread name 1:- " + Thread.currentThread().getName());
		validationService.validateCustomer(customer);
		
		Long end = System.currentTimeMillis();
		logger.info("Completation Time : {}" + (end-start));
        
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String customerId) {
		try {
			customerService.deleteCustomer(Long.parseLong(customerId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
