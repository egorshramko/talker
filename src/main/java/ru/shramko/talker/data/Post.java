package ru.shramko.talker.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.shramko.talker.security.data.User;

@Data
@Entity
public final class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_pkey_id_generator")
	@SequenceGenerator(name = "post_pkey_id_generator", sequenceName = "post_pkey_sequence",
			initialValue = 1, allocationSize = 1)
	private Long id;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User author;
	
	@NotNull
	@Size(min = 3, max = 100, message = "Заголовок обязателен. Минимум 3 символа")
	private String title;
	
	private String message;
	
	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();
	
}
