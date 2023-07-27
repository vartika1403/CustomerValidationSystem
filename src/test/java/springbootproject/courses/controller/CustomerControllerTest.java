package springbootproject.courses.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import springbootproject.courses.entities.Customer;
import springbootproject.courses.services.CustomerService;
import springbootproject.courses.services.ValidationService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {
	private MockMvc mockMvc;

	
	@Mock
    CustomerService customerService;
	
	@Mock
	private ValidationService validationService;
	
	 Customer customer;
 
	 @InjectMocks
	 private CustomerController customerController ;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    	customer = new Customer();
    	customer.setId(1);
    	customer.setCompanyName("Infosys");
    	customer.setEmail("vartika.1403@gmail.com");
    	customer.setNoOfSubscriptions(2);
    	customer.setValidationStatus("Not Started");
    	
    }
 
    @Test
    public void testGetAllCustomers() throws Exception {
    	List<Customer> customerList = new ArrayList<>();
    	customerList.add(customer);
    	when(customerService.getCustomers()).thenReturn(customerList);
    	mockMvc.perform(get("/customers")).andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(customerList.size())));
    	
    }
    
    @Test
    public void testGetOneCustomers() throws Exception {
    	when(customerService.getCustomer(customer.getId())).thenReturn(customer);
    	Customer customer1 = customerController.getCustomer(""+customer.getId());
    	assert(customer1.getEmail()).equals(customer.getEmail());	
    }
    
    @Test
    public void testCustomerValidationStatus() throws Exception {
    	when(validationService.getValidationStatus(customer.getId())).thenReturn("In Progress");
    	String status = customerController.getCustomerValidationStatus(""+customer.getId());
    	assert(status).equals("In Progress");	
    }
    
    @Test
    public void testCreateCustomer() throws Exception {
    	when(validationService.getValidationStatus(customer.getId())).thenReturn("In Progress");
    	String status = customerController.getCustomerValidationStatus(""+customer.getId());
    	assert(status).equals("In Progress");	
    }
}
