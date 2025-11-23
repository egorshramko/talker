package ru.shramko.talker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.shramko.talker.data.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
	
	@Query("Select p from Profile p, User u "
			+ "where u = p.user and u.id = ?1")
	Optional<Profile> getProfileByUserId(Long userId);
	
}
