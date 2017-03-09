package io.junq.examples.emall.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EmallEurekaClientApplication {

	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping("/account-service")
	public String serviceInfo(){
		ServiceInstance instance = loadBalancer.choose("account-service");
		if (instance == null)
			return "Could not find any account-service instances.";
		
		return instance.getUri().toString();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EmallEurekaClientApplication.class, args);
	}
}
