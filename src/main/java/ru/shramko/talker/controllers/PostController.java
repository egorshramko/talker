package ru.shramko.talker.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ru.shramko.talker.data.Comment;
import ru.shramko.talker.data.Post;
import ru.shramko.talker.dto.CommentDto;
import ru.shramko.talker.dto.utils.CommentMapper;
import ru.shramko.talker.repo.CommentRepository;
import ru.shramko.talker.repo.PostRepository;
import ru.shramko.talker.service.CommentService;

@Slf4j
@Controller
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentService commentService;
	
	@ModelAttribute("posts")
	public Iterable<Post> allPosts() {
		return postRepository.findAll();
	}
	
	@ModelAttribute("post")
	public Post post() {
		return new Post();
	}
	
	@GetMapping("/posts")
	public String getPostsPage() {
		return "posts";
	}
	
	@GetMapping("/post")
	public String getCreatingPostPage() {
		return "create-post";
	}
	
	@PostMapping("/post")
	public String createPost(@Valid Post post,
			Errors errors) {
		
		if (errors.hasErrors()) {
			return "create-post";
		}
		
		postRepository.save(post);
		
		return "redirect:/posts";
	}
	
	@GetMapping("/post/{id}")
	public String getExistsPost(@PathVariable Long id,
			Model model) {
		
		Post opensPost = postRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Post with id " + id.toString() + " does not exist"));
		
		
		model.addAttribute("post", opensPost);
		return "post";
		
	}
	
	@GetMapping("/post/{id}/comment")
	public String getCommentPage(@PathVariable Long id, 
			Model model) {
		
		Optional<Post> foundedPost = postRepository.findById(id);
		
		if (foundedPost.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Post with id " + id.toString() + " does not exist");
		}
		
		CommentDto commentDto = new CommentDto();
		model.addAttribute("postId", id);
		model.addAttribute("comment", commentDto);
		
		return "create-comment";
		
	}
	
	@PostMapping("/post/{id}/comment")
	public String handleCommentCreating(@PathVariable("id") Long postId,
			@Valid CommentDto commentDto,
			Errors errors) {
		
		log.debug("launch handleCommentCreating");
		
		if (errors.hasErrors()) {
			log.debug("comment has errors");
			log.debug("errors count: " + 
					Integer.valueOf(errors
							.getErrorCount())
							.toString());
			
			return "create-comment";
		}
		
		commentService.addCommentToPost(commentDto, postId);
		
		return "redirect:/post/" + postId.toString();
		
	}
	
}
