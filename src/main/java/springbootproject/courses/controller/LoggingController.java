package springbootproject.courses.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RestController
public class LoggingController {
	  Logger logger = LoggerFactory.getLogger(LoggingController.class);

	    public void info(String info) {
	        logger.info(info);
	    }
}
