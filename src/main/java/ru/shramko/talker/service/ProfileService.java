package ru.shramko.talker.service;

import ru.shramko.talker.data.Profile;

public interface ProfileService {
	Profile getProfileByUserId(Long userId);
}
