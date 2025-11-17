package ru.shramko.talker.dto.utils;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ru.shramko.talker.data.Comment;
import ru.shramko.talker.dto.CommentDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
	
	CommentDto toCommentDto(Comment comment);
	List<CommentDto> toCommentDtoList(List<Comment> comments);
	
	Comment toComment(CommentDto commentDto);
	List<Comment> toCommentList(List<CommentDto> commentDtoList);
	
}
