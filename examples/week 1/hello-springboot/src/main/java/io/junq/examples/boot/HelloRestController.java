package io.junq.examples.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloRestController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "text/plain")
	public String hello() {
		return "Hello world, Spring Boot rocks";
	}
	
}
