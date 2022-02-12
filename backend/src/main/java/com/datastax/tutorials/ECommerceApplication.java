package com.datastax.tutorials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Main class.
 *
 * @author Cedrick LUNVEN
 * @author Aaron PLOETZ 
 */
@SpringBootApplication
public class ECommerceApplication extends WebSecurityConfigurerAdapter {

	/**
	 * Main method.
	 * 
	 * @param args
	 *         no argumemts provided here
	 */
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
        http
        	// only way to get Test03 to work
        	//.httpBasic().and().csrf().disable();
            .authorizeRequests(a -> a
                .antMatchers("/api/v1/user/**", "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
            .logout(l -> l
                    .logoutSuccessUrl("/").permitAll()
                )
            .csrf(c -> c
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
            .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            .oauth2Login();
    }
    
    @Override
	public void configure(WebSecurity web) throws Exception {
    	web
    		.ignoring().antMatchers("/api/v1/products/**",
    				                "/api/v1/categories/**",
    				                "/api/v1/users/**",
    				                "/api/v1/prices/**",
    				                "/api/v1/featured/**",
    				                "/swagger-ui/**",
    				                "**swagger**");
    }
}
