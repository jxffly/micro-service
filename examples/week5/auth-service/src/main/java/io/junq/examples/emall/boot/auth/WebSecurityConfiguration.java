package io.junq.examples.emall.boot.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // 测试用，内存保存账号、密码
			.withUser("user1").password("password1").roles("USER") // 普通用户，用户名uesr1、密码password1 
			.and()
			.withUser("admin1").password("password2").roles("ADMIN") // 管理员，用户名admin1、密码password2
		;
	}
}
