package io.junq.examples.emall.boot.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@EnableResourceServer
@Configuration
public class OrderResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	@Value("${spring.application.name}")
	private String resourceId;
	
	@Value("${emall.oauth2.client-id}")
	private String clientId;
	
	@Value("${emall.oauth2.client-secret}")
	private String clientSecret;
	
	@Value("${emall.services.auth-service}")
	private String authServiceName;
	
	@Value("${emall.oauth2.access-token-uri}")
	private String accessTokenUri;
	
	@Value("${emall.oauth2.user-authorization-uri}")
	private String authorizationUri;

	@Value("${emall.oauth2.check-token-access}")
	private String checkTokenUri;
	
//	@Autowired
//	private EurekaClient client;
	
	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().anyRequest().authenticated().antMatchers("/oauth/token").permitAll();
	}
	
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenService()).resourceId(resourceId);
    }
	
	@Bean
	public RemoteTokenServices tokenService(){
		RemoteTokenServices tokenService = new RemoteTokenServices();
		//TODO change EurekaClient to Consul
//		InstanceInfo instance = client.getNextServerFromEureka(authServiceName, false);
//		String authServiceUrl = instance.getHomePageUrl();
//		tokenService.setCheckTokenEndpointUrl(authServiceUrl + checkTokenUri);
//		tokenService.setClientId(clientId);
//		tokenService.setClientSecret(clientSecret);
		return tokenService;
	}

}
