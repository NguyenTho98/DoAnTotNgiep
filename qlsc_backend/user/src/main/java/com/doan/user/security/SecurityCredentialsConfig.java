package com.doan.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

 	@Qualifier("UserDetailsServiceImpl")
	@Autowired
	private UserDetailsService userService;

	@Autowired
	private JwtConfig jwtConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	            .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	        .and()
		    .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))
				.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		    .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
//				.antMatchers("/admin/products/image/**").permitAll()
//				.antMatchers("/admin/provinces/**").hasAnyRole("3","1")
//				.antMatchers("/admin/wards/**").hasAnyRole("3","1")
//				.antMatchers(HttpMethod.DELETE,"/admin/customers/**").hasAnyRole("3")
//				.antMatchers(HttpMethod.POST,"/admin/maintenanceCards/**").hasAnyRole("1","3")
//				.antMatchers(HttpMethod.GET,"/admin/maintenanceCards/**").hasAnyRole("1","2","3")
//				.antMatchers(HttpMethod.PUT,"/admin/maintenanceCards/workStatus/**").hasAnyRole("2")
////				.antMatchers(HttpMethod.PUT,"/admin/maintenanceCardDetails/status").hasAnyRole("2")
//				.antMatchers("/admin/paymentHistories/**").hasAnyRole("3")
//				.antMatchers(HttpMethod.GET,"/admin/products/**").hasAnyRole("3","1")
//				.antMatchers(HttpMethod.POST,"/admin/products/**").hasAnyRole("3")
//				.antMatchers(HttpMethod.PUT,"/admin/products/**").hasAnyRole("3")
//				.antMatchers(HttpMethod.DELETE,"/admin/products/**").hasAnyRole("3")
//				.antMatchers("/admin/accessories").hasAnyRole("3")
//				.antMatchers("/admin/services").hasAnyRole("3")
//				.antMatchers("/admin/users/maintenanceCard").hasAnyRole("1","3")
//				.antMatchers("/admin/users").hasAnyRole("3")
//				.antMatchers("/admin/customers/**").hasAnyRole("3","1")
//				.antMatchers("/admin/**").hasAnyRole("2","1","3")
//				.antMatchers(HttpMethod.POST,"/admin/**").hasAnyRole("1")
//				.antMatchers("/admin/**").hasAnyRole("ADMIN")
		    .anyRequest().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
