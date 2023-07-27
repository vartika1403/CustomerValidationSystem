package springbootproject.courses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootproject.courses.dao.CustomerDao;
import springbootproject.courses.entities.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerDao;

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(long id) {
		// TODO Auto-generated method stub
		customerDao.deleteById(id);
		
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return customerDao.findAll();
	}

	@Override
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		return customerDao.findById(id).get();
	}

	

}
