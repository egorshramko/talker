package ru.shramko.talker.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;
import ru.shramko.talker.security.repo.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests((authorizeHttpRequests) -> {
					authorizeHttpRequests
							.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
							.requestMatchers("/logout").authenticated()
							.requestMatchers("/posts", "/post/**", "/profile/**").hasRole("USER")
							.requestMatchers("/", "/login", "/signup", "/images/**").permitAll()
							.anyRequest().denyAll();
				})
				.formLogin((formLogin) -> {
					formLogin
							.loginPage("/login")
							.defaultSuccessUrl("/posts")
							.permitAll();
				})
				.logout((logout) -> {
					logout.logoutUrl("/logout")
						.logoutSuccessUrl("/");
				})
				.anonymous((anonymous) -> {
					anonymous.disable();
				})
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return username -> userRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"User with username " + username + " not found"));
	}
	
}
