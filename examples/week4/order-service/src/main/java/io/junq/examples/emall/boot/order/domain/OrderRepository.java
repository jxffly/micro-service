package io.junq.examples.emall.boot.order.domain;

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

/**
 * 订单数据访问
 * @author junqiangliu
 *
 */
@Repository
public class OrderRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepository.class);
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Order> ROW_MAPPER = (rs, rowNum) ->
		Order.Builder.newBuilder()
		.withId(rs.getLong("id"))
		.withUserId(rs.getString("user_id"))
		.withProducts(rs.getString("products"))
		.withAmount(rs.getInt("amount"))
		.withStatus(rs.getInt("status"))
		.withPaymentType(rs.getString("payment_type"))
		.withNote(rs.getString("note"))
		.withCreatedAt(rs.getTimestamp("created_at").getTime())
		.withChangedAt(rs.getTimestamp("changed_at").getTime())
		.build();
		
	public Order findOrderById(Long id) {
		List<Order> orders = jdbcTemplate.query("SELECT * FROM `order` WHERE id = ?", ROW_MAPPER, id);
		
		return CollectionUtils.isEmpty(orders) ? null : orders.get(0);
	}
	
	public List<Order> findOrdersByUserId(String userId) {
		List<Order> orders = jdbcTemplate.query("SELECT * FROM `order` WHERE user_id = ?", ROW_MAPPER, userId);
		
		return orders;
	}
	
	public long createOrder(final Order order) {
		final String sql = "INSERT INTO `order`(user_id, "
											+ "products, "
											+ "amount, "
											+ "status, "
											+ "payment_type, "
											+ "note) VALUES (?, ?, ?, ?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, order.getUserId());
				ps.setString(2, order.getProducts());
				ps.setInt(3, order.getAmount());
				ps.setInt(4, order.getStatus());
				ps.setString(5, order.getPaymentType());
				ps.setString(6, order.getNote());
				return ps;
			}
			
		}, holder);
		
		long newOrderId = holder.getKey().longValue();
		return newOrderId;
	}

}
