package ru.shramko.talker.security.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shramko.talker.security.data.User;

@Data
@NoArgsConstructor
public class RegistrationDto {

	@NotBlank(message = "Поле не должно быть пустым")
	private String username;
	
	@NotBlank(message = "Поле не должно быть пустым")
	private String password;
	
	@NotBlank(message = "Поле не должно быть пустым")
	private String confirm;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.build();
	}
}
