package io.junq.examples.emall.boot.cart;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import io.junq.examples.emall.boot.cart.domain.Cart;
import io.junq.examples.emall.boot.cart.domain.CartRepository;
import io.junq.examples.emall.boot.cart.domain.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
			scripts = "classpath:before-scripts.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, 
	scripts = "classpath:after-scripts.sql") })
public class CartRepositoryTest {
	
	@Autowired
	private CartRepository cartRepo;
	
	private static String itemId = null;
	
	private static String customerId = null;
	
	private static Cart emptyCart;
	
	@BeforeClass
	public static void setup() {
		itemId = UUID.randomUUID().toString();
		customerId = UUID.randomUUID().toString();
		
		emptyCart = Cart.Builder.newBuilder()
				.withCustomerId(customerId)
				.build();
	}
	
	@AfterClass
	public static void teardown() {
		
	}
	
	@Test
	public void createCart() {
		Item item = Item.Builder.newBuilder()
					.withItemId(itemId)
					.withQuantity(10)
					.withPrice(1000)
					.build();
		Cart cart = Cart.Builder.newBuilder()
					.withCustomerId(customerId)
					.withItems(Arrays.asList(item))
					.build();
		
		long cartId = cartRepo.createCart(cart);
		
		Cart savedCart = cartRepo.findCartById(cartId);
		
		assertTrue(savedCart.getCustomerId().equals(customerId) && 
				savedCart.getItems().size() == 1 &&
				savedCart.getItems().get(0).getItemId().equals(itemId));
	}
	
	@Test
	public void removeCarts() {
		cartRepo.createCart(emptyCart);
		assertFalse(CollectionUtils.isEmpty(cartRepo.findCartsByCustomerId(customerId)));
		
		cartRepo.deleteCartsByCustomerId(customerId);
		assertTrue(CollectionUtils.isEmpty(cartRepo.findCartsByCustomerId(customerId)));
	}
	
	@Test
	public void addItemIntoCart() {
		cartRepo.createCart(emptyCart);
		assertFalse(CollectionUtils.isEmpty(cartRepo.findCartsByCustomerId(customerId)));
		
		assertTrue(CollectionUtils.isEmpty(cartRepo.findCartsByCustomerId(customerId).get(0).getItems()));
		
		int price = 1000;
		int quantity = 3; 
		Item item = Item.Builder.newBuilder()
				.withItemId(itemId)
				.withPrice(price)
				.withQuantity(quantity)
				.build();
		Cart cart = cartRepo.findCartsByCustomerId(customerId).get(0);
		cart.addItem(item);
		cartRepo.updateCart(cart);
		
		assertTrue(cartRepo.findCartsByCustomerId(customerId).get(0)
					.getItems().get(0)
						.getQuantity() == quantity
					);
		assertTrue(cartRepo.findCartsByCustomerId(customerId).get(0)
					.getItems().get(0)
						.getItemId().equals(item.getItemId())
				);
	}
}
