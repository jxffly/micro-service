package io.junq.examples.emall.boot.account.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

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
				.withCreatedAt(rs.getDate("created_at"))
				.withChangedAt(rs.getDate("changed_at"))
				.withEmail(rs.getString("email"))
				.withMobilePhone(rs.getString("mobile_phone"))
				.withPassword(rs.getString("password"))
				.build();

	public User findUserByDisplayId(String displayId) {
		 List<User> users = jdbcTemplate.query("SELECT * FROM user WHERE display_id = ?", ROW_MAPPER, displayId);
		 
		 return CollectionUtils.isEmpty(users) ? null : users.get(0);
	}

}
