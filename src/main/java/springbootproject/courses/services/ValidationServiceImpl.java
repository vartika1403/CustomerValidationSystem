package springbootproject.courses.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import springbootproject.courses.controller.LoggingController;
import springbootproject.courses.dao.CustomerDao;
import springbootproject.courses.entities.Customer;
import springbootproject.courses.entities.ValidationStatus;
import springbootproject.courses.utils.CustomerValidation;


@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	CustomerDao customerDao;
    private Customer customer;

    @Autowired
    private LoggingController logger;
    
    @Autowired
    private ValidateCustomerForProductService validateCustomerForProductService;
	
	@Override
	public String getValidationStatus(Long customerId) {
		if (customer == null) {
		 customer = customerDao.getOne(customerId);
		}
		return customer.getValidationStatus();
	}

	@Override
	@Async
	public void validateCustomer(Customer customer) {
		Long start = System.currentTimeMillis();

		logger.info("Thread name 2:- " + Thread.currentThread().getName());

		this.customer = customer;
		customer.setValidationStatus(ValidationStatus.IN_PROGRESS.getValue());
        int n = customer.getNoOfSubscriptions();
        int i = 0;
        List<CompletableFuture<ValidationStatus>> validationStatusFuturesList = new ArrayList<>();
        while (i < n) {
    	   validationStatusFuturesList.add(validateCustomerForProductService.validateCustomerForProduct(i, customer));
    	   i++;
        }
        CompletableFuture.allOf(validationStatusFuturesList.toArray(new CompletableFuture[]{})).join();
       
        for(CompletableFuture<ValidationStatus> validationStatus : validationStatusFuturesList) {
   
        	 try {
				if(validationStatus.get() == ValidationStatus.REJECTED) {
					customer.setValidationStatus(ValidationStatus.REJECTED.getValue());
					return;
				 }
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
           }
    
					
		Long end = System.currentTimeMillis();

		logger.info("Thread name 6:- " +(end-start));

       customer.setValidationStatus(ValidationStatus.ACCEPTED.getValue());
       customerDao.save(customer);
	}	
}
