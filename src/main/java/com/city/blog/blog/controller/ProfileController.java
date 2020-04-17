package com.city.blog.blog.controller;

import com.city.blog.blog.dto.PaginationDTO;
import com.city.blog.blog.model.User;
import com.city.blog.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/19
 * Time: 18:50
 * Description: No Description
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")///profile/questions----/profile/值-用来验证请求
    public String profile(@PathVariable(name = "action") String acttion, Map<String, Object> map,
                          HttpServletRequest request, @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        /*Cookie[] tokens = request.getCookies();
        if (tokens != null && tokens.length != 0) {
            for (Cookie token : tokens) {
                if ("token".equals(token.getName())) {
                    String userToken = token.getValue();
                    //通过token查数据库是否存在,这样就不用没登录的时候每次都去访问github登录了，这样很慢，判断数据库库是或否存在用户不存在再登录
                    User user = userMapper.queryUserByToken(userToken);
                    if (user != null) {
                    request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }*/
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if ("questions".equals(acttion)) {
                map.put("section", "questions");
                map.put("sectionName", "我的问题");
            } else if ("replies".equals(acttion)) {
                map.put("section", "replies");
                map.put("sectionName", "最新回复");
            }
            PaginationDTO paginationDTO = questionService.questionByUserIdList(user.getId(), page, size);
            map.put("paginationDTO", paginationDTO);
            return "profile";
        }
        return "redirect:/";
    }
}
