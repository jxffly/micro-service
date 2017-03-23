package io.junq.examples.emall.boot.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@ComponentScan({ "io.junq.examples.emall" })
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SimpleOauthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleOauthServerApplication.class, args);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/resource/read")
	@PreAuthorize("hasRole('USER')")
	public String testRead() {
		return "A welcome message for read action.";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/resource/write")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public String testWrite() {
		return "Succeed: test write action.";
	}

}
