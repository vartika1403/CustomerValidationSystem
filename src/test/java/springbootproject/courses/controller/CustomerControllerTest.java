package springbootproject.courses.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CustomerControllerTest {
	@InjectMocks
    private CustomerController customerController;
 
    private MockMvc mockMvc;
 
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
 
    @Test
    public void testCreateSignupFormInvalidUser() throws Exception {
        //this.mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @AfterEach
    public void tearDown() {
    	
    }
}
