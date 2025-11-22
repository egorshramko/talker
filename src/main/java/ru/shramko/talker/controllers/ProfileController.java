package ru.shramko.talker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.shramko.talker.security.data.User;
import ru.shramko.talker.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@ModelAttribute("user")
	public User user(@AuthenticationPrincipal User user) {
		return user;
	}
	
	@GetMapping("/{id}")
	public String profilePage(@PathVariable Long id, Model model) {
		model.addAttribute("profile", profileService.getProfileByUserId(id));
		return "profile";
	}
	
}
