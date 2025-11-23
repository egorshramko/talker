package ru.shramko.talker.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.shramko.talker.data.Post;
import ru.shramko.talker.security.data.User;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	List<Post> findAllByAuthor(User author);
}
