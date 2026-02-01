package com.example.SBW88.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.SBW88.filter.JwtFiilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
	private JwtFiilter jwtFilter;
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ No sessions for JWT
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // ✅ Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                    ).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider)
            // ✅ JWT filter BEFORE UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
	/*@Bean
	public SecurityFilterChain filterChian(HttpSecurity http) {

		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth ->  auth
				.requestMatchers("/home").permitAll()
				.anyRequest().authenticated()

				)
		.userDetailsService(userDetailsService())
		.httpBasic(httpBasic -> httpBasic.realmName("myApp"));
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(){

		UserDetails userDetails=User.builder()
				.username("hrushi")
				.password("{noop}123456")
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
	
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
*/

}
