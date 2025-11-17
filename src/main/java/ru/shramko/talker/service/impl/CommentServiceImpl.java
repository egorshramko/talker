package ru.shramko.talker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.shramko.talker.data.Comment;
import ru.shramko.talker.data.Post;
import ru.shramko.talker.dto.CommentDto;
import ru.shramko.talker.dto.utils.CommentMapper;
import ru.shramko.talker.repo.CommentRepository;
import ru.shramko.talker.repo.PostRepository;
import ru.shramko.talker.service.CommentService;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public Comment addCommentToPost(CommentDto commentDto, Long postId) {
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("Post not found - " + postId.toString()));
		
		Comment comment = commentMapper.toComment(commentDto);
		comment.setPost(post);
		
		return commentRepository.save(comment);
		
	}
	
}
