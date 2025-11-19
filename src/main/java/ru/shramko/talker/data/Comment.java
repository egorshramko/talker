package ru.shramko.talker.data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.shramko.talker.security.data.User;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_pkey_generator")
	@SequenceGenerator(name = "comment_id_pkey_generator", sequenceName = "comment_pkey_seq",
			initialValue = 1, allocationSize = 1)
	private Long id;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private User author;
	
	@NotNull
	@Size(min = 1, message = "Оставьте комментарий")
	private String message;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
}
