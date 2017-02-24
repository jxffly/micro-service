package io.junq.examples.emall.boot.account.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.junq.examples.emall.boot.account.domain.User;
import io.junq.examples.emall.boot.account.service.AccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户账号API入口
 * @author junqiangliu
 *
 */
@RestController("用户相关API")
@RequestMapping(value = "/v1/users",
				produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserRestController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
    private AccountService accountService;
	
    @ApiOperation(value="查询用户", notes="")
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "userId", paramType = "path", value = "用户id", required = true, dataType = "String")
    })
	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public User findUserByDisplayId(@PathVariable String userId) {
    		LOGGER.debug("try to find user by display_id: " + userId);
		return accountService.findUserByDisplayId(userId);
	}
	
}
