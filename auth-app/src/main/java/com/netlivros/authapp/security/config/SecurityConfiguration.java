package com.netlivros.authapp.security.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.netlivros.authapp.user.Permission;
import com.netlivros.authapp.user.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
				.antMatchers("/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**",
						"/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security",
						"/swagger-ui/**", "/webjars/**", "/swagger-ui.html")
				.permitAll()

				.anyRequest().permitAll()
//				.antMatchers("/api/v1/management/**").hasAnyRole(Role.ADMIN.name(), Role.AUTHOR.name())
//
//				.antMatchers(GET, "/api/v1/management/**")
//				.hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.AUTHOR_READ.name())
//				.antMatchers(POST, "/api/v1/management/**")
//				.hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.AUTHOR_CREATE.name())
//				.antMatchers(PUT, "/api/v1/management/**")
//				.hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.AUTHOR_UPDATE.name())
//				.antMatchers(DELETE, "/api/v1/management/**")
//				.hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.AUTHOR_DELETE.name())
//				.anyRequest().authenticated()

				/*
				 * .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
				 * 
				 * .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
				 * .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
				 * .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
				 * .requestMatchers(DELETE,
				 * "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())
				 */

				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).logout()
				.logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler)
				.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

		return http.build();
	}
}
