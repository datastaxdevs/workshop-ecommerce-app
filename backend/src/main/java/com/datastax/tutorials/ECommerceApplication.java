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
	 *         no arguments provided here
	 */
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
        http
        	.authorizeRequests(a -> a
            		.antMatchers("/", "/api/v1/users/", "/error", "/webjars/**").permitAll()
            		.anyRequest().authenticated()
            		//.anyRequest().permitAll()
            	)
            .formLogin(fl -> fl
            		.loginPage("/login").permitAll()
            	)
            .logout(l -> l
					.logoutUrl("/logout")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
					.permitAll()
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
    				                "/api/v1/prices/**",
    				                "/api/v1/featured/**",
    				                "/api/v1/carts/**",
    				                "/swagger-ui/**",
									"/logout",
									"/static/**",
									"/index.html",
									"/images/**",
									"/favicon.ico",
									"/manifest.json",
    				                "/v3/api-docs/**",
    				                "/configuration/**",
    				                "/swagger-resources/**",
    				                "/configuration/security",
    				                "/swagger-ui.html");
    }
}
