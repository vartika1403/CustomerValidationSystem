package springbootproject.courses.services;

import springbootproject.courses.entities.Customer;

public interface ValidationService {
	
	public String getValidationStatus(Long customerId);
	
	public void validateCustomer(Customer customer);

}
