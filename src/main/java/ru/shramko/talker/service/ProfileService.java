package ru.shramko.talker.service;

import ru.shramko.talker.data.Profile;
import ru.shramko.talker.security.data.User;

public interface ProfileService {
	Profile getProfileByUserId(Long userId);
	Profile getProfileByUserIdForEditing(Long userId);
	Profile saveProfile(Profile profile, User user);
}
