package com.city.blog.blog.controller;

import com.city.blog.blog.mapper.BlogUserMapper;
import com.city.blog.blog.model.BlogUser;
import com.city.blog.blog.model.BlogUserExample;
import com.city.blog.blog.model.User;
import com.city.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/5/14
 * Time: 17:50
 * Description: No Description
 */
@Controller
public class loginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogUserMapper blogUserMapper;

    @GetMapping("/goLogin")
    public String goLogin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse response, Map<String, String> map, @RequestParam("name") String name, @RequestParam("password") String password, HttpServletRequest request) {
        if (name != null && name.length() != 0 && password != null && password.length() != 0) {
            Integer login = userService.login(name, password);
            if (login == 1) {
                map.put("message", "用户不存在！");
                return "login";
            } else if (login == 3) {
                map.put("message", "密码错误！");
                return "login";
            } else if (login == 2) {
                map.put("message", "登录失败，请稍后再试！");
                return "login";
            }
            BlogUserExample blogUserExample = new BlogUserExample();
            blogUserExample.createCriteria().andNameEqualTo(name);
            List<BlogUser> blogUsers = blogUserMapper.selectByExample(blogUserExample);
            for (BlogUser blogUser : blogUsers) {
                response.addCookie(new Cookie("token",blogUser.getToken()));
            }
            return "redirect:/";
        } else {
            map.put("message", "请输入用户名密码！");
            return "login";
        }
    }
}
