package io.junq.examples.emall.boot.account.service;

import io.junq.examples.emall.boot.account.domain.User;

/**
 * 用户账号服务接口
 * @author junqiangliu
 *
 */
public interface AccountService {
	/**
	 * 通过用户id查询用户信息
	 * @param displayId
	 * @return {@link User}对象
	 */
	User findUserByDisplayId(String displayId);
}
