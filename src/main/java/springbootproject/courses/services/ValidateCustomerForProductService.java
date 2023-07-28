package springbootproject.courses.services;

import java.util.concurrent.CompletableFuture;

import springbootproject.courses.entities.Customer;
import springbootproject.courses.entities.ValidationStatus;

public interface ValidateCustomerForProductService {
	CompletableFuture<ValidationStatus> validateCustomerForProduct(int productNo, Customer customer);

}
