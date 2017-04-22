package io.junq.examples.emall.boot.account.domain;

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

import io.junq.examples.emall.boot.domain.User;

/**
 * 用户账号数据访问
 * 
 * @author junqiangliu
 *
 */
@Repository
public class AccountRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepository.class);

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	private static final RowMapper<User> ROW_MAPPER = (rs, rowNum) -> 
			User.Builder.newBuilder()
				.withDisplayId(rs.getString("display_id"))
				.withGender(rs.getInt("gender"))
				.withDisplayName(rs.getString("display_name"))
				.withAvatar(rs.getString("avatar"))
				.withCreatedAt(rs.getTimestamp("created_at").getTime())
				.withChangedAt(rs.getTimestamp("changed_at").getTime())
				.withEmail(rs.getString("email"))
				.withMobilePhone(rs.getString("mobile_phone"))
				.withPassword(rs.getString("password"))
				.build();

	public User findUserByDisplayId(final String displayId) {
		 List<User> users = jdbcTemplate.query("SELECT * FROM user WHERE display_id = ?", ROW_MAPPER, displayId);
		 
		 return CollectionUtils.isEmpty(users) ? null : users.get(0);
	}
	
	public long createUser(final User user) {
		final String sql = "INSERT INTO user(display_id, "
										+ "display_name, "
										+ "gender, "
										+ "avatar, "
										+ "password, "
										+ "email, "
										+ "mobile_phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getDisplayId());
				ps.setString(2, user.getDisplayName());
				ps.setInt(3, user.getGender());
				ps.setString(4, user.getAvatar());
				ps.setString(5, user.getPassword());
				ps.setString(6, user.getEmail());
				ps.setString(7, user.getMobilePhone());
				return ps;
			}
			
		}, holder);
		
		long newUserId = holder.getKey().longValue();
		return newUserId;
	}

}
