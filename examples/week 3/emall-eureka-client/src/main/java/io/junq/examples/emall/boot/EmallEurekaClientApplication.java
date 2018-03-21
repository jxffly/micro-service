package io.junq.examples.emall.boot;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EmallEurekaClientApplication {

	@Autowired
	private EurekaClient client;
	
	@RequestMapping("/account-service")
	public String serviceInfo(){
		InstanceInfo instance = client.getNextServerFromEureka("account-service", false);
		return instance.getHomePageUrl();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EmallEurekaClientApplication.class, args);
	}
}
