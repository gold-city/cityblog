package com.city.blog.blog.controller;

import com.city.blog.blog.cache.TagCache;
import com.city.blog.blog.dto.QuestionDTO;
import com.city.blog.blog.model.Question;
import com.city.blog.blog.model.User;
import com.city.blog.blog.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/8
 * Time: 23:04
 * Description: No Description
 */
@Controller
public class PublishController {
    private Integer questionId;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request, Map<String, Object> map) {
        map.put("Tags",TagCache.getTag());
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "publish";
        }
        map.put("error", "用户未登录！");
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(/*@requestboby是用来接收json数据封装到对象的，如果前端传来的不是json数据，不需要加*/Question question, @RequestParam(value = "id",required = false) Integer questionId, Map<String, Object> map, HttpServletRequest request) {//测试使用实体类接收参数
        map.put("Tags",TagCache.getTag());
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            map.put("error", "用户未登录！");
            return "publish";
        } else {

            //发布失败，返回发布
            map.put("tag", question.getTag());
            map.put("description", question.getDescription());
            map.put("title", question.getTitle());
            //前端校验容易绕过，后端同时进行校验安全
            if (question.getTitle() == "" || question.getTitle() == null) {
                map.put("error", "标题不能为空！");
                return "publish";
            }
            if (question.getTag() == "" || question.getTag() == null) {
                map.put("error", "标签不能为空！");
                return "publish";
            }
            if (question.getDescription() == "" || question.getDescription() == null) {
                map.put("error", "问题描述不能为空！");
                return "publish";
            }

            //校验tag是否合法
            String valid = TagCache.isValid(question.getTag());
            if (StringUtils.isNotBlank(valid)){
                map.put("error","输入的标签"+valid+"不存在！");
                return "publish";
            }


            //补全自动注入的属性值，然后插入
            question.setCreator(user.getId());
            //如果用户通过前端修改questionid值，则能会修改到别人的问题，这逻辑有问题
            question.setId(questionId);
            questionService.insertOrUpdate(question,this.questionId);
            //发布成功，跳回首页显示内容
            return "redirect:/";
        }
    }
    @GetMapping("/publish/{questionId}")
    public String questionEdit(@PathVariable("questionId") Integer questionId,Map<String,Object> map){
        map.put("Tags",TagCache.getTag());
        this.questionId=questionId;
        QuestionDTO question = questionService.queryQuestionByQuestionId(questionId);
        map.put("tag", question.getTag());
        map.put("description", question.getDescription());
        map.put("title", question.getTitle());
        map.put("edit","提交修改");
        map.put("questionId",questionId);
        return "publish";
    }
}