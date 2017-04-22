package io.junq.examples.emall.boot.account.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.junq.examples.emall.boot.account.service.AccountService;
import io.junq.examples.emall.boot.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 用户账号API入口
 * 
 * @author junqiangliu
 *
 */
@RestController("用户相关API")
@RequestMapping(value = "/v1", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public class UserRestController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private AccountService accountService;

	@ApiOperation(value = "创建用户", notes = "")
	@ApiImplicitParams({
		
	})
	@ApiResponses({
		@ApiResponse(code = 201,message = "用户创建成功" ),
	})
	@RequestMapping(method = RequestMethod.POST, value = "/users", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public User createUser(@ApiParam(name = "user", value = "待创建的用户对象json实例", required = true) @RequestBody @Validated User user) {
		LOGGER.debug("try to create user by user: " + user);
		return accountService.createUser(user);
	}
	
	@ApiOperation(value = "查询用户", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", paramType = "path", value = "用户id", required = true, dataType = "String")
		})
	@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
	public User findUserByDisplayId(@PathVariable String userId) {
		LOGGER.debug("try to find user by display_id: " + userId);
		return accountService.findUserByDisplayId(userId);
	}

}
