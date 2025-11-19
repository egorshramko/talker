package ru.shramko.talker.security.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shramko.talker.security.data.User;

@Data
@NoArgsConstructor
public class RegistrationDto {

	private String username;
	private String password;
	private String confirm;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.build();
	}
}
