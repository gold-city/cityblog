package com.city.blog.blog.controller;

import com.city.blog.blog.model.User;
import com.city.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/goLogin")
    public String goLogin(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletRequest request){
        userService.login(name, password);
        return "redirect:/";
    }
}
