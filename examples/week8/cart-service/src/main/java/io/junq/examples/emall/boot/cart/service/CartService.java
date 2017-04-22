package io.junq.examples.emall.boot.cart.service;

import java.util.List;

import io.junq.examples.emall.boot.domain.Cart;
import io.junq.examples.emall.boot.domain.Item;

/**
 * 购物车服务接口
 * @author junqiangliu
 *
 */
public interface CartService {
	
	/**
	 * 创建新购物车
	 * @param cart 待创建的购物车对象实例
	 * @return 创建完毕后返回购物车对象 {@link Cart}
	 */
	Cart createCart(final Cart cart);
	
	/**
	 * 通过顾客id清空购物车
	 * @param cumstomerId 顾客id
	 * @return
	 */
	void cleanupCartsByCustomerId(final String customerId);
	
	/**
	 * 添加商品信息到购物车
	 * @param customerId 顾客id
	 * @param item 商品信息
	 */
	void addItemIntoCart(final String customerId, final Item item);
	
	/**
	 * 从购物车中删除商品信息
	 * @param customerId 顾客id
	 * @param itemId 商品信息id
	 */
	void removeItemFromCart(final String customerId, final String itemId);
	
	/**
	 * 通过购物车id查询购物车
	 * @param id 购物车id
	 * @return {@link Cart}
	 */
	Cart findCartById(final long id);
	
	/**
	 * 通过顾客id查询购物车列表
	 * @param customerId 顾客id
	 * @return {@link List<Customer>}
	 */
	List<Cart> findCartsByCustomerId(final String customerId);
}
