package io.junq.examples.emall.boot.cart;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.junq.examples.emall.boot.domain.Cart;
import io.junq.examples.emall.boot.domain.Item;

public class CartTest {

	private static String itemId = null;
	
	private static String customerId = null;
	
	private static int itemPrice;
	
	private static int itemQuantity;
	
	private static Item expectedItem;
	
	private static Cart cartWithOneItem;
	
	@BeforeClass
	public static void setup() {
		itemId = UUID.randomUUID().toString();
		customerId = UUID.randomUUID().toString();
		
		itemPrice = 100;
		itemQuantity = 1;
		
		expectedItem = Item.Builder.newBuilder()
				.withItemId(itemId)
				.withPrice(itemPrice)
				.withQuantity(itemQuantity)
				.build();
			
		cartWithOneItem = Cart.Builder.newBuilder()
					.withCustomerId(customerId)
					.withItems(Arrays.asList(expectedItem))
					.build();
	}
	
	@AfterClass
	public static void teardown() {
		
	}

	@Test
	public void addSingleItem() {
		// 添加新的商品信息到购物车
		Item item = Item.Builder.newBuilder()
			.withItemId(itemId)
			.withPrice(itemPrice)
			.withQuantity(itemQuantity)
			.build();
		
		Cart cart = Cart.Builder.newBuilder()
				.withCustomerId(customerId)
				.withItems(Arrays.asList(item))
				.build();
		
		assertTrue(cart.getItems().size() == 1);
	}

	@Test
	public void removeItem() {
		Item item = Item.Builder.newBuilder()
				.withItemId(itemId)
				.withPrice(itemPrice)
				.withQuantity(itemQuantity)
				.build();
			
		Cart cart = Cart.Builder.newBuilder()
					.withCustomerId(customerId)
					.withItems(Arrays.asList(item))
					.build();
		
		cart.removeItem(UUID.randomUUID().toString());
		assertTrue(cart.getItems().size() == 1);
		
		cart.removeItem(item.getItemId());
		assertTrue(CollectionUtils.sizeIsEmpty(cart.getItems()));
	}
	
	@Test
	public void addExistedItem() throws IOException {
		// 添加一个已存在的Item到Cart时，仅更新item数量
		ObjectMapper mapper = new ObjectMapper();
		String cartStr = mapper.writeValueAsString(cartWithOneItem);
		String itemStr = mapper.writeValueAsString(expectedItem);
		
		Cart cart = mapper.readValue(cartStr, new TypeReference<Cart>(){});
		Item item = mapper.readValue(itemStr, new TypeReference<Item>(){});
		
		int quantity = 9;
		Item itemToAdd = Item.Builder.newBuilder()
				.withItemId(item.getItemId())
				.withPrice(item.getPrice())
				.withQuantity(quantity)
				.build();
		cart.addItem(itemToAdd);
		
		assertTrue(cart.getItems().get(0).getQuantity() == (itemToAdd.getQuantity() + item.getQuantity()));
	}
}
