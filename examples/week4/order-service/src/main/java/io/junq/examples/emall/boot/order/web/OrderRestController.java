package io.junq.examples.emall.boot.order.web;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.junq.examples.emall.boot.order.domain.Order;
import io.junq.examples.emall.boot.order.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 订单API入口
 * @author junqiangliu
 *
 */
@RestController("订单相关API")
@RequestMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class OrderRestController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);
	
	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value = "创建订单", notes = "")
	@ApiImplicitParams({
		
	})
	@ApiResponses({
		@ApiResponse(code = 201,message = "订单创建成功" ),
	})
	@RequestMapping(method = RequestMethod.POST, value = "/orders", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@PreAuthorize("hasRole('USER')")
	public Order createOrder(Principal principal, @ApiParam(name = "order", value = "待创建的订单对象json实例", required = true) @RequestBody @Validated Order order) {
		LOGGER.debug("try to create order by order: " + order + " principal: " + principal.getName());
		return orderService.createOrder(order);
	}
	
	@ApiOperation(value = "查询订单", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", paramType = "path", value = "订单id", required = true, dataType = "Long")
		})
	@RequestMapping(method = RequestMethod.GET, value = "/orders/{orderId}")
	@PreAuthorize("hasRole('USER')")
	public Order findOrderById(@PathVariable Long orderId) {
		LOGGER.debug("try to find order by id: " + orderId);
		return orderService.findOrderById(orderId);
	}
	
	@ApiOperation(value = "查询用户订单", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", paramType = "path", value = "用户id", required = true, dataType = "String")
		})
	@RequestMapping(method = RequestMethod.GET, value = "/orders/users/{userId}")
	@PreAuthorize("hasRole('USER')")
	public List<Order> findOrdersByUserId(@PathVariable String userId) {
		LOGGER.debug("try to find orders by userId: " + userId);
		return orderService.findOrdersByUserId(userId);
	}
}
