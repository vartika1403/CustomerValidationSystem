package springbootproject.courses.services;

import java.util.List;

import springbootproject.courses.entities.Customer;

public interface CustomerService {
	
	public Customer getCustomer(long id);
	
	public List<Customer> getCustomers();
	
	public Customer addCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);
	
	public void deleteCustomer(long id);


}
