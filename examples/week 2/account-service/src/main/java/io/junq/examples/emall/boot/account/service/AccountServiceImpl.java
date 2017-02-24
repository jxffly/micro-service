package io.junq.examples.emall.boot.account.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public User findUserByDisplayId(String displayId) {
		return accountRepo.findUserByDisplayId(displayId);
	}

}
