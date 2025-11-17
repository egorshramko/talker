package ru.shramko.talker.service;

import ru.shramko.talker.data.Comment;
import ru.shramko.talker.dto.CommentDto;

public interface CommentService {
	Comment addCommentToPost(CommentDto commentDto, Long postId);
}
