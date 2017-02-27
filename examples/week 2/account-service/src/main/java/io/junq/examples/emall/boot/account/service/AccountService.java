package io.junq.examples.emall.boot.account.service;

import io.junq.examples.emall.boot.account.domain.User;

/**
 * 用户账号服务接口
 * @author junqiangliu
 *
 */
public interface AccountService {
	
	/**
	 * 创建用户
	 * @param user 待创建的用户对象实例
	 * @return 创建完毕后的用户对象
	 */
	User createUser(final User user);
	
	/**
	 * 通过用户id查询用户信息
	 * @param displayId
	 * @return {@link User}对象
	 */
	User findUserByDisplayId(final String displayId);
}
