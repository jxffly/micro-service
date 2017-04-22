package io.junq.examples.emall.boot.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.junq.examples.emall.boot.api.EmallAPIResponse;
import io.junq.examples.emall.boot.domain.Order;
import io.junq.examples.emall.boot.domain.User;
import io.junq.examples.emall.boot.order.domain.OrderRepository;

/**
 * 订单服务实现
 * @author junqiangliu
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	
    @Autowired
    private OrderRepository orderRepo;
	
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;
    
	@Override
	public Order createOrder(Order order) throws Exception {
		
		Order.Builder builder = Order.Builder.newBuilder();
		builder.withUserId(order.getUserId());
		builder.withProducts(order.getProducts());
		builder.withAmount(order.getAmount());
		builder.withStatus(order.getStatus());
		builder.withPaymentType(order.getPaymentType());
		builder.withNote(order.getNote());
		
		Order newOrder = builder.build();
		long newOrderId = orderRepo.createOrder(newOrder);
		LOGGER.debug("new order created with id: " + newOrderId);
		return findOrderById(newOrderId);
	}

	@Override
	public Order findOrderById(long id) {
		return orderRepo.findOrderById(id);
	}

	@Override
	public List<Order> findOrdersByUserId(String userId) {
		return orderRepo.findOrdersByUserId(userId);
	}

	@HystrixCommand(
			fallbackMethod = "getUserByDisplayIdFallback",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000")
				}
			)
	@Override
	public EmallAPIResponse<User> getUserByDisplayId(String displayId) {
		return restTemplate.
				exchange("http://account-service/v1/users/" + displayId,
							HttpMethod.GET,
							null,
							new ParameterizedTypeReference<EmallAPIResponse<User>>() {}).getBody();
	}

	@Override
	public EmallAPIResponse<User> getUserByDisplayIdFallback(String displayId) {
		return null;
	}

}
