package org.java.demo.auth.conf;

import org.java.demo.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {

	@Bean
	PasswordEncoder passwordEncoder() {
		
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
		return 
				http.csrf(c -> c.disable())
						.authorizeHttpRequests(a -> a
						.requestMatchers("/offer/create/**").hasAuthority("ADMIN")
						.requestMatchers("/offer/update/**").hasAuthority("ADMIN")
						.requestMatchers("/offer/delete/**").hasAuthority("ADMIN")
						.requestMatchers("/ingredients/delete/**").hasAuthority("ADMIN")
						.requestMatchers("/ingredients/update/**").hasAuthority("ADMIN")
						.requestMatchers("/ingredients/create/**").hasAuthority("ADMIN")
						.requestMatchers("/pizza/delete/**").hasAuthority("ADMIN")
						.requestMatchers("/pizza/update/**").hasAuthority("ADMIN")
						.requestMatchers("/pizza/create/**").hasAuthority("ADMIN")
						.requestMatchers("/api/v1/**").permitAll()
				        .requestMatchers("/**").hasAnyAuthority("USER", "ADMIN")
				        
				).formLogin(f -> f.permitAll()
				).logout(l -> l.logoutSuccessUrl("/")
				).build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
	    return new UserService();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
	
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	 
	    return authProvider;
	}
}
