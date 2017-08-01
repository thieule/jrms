package com.thieule.rms.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment env;

    //

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests()
				.anyRequest().permitAll();
		// .requestMatchers().antMatchers("/foos/**","/bars/**")
		// .and()
		// .authorizeRequests()
		// .antMatchers(HttpMethod.GET,"/foos/**").access("#oauth2.hasScope('foo')
		// and #oauth2.hasScope('read')")
		// .antMatchers(HttpMethod.POST,"/foos/**").access("#oauth2.hasScope('foo')
		// and #oauth2.hasScope('write')")
		// .antMatchers(HttpMethod.GET,"/bars/**").access("#oauth2.hasScope('bar')
		// and #oauth2.hasScope('read')")
		// .antMatchers(HttpMethod.POST,"/bars/**").access("#oauth2.hasScope('bar')
		// and #oauth2.hasScope('write') and hasRole('ROLE_ADMIN')")
		;
		// @formatter:on
    }

    // Remote token service
    
    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(env.getProperty("authServer.checktokenAddress"));
        tokenService.setClientId("fooClientIdPassword");
        tokenService.setClientSecret("secret");
        return tokenService;
    }

}
