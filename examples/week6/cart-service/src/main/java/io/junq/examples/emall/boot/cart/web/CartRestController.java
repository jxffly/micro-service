package io.junq.examples.emall.boot.cart.web;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.junq.examples.emall.boot.cart.domain.Cart;
import io.junq.examples.emall.boot.cart.domain.Item;
import io.junq.examples.emall.boot.cart.service.CartService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 购物车API入口
 * @author junqiangliu
 *
 */
@RestController("购物车相关API")
@RequestMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
@Configuration
public class CartRestController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CartRestController.class);
	
	@Autowired
	private CartService cartService;
	
	@ApiOperation(value = "查询购物车", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId", paramType = "path", value = "顾客id", required = true, dataType = "String")
		})
	@RequestMapping(method = RequestMethod.GET, value = "/carts/{customerId}")
	public List<Cart> findCartsByCustomerId(@PathVariable String customerId) {
		LOGGER.debug("try to find carts by customer id: " + customerId);
		List<Cart> carts = cartService.findCartsByCustomerId(customerId);
		return carts;
	}
	
	@ApiOperation(value = "清空购物车", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId", paramType = "path", value = "顾客id", required = true, dataType = "String")
		})
	@ApiResponses({
		@ApiResponse(code = 202,message = "清空购物车请求已被接受" ),
	})
	@RequestMapping(method = RequestMethod.DELETE, value = "/carts/{customerId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteCartsByCustomerId(@PathVariable String customerId) {
		LOGGER.debug("try to cleanup carts by customer id: " + customerId);
		cartService.cleanupCartsByCustomerId(customerId);
	}
	
	@ApiOperation(value = "添加商品到购物车", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId", paramType = "path", value = "顾客id", required = true, dataType = "String")
		})
	@ApiResponses({
		@ApiResponse(code = 201,message = "添加到购物车成功" ),
	})
	@RequestMapping(method = RequestMethod.POST, value = "/carts/{customerId}/items", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public void addItemIntoCart(@PathVariable String customerId, 
			@ApiParam(name = "item", value = "待添加的item对象json实例", required = true)
									@RequestBody @Validated Item item) {
		LOGGER.debug("try to add item(" + item + ") into cart by customer id: " + customerId);
		
		cartService.addItemIntoCart(customerId, item);
	}
	
	@ApiOperation(value = "从购物车中移除商品", notes = "")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerId", paramType = "path", value = "顾客id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "itemId", paramType = "path", value = "商品id", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = 202,message = "从购物车移除商品信息请求已被接受" ),
	})
	@RequestMapping(method = RequestMethod.DELETE, value = "/carts/{customerId}/items/{itemId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void removeItemFromCart(@PathVariable String customerId,
									@PathVariable String itemId) {
		LOGGER.debug("try to remove item(" + itemId + ") from cart by customer id: " + customerId);
		
		cartService.removeItemFromCart(customerId, itemId);
	}
	
	@ApiOperation(value = "更新购物车中的商品信息", notes = "")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerId", paramType = "path", value = "顾客id", required = true, dataType = "String")
	})
	@ApiResponses({
		@ApiResponse(code = 202,message = "更新商品信息请求已被接受" ),
	})
	@RequestMapping(method = RequestMethod.PATCH, value = "/carts/{customerId}/items", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateItemInCart(@PathVariable String customerId,
						@ApiParam(name = "item", value = "待更新的item对象json实例", required = true)
									@RequestBody @Validated Item item) {
		LOGGER.debug("try to update item(" + item + ") in cart by customer id: " + customerId);

		cartService.addItemIntoCart(customerId, item);
	}
}
