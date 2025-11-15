package ru.shramko.talker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ru.shramko.talker.data.Post;
import ru.shramko.talker.repo.PostRepository;

@Slf4j
@Controller
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
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
	
}
