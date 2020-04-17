package com.city.blog.blog.mapper;

import com.city.blog.blog.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}