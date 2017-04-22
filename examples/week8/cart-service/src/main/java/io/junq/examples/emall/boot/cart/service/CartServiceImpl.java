package io.junq.examples.emall.boot.cart.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.junq.examples.emall.boot.cart.domain.CartRepository;
import io.junq.examples.emall.boot.domain.Cart;
import io.junq.examples.emall.boot.domain.Item;

/**
 * 购物车服务实现
 * @author junqiangliu
 *
 */
@Service
public class CartServiceImpl implements CartService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private CartRepository cartRepo;
	
	@Override
	public Cart findCartById(long id) {
		return cartRepo.findCartById(id);
	}

	@Override
	public List<Cart> findCartsByCustomerId(String customerId) {
		return cartRepo.findCartsByCustomerId(customerId);
	}

	@Override
	public Cart createCart(Cart cart) {
		long cartId = cartRepo.createCart(cart);
		return findCartById(cartId);
	}

	@Override
	public void cleanupCartsByCustomerId(String customerId) {
		cartRepo.deleteCartsByCustomerId(customerId);
	}

	@Override
	public void addItemIntoCart(String customerId, Item item) {
		Cart cartToAdd = Cart.Builder.newBuilder()
					.withCustomerId(customerId)
					.withItems(Arrays.asList(item))
					.build();
		
		List<Cart> carts = cartRepo.findCartsByCustomerId(customerId);
		if (CollectionUtils.isEmpty(carts)) {
			cartRepo.createCart(cartToAdd);
			return;
		}
		
		for (Cart cart : carts) {
			if (cart.hasItem(item.getItemId())) {
				cart.addItem(item);
				
				cartRepo.updateCart(cart);
				return;
			}
		}
		cartRepo.createCart(cartToAdd);
	}

	protected void updateCart(Cart cart) {
		cartRepo.updateCart(cart);
	}

	@Override
	public void removeItemFromCart(String customerId, String itemId) {
		List<Cart> carts = findCartsByCustomerId(customerId);
		if (CollectionUtils.isEmpty(carts))
			return;
		
		for (Cart cart : carts) {
			if (cart.hasItem(itemId)) {
				cart.removeItem(itemId);
				
				if (CollectionUtils.sizeIsEmpty(cart.getItems())) {
					cartRepo.deleteCartById(cart.getId());
				} else {
					cartRepo.updateCart(cart);	
				}
				return;
			}
		}
	}
}
