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

import springbootproject.courses.dao.CustomerDao;
import springbootproject.courses.entities.Customer;
import springbootproject.courses.entities.ValidationStatus;


@Service
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	CustomerDao customerDao;
    Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);
    private Customer customer;

	
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
		logger.info("Thread name 2:- " + Thread.currentThread().getName());

		this.customer = customer;
		customer.setValidationStatus(ValidationStatus.IN_PROGRESS.getValue());
       int n = customer.getNoOfSubscriptions();
       int i = 0;
       List<CompletableFuture<ValidationStatus>> validationStatusFuturesList = new ArrayList<>();
       while (i < n) {
    	   validationStatusFuturesList.add(validateCustomerForProduct(i, customer));
    	   i++;
       }
       CompletableFuture.allOf(validationStatusFuturesList.toArray(new CompletableFuture[]{})).join();
       
        for(CompletableFuture<ValidationStatus> validationStatus : validationStatusFuturesList) {
        	try {
    			logger.info("Thread name 4:- " + validationStatus.get().getValue());
    		} catch (InterruptedException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (ExecutionException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}

        	 try {
					if(validationStatus.get() == ValidationStatus.REJECTED) {
						logger.info("Thread name 5:- ");
						customer.setValidationStatus(ValidationStatus.REJECTED.getValue());
						return;
					}
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
           }
    
					
       try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		logger.info("Thread name 6:- " );

       customer.setValidationStatus(ValidationStatus.ACCEPTED.getValue());
       customerDao.save(customer);
	}
	
	@Async
	public CompletableFuture<ValidationStatus> validateCustomerForProduct(int productNo,Customer customer) {
		logger.info("Thread name 3:- " + Thread.currentThread().getName() + " email: " + customer.getEmail());

		 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (customer.getEmail().isEmpty() || customer.getEmail() == null) {

			return CompletableFuture.completedFuture(ValidationStatus.REJECTED);
		}
		
		return CompletableFuture.completedFuture(ValidationStatus.ACCEPTED);
		
	}
	
	

}
