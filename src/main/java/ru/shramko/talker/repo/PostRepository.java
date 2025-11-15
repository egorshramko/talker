package ru.shramko.talker.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.shramko.talker.data.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
