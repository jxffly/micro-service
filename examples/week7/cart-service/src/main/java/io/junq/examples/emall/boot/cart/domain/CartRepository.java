package io.junq.examples.emall.boot.cart.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 购物车数据访问
 * @author junqiangliu
 *
 */
@Repository
public class CartRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CartRepository.class);

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Cart> ROW_MAPPER = (rs, rowNum) -> 
		Cart.Builder.newBuilder()
		.withId(rs.getLong("id"))
		.withCustomerId(rs.getString("customer_id"))
		.withItems(rs.getString("items"))
		.withCreatedAt(rs.getTimestamp("created_at").getTime())
		.withChangeAt(rs.getTimestamp("changed_at").getTime())
		.build();
	
	public Cart findCartById(Long id) {
		List<Cart> carts = jdbcTemplate.query("SELECT * FROM `cart` WHERE id = ?", ROW_MAPPER, id);
		
		return CollectionUtils.isEmpty(carts) ? null : carts.get(0);
	}
	
	public List<Cart> findCartsByCustomerId(String customerId) {
		List<Cart> carts = jdbcTemplate.query("SELECT * FROM `cart` WHERE customer_id = ?", ROW_MAPPER, customerId);
		
		return carts;
	}
	
	public long createCart(Cart cart) {
		final String sql = "INSERT INTO `cart`(customer_id, "
				+ "items) "
				+ "VALUES (?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				ObjectMapper mapper = new ObjectMapper();
				String itemsStr = null;
				try {
					itemsStr = CollectionUtils.isEmpty(cart.getItems()) ? null : mapper.writeValueAsString(cart.getItems());
				} catch (JsonProcessingException e) {
					LOGGER.warn(e.getMessage());
				}
				
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,cart.getCustomerId());
				ps.setString(2, itemsStr);
				return ps;
			}
			
		}, holder);
		
		long newCartId = holder.getKey().longValue();
		return newCartId;
	}
	
	public void updateCart(Cart cart) {
		ObjectMapper mapper = new ObjectMapper();
		String itemsStr = null;
		try {
			itemsStr = CollectionUtils.isEmpty(cart.getItems()) ? null : mapper.writeValueAsString(cart.getItems());
		} catch (JsonProcessingException e) {
			LOGGER.warn(e.getMessage());
		}
		
		jdbcTemplate.update("UPDATE `cart` SET items = ? WHERE `id` = ?", itemsStr, cart.getId());
	}
	
	public void deleteCartsByCustomerId(String customerId) {
		int result = jdbcTemplate.update("DELETE FROM `cart` WHERE customer_id = ?", customerId);
		LOGGER.debug("delete carts result(" + result + ") by customerId: " + customerId);
	}
	
	public void deleteCartById(long id) {
		int result = jdbcTemplate.update("DELETE FROM `cart` WHERE id = ?", id);
		LOGGER.debug("delete cart result(" + result + ") by id: " + id); 
	}
}
