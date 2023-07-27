package springbootproject.courses;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseApplicationTest {

	@Test
	void contextLoads() {
	}

	 @Test    
	    public void testReverseWord(){    
	        System.out.println("test case reverse word");  
	        assertEquals("ym eman si nahk ","my name is khan");    
	    }  
}
