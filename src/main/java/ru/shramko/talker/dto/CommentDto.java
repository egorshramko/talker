package ru.shramko.talker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CommentDto {
	
	@NotBlank(message = "Оставь комментарий")
	private String message;
	
}
