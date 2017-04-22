package io.junq.examples.emall.boot.cart;

import java.util.Arrays;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import io.junq.examples.emall.boot.cart.domain.CartRepository;
import io.junq.examples.emall.boot.domain.Cart;
import io.junq.examples.emall.boot.domain.Item;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, 
			scripts = "classpath:before-scripts.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, 
	scripts = "classpath:after-scripts.sql") })
public class CartRestApiTest {

	private static final String META_FIELD = "meta";
	private static final String DATA_FIELD = "data";
	
	private static final String CARTS_RESOURCE = "/v1/carts/{customerId}";
	private static final String ITEMS_RESOURCE = "/v1/carts/{customerId}/items";
	private static final String ITEM_RESOURCE = "/v1/carts/{customerId}/items/{itemId}";
	private static final int NON_EXISTING_ID = 999;
	
	private static final String APPLICATION_JSON = "application/json";

	@Autowired
	private CartRepository cartRepo;
	
	private String itemId1 = null;
	
	private String itemId2 = null;
	
	private String customerId = null;
	
	@Value("${local.server.port}")
	private int serverPort;
	
	private Cart FIRST_CART;
	
	private Cart SECOND_CART;
	
	@Before
	public void setUp() {
		itemId1 = UUID.randomUUID().toString();
		itemId2 = UUID.randomUUID().toString();
		customerId = UUID.randomUUID().toString();
		
		Item item1 = Item.Builder.newBuilder()
					.withItemId(itemId1)
					.withPrice(1000)
					.withQuantity(7)
					.build();
		
		FIRST_CART = Cart.Builder.newBuilder()
						.withCustomerId(customerId)
						.withItems(Arrays.asList(item1))
						.build();
		
		cartRepo.createCart(FIRST_CART);
		
		Item item2 = Item.Builder.newBuilder()
				.withItemId(itemId2)
				.withPrice(1900)
				.withQuantity(2)
				.build();
		
		SECOND_CART = Cart.Builder.newBuilder()
						.withCustomerId(customerId)
						.withItems(Arrays.asList(item2))
						.build();
		
		cartRepo.createCart(SECOND_CART);
		
	    RestAssured.port = serverPort;
	}

	@After
	public void teardown() {

	}

	@Test
	public void getCartsShouldReturnBothCarts() {
//		given()
//			.accept(ContentType.JSON)
//		.when()
//			.get(CARTS_RESOURCE, customerId)
//		.then()
//			.body("data.items.item_id", hasItems(itemId1, itemId2));
	}

}
