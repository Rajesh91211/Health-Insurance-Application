package com.example.springboot.configuration;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig 
//{
//	
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder )
//	{
//		UserDetails admin=User.withUsername("Rajesh")
//				.password(encoder.encode("Rajesh12"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails user=User.withUsername("user")
//				.password(encoder.encode("user12"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
//	}
//	
//	@Bean
//	public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception
//	{
//		
//		return http.csrf().disable()
//				.authorizeHttpRequests()
//				.requestMatchers("/api/welcome")
//				.permitAll()
//				.and()
//				.authorizeHttpRequests()
//				.requestMatchers("/api/**")
//				.authenticated().and().formLogin().and().build();
//	}
//	 
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//		
//		return new BCryptPasswordEncoder();
//	}
//	

//}
