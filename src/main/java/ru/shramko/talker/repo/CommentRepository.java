package ru.shramko.talker.repo;

import org.springframework.data.repository.CrudRepository;

import ru.shramko.talker.data.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
