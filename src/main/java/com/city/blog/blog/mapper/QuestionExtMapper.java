package com.city.blog.blog.mapper;

import com.city.blog.blog.model.Question;

public interface QuestionExtMapper {
    int incView (Question record);
    int incCommentCount(Question question);
}