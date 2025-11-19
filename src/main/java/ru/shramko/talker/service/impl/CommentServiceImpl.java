package ru.shramko.talker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ru.shramko.talker.data.Comment;
import ru.shramko.talker.data.Post;
import ru.shramko.talker.dto.CommentDto;
import ru.shramko.talker.dto.utils.CommentMapper;
import ru.shramko.talker.repo.CommentRepository;
import ru.shramko.talker.repo.PostRepository;
import ru.shramko.talker.security.data.User;
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
	@Transactional
	public Comment addCommentToPost(CommentDto commentDto, Long postId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("Post not found - " + postId.toString()));
		
		Comment comment = commentMapper.toComment(commentDto);
		comment.setPost(post);
		comment.setAuthor(user);
		
		return commentRepository.save(comment);
		
	}
	
}
