package io.junq.examples.emall.boot.account.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import io.junq.examples.emall.boot.account.domain.AccountRepository;
import io.junq.examples.emall.boot.account.domain.User;

/**
 * 用户账号服务实现
 * @author junqiangliu
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	
    @Autowired
    private AccountRepository accountRepo;

	@Override
	public User findUserByDisplayId(final String displayId) {
		return accountRepo.findUserByDisplayId(displayId);
	}

	@Override
	public User createUser(final User user) {
		User.Builder builder = User.Builder.newBuilder();
		builder.withAvatar(user.getAvatar())
			.withDisplayName(user.getDisplayName())
			.withEmail(user.getEmail())
			.withGender(user.getGender())
			.withMobilePhone(user.getMobilePhone())
			.withPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes())) //TODO md5 hash with salt
			.withDisplayId(UUID.randomUUID().toString()) //TODO global id generator
			; 
		User newUser = builder.build();
		long newUserId = accountRepo.createUser(newUser);
		LOGGER.debug("new user created with user_id: " + newUserId);
		return findUserByDisplayId(newUser.getDisplayId());
	}

}
