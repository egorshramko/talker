package ru.shramko.talker.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ru.shramko.talker.security.dto.RegistrationDto;
import ru.shramko.talker.security.repo.UserRepository;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ModelAttribute
	public RegistrationDto registrationDto() {
		return new RegistrationDto();
	}
	
	@GetMapping
	public String signUpPage() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			return "redirect:/posts";
		}
		
		return "sign-up";
	}
	
	@PostMapping
	public String signUp(@Valid RegistrationDto registrationForm,
			Errors errors) {
		
		if (errors.hasErrors()) {
			return "sign-up";
		}
		
		boolean userIsExist = userRepository
				.findByUsername(registrationForm.getUsername())
				.isPresent();
		
		if (userIsExist) {
			errors.rejectValue("username", null, "Имя пользователя уже занято");
		}
		
		if (!registrationForm.getPassword().equals(registrationForm.getConfirm())) {
			errors.rejectValue("confirm", null, "Пароли не совпадают");
		}
		
		if (errors.hasErrors()) {
			return "sign-up";
		}
		
		userRepository.save(registrationForm.toUser(passwordEncoder));
		
		return "redirect:/login";
	}
	
}
