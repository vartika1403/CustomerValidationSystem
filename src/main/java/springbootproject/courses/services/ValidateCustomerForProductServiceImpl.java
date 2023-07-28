package springbootproject.courses.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import springbootproject.courses.controller.LoggingController;
import springbootproject.courses.entities.Customer;
import springbootproject.courses.entities.ValidationStatus;
import springbootproject.courses.utils.CustomerValidation;

@Service
public class ValidateCustomerForProductServiceImpl implements ValidateCustomerForProductService{

	@Autowired
    public LoggingController logger;
	
	@Override
	@Async
	public CompletableFuture<ValidationStatus> validateCustomerForProduct(int productNo, Customer customer) {
		logger.info("Thread name 3:- " + Thread.currentThread().getName() + " email: " + customer.getEmail());

		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (!CustomerValidation.isValidEmail(customer.getEmail()) 
				|| CustomerValidation.isValidPanCardNo(customer.getPanNo())) {

			return CompletableFuture.completedFuture(ValidationStatus.REJECTED);
		}
		
		return CompletableFuture.completedFuture(ValidationStatus.ACCEPTED);
		
	}

}
