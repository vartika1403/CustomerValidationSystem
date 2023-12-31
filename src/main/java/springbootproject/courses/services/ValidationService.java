package springbootproject.courses.services;

import java.util.concurrent.CompletableFuture;

import springbootproject.courses.entities.Customer;
import springbootproject.courses.entities.ValidationStatus;

public interface ValidationService {
	
	public String getValidationStatus(Long customerId);
	
	public void validateCustomer(Customer customer);

}
