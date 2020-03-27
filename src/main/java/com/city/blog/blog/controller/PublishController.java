package com.city.blog.blog.controller;

import com.city.blog.blog.dto.QuestionDTO;
import com.city.blog.blog.model.Question;
import com.city.blog.blog.model.User;
import com.city.blog.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request, Map<String, String> map) {
        //获取cookie种的token查询当前用户的id
        /*Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    String creatorId = cookie.getValue();
                    user = userMapper.queryUserByToken(creatorId);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }else{
                        map.put("error","用户未登录！");
                        return "publish";
                    }
                    break;
                }
            }
        }*/
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "publish";
        }
        map.put("error", "用户未登录！");
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(Question question, @RequestParam(value = "id",required = false) Integer questionId, Map<String, String> map, HttpServletRequest request) {//测试使用实体类接收参数
        //获取cookie种的token查询当前用户的id
        /*Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    String creatorId = cookie.getValue();
                    user = userMapper.queryUserByToken(creatorId);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }else{
                        map.put("error","用户未登录！");
                        return "publish";
                    }
                    break;
                }
            }
        }*/

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

            //补全自动注入的属性值，然后插入
            question.setCreator(user.getId());
            question.setId(questionId);//如果用户通过前端修改questionid值，则能会修改到别人的问题，这逻辑有问题
            questionService.insertOrUpdate(question);
            //发布成功，跳回首页显示内容
            return "redirect:/";
        }
    }
    @GetMapping("/publish/{questionId}")
    public String questionEdit(@PathVariable("questionId") Integer questionId,Map<String,Object> map){
        QuestionDTO question = questionService.queryQuestionByQuestionId(questionId);
        map.put("tag", question.getTag());
        map.put("description", question.getDescription());
        map.put("title", question.getTitle());
        map.put("edit","提交修改");
        map.put("questionId",questionId);
        return "publish";
    }
}
