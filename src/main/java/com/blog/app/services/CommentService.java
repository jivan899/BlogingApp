package com.blog.app.services;

import com.blog.app.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Long postId);
	
	void deleteComment(Long commentId);

}
