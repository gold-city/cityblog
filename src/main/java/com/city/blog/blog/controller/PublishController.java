package com.city.blog.blog.controller;

import com.city.blog.blog.mapper.QuestionMapper;
import com.city.blog.blog.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/8
 * Time: 23:04
 * Description: No Description
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String toPublish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String publish(Question question){//测试使用实体类接收参数
        //补全自动注入的属性值，然后插入

        questionMapper.create(question);
        return "publish";
    }
}
