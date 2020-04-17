package com.city.blog.blog.mapper;

import com.city.blog.blog.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView (Question record);
    int incCommentCount(Question question);
    List<Question> selectQuestionByTag(Question question);
}