package ru.shramko.talker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CommentDto {
	
	@Size(min = 1, message = "Оставь комментарий")
	private String message;
	
}
