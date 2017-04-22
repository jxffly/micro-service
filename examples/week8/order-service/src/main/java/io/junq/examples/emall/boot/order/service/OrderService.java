package io.junq.examples.emall.boot.order.service;

import java.util.List;

import io.junq.examples.emall.boot.domain.Order;

/**
 * 订单服务接口
 * @author junqiangliu
 *
 */
public interface OrderService {
	
	/**
	 * 创建新订单
	 * @param order 待创建的订单对象实例
	 * @return 创建完毕后返回订单对象 {@link Order}
	 */
	Order createOrder(final Order order);
	
	/**
	 * 通过订单id查询订单
	 * @param id 订单id
	 * @return {@link Order}
	 */
	Order findOrderById(final long id);
	
	/**
	 * 查找用户的订单列表
	 * @param userId 用户display_id
	 * @return {@link List<Order>}
	 */
	List<Order> findOrdersByUserId(final String userId);
}
