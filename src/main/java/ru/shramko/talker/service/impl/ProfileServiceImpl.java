package ru.shramko.talker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ru.shramko.talker.data.Profile;
import ru.shramko.talker.repo.PostRepository;
import ru.shramko.talker.repo.ProfileRepository;
import ru.shramko.talker.security.repo.UserRepository;
import ru.shramko.talker.service.ProfileService;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	@Transactional
	public Profile getProfileByUserId(Long userId) {
		Profile profile = profileRepository.getProfileByUserId(userId)
				.orElse(new Profile(userRepository
						.findById(userId)
						.orElseThrow(() -> 
								new UsernameNotFoundException(
										"User with id " + userId.toString() + " not found"))));
		
		profile.setPosts(postRepository.findAllByAuthor(profile.getUser()));
		
		return profile;
				
	}

}
