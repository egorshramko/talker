package ru.shramko.talker.data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public final class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_pkey_generator")
	@SequenceGenerator(name = "comment_id_pkey_generator", sequenceName = "comment_pkey_seq",
			initialValue = 1, allocationSize = 1)
	private Long id;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@NotNull
	private String authorNickname;
	
	@NotNull
	private String message;
	
	@NotNull
	@ManyToOne
	private Post post;
	
}
