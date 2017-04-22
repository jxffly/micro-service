package io.junq.examples.emall.boot.product.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import io.junq.examples.emall.boot.domain.Product;

/**
 * 产品数据访问
 * @author junqiangliu
 *
 */
@Repository
public class ProductRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepository.class);

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Product> ROW_MAPPER = (rs, rowNum) -> 
		Product.Builder.newBuilder()
		.withId(rs.getLong("id"))
		.withType(rs.getInt("type"))
		.withName(rs.getString("name"))
		.withSummary(rs.getString("summary"))
		.withDescription(rs.getString("description"))
		.withPrice(rs.getInt("price"))
		.withImages(CollectionUtils.arrayToList(rs.getString("images").split(Product.SEPERATOR)))
		.withCreatedAt(rs.getTimestamp("created_at").getTime())
		.withChangedAt(rs.getTimestamp("changed_at").getTime())
		.build();

	public Product findProductById(Long id) {
		List<Product> products = jdbcTemplate.query("SELECT * FROM product WHERE id = ?", ROW_MAPPER, id);
		 
		 return CollectionUtils.isEmpty(products) ? null : products.get(0);
	}
	
	public long createProduct(final Product product) {
		final String sql = "INSERT INTO product(type, "
										+ "name, "
										+ "summary, "
										+ "description, "
										+ "price, "
										+ "images) VALUES (?, ?, ?, ?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, product.getType());
				ps.setString(2, product.getName());
				ps.setString(3, product.getSummary());
				ps.setString(4, product.getDescription());
				ps.setInt(5, product.getPrice());
				ps.setString(6, StringUtils.collectionToDelimitedString(product.getImages(), Product.SEPERATOR));
				return ps;
			}
			
		}, holder);
		
		long newProductId = holder.getKey().longValue();
		return newProductId;
	}
	
}
