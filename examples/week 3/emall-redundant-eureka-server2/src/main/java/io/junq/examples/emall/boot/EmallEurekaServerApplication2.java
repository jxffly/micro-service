package io.junq.examples.emall.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EmallEurekaServerApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(EmallEurekaServerApplication2.class, args);
	}
}
