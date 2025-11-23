package ru.shramko.talker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import ru.shramko.talker.data.Profile;
import ru.shramko.talker.security.data.User;
import ru.shramko.talker.service.ProfileService;

@Slf4j
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
	
	@GetMapping("/{id}/edit")
	public String editProfilePage(@PathVariable Long id, Model model) {
		try {
			model.addAttribute("profile", profileService.getProfileByUserIdForEditing(id));
			return "edit-profile";
		}
		catch (AuthorizationDeniedException e) {
			return "redirect:/profile/" + id.toString();
		}
		
	}
	
	@PostMapping("/{id}/edit")
	@PreAuthorize("hasRole('USER') and #id == #user.id")
	public String handleEditingProfile(@PathVariable Long id,
			@ModelAttribute("profile") Profile profile,
			@AuthenticationPrincipal User user) {
			
		profileService.saveProfile(profile, user);
		
		return "redirect:/profile/" + id.toString();
		
	}
	
}
