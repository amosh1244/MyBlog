package com.MyBlog.MyBlog.Service;

import com.MyBlog.MyBlog.PayLoad.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(long postId, CommentDto commentDto);

    public void deleteCommentById(Long postId, long commentId);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto updateComment(long commentId, CommentDto commentDto);

}
