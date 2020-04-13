package com.city.blog.blog.controller;

import com.city.blog.blog.dto.CommentListDTO;
import com.city.blog.blog.dto.QuestionDTO;
import com.city.blog.blog.enums.CommentTypeEnum;
import com.city.blog.blog.service.CommentService;
import com.city.blog.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/23
 * Time: 23:57
 * Description: No Description
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{questionId}")
    public String questionn(@PathVariable("questionId") Integer questionId, Map<String, Object> map){
        //查id为questionId的question,返回到显示question的页面
        QuestionDTO questionDTO = questionService.queryQuestionByQuestionId(questionId);
        List<CommentListDTO> questionCommentS =commentService.queryCommentByQuestionId(questionId,CommentTypeEnum.QUESTION);
        map.put("questionCommentS",questionCommentS);
        if (questionDTO!=null){
            questionService.incView(questionId);
            map.put("questionDTO",questionDTO);
            return "question";
        }
        return "redirect:/";
    }
}
